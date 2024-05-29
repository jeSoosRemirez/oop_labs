package uni.admin.lab.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uni.admin.lab.dto.DriverDTO;
import uni.admin.lab.dto.TripDTO;
import uni.admin.lab.entity.Driver;
import uni.admin.lab.entity.Trip;


@Mapper
public interface TripMapper {
    TripMapper INSTANCE = Mappers.getMapper(TripMapper.class);

    TripDTO toDTO(Trip obj);
    Trip fromDTO(TripDTO dto);
}
