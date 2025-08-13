package dev.skyherobrine.backend.services.admins;

import dev.skyherobrine.backend.dtos.UserDto;
import dev.skyherobrine.backend.models.oracle.Address;
import dev.skyherobrine.backend.models.oracle.User;
import dev.skyherobrine.backend.repositories.oracle.AddressRepository;
import dev.skyherobrine.backend.repositories.oracle.UserRepository;
import dev.skyherobrine.backend.repositories.oracle.UserRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public User addUser(UserDto dto) {
        User user = new User(
                generateUserId(),
                dto.firstName(),
                dto.lastName(),
                dto.sex(),
                dto.birthDate(),
                dto.phoneNumber(),
                dto.address().toObject(),
                dto.email(),
                dto.password(),
                userRoleRepository.findByRoleName("USER").orElseThrow(() -> new EntityNotFoundException("The role name 'USER' wasn't found!"))
        );
        return userRepository.save(user);
    }

    private String generateUserId() {
        while(true) {
            String result = "USR";
            result += LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            result += String.valueOf(ThreadLocalRandom.current().nextInt(10000, 99999));
            if(userRepository.findByUserId(result).isEmpty()) return result;
        }
    }

    public User updateUser(String userId, @Valid UserDto dto) {
        Address address =
                addressRepository.findByHouseNumberAndStreetAndWardAndCityAndCountry(
                        dto.address().houseNumber(),
                                dto.address().street(),
                                dto.address().ward(),
                                dto.address().city(),
                                dto.address().country())
                        .orElse(dto.address().toObject());
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException("The userId '" + userId + "' wasn't found!"));
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setSex(dto.sex());
        user.setBirthDate(dto.birthDate());
        user.setPhoneNumber(dto.phoneNumber());
        user.setAddress(address);
        user.setEmail(dto.email());
        return userRepository.save(user);
    }
}
