package com.example.demo.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.Restaurante;
import com.example.demo.domain.repository.RestauranteRepository;


@Service
public class RestauranteService {	
	
	@Autowired
	RestauranteRepository restauranteRepository;
	
	public List<Restaurante> buscarTodos(){
		return restauranteRepository.findAll();
	}
	
	public List<Restaurante> buscarPorNome(String nome){
		return restauranteRepository.buscarPorNome(nome);
		}
	
	public Restaurante salvar(Restaurante r) {
		return restauranteRepository.save(r);
	}
	
	public void deletarPorId(Long id) {
		restauranteRepository.deleteById(id);
	}

}