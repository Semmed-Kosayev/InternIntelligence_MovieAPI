package az.semmed.internintelligence_movieapi.controller;

import az.semmed.internintelligence_movieapi.model.dto.request.CreateMovieRequest;
import az.semmed.internintelligence_movieapi.model.dto.request.UpdateMovieRequest;
import az.semmed.internintelligence_movieapi.model.dto.response.MovieResponseDto;
import az.semmed.internintelligence_movieapi.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "Movies", description = "Endpoints for managing movies")
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    @Operation(summary = "Get all movies")
    public ResponseEntity<Page<MovieResponseDto>> getAllMovies(
            @Parameter(hidden = true)
            @PageableDefault(page = 0, size = 10)
            Pageable pageable
    ) {
        return ResponseEntity.ok(movieService.getAllMovies(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a movie by ID")
    public ResponseEntity<MovieResponseDto> getMovieById(@NotNull @Min(1) @PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PostMapping
    @Operation(summary = "Add a new movie")
    public ResponseEntity<MovieResponseDto> createMovie(@Valid @RequestBody CreateMovieRequest request) {
        return ResponseEntity.ok(movieService.saveMovie(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a movie")
    public ResponseEntity<MovieResponseDto> updateMovie(
            @NotNull @Min(1) @PathVariable Long id,
            @Valid @RequestBody UpdateMovieRequest requestDto
    ) {
        return ResponseEntity.ok(movieService.updateMovie(id, requestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a movie")
    public ResponseEntity<Void> deleteMovie(@NotNull @Min(1) @PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

}
