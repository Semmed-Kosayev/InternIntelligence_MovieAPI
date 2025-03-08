package az.semmed.internintelligence_movieapi.service;

import az.semmed.internintelligence_movieapi.domain.entity.MovieEntity;
import az.semmed.internintelligence_movieapi.domain.repository.MovieRepository;
import az.semmed.internintelligence_movieapi.exception.AlreadyExistsException;
import az.semmed.internintelligence_movieapi.exception.NotFoundException;
import az.semmed.internintelligence_movieapi.mapper.MovieMapper;
import az.semmed.internintelligence_movieapi.model.dto.request.CreateMovieRequest;
import az.semmed.internintelligence_movieapi.model.dto.request.UpdateMovieRequest;
import az.semmed.internintelligence_movieapi.model.dto.response.MovieResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public Page<MovieResponseDto> getAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable)
                .map(movieMapper::toMovieResponseDto);
    }

    public MovieResponseDto getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(movieMapper::toMovieResponseDto)
                .orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));
    }

    @Transactional
    public MovieResponseDto saveMovie(CreateMovieRequest requestDto) {
        MovieEntity movie = movieMapper.toMovieEntity(requestDto);

        boolean isMovieExist = movieRepository.existsByTitleIgnoreCase(movie.getTitle());
        if (isMovieExist) {
            throw new AlreadyExistsException("Movie already exists");
        }

        MovieEntity savedMovie = movieRepository.save(movie);

        return movieMapper.toMovieResponseDto(savedMovie);
    }

    @Transactional
    public MovieResponseDto updateMovie(Long id, UpdateMovieRequest request) {
        MovieEntity movie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));

        MovieEntity movieEntity = movieMapper.toMovieEntity(movie, request);

        return movieMapper.toMovieResponseDto(movieRepository.save(movieEntity));
    }

    @Transactional
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new NotFoundException("Movie not found with ID: " + id);
        }
        movieRepository.deleteById(id);
    }
}
