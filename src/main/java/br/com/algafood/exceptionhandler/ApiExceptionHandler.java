package br.com.algafood.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafood.domain.exception.IdNuloNaoPermitidoException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String ARGUMENTO_INVALIDO_EXCEPTION = "O parâmetro '%s' recebeu o valor '%s', porém o valor esperado deve ser do tipo %s";
	
	@Autowired
	MessageSource messageSource;


	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String detail = ex.getMessage();

		Problema problema = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problema, null, status, request); // A REQUISIÇÃO É PASSADA PELO PROPRIO
																				// SPRING.
	}
	
	@ExceptionHandler(IdNuloNaoPermitidoException.class)
	public ResponseEntity<?> handleIdNuloNaoPermitido(IdNuloNaoPermitidoException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ID_NULO_NAO_PERMITIDO;
		String detail = ex.getMessage();
		
		Problema problema = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problema, null, status, request);
	}
	
	

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Throwable rootCause = ExceptionUtils.getRootCause(ex);

		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormat((InvalidFormatException) rootCause, request);
		}

		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = ex.getMessage();
		Problema problema = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problema, null, status, request);

	}

	public ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.FORMATO_INVALIDO;

		String nomeParametro = ex.getPath().stream().map(prop -> prop.getFieldName()).filter(prop -> prop != null)
				.collect(Collectors.joining("."));

		String detail = String.format(ARGUMENTO_INVALIDO_EXCEPTION, nomeParametro, ex.getValue(),
				ex.getTargetType().getSimpleName());
		Problema problema = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problema, null, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		/*
		 * String field = ex.getFieldError().getField(); String fieldType =
		 * ex.getFieldType(field).getSimpleName();
		 */

		String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente";

		ProblemType problemType = ProblemType.FORMATO_INVALIDO;

		
		
		
		List<Problema.FieldError> objectErrors = ex.getBindingResult().getAllErrors().stream().map(objectError -> {

			String mensagem = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
			
			String name = null;
			
			name = objectError.getObjectName();
			
			if (objectError instanceof FieldError) {
				name = ((FieldError)objectError).getField();
			}

			return Problema.FieldError.builder()
					.fieldName(name)
					.message(mensagem)
					.build();
		}

		).collect(Collectors.toList());

		Problema problema = createProblemBuilder(status, problemType, detail) // Ainda posso continuar porque
																				// createProblemBuilder retorna um
																				// Builder de Problema.
				.fieldErros(objectErrors).build();

		return handleExceptionInternal(ex, problema, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			body = Problema.builder().title(status.getReasonPhrase()).status(status.value()).detail(ex.toString())
					.build();

		} else if (body instanceof String) {
			body = Problema.builder().title(status.getReasonPhrase()).status(status.value()).detail(ex.getMessage())
					.build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private Problema.ProblemaBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		return Problema.builder().title(problemType.getTitle()).status(status.value()).type(problemType.getUri())
				.detail(detail);
	}

}
