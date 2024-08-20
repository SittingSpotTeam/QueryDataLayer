package com.sittingspot.querydatalayer.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.sittingspot.querydatalayer.models.Area;
import com.sittingspot.querydatalayer.models.QueryResult;
import com.sittingspot.querydatalayer.models.Tag;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.sittingspot.querydatalayer.models.Query}
 */
@Getter
@Setter
@JsonAutoDetect
public class QueryInDTO implements Serializable {
    private Area area;
    private List<Tag> tags;
    private List<String> labels;
    private List<QueryResult> results;
}