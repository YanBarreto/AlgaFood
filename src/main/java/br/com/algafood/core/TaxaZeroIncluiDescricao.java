package br.com.algafood.core;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = {TaxaZeroDescricaoValidator.class })
@Target(ElementType.TYPE)
@Retention(RUNTIME)
public @interface TaxaZeroIncluiDescricao {
	
	String message() default "descricao obrigatoria invalida" ;

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String taxaValidar();
	String descricaoValidar();
	String textoValidacao();
	

}
