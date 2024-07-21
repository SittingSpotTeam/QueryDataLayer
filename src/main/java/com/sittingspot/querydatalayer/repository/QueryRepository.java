package com.sittingspot.querydatalayer.repository;

import com.sittingspot.querydatalayer.models.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QueryRepository extends JpaRepository<Query, UUID> {

}