package com.sittingspot.querydatalayer.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Area {
    private Location center;
    private double range;

    public Double x(){
        return center.getX();
    }

    public Double y(){
        return center.getY();
    }
}
