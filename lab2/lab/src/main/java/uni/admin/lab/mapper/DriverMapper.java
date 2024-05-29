package uni.admin.lab.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uni.admin.lab.dto.DriverDTO;
import uni.admin.lab.entity.Driver;


@Mapper
public interface DriverMapper {
    DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);

    DriverDTO toDTO(Driver driver);
    Driver fromDTO(DriverDTO dto);
}
