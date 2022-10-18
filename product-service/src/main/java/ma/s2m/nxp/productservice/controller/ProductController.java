package ma.s2m.nxp.productservice.controller;


import ma.s2m.nxp.productservice.dto.ProductCodesDto;
import ma.s2m.nxp.productservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    IProductService productService;



    @PostMapping("/suppliers")
    public List<String> getSuppliers(@RequestBody List<String> dto){
        return productService.getSuppliersByProductsCodes(dto);
    }
}
