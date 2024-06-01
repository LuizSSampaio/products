package net.lsamp.products.controllers;

import jakarta.validation.Valid;
import net.lsamp.products.dtos.ProductRecordDto;
import net.lsamp.products.models.ProductModel;
import net.lsamp.products.repositories.ProductRepository;
import net.lsamp.products.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        ProductModel productModel = productService.saveProduct(productRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productModel);
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getOneProduct(@PathVariable(value = "id") UUID id) throws ResponseStatusException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getOneProduct(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(
            @PathVariable(value = "id") UUID id,
            @RequestBody @Valid ProductRecordDto productRecordDto
    ) throws ResponseStatusException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, productRecordDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") UUID id) throws ResponseStatusException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(id));
    }
}
