package ma.s2m.nxp.productservice.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;


@Getter @Setter
@NoArgsConstructor @ToString @AllArgsConstructor
public class ProductCodesDto {
    List<String> codes;
}
