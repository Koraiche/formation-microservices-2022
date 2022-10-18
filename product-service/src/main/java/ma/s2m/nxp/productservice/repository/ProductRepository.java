package ma.s2m.nxp.productservice.repository;

import ma.s2m.nxp.productservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select distinct p.supplier from Product p where p.code in ?1")
    List<String> findAllByCodeIn(List<String> codes); //projection
}
