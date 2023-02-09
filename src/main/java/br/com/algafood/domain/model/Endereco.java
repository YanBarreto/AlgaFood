package br.com.algafood.domain.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Endereco {
	
	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;

}
