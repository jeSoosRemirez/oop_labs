package uni.admin.lab.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="drivers")
public class Driver {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String model;
    @Column(name = "status")
    private boolean status;
}
