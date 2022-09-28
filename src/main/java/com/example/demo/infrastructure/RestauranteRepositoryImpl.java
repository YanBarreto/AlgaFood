package com.example.demo.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Restaurante;



@Repository
public class RestauranteRepositoryImpl{
	
	@Autowired
	EntityManager entityManager;
	
	  public List<Restaurante> buscarPorNome(String nome){
	  return
	  entityManager.createQuery("from Restaurante where nomeRestaurante like :nome", Restaurante.class) 
	  .setParameter("nome", "%" + nome + "%")
	  .getResultList(); 
	  }
	  
	 
	
}
