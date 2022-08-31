package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.Restaurante;
import com.example.demo.service.RestauranteService;

@RestController
@RequestMapping("restaurantes")
public class RestauranteController {

	@Autowired
	RestauranteService restauranteService;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		List<Restaurante> listaRestaurantes = restauranteService.buscarTodos();
		return ResponseEntity.ok(listaRestaurantes);
	}
	
	@GetMapping("por-nome")
	public ResponseEntity<?> listarPorNome(@RequestParam String nome){
			try {
				List<Restaurante> listaRestaurantes = restauranteService.buscarPorNome(nome);
				return ResponseEntity.ok(listaRestaurantes);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return ResponseEntity.notFound().build();
			}
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Restaurante r, UriComponentsBuilder uriComponentsBuilder ){
		
		r = restauranteService.salvar(r);
		URI uri = uriComponentsBuilder.path("/restaurantes/{id}").buildAndExpand(r.getId()).toUri();
		return ResponseEntity.created(uri).body(r);
		
		}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarPorId(@PathVariable Long id){
		try {
			restauranteService.deletarPorId(id);
			return ResponseEntity.noContent().build();
			
		}catch(Exception e){
			return ResponseEntity.notFound().build();
		}
	}
	
}
