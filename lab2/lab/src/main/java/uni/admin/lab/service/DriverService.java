package uni.admin.lab.service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import uni.admin.lab.dto.DriverDTO;
import uni.admin.lab.entity.Driver;
import uni.admin.lab.entity.Crewmate;
import uni.admin.lab.mapper.DriverMapper;
import uni.admin.lab.repository.DriverRepo;
import uni.admin.lab.repository.CrewRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepo repository;
    private final DriverMapper mapper = DriverMapper.INSTANCE;
    public void update(DriverDTO driverDTO){
        Optional<Driver> driverRecord = repository.findById(driverDTO.getId());
        if(driverRecord.isEmpty()){
            return;
        }
        Driver driver = driverRecord.get();
        driver.setName(driverDTO.getName());
        repository.save(driver);
    }

    public void delete(String Id){
        if(repository.existsById(Id)){
            repository.deleteById(Id);
            repository.cascadeUpdate(Id);
            repository.cascadeDelete(Id);
        }
    }

    public Optional<DriverDTO> get(String Id){
        Optional<Driver> driver = repository.findById(Id);
        return driver.map(mapper::toDTO);
    }

    public List<DriverDTO> getAll(){
        List<Driver> drivers = repository.findAll();
        List<DriverDTO> DTOs = new ArrayList<>();
        for (Driver driver : drivers){
            DTOs.add(mapper.toDTO(driver));
        }
        return DTOs;
    }

    public List<DriverDTO> getByName(String name){
        Optional<List<Driver>> drivers = repository.findByName(name);
        if(drivers.isEmpty()){
            return new ArrayList<>();
        }
        List<DriverDTO> DTOs = new ArrayList<>();
        for (Driver driver : drivers.get()){
            DTOs.add(mapper.toDTO(driver));
        }
        return DTOs;
    }

    public void create(DriverDTO dto){
        dto.setId(UUID.randomUUID().toString());
        Driver driver = mapper.fromDTO(dto);
        repository.save(driver);
    }
}
