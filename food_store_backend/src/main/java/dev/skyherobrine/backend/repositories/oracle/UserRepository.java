package dev.skyherobrine.backend.repositories.oracle;

import dev.skyherobrine.backend.models.oracle.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    @Query("select u from User u")
    List<User> getPageListUser(Pageable pageable);

    List<User> findByRole_RoleName(String roleRoleName);

    List<User> findByRole_RoleName(String roleRoleName, Pageable pageable);
}