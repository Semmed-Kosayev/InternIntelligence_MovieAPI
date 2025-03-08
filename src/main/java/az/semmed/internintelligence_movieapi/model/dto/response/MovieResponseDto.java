package az.semmed.internintelligence_movieapi.model.dto.response;

import lombok.Builder;

@Builder
public record MovieResponseDto(
        Long id,
        String title,
        String director,
        int releaseYear,
        String genre,
        double imdbRating
) {
}
