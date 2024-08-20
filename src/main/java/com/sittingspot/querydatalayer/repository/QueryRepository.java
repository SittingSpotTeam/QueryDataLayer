package com.sittingspot.querydatalayer.repository;

import com.sittingspot.querydatalayer.models.Area;
import com.sittingspot.querydatalayer.models.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface QueryRepository extends JpaRepository<Query, UUID> {

    @org.springframework.data.jpa.repository.Query(value = "SELECT q FROM Query q " +
            "where q.area.center.x between :x - :range and :x + :range " +
            "and q.area.center.y between :y - :range and :y + :range ")
    public List<Query> findByArea(@Param("x") Double x,@Param("y") Double y,@Param("range") Double range);
}