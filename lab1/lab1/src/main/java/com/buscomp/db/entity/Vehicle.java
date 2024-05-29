package com.buscomp.db.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle extends Entity{

    private String model;
    private boolean status;

    public Vehicle(UUID id) {
        super(id);
    }
}
