package nl.novi.youbike_api.repository;

import nl.novi.youbike_api.model.BikeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeCommentRepository extends JpaRepository<BikeComment, Long> {
}
