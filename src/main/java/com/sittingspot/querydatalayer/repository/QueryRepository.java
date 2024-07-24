package com.sittingspot.querydatalayer.repository;

import com.sittingspot.querydatalayer.models.Area;
import com.sittingspot.querydatalayer.models.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface QueryRepository extends JpaRepository<Query, UUID> {

    @org.springframework.data.jpa.repository.Query(value = "SELECT * from query q " +
            "where q.location.x between :area.center.x - :area.range and :area.center.x + :area.range " +
            "and q.location.y between :area.center.y - :area.range and :area.center.y + :area.range ", nativeQuery = true)
    public List<Query> findByArea(@Param("area") Area area);
}