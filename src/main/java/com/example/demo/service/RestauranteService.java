package com.example.demo.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.infrastructure.RestauranteRepositoryImpl;
import com.example.demo.model.Restaurante;
import com.example.demo.repository.RestauranteRepository;


@Service
public class RestauranteService {	
	
	@Autowired
	RestauranteRepository restauranteRespository;
	
	@Autowired
	EntityManager entityManager;
	
	RestauranteRepositoryImpl restauranteRepositoryImpl = new RestauranteRepositoryImpl();
	
	public List<Restaurante> buscarTodos(){
		return restauranteRepositoryImpl.buscar(restauranteRespository);
	}
	
	public List<Restaurante> buscarPorNome(String nome){
		return restauranteRepositoryImpl.buscarPorNome(nome, entityManager);
	}
	
	public Restaurante salvar(Restaurante r) {
		return restauranteRepositoryImpl.salvar(r, restauranteRespository);
	}
	
	public void deletarPorId(Long id) {
		restauranteRepositoryImpl.deletarPorId(id, restauranteRespository);
		return;
	}

}