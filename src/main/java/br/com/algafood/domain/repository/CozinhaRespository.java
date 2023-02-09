package br.com.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRespository extends JpaRepository<Cozinha, Long> {
	
	public List<Cozinha> findByNomeCozinhaContaining(String nome);

}
