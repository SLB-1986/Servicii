import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Package {
    private String targetLocation;
    private int targetDistance;
    private int packageValue;
    private LocalDate deliveryDate;
}
