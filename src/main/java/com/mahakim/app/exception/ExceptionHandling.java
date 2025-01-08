package com.mahakim.app.exception;

import static com.mahakim.app.constant.ExceptionHandlingConstant.ERROR_PATH;
import static com.mahakim.app.constant.ExceptionHandlingConstant.ERROR_PROCESSING_FILE;
import static com.mahakim.app.constant.ExceptionHandlingConstant.INTERNAL_SERVER_ERROR_MSG;
import static com.mahakim.app.constant.ExceptionHandlingConstant.METHOD_IS_NOT_ALLOWED;
import static com.mahakim.app.constant.ExceptionHandlingConstant.NOT_ENOUGH_PERMISSION;
import static com.mahakim.app.constant.ExceptionHandlingConstant.NO_MAPPING_FOR_THIS_URL;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.Objects;

import javax.net.ssl.SSLHandshakeException;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.codec.DecodingException;
import org.springframework.core.codec.EncodingException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.mahakim.app.exception.exceptions.AlreadyExistException;
import com.mahakim.app.exception.exceptions.EmailExistException;
import com.mahakim.app.exception.exceptions.EmailNotFoundException;
import com.mahakim.app.exception.exceptions.ErrorDataLoadingException;
import com.mahakim.app.exception.exceptions.NotFoundException;
import com.mahakim.app.exception.exceptions.TokenRefreshException;
import com.mahakim.app.exception.exceptions.UsernameExistException;
import com.mahakim.app.response.model.HttpResponse;

import io.netty.channel.ConnectTimeoutException;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.handler.timeout.TimeoutException;
import io.netty.handler.timeout.WriteTimeoutException;
import reactor.netty.http.client.PrematureCloseException;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
		return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, message), httpStatus);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<HttpResponse> accessDeniedException() {
		return createHttpResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION);
	}

	@ExceptionHandler(value = TokenRefreshException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<HttpResponse> tokenRefreshException(TokenRefreshException exception, WebRequest request) {
		return
//				new ErrorMessage(HttpStatus.FORBIDDEN.value(), new Date(), exception.getMessage(),
//				request.getDescription(false));
		createHttpResponse(UNAUTHORIZED, exception.getMessage());
	}

	@ExceptionHandler(SQLException.class)
	public ResponseEntity<HttpResponse> SqlExceptionHelper(SQLException exception) {
		return createHttpResponse(UNAUTHORIZED, exception.getMessage());
	}

	@ExceptionHandler(EmailExistException.class)
	public ResponseEntity<HttpResponse> emailExistException(EmailExistException exception) {
		return createHttpResponse(BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HttpResponse> validationLogin(MethodArgumentNotValidException exception) {
		String error = exception.getAllErrors() + "";
		return createHttpResponse(BAD_REQUEST,
				error.substring(error.lastIndexOf("default message [*") + 17, error.length() - 2) + "");
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<HttpResponse> validation(ConstraintViolationException exception) {
		String error = exception.getMessage() + "";
		return createHttpResponse(BAD_REQUEST,
				error.substring(error.lastIndexOf("messageTemplate='") + 17, error.length() - 4) + "");
	}

	@ExceptionHandler(UsernameExistException.class)
	public ResponseEntity<HttpResponse> usernameExistException(UsernameExistException exception) {
		return createHttpResponse(BAD_REQUEST, exception.getMessage());
	}

	@ExceptionHandler(AlreadyExistException.class)
	public ResponseEntity<HttpResponse> alreadyExistException(AlreadyExistException exception) {
		return createHttpResponse(CONFLICT, exception.getMessage());
	}

	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<HttpResponse> emailNotFoundException(EmailNotFoundException exception) {
		return createHttpResponse(BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(ErrorDataLoadingException.class)
	public ResponseEntity<HttpResponse> errorDataLoadingException(ErrorDataLoadingException exception) {
		return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<HttpResponse> errorDataLoadingException(MissingServletRequestParameterException exception) {
		return createHttpResponse(BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(UnknownHostException.class)
	public ResponseEntity<HttpResponse> errorDataLoadingException(UnknownHostException exception) {
		return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
	}
	
	@ExceptionHandler(ConnectException.class)
	public ResponseEntity<HttpResponse> errorDataLoadingException(ConnectException exception) {
		return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
	}
	
	@ExceptionHandler(ConnectTimeoutException.class)
	public ResponseEntity<HttpResponse> errorDataLoadingException(ConnectTimeoutException exception) {
		return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
	}
	
	@ExceptionHandler(SSLHandshakeException.class)
	public ResponseEntity<HttpResponse> errorDataLoadingException(SSLHandshakeException exception) {
		return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
	}
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<HttpResponse> errorDataLoadingException(IllegalStateException  exception) {
		return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
	}
	
	@ExceptionHandler(DecodingException.class)
	public ResponseEntity<HttpResponse> errorDataLoadingException(DecodingException  exception) {
		return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
	}
	
	@ExceptionHandler(ReadTimeoutException.class)
	public ResponseEntity<HttpResponse> errorDataLoadingException(ReadTimeoutException  exception) {
		return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
	}
	
	@ExceptionHandler(WebClientRequestException.class)
	public ResponseEntity<HttpResponse> errorDataLoadingException(WebClientRequestException  exception) {
		return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
	}
	
	@ExceptionHandler(WebClientResponseException.class)
	public ResponseEntity<HttpResponse> errorDataLoadingException(WebClientResponseException  exception) {
		return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
	}
	
	@ExceptionHandler(TimeoutException.class)
	public ResponseEntity<HttpResponse> errorDataLoadingException(TimeoutException  exception) {
		return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
	}
	
	@ExceptionHandler(WriteTimeoutException.class)
	public ResponseEntity<HttpResponse> errorDataLoadingException(WriteTimeoutException  exception) {
		return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
	}
	
	@ExceptionHandler(PrematureCloseException.class)
	public ResponseEntity<HttpResponse> errorDataLoadingException(PrematureCloseException  exception) {
		return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
	}
	
	@ExceptionHandler(EncodingException.class)
	public ResponseEntity<HttpResponse> errorDataLoadingException(EncodingException exception) {
		return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<HttpResponse> userNotFoundException(NotFoundException exception) {
		return createHttpResponse(NOT_FOUND, exception.getMessage());
	}

//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<HttpResponse> noHandlerFoundException(NoHandlerFoundException e) {
//        return createHttpResponse(BAD_REQUEST, "There is no mapping for this URL");
//    }

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
		HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
		return createHttpResponse(METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception) {
		LOGGER.error(exception.getMessage());
		return createHttpResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<HttpResponse> iOException(IOException exception) {
		LOGGER.error(exception.getMessage());
		return createHttpResponse(INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
	}

	@RequestMapping(ERROR_PATH)
	public ResponseEntity<HttpResponse> notFound404() {
		return createHttpResponse(NOT_FOUND, NO_MAPPING_FOR_THIS_URL);
	}

//	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}
