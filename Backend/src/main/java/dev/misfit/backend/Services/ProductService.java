package dev.misfit.backend.Services;

import dev.misfit.backend.io.ProductRequest;
import dev.misfit.backend.io.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductService {
    ProductResponse saveProduct(ProductRequest request, MultipartFile file) throws IOException;
    List<ProductResponse> getProducts();
    ProductResponse getImage(String id) throws IOException;
}
