package uni.admin.lab.dto;
import lombok.Data;


@Data
public class RaceDTO {
    private int request_id;
    private int driver_id;
    private int vehicle_id;
    private boolean trip_status;
}
