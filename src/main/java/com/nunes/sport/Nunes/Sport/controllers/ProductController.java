package com.nunes.sport.Nunes.Sport.controllers;

import com.nunes.sport.Nunes.Sport.domain.products.Product;
import com.nunes.sport.Nunes.Sport.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findProductById() {
        List<Product> products = productService.findAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        Product product = productService.findProductById(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> insertProduct(@RequestBody Product product, UriComponentsBuilder uriComponentsBuilder) {
        product = productService.insertProduct(product);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product = productService.update(id, product);
        return ResponseEntity.ok().body(product);
    }
}
