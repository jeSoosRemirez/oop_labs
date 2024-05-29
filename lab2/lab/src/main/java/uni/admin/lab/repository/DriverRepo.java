package uni.admin.lab.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uni.admin.lab.entity.Brigade;
import java.util.List;
import java.util.Optional;


@Repository
public interface DriverRepo extends JpaRepository<Driver, String> {
    Optional<List<Driver>> findByName(String name);

    @Modifying
    @Query("UPDATE Driver c SET c.driverId = '' WHERE c.driverId = ?1")
    void cascadeUpdate(String Id);
    @Modifying
    @Query("DELETE Trip f WHERE f.driverId = ?1")
    void cascadeDelete(String Id);
}
