package br.com.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algafood.domain.model.Cidade;
import br.com.algafood.domain.repository.CidadeRespository;

@RestController
@RequestMapping("cidades")
public class CidadeController {
	
	@Autowired
	CidadeRespository cidadeRespository;
	
	@GetMapping
	public List<Cidade> listarCidades(){
		return cidadeRespository.findAll();
	}
	
}
