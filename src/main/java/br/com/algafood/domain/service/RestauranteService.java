package br.com.algafood.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafood.domain.model.Restaurante;
import br.com.algafood.domain.repository.ProdutoRepository;
import br.com.algafood.domain.repository.RestauranteRepository;


@Service
public class RestauranteService {	
	
	@Autowired
	RestauranteRepository restauranteRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Restaurante não encontrado";
	
	
	
	public List<Restaurante> buscarTodos(){
		return restauranteRepository.findAll();
	}
	
	public List<Restaurante> buscarPorNome(String nome){
		
		List<Restaurante> restaurantesEncontrados = restauranteRepository.buscarPorNome(nome);
		
		if(restaurantesEncontrados.isEmpty()) {
			throw new EntidadeNaoEncontradaException(MSG_RESTAURANTE_NAO_ENCONTRADO);
		}
		return restaurantesEncontrados;
		}
	
	
	@Transactional
	public Restaurante salvar(Restaurante r) {
		
		//Restaurante restauranteNovo = restauranteRepository.save(r);
		
		//r.getListaProdutos().forEach(  (produto) -> produto.setRestaurante(restauranteNovo));
		
		//produtoRepository.saveAll(r.getListaProdutos());
		
		//return restauranteRepository.findById(restauranteNovo.getId()).get();
		
		return restauranteRepository.save(r);
	}
	
	
	
	
	public void deletarPorId(Long id) {
		produtoRepository.deleteById(id);
		restauranteRepository.deleteById(id);
		
	}

}