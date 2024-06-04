package net.lsamp.products.services;

import net.lsamp.products.dtos.ProductRecordDto;
import net.lsamp.products.models.ProductModel;
import net.lsamp.products.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveProduct() {
        UUID id = createProduct("save", BigDecimal.valueOf(10), 34).getId();

        ProductModel fetchedProductModel = productRepository.findById(id).orElse(null);

        assertNotNull(fetchedProductModel);

        assertEquals("save", fetchedProductModel.getName());
        assertEquals(
                BigDecimal.valueOf(10),
                fetchedProductModel.getPrice()
        );
        assertEquals(34, fetchedProductModel.getStock());
    }

    @Test
    void getAllProducts() {
        createProduct("getAll1", BigDecimal.valueOf(10), 10);
        createProduct("getAll2", BigDecimal.valueOf(20), 20);

        List<ProductModel> allProducts = productService.getAllProducts();
        ProductModel firstProduct = allProducts.get(0);
        ProductModel lastProduct = allProducts.get(1);

        assertNotNull(firstProduct);
        assertNotNull(lastProduct);

        assertEquals("getAll1", firstProduct.getName());
        assertEquals(
                BigDecimal.valueOf(10),
                firstProduct.getPrice()
        );
        assertEquals(10, firstProduct.getStock());

        assertEquals("getAll2", lastProduct.getName());
        assertEquals(
                BigDecimal.valueOf(20),
                lastProduct.getPrice()
        );
        assertEquals(20, lastProduct.getStock());
    }

    @Test
    void getOneProduct() {
        UUID id = createProduct("getOne", BigDecimal.valueOf(35), 78).getId();

        ProductModel fetchedProduct = productService.getOneProduct(id);

        assertNotNull(fetchedProduct);

        assertEquals("getOne", fetchedProduct.getName());
        assertEquals(
                BigDecimal.valueOf(35),
                fetchedProduct.getPrice()
        );
        assertEquals(78, fetchedProduct.getStock());
    }

    @Test
    void getOneProductNotFoundCase() {
        assertThrows(ResponseStatusException.class, () -> productService.getOneProduct(UUID.randomUUID()));
    }

    @Test
    void updateProduct() {
        UUID id = createProduct("update", BigDecimal.valueOf(41), 28).getId();

        ProductRecordDto productRecordDto = new ProductRecordDto("updated", BigDecimal.valueOf(23), 45);
        ProductModel updatedProduct = productService.updateProduct(id, productRecordDto);

        assertNotNull(updatedProduct);

        assertEquals("updated", updatedProduct.getName());
        assertEquals(
                BigDecimal.valueOf(23),
                updatedProduct.getPrice()
        );
        assertEquals(45, updatedProduct.getStock());
    }

    @Test
    void updateProductNotFoundCase() {
        ProductRecordDto productRecordDto = new ProductRecordDto("updated", BigDecimal.valueOf(23), 45);

        assertThrows(ResponseStatusException.class, () -> productService.updateProduct(
                UUID.randomUUID(), productRecordDto
        ));
    }

    @Test
    void deleteProduct() {
        UUID id = createProduct("toBeDeleted", BigDecimal.valueOf(23), 80).getId();

        String responseMessage = productService.deleteProduct(id);

        assertEquals("Product deleted successfully", responseMessage);

        assertThrows(ResponseStatusException.class, () -> productService.getOneProduct(id));
    }

    @Test
    void deleteProductNotFoundCase() {
        assertThrows(ResponseStatusException.class, () -> productService.deleteProduct(UUID.randomUUID()));
    }

    private ProductModel createProduct(String name, BigDecimal price, int stock) {
        ProductRecordDto productRecordDto = new ProductRecordDto(name, price, stock);
        return productService.saveProduct(productRecordDto);
    }
}