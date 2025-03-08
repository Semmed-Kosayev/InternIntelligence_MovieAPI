package az.semmed.internintelligence_movieapi.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateMovieRequest(

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Director is required")
        String director,

        @Min(value = 1800, message = "Release year must be at least 1800")
        int releaseYear,

        @NotBlank(message = "Genre is required")
        String genre
) {
}
