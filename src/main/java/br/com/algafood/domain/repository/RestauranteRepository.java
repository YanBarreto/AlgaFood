package br.com.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{
	
	public List<Restaurante> findRestaurantesByNomeRestauranteContaining(String nome);//*MESMA FUNCAO COM IMPLEMENTACOES DIFERENTES 
	public List<Restaurante> buscarPorNome(String nome);//*MESMA FUNCAO COM IMPLEMENTACOES DIFERENTES 
}
