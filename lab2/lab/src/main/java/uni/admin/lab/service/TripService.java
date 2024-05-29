package uni.admin.lab.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.admin.lab.dto.CrewDTO;
import uni.admin.lab.dto.TripDTO;
import uni.admin.lab.entity.Crewmate;
import uni.admin.lab.entity.Trip;
import uni.admin.lab.mapper.CrewMapper;
import uni.admin.lab.mapper.TripMapper;
import uni.admin.lab.repository.CrewRepo;
import uni.admin.lab.repository.TripRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepo repository;
    private final TripMapper mapper = TripMapper.INSTANCE;

    public void update(TripDTO dto){
        Optional<Trip> record = repository.findById(dto.getId());
        if(record.isEmpty()){
            return;
        }
        Trip obj = record.get();
        obj.setPlaneId(dto.getPlaneId());
        obj.setBrigadeId(dto.getBrigadeId());
        obj.setRaceId(dto.getRaceId());
        repository.save(obj);
    }

    public void delete(String Id){
        if(repository.existsById(Id)){
            repository.deleteById(Id);
        }
    }

    public Optional<TripDTO> get(String Id){
        Optional<Trip> obj = repository.findById(Id);
        return obj.map(mapper::toDTO);
    }

    public List<TripDTO> getAll(){
        List<Trip> objs = repository.findAll();
        List<TripDTO> DTOs = new ArrayList<>();
        for (Trip o : objs){
            DTOs.add(mapper.toDTO(o));
        }
        return DTOs;
    }

    public List<String> getIDs(String idCheck){
        switch (idCheck){
            case "race" -> {
                return repository.findRaceIds().get();
            }
            case "plane" -> {
                return repository.findPlaneIds().get();
            }
            case "brigade" -> {
                return repository.findBrigadeIds().get();
            }
            default -> {
                return new ArrayList<String>();
            }
        }
    }

    public void create(TripDTO dto){
        dto.setId(UUID.randomUUID().toString());
        Trip o = mapper.fromDTO(dto);
        repository.save(o);
    }
}
