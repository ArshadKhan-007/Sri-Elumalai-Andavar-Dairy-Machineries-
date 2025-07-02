package dev.misfit.backend.Services;

import dev.misfit.backend.entity.UserEntity;
import dev.misfit.backend.io.UserRequest;
import dev.misfit.backend.io.UserResponse;
import dev.misfit.backend.repository.UserRepository;
import dev.misfit.backend.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository repository, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String login(UserRequest user) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getPassword()));
        if (auth.isAuthenticated()){
            return jwtUtil.generateToken(user.getUserEmail());
        }
        throw new UsernameNotFoundException("Invalid user request");
    }

    @Override
    public UserResponse register(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repository.save(user);
        return convertToUserResponse(user);
    }

    private UserResponse convertToUserResponse(UserEntity newUser) {
        return UserResponse.builder()
                .id(newUser.getId())
                .userName(newUser.getUserName())
                .userEmail(newUser.getUserEmail())
                .build();
    }
}
