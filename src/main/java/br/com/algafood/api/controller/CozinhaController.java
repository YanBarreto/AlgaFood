package br.com.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.algafood.domain.model.Cozinha;
import br.com.algafood.domain.repository.CozinhaRespository;
import br.com.algafood.domain.service.CozinhaService;

@RestController
@RequestMapping("cozinhas")
public class CozinhaController {
	
	@Autowired
	CozinhaService cozinhaService;
	
	@Autowired
	CozinhaRespository cozinhaRepository;
	
	@GetMapping
	private List<Cozinha> buscarTodas(){
		return cozinhaService.buscarTodasCozinhas();				
	}
	
	@GetMapping("/{id}")
	public Cozinha buscar(@PathVariable Long id) {
		return cozinhaService.buscarPorId(id);
	}
	
	@GetMapping("por-nome")
	private List<Cozinha> buscarPorNome(@RequestParam String nome){
		return cozinhaService.buscarCozinhasPorNome(nome);
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	private Cozinha criarCozinha(@Valid @RequestBody Cozinha cozinha){
		return cozinhaService.salvarCozinha(cozinha);
	}
	
	@PutMapping("/{id}")
	private Cozinha atualizaCozinha(@PathVariable Long id, @Valid @RequestBody Cozinha cozinha){
		
		Cozinha c = cozinhaService.buscarPorId(id);
		BeanUtils.copyProperties(cozinha, c, "id");
		
		return cozinhaService.salvarCozinha(c);
		
	}
	

}
