package com.nimai.inventory_management_system.controller;

import com.nimai.inventory_management_system.product.Product;
import com.nimai.inventory_management_system.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(
        name = "Product Management",
        description = "APIs for creating, reading, updating and deleting products"
)
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create Product
    @PostMapping
    @Operation(
            summary = "Create a new product",
            description = "Creates a new product in the inventory"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Product created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid product data"
            )
    })
    public ResponseEntity<Product> createProduct(
            @Valid @RequestBody Product product) {

        Product createdProduct = productService.createProduct(product);

        return new ResponseEntity<>(
                createdProduct,
                HttpStatus.CREATED
        );
    }

    // Get All Products
    @GetMapping
    @Operation(
            summary = "Get all products",
            description = "Returns all products available in the inventory"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Products retrieved successfully"
    )
    public ResponseEntity<List<Product>> getAllProducts() {

        return ResponseEntity.ok(
                productService.getAllProducts()
        );
    }

    // Get Product By ID
    @GetMapping("/{id}")
    @Operation(
            summary = "Get product by ID",
            description = "Returns a single product using its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Product found successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found"
            )
    })
    public ResponseEntity<Product> getProductById(

            @Parameter(
                    description = "ID of the product",
                    example = "1"
            )
            @PathVariable Long id) {

        return ResponseEntity.ok(
                productService.getProductById(id)
        );
    }

    // Update Product
    @PutMapping("/{id}")
    @Operation(
            summary = "Update product",
            description = "Updates an existing product using its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Product updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid product data"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found"
            )
    })
    public ResponseEntity<Product> updateProduct(

            @Parameter(
                    description = "ID of the product to update",
                    example = "1"
            )
            @PathVariable Long id,

            @Valid @RequestBody Product product) {

        return ResponseEntity.ok(
                productService.updateProduct(id, product)
        );
    }

    // Delete Product
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete product",
            description = "Deletes a product using its ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Product deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found"
            )
    })
    public ResponseEntity<Void> deleteProduct(

            @Parameter(
                    description = "ID of the product to delete",
                    example = "1"
            )
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}