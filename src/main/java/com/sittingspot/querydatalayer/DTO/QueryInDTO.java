package com.sittingspot.querydatalayer.DTO;

import com.sittingspot.querydatalayer.models.Area;
import com.sittingspot.querydatalayer.models.QueryResult;
import com.sittingspot.querydatalayer.models.Tag;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.sittingspot.querydatalayer.models.Query}
 */
public record QueryInDTO(Area area, List<Tag> tags, List<String> labels, List<QueryResult> results) implements Serializable {
}