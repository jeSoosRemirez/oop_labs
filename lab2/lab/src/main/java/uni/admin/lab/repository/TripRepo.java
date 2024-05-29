package uni.admin.lab.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uni.admin.lab.entity.Trip;
import java.util.List;
import java.util.Optional;


@Repository
public interface TripRepo extends JpaRepository<Trip, String> {
    void deleteAllByDriverId(String driverId);
    void deleteAllByVehicleId(String vehicleId);
    void deleteAllByTripId(String tripId);
    @Query("SELECT f.driverId FROM Trip f")
    Optional<List<String>> findDriverIds();
    @Query("SELECT f.vehicleId FROM Trip f")
    Optional<List<String>> findVehicleIds();
    @Query("SELECT f.tripId FROM Trip f")
    Optional<List<String>> findTripIds();
}
