package nl.novi.youbike_api.repository;

import nl.novi.youbike_api.model.BikeCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeCompanyRepository extends JpaRepository<BikeCompany, Integer> {
}
