package ma.nxp.s2m.feign.wrapper;

import com.netflix.discovery.converters.Auto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import ma.nxp.s2m.dto.SaleDto;
import ma.nxp.s2m.feign.SalesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SalesClientWrapper {

    @Autowired
    private SalesClient salesClient;

    @CircuitBreaker(name="salesCB", fallbackMethod="salesFallBack")
    public List<SaleDto> getClientSales(Long id){
        return salesClient.getClientSales(id);
    }
    public List<SaleDto> salesFallBack(Long id, Throwable throwable){
        return new ArrayList<>();
    }
}
