package br.com.algafood.core;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;

public class TaxaZeroDescricaoValidator implements ConstraintValidator<TaxaZeroIncluiDescricao, Object> {

	String propriedadeNumero;
	String propriedadeTexto;
	String propriedadevalidar;
	
	@Override
	public void initialize(TaxaZeroIncluiDescricao constraintAnnotation) {
		
		this.propriedadeNumero = constraintAnnotation.taxaValidar();
		this.propriedadeTexto = constraintAnnotation.descricaoValidar();
		this.propriedadevalidar = constraintAnnotation.textoValidacao();

	}
	
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		boolean valido = true;
		
	    try {
			BigDecimal valorPropriedadeNumero =  (BigDecimal) BeanUtils.getPropertyDescriptor(value.getClass(), propriedadeNumero).getReadMethod().invoke(value);
			String valorPropriedadeTexto = (String) BeanUtils.getPropertyDescriptor(value.getClass(), propriedadeTexto).getReadMethod().invoke(value);
		
			
			if(valorPropriedadeNumero.compareTo(BigDecimal.ZERO) == 0 && propriedadeTexto != null && propriedadeNumero != null) {
				return valorPropriedadeTexto.toLowerCase().contains(propriedadevalidar.toLowerCase());

			}

		} catch (Exception e) {
			throw new ValidationException(e);
		} 
	    //VALUE NESSE CASO É O OBJETO (CLASSE) ANOTADO PELA ANOTAÇÃO TAXAZEROINCLUIDESCRICAO
	   return valido;
	}

}
