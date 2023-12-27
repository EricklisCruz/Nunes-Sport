package com.nunes.sport.Nunes.Sport.repositories;

import com.nunes.sport.Nunes.Sport.domain.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductByCode(Integer code);
}
