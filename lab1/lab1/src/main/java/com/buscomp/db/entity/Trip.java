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
public class Trip extends Entity{

    private int request_id;
    private int driver_id;
    private int vehicle_id;
    private boolean trip_status;

    public Trip(UUID id) {
        super(id);
    }
}
