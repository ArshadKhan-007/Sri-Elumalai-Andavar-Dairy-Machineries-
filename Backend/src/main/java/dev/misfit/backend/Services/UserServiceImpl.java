package dev.misfit.backend.Services;

import dev.misfit.backend.io.UserRequest;
import dev.misfit.backend.io.UserResponse;
import dev.misfit.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public String login(UserRequest user) {
        return "";
    }

    @Override
    public UserResponse register(UserRequest user) {
        return null;
    }
}
