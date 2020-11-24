package hu.dreamteam.lux.repo;

import hu.dreamteam.lux.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByRole(String role);

}
