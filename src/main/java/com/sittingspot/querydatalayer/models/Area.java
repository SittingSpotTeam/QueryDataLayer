package com.sittingspot.querydatalayer.models;

import jakarta.persistence.Embeddable;

@Embeddable
public record Area(Location center, double range) {
}
