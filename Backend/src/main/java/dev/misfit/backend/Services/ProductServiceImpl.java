package dev.misfit.backend.Services;

import dev.misfit.backend.entity.ProductEntity;
import dev.misfit.backend.io.ProductRequest;
import dev.misfit.backend.io.ProductResponse;
import dev.misfit.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;
    @Value("${image.upload.dir}")
    private String uploadDir;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductResponse saveProduct(ProductRequest request, MultipartFile file) throws IOException {
        try {
            String rootPath = System.getProperty("user.dir");
            String uploadFile = rootPath + File.separator + "uploads" + File.separator + file.getOriginalFilename();
            file.transferTo(new File(uploadFile));

            ProductEntity saved = repository.save(ProductEntity.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .points(request.getPoints())
                    .imageUrl(file.getOriginalFilename())
                    .build());

            return ProductResponse.builder()
                    .id(saved.getId())
                    .name(saved.getName())
                    .description(saved.getDescription())
                    .points(saved.getPoints())
                    .imageUrl(saved.getImageUrl())
                    .imageType(file.getContentType())
                    .build();
        }catch (Exception e){
            e.printStackTrace();
            throw new IOException("Failed to save image and product.");
        }
    }

    @Override
    public List<ProductResponse> getProducts(){
        return repository.findAll().stream().map(product -> {
            String filePath = product.getImageUrl();
            String rootPath = System.getProperty("user.dir") + File.separator + "uploads" + File.separator + filePath;
            String contentType = "";
            String base64Image = "";
            byte[] imageBytes;
            try {
                imageBytes = Files.readAllBytes(Paths.get(rootPath));
                contentType = Files.probeContentType(Path.of(rootPath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            base64Image = Base64.getEncoder().encodeToString(imageBytes);
            return ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .points(product.getPoints())
                    .imageUrl(product.getImageUrl())
                    .imageType(contentType)
                    .imageBase64(base64Image)
                    .build();
        }).collect(Collectors.toList());

    }

    @Override
    public ProductResponse getImage(String id) throws IOException {
        ProductEntity product = repository.findById(id).orElseThrow(()->new FileNotFoundException("Product not found."));
        String filePath = product.getImageUrl();
        String rootPath = System.getProperty("user.dir")+ File.separator + "uploads" + File.separator + filePath;
        String contentType = "";
        String base64Image = "";
        byte[] imageBytes = Files.readAllBytes(Paths.get(rootPath));
        contentType = Files.probeContentType(Path.of(rootPath));
        base64Image = Base64.getEncoder().encodeToString(imageBytes);
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .imageUrl(product.getImageUrl())
                .points(product.getPoints())
                .description(product.getDescription())
                .imageBase64(base64Image)
                .imageType(contentType)
                .build();
    }
}
