package com.sittingspot.querydatalayer.models;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record QueryResult(String spotId, Location location) {
}
