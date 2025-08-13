package dev.skyherobrine.backend.services;

import dev.skyherobrine.backend.dtos.UserDto;
import dev.skyherobrine.backend.models.oracle.User;
import dev.skyherobrine.backend.projects.UserProject;
import dev.skyherobrine.backend.repositories.oracle.UserRepository;
import dev.skyherobrine.backend.repositories.oracle.UserRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AuthenticateService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    private String generateUserId() {
        while(true) {
            String result = "USR";
            result += LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            result += String.valueOf(ThreadLocalRandom.current().nextInt(10000, 99999));
            if(userRepository.findByUserId(result).isEmpty()) return result;
        }
    }

    public UserProject checkLogin(String email, String password) {
        User source = userRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new EntityNotFoundException("Don't have account with this email and password"));
        UserProject result = new UserProject();
        BeanUtils.copyProperties(source, result);
        return result;
    }

    public UserProject registerAccount(UserDto dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setUserId(generateUserId());
        user.setRole(userRoleRepository.findByRoleName("USER").orElseThrow(() -> new EntityNotFoundException("The role 'USER' wasn't found on database system!")));
        UserProject result = new UserProject();
        BeanUtils.copyProperties(userRepository.save(user), result, "id", "password");
        return result;
    }

    public UserProject updateAccount(String userId, UserDto dto) {
        User target = userRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException(""));
        BeanUtils.copyProperties(dto, target, "id", "password", "createdAt", "updatedAt");
        User userSaved = userRepository.save(target);
        UserProject result = new UserProject();
        BeanUtils.copyProperties(userSaved, result, "id", "password");
        return result;
    }
}
