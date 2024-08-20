package com.sittingspot.querydatalayer.controller;

import com.sittingspot.querydatalayer.DTO.QueryInDTO;
import com.sittingspot.querydatalayer.DTO.QueryOutDTO;
import com.sittingspot.querydatalayer.models.*;
import com.sittingspot.querydatalayer.repository.QueryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class QueryDataLayerController {

    private QueryRepository queryRepository;

    @GetMapping
    public List<QueryOutDTO> getQueries(@RequestParam("x") Double x,
                                        @RequestParam("y") Double y,
                                        @RequestParam("area") Double area,
                                        @RequestParam(value = "tags",required = false) List<Tag> tags,
                                        @RequestParam(value = "labels",required = false) List<String> labels){

        if(tags == null){ tags = new ArrayList<>(); }
        if(labels == null){ labels = new ArrayList<>(); }

        var location = new Area(new Location(x,y),area);
        //copies to use in lambda
        List<Tag> finalTags = tags;
        List<String> finalLabels = labels;

        // To be able to filter by area we need to use the function below, which cannot be called by jpa
        // so the best approach we have is to make jpa retrieve all the entries and filter them
        // this could obviously slow things down once there are a lot of queries.
        // The first filter checks if the area searched is contained in the area of another query
        // which is done by checking if the radius of the supposedly bigger area is greater than the distance between
        // the two centers plus the radius of the searched area, all values in meters.
        return queryRepository.findAll().stream()
                .filter(e -> e.getArea().getRange() > distFrom(e.getArea().y(), e.getArea().x(), y,x)*1000 + area )
                .filter(e ->
                (new HashSet<>(e.getTags()).containsAll(finalTags) && new HashSet<>(e.getLabels()).containsAll(finalLabels))
        ).map(Query::toOutDTO).toList();
    }

    @PostMapping
    public UUID createQuery(@RequestBody QueryInDTO query) {
        var newQuery = new Query(query);
        queryRepository.save(newQuery);
        return newQuery.getId();
    }

    /*@PutMapping("/{Id}")
    public void updateQuery(@PathVariable UUID Id, @RequestBody List<QueryResult> results) {
        var q = queryRepository.findById(Id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "query not found"));
        results.addAll(q.getResults());
        q.setResults((new HashSet<>(results)).stream().toList());
    }*/

    //return distance between two coordinates in km
    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371.0; // miles (or 6371.0 kilometers)
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;

        return dist;
    }
}
