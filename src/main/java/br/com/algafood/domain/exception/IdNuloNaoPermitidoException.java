package br.com.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IdNuloNaoPermitidoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	
	public IdNuloNaoPermitidoException (String mensagem) {
		super(mensagem);}

	
	public IdNuloNaoPermitidoException (String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
	
}
