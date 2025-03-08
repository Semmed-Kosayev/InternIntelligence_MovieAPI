package az.semmed.internintelligence_movieapi.domain.repository;

import az.semmed.internintelligence_movieapi.domain.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    boolean existsByTitleIgnoreCase(String title);

    List<MovieEntity> findAllByTitleIgnoreCase(String title);

    List<MovieEntity> findAllByGenreIgnoreCase(String genre);

    List<MovieEntity> findAllByDirectorIgnoreCase(String director);

    List<MovieEntity> findAllByImdbRatingGreaterThanEqual(double rating);
}
