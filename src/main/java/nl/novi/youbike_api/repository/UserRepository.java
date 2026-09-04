package nl.novi.youbike_api.repository;

import nl.novi.youbike_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmailLowercase(String emailLowercase);
}
