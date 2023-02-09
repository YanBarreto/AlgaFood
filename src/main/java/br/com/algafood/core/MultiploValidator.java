package br.com.algafood.core;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {

	int multiploAnnotation;
	
	@Override
	public void initialize(Multiplo constraintAnnotation) {
		this.multiploAnnotation = constraintAnnotation.numero();
		
	}
	
	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		
		Boolean valido = true;
		
		if(value != null) {
			BigDecimal numeroValidar = BigDecimal.valueOf(value.doubleValue());
			BigDecimal multiploValidador = BigDecimal.valueOf(this.multiploAnnotation);
			
			BigDecimal resto  = numeroValidar.remainder(multiploValidador);
			valido = BigDecimal.ZERO.compareTo(resto) == 0;
			
		}
		
		return valido;
	}
	
	

}
