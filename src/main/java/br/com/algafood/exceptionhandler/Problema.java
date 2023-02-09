package br.com.algafood.exceptionhandler;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problema {
	
	private String title;
	private Integer status;
	private String type;
	private String detail;
	private List<Problema.FieldError> fieldErros;
	
	
	@Getter
	@Builder
	public static class FieldError{
		
		private String fieldName;
		private String message;
		
	}
	

}
