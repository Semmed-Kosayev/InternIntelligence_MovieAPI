package az.semmed.internintelligence_movieapi.mapper;

import az.semmed.internintelligence_movieapi.domain.entity.MovieEntity;
import az.semmed.internintelligence_movieapi.model.dto.request.CreateMovieRequest;
import az.semmed.internintelligence_movieapi.model.dto.request.UpdateMovieRequest;
import az.semmed.internintelligence_movieapi.model.dto.response.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public MovieResponseDto toMovieResponseDto(MovieEntity movie) {
        return MovieResponseDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .releaseYear(movie.getReleaseYear())
                .genre(movie.getGenre())
                .imdbRating(movie.getImdbRating())
                .build();
    }

    public MovieEntity toMovieEntity(CreateMovieRequest request) {
        return MovieEntity.builder()
                .title(request.title())
                .director(request.director())
                .releaseYear(request.releaseYear())
                .genre(request.genre())
                .imdbRating(0.0)
                .build();
    }

    public MovieEntity toMovieEntity(MovieEntity movie, UpdateMovieRequest request) {
        return movie.toBuilder()
                .title(request.title())
                .director(request.director())
                .releaseYear(request.releaseYear())
                .genre(request.genre())
                .imdbRating(request.imdbRating())
                .build();
    }
}
