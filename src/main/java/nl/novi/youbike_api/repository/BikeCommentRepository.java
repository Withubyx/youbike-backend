package nl.novi.youbike_api.repository;

import nl.novi.youbike_api.model.BikeComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BikeCommentRepository extends JpaRepository<BikeComment, Long> {

    List<BikeComment> findByAuthor_Id(int authorId);

    List<BikeComment> findByAuthor_IdOrderByCreatedDateTimeAsc(int authorId);

    List<BikeComment> findByBike_IdOrderByCreatedDateTimeAsc(int bikeId);
}
