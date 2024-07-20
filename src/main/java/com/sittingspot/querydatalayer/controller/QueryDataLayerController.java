package com.sittingspot.querydatalayer.controller;

import com.sittingspot.querydatalayer.DTO.QueryInDTO;
import com.sittingspot.querydatalayer.DTO.QueryOutDTO;
import com.sittingspot.querydatalayer.models.Area;
import com.sittingspot.querydatalayer.models.Query;
import com.sittingspot.querydatalayer.models.QueryResult;
import com.sittingspot.querydatalayer.models.Tag;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/query-dl/api/v1")
public class QueryDataLayerController {

    @GetMapping("/")
    public List<QueryOutDTO> getQueries(@RequestParam("queryId") UUID queryId,
                                        @RequestParam("location") Area location,
                                        @RequestParam("tags") List<Tag> tags,
                                        @RequestParam("labels") List<String> labels){

        return List.of();
    }

    @PostMapping("/")
    public UUID createQuery(@RequestBody QueryInDTO query) {

        return UUID.randomUUID();
    }

    @PutMapping("/{Id}")
    public void updateQuery(@PathVariable UUID Id, @RequestBody List<QueryResult> query) {}
}
