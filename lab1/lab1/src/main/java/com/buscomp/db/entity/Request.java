package com.buscomp.db.entity;

import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request extends Entity{

    private String request_details;
    private String vehicle_requirements;

    public Request(UUID id) {
        super(id);
    }
}
