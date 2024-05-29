package uni.admin.lab.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="trips")
public class trip {
    @Id
    @Column(name = "id")
    private String Id;
    @Column(name = "request_id")
    private int request_id;
    @Column(name = "vehicle_id")
    private int vehicle_id;
    @Column(name = "trip_status")
    private boolean trip_status;
}
