package uni.admin.lab.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.admin.lab.dto.VehicleDTO;
import uni.admin.lab.entity.Vehicle;
import uni.admin.lab.mapper.VehicleMapper;
import uni.admin.lab.repository.VehicleRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepo repository;
    private final VehicleMapper mapper = VehicleMapper.INSTANCE;
    public void update(VehicleDTO dto){
        Optional<Vehicle> record = repository.findById(dto.getId());
        if(record.isEmpty()){
            return;
        }
        Vehicle obj = record.get();
        obj.setModel(dto.getModel());
        obj.setMaxLuggage(dto.getMaxLuggage());
        obj.setPassengerSeats(dto.getPassengerSeats());
        obj.setMaxFlightInMins(dto.getMaxFlightInMins());
        repository.save(obj);
    }

    public void delete(String Id){
        if(repository.existsById(Id)){
            repository.deleteById(Id);
            repository.cascadeDelete(Id);
        }
    }

    public Optional<VehicleDTO> get(String Id){
        Optional<Vehicle> obj = repository.findById(Id);
        return obj.map(mapper::toDTO);
    }

    public List<VehicleDTO> getAll(){
        List<Vehicle> objs = repository.findAll();
        List<VehicleDTO> DTOs = new ArrayList<>();
        for (Vehicle o : objs){
            DTOs.add(mapper.toDTO(o));
        }
        return DTOs;
    }

    public List<VehicleDTO> getByModel(String name){
        Optional<List<Vehicle>> objs = repository.findByModel(name);
        if(objs.isEmpty()){
            return new ArrayList<>();
        }
        List<VehicleDTO> DTOs = new ArrayList<>();
        for (Vehicle o : objs.get()){
            DTOs.add(mapper.toDTO(o));
        }
        return DTOs;
    }

    public void create(VehicleDTO dto){
        dto.setId(UUID.randomUUID().toString());
        Vehicle o = mapper.fromDTO(dto);
        repository.save(o);
    }
}
