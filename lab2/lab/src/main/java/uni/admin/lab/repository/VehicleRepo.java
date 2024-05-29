package uni.admin.lab.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uni.admin.lab.entity.Vehicle;
import java.util.List;
import java.util.Optional;


@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, String> {
    Optional<List<Vehicle>> findByModel(String model);
    @Modifying
    @Query("DELETE Trip f WHERE f.vehicleId = ?1")
    void cascadeDelete(String Id);
}
