package com.example.demo.infrastructure;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.model.Restaurante;
import com.example.demo.repository.RestauranteRepository;


public class RestauranteRepositoryImpl{
	
	
	public List<Restaurante> buscar(RestauranteRepository restauranteRepository){
		return restauranteRepository.findAll();		
	}
	
	public List<Restaurante> buscarPorNome(String nome, EntityManager entityManager){
		return entityManager.createQuery("from Restaurante where nomeRestaurante like :nome", Restaurante.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}
		
	public Restaurante salvar(Restaurante r, RestauranteRepository restauranteRepository){
			
		if(r.getId() == null) {
			return restauranteRepository.save(r);
		}
		else
		{
			Optional<Restaurante> restaurantePersistido = restauranteRepository.findById(r.getId());
			if(restaurantePersistido.isEmpty()) {
				return restauranteRepository.save(r);
			}else {
				BeanUtils.copyProperties(r, restaurantePersistido.get(),"id");
				restauranteRepository.save(restaurantePersistido.get());
				return restaurantePersistido.get();
			}
		}
		
	}
	
	public void deletarPorId(Long id, RestauranteRepository restauranteRepository) {
		restauranteRepository.deleteById(id);
		return;
	}
	

	

}
