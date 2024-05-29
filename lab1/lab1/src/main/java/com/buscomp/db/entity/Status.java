package com.buscomp.db.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status extends Entity{

    private String model;
    private int vehicle_id;
    private int trip_id;
    private String status_details;

    public Status(UUID id) {
        super(id);
    }

}
