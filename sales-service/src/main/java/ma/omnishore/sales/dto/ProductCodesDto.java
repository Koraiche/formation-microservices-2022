package ma.omnishore.sales.dto;

import lombok.*;

import java.util.Collection;


@Data
@Builder
public class ProductCodesDto {
    Collection<String> codes;
}
