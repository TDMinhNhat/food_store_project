package dev.skyherobrine.backend.repositories.oracle;

import dev.skyherobrine.backend.models.oracle.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    Optional<UserRole> findByRoleName(String roleName);
}