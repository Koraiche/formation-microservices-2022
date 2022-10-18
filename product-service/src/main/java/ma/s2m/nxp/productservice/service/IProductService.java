package ma.s2m.nxp.productservice.service;

import ma.s2m.nxp.productservice.dto.ProductCodesDto;

import java.util.List;

public interface IProductService {
    List<String> getSuppliersByProductsCodes(List<String> products);
}
