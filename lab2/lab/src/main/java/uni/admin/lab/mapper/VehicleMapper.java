package uni.admin.lab.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uni.admin.lab.dto.TripDTO;
import uni.admin.lab.dto.VehicleDTO;
import uni.admin.lab.entity.Trip;
import uni.admin.lab.entity.Vehicle;


@Mapper
public interface VehicleMapper {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    VehicleDTO toDTO(Vehicle obj);
    Vehicle fromDTO(VehicleDTO dto);
}
