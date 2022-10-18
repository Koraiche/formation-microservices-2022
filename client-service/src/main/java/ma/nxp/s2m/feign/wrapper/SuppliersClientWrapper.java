package ma.nxp.s2m.feign.wrapper;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import ma.nxp.s2m.dto.SupplierDto;
import ma.nxp.s2m.feign.SalesClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SuppliersClientWrapper {

    @Autowired
    private SalesClient salesClient;

    private static final Logger log = LoggerFactory.getLogger(SalesClientWrapper.class);

    @CircuitBreaker(name="suppCB", fallbackMethod="suppliersFallBack")
    public List<String> getClientSuppliers(Long id){
        return salesClient.getSuppliers(id);
    }
    public List<String> suppliersFallBack(Long id, Throwable throwable) throws AccessDeniedException {
        log.error("error: {}", id, throwable);
        FeignException salesException = (FeignException) throwable;
        if(salesException.status()== HttpStatus.UNAUTHORIZED.value()) {
            throw new AccessDeniedException("Vous n'avez pas l'acces a cette ressource");
        }
        return new ArrayList<>();
    }
}
