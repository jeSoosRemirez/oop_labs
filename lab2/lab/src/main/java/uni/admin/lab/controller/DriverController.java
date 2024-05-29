package uni.admin.lab.controller;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uni.admin.lab.dto.DriverDTO;
import uni.admin.lab.entity.Driver;
import uni.admin.lab.service.DriverService;
import uni.admin.lab.service.JsonParser;
import uni.admin.lab.service.RoleUtil;
import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/driver")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class DriverController {

    private final DriverService service;

    @PutMapping
    private String doPut(@RequestHeader("access-token") String token, @RequestBody DriverDTO driverDTO) throws Exception {
        if(!RoleUtil.validateAccess(RoleUtil.getRole(token), RoleUtil.getAllowedRoles(new String[]{RoleUtil.ADMIN, RoleUtil.DISPATCH}))){
            return "[]";
        }
        service.update(driverDTO);
        return JsonParser.toJsonObject(service.getAll());
    }

    @GetMapping
    private String doGet(@RequestHeader("access-token") String token, @RequestParam("field") String field, @RequestParam("value") String value) throws Exception {
        String role = RoleUtil.getRole(token);
        List<DriverDTO> driverDTOList = new ArrayList<>();
        if(RoleUtil.validateAccess(role, RoleUtil.getAllowedRoles(new String[]{RoleUtil.USER, RoleUtil.DISPATCH, RoleUtil.ADMIN}))) {
            switch (field) {
                case "name":
                    driverDTOList = service.getByName(value);
                    break;
                case "id":
                    Optional<DriverDTO> driverDTO = service.get(value);
                    driverDTO.ifPresent(driverDTOList::add);
                    break;
                case "all":
                    driverDTOList = service.getAll();
                    break;
                default:
                    if (RoleUtil.validateAccess(role, RoleUtil.getAllowedRoles(new String[]{RoleUtil.DISPATCH, RoleUtil.ADMIN}))) {
                        switch (field) {
                            case "delete":
                                service.delete(value);
                                driverDTOList = service.getAll();
                                break;
                            default:
                                return "[]";
                        }
                    } else {
                        return "[]";
                    }
            }
        }
        return JsonParser.toJsonObject(driverDTOList);
    }

    @PostMapping
    private String doPost(@RequestHeader("access-token") String token, @RequestBody DriverDTO driverDTO) throws Exception {
        if(!RoleUtil.validateAccess(RoleUtil.getRole(token), RoleUtil.getAllowedRoles(new String[]{RoleUtil.ADMIN, RoleUtil.DISPATCH}))){
            return "[]";
        }
        service.create(driverDTO);
        return JsonParser.toJsonObject(service.getAll());
    }
}
