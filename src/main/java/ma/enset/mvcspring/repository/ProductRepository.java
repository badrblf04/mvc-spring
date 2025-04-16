package ma.enset.mvcspring.repository;

import ma.enset.mvcspring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
