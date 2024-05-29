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
public class Driver extends Entity{

    private String name;
    private int assigned_vehicle_id;

    public Driver(UUID id) {
        super(id);
    }
}
