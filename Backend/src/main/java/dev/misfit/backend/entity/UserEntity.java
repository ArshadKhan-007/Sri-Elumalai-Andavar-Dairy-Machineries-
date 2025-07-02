package dev.misfit.backend.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @Indexed(unique = true)
    @NonNull
    private String userEmail;
    private String password;
}
