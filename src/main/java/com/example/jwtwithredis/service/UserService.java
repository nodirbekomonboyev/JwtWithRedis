package com.example.jwtwithredis.service;


import com.example.jwtwithredis.DTOS.AuthDto;
import com.example.jwtwithredis.DTOS.UserCreateDto;
import com.example.jwtwithredis.DTOS.response.JwtResponse;
import com.example.jwtwithredis.entity.UserEntity;
import com.example.jwtwithredis.exception.DataAlreadyExistsException;
import com.example.jwtwithredis.exception.DataNotFoundException;
import com.example.jwtwithredis.repository.UserRepository;
import com.example.jwtwithredis.service.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService  {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String add(UserCreateDto dto) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(dto.getUsername());
        if(userEntity.isPresent()) {
            throw  new DataAlreadyExistsException("User already exists");
        }
        UserEntity map = modelMapper.map(dto, UserEntity.class);
        map.setPassword(passwordEncoder.encode(map.getPassword()));
        userRepository.save(map);
        return "Successfully signed up";
    }


    public JwtResponse signIn(AuthDto dto) {
        UserEntity user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new DataNotFoundException("user not found"));
        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return new JwtResponse(jwtUtil.generateToken(user));
        }
        throw new AuthenticationCredentialsNotFoundException("password didn't match");
    }
}
