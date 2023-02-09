package br.com.algafood.domain.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.algafood.domain.model.Cozinha;
import br.com.algafood.domain.repository.CozinhaRespository;

@Service
public class CozinhaService {

	private final String MSG_COZINHA_NAO_ENCONTRADA = "Não foi encontrada nenhuma cozinha com";
	@Autowired
	CozinhaRespository cozinhaRepository;

	@ResponseStatus(value = HttpStatus.OK)
	public List<Cozinha> buscarTodasCozinhas() {

		List<Cozinha> cozinhas = cozinhaRepository.findAll();

		if (cozinhas == null) {
			throw new EntidadeNaoEncontradaException(null);
		}

		return cozinhas;

	}

	public List<Cozinha> buscarCozinhasPorNome(String nome) {
		List<Cozinha> cozinhas = cozinhaRepository.findByNomeCozinhaContaining(nome);
		System.out.println(cozinhas);
		if (cozinhas.isEmpty()) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA, nome));
		}

		return cozinhas;
	}

	public Cozinha buscarPorId(Long id) {
		return cozinhaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA+ " id: %s ", id)));
	}

	@Transactional
	public Cozinha salvarCozinha(Cozinha cozinha) {
		cozinha.setId(null);
		return cozinhaRepository.save(padronizarNome(cozinha));
	}

	private Cozinha padronizarNome(Cozinha cozinha) {
		String nomeCozinha = StringUtils.capitalize(cozinha.getNomeCozinha().toLowerCase());
		cozinha.setNomeCozinha(nomeCozinha);
		
		return cozinha;
	}
}
