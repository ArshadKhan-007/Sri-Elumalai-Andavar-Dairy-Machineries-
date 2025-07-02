package dev.misfit.backend.Services;

import dev.misfit.backend.entity.UserEntity;
import dev.misfit.backend.io.UserRequest;
import dev.misfit.backend.io.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String login(UserRequest user);
    UserResponse register(UserEntity user);
}
