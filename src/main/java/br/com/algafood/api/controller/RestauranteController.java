package br.com.algafood.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.algafood.core.Groups;
import br.com.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafood.domain.model.Restaurante;
import br.com.algafood.domain.service.RestauranteService;

@RestController
@RequestMapping("restaurantes")
public class RestauranteController {

	@Autowired
	RestauranteService restauranteService;
	
	@GetMapping
	public List<Restaurante> listar(){
		return restauranteService.buscarTodos();
	}
	
	@GetMapping("por-nome")
	public List<Restaurante> listarPorNome(@RequestParam String nome){
		
		try {
			restauranteService.buscarPorNome(nome);
		} catch (EntidadeNaoEncontradaException ex) {
			throw new EntidadeNaoEncontradaException(ex.getMessage(), ex);
		}
		
     return restauranteService.buscarPorNome(nome);
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Validated(Groups.CadastroRestaurante.class) Restaurante r, UriComponentsBuilder uriComponentsBuilder ){
		
		r = restauranteService.salvar(r);
		URI uri = uriComponentsBuilder.path("/restaurantes/{id}").buildAndExpand(r.getId()).toUri();
		return ResponseEntity.created(uri).body(r);
		
		}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void deletarPorId(@PathVariable Long id){
			try {
				restauranteService.deletarPorId(id);
			}catch(Exception ex) {
				throw new EntidadeNaoEncontradaException(ex.getMessage(), ex);
			}
			
	}
	
}
