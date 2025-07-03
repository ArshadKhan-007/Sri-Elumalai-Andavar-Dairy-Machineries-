package dev.misfit.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {

    @Id
    private String id;
    private String name;
    private String description;
    private List<String> points;
    private String imageUrl;
}
