package ma.omnishore.sales.feign;

import ma.omnishore.sales.dto.ProductCodesDto;
import ma.omnishore.sales.dto.SupplierDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="product-service")
public interface ProductClient {
    @PostMapping("/api/v1/products/suppliers")
    List<String> getSuppliers(@RequestBody List<String> dto);

}
