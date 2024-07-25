package com.sittingspot.querydatalayer.controller;

import com.sittingspot.querydatalayer.DTO.QueryInDTO;
import com.sittingspot.querydatalayer.DTO.QueryOutDTO;
import com.sittingspot.querydatalayer.models.Area;
import com.sittingspot.querydatalayer.models.Query;
import com.sittingspot.querydatalayer.models.QueryResult;
import com.sittingspot.querydatalayer.models.Tag;
import com.sittingspot.querydatalayer.repository.QueryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController("/api/v1")
public class QueryDataLayerController {

    private QueryRepository queryRepository;

    @GetMapping("/")
    public List<QueryOutDTO> getQueries(@RequestParam("location") Area location,
                                        @RequestParam("tags") List<Tag> tags,
                                        @RequestParam("labels") List<String> labels){
        return queryRepository.findByArea(location).stream().filter(x ->
                (new HashSet<>(x.getTags()).containsAll(tags) && new HashSet<>(x.getLabels()).containsAll(labels))
        ).map(Query::toOutDTO).toList();
    }

    @PostMapping("/")
    public UUID createQuery(@RequestBody QueryInDTO query) {
        var newQuery = new Query(query);
        queryRepository.save(newQuery);
        return newQuery.getId();
    }

    @PutMapping("/{Id}")
    public void updateQuery(@PathVariable UUID Id, @RequestBody List<QueryResult> results) {
        var q = queryRepository.findById(Id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "query not found"));
        results.addAll(q.getResults());
        q.setResults((new HashSet<>(results)).stream().toList());
    }
}
