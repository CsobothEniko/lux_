package hu.dreamteam.lux.repo;

import hu.dreamteam.lux.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

    User findByEmail(String email);

}
