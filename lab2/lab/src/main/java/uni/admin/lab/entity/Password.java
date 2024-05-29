package uni.admin.lab.entity;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Password {
    private String hash;
    private String salt;
}
