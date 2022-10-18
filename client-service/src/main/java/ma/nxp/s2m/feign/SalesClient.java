package ma.nxp.s2m.feign;


import ma.nxp.s2m.dto.SaleDto;
import ma.nxp.s2m.dto.SupplierDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="sales-service")
public interface SalesClient {
    @GetMapping("/api/sale/client/{id}")
    List<SaleDto> getClientSales(@PathVariable("id") Long clientId);

    @GetMapping("/api/sale/client/{id}/suppliers")
    List<String> getSuppliers(@PathVariable("id") Long id);
}
