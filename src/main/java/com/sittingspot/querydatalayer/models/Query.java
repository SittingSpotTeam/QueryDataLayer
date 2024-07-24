package com.sittingspot.querydatalayer.models;

import com.sittingspot.querydatalayer.DTO.QueryInDTO;
import com.sittingspot.querydatalayer.DTO.QueryOutDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "query")
public class Query {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "area", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Area area;

    @ElementCollection
    @CollectionTable(name = "tags", joinColumns = @JoinColumn(name = "id"))
    private List<Tag> tags = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "labels", joinColumns = @JoinColumn(name = "id"))
    private List<String> labels = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "results", joinColumns = @JoinColumn(name = "id"))
    private List<QueryResult> results = new ArrayList<>();

    @Transient
    public QueryOutDTO toOutDTO(){
        return new QueryOutDTO(id, area, tags, labels, results);
    }

    public Query(QueryInDTO dto){
        this.area = dto.area();
        this.tags = dto.tags();
        this.labels = dto.labels();
        this.id = UUID.randomUUID();
    }
}