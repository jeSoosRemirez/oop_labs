package uni.admin.lab.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uni.admin.lab.dto.AuthDTO;
import uni.admin.lab.dto.LoginDTO;
import uni.admin.lab.service.AuthService;
import uni.admin.lab.service.JsonParser;
import java.util.Optional;


@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/")
public class AuthController {

    private final AuthService authService;
    @PostMapping
    private String auth(@RequestBody LoginDTO loginDto) throws Exception {
        Optional<AuthDTO> response = authService.auth(loginDto);
        if(response.isEmpty()){
            return "[]";
        }
        return JsonParser.toJsonObject(response.get());
    }
}
