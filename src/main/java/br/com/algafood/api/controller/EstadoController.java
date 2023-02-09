package br.com.algafood.api.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafood.domain.model.Estado;
import br.com.algafood.domain.repository.EstadoRepository;

@RestController
@RequestMapping("estados")
public class EstadoController {

	@Autowired
	EstadoRepository estadoRepository;
	
	@GetMapping
	public List<Estado> listarEstados(){
		return estadoRepository.findAll(); 
	}
	
	@GetMapping("/{id}")
	public Estado listarPorId(@PathVariable Long id) {
		
		try {
			 return estadoRepository.findById(id).get();

		}catch (NoSuchElementException ex) {
			throw new EntidadeNaoEncontradaException(String.format("O estado de id %d não foi encontrado. Por favor insira um ID existente ou cadastre-o", id));
		}
		
		

	}
	
	
	@PostMapping
	public ResponseEntity<?> salvarEstado(@RequestBody Estado estado, UriComponentsBuilder uriComponentsBuilder){
		
		estado = estadoRepository.save(estado);
		URI uri = uriComponentsBuilder.path("/estados/{id}").buildAndExpand(estado.getId()).toUri();
		
		return ResponseEntity.created(uri).body(estado);
	}
	
}
