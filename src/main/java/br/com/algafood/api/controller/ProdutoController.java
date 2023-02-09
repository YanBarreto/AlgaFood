package br.com.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algafood.domain.model.Produto;
import br.com.algafood.domain.repository.ProdutoRepository;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping
	public List<Produto> listarProdutos(){
		return produtoRepository.findAll();
	}

}
