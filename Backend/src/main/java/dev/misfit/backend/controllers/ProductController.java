package dev.misfit.backend.controllers;


import dev.misfit.backend.Services.ProductService;
import dev.misfit.backend.io.ProductRequest;
import dev.misfit.backend.io.ProductResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductResponse saveProduct(@RequestPart("products") ProductRequest request,
                                       @RequestPart("files") MultipartFile file) throws IOException {
        return productService.saveProduct(request, file);
    }

    @GetMapping
    public List<ProductResponse> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getImage(@PathVariable String id) throws IOException {
        return productService.getImage(id);
    }
}
