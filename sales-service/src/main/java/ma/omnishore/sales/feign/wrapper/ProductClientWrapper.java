package ma.omnishore.sales.feign.wrapper;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import ma.omnishore.sales.dto.ProductCodesDto;
import ma.omnishore.sales.dto.SupplierDto;
import ma.omnishore.sales.feign.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductClientWrapper {

    @Autowired
    private ProductClient productClient;

    @CircuitBreaker(name="suppliersCB", fallbackMethod="suppliersFallBack")
    public List<String> getSuppliers(List<String> dto){
        return productClient.getSuppliers(dto);
    }

    public List<String> suppliersFallBack(Throwable throwable){
        return new ArrayList<>();
    }
}
