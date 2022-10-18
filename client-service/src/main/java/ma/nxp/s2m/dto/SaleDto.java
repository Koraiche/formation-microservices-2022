package ma.nxp.s2m.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString
public class SaleDto  implements Serializable {
    private Long id;
    private String productCode;
    private Long clientId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date operationDate;
    private Long quantity;
    private Double amount;
}
