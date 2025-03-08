package az.semmed.internintelligence_movieapi.model.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorCode {
    public static final String ALREADY_EXISTS = "already_exists";
    public static final String NOT_FOUND = "not_found";
    public static final String INTERNAL_SERVER_ERROR = "internal_server_error";
}
