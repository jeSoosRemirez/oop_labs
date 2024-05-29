package uni.admin.lab.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="vehicles")
public class Vehicle {
    @jakarta.persistence.Id
    @Column(name = "id")
    private String Id;
    @Column(name = "model")
    private String model;
    @Column(name = "status")
    private boolean status;
}
