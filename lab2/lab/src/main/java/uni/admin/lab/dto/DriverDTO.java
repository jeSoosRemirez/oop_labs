package uni.admin.lab.dto;
import lombok.Data;
import uni.admin.lab.entity.Driver;


@Data
public class CrewDTO {
    private String name;
    private int assigned_vehicle_id;
}
