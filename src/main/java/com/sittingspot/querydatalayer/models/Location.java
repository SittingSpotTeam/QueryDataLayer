package com.sittingspot.querydatalayer.models;

import jakarta.persistence.Embeddable;

@Embeddable
public record Location(double x, double y) {
}
