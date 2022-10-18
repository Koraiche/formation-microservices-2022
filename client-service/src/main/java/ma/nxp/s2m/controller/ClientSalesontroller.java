package ma.nxp.s2m.controller;


import ma.nxp.s2m.domain.Client;
import ma.nxp.s2m.dto.SaleDto;
import ma.nxp.s2m.dto.SupplierDto;
import ma.nxp.s2m.feign.SalesClient;
import ma.nxp.s2m.feign.wrapper.SalesClientWrapper;
import ma.nxp.s2m.feign.wrapper.SuppliersClientWrapper;
import ma.nxp.s2m.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientSalesontroller {

    private IClientService clientService;

    private SalesClient salesClient;

    private SalesClientWrapper salesClientWrapper;

    private SuppliersClientWrapper suppliersClientWrapper;

    @Autowired
    public ClientSalesontroller(IClientService clientService,
                                SalesClient salesClient,
                                SalesClientWrapper salesClientWrapper,
                                SuppliersClientWrapper suppliersClientWrapper) {
        this.clientService = clientService;
        this.salesClient = salesClient;
        this.salesClientWrapper = salesClientWrapper;
        this.suppliersClientWrapper = suppliersClientWrapper;
    }


    @GetMapping("/{id}/sales")
    public ResponseEntity<List<SaleDto>> getAll(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.salesClientWrapper.getClientSales(id),HttpStatus.OK);
    }

    @GetMapping("/{id}/suppliers")
    public ResponseEntity<List<String>> getSuppliers(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.suppliersClientWrapper.getClientSuppliers(id),HttpStatus.OK);
    }

}
