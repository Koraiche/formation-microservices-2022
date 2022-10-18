package ma.s2m.nxp.productservice.service.impl;

import ma.s2m.nxp.productservice.dto.ProductCodesDto;
import ma.s2m.nxp.productservice.repository.ProductRepository;
import ma.s2m.nxp.productservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService  implements IProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<String> getSuppliersByProductsCodes(List<String> products) {
        return productRepository.findAllByCodeIn(products);
    }
}
