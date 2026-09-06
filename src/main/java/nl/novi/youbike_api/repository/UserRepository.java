package nl.novi.youbike_api.repository;

import nl.novi.youbike_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmailLowercase(String emailLowercase);

    Optional<User> findByCyclist_Id(int cyclistId);

    Optional<User> findByBikeCompany_Id(int bikeCompanyId);
}
