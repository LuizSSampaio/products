package net.lsamp.products.services;

import net.lsamp.products.dtos.ProductRecordDto;
import net.lsamp.products.models.ProductModel;
import net.lsamp.products.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductModel saveProduct(ProductRecordDto productRecordDto) {
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return productRepository.save(productModel);
    }

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductModel getOneProduct(UUID id) throws ResponseStatusException {
        Optional<ProductModel> optionalProductModel = productRepository.findById(id);
        if (optionalProductModel.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        return optionalProductModel.get();
    }

    public ProductModel updateProduct(UUID id, ProductRecordDto productRecordDto) throws ResponseStatusException {
        Optional<ProductModel> optionalProductModel = productRepository.findById(id);
        if (optionalProductModel.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        ProductModel productModel = optionalProductModel.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return productRepository.save(productModel);
    }

    public String deleteProduct(UUID id) throws ResponseStatusException {
        Optional<ProductModel> optionalProductModel = productRepository.findById(id);
        if (optionalProductModel.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
        productRepository.delete(optionalProductModel.get());
        return "Product deleted successfully";
    }
}
