package com.nunes.sport.Nunes.Sport.services;

import com.nunes.sport.Nunes.Sport.domain.products.Product;
import com.nunes.sport.Nunes.Sport.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id) {
        return productRepository.getReferenceById(id);
    }

    public Product insertProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        boolean isProductRegistered = productRepository.existsById(id);
        if (isProductRegistered) {
            productRepository.getReferenceById(id);
        } else {
            throw new RuntimeException("Product is not registered");
        }
    }

    public Product update(Long id, Product product) {
        try {
            Product entity = productRepository.getReferenceById(id);
            updateData(entity, product);
            return productRepository.save(entity);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateData(Product entity, Product product) {
        entity.setName(product.getName());
        entity.setCode(product.getCode());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
    }
}
