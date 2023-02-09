package br.com.algafood.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	ENTIDADE_NAO_ENCONTRADA("entidade-nao-encontrada","Entidade não encontrada"),
	FORMATO_INVALIDO("formato-invalido","Formato Inválido"),
	MENSAGEM_INCOMPREENSIVEL("mensagem-incompreensível","Mensagem Incompreensível"),
	ID_NULO_NAO_PERMITIDO("id-nulo-permissao","Id nulo não é permitido");
	
	private String uri;
	private String title;
	
	
	ProblemType(String path, String title){
		this.uri = "https://algafoodapi.com.br/" + path;
		this.title = title;
	}
	

}
