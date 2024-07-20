package com.sittingspot.querydatalayer.models;

import jakarta.persistence.Embeddable;

@Embeddable
public record Tag(String key, String value) {
}
