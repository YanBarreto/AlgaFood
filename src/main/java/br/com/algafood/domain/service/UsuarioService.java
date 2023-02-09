package br.com.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.algafood.domain.model.Usuario;
import br.com.algafood.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	
	public List<Usuario> buscarTodosUsuarios(){
		return usuarioRepository.findAll();
	}
	
	public Usuario salvarUsuario(Usuario usuario) {
		return  usuarioRepository.save(usuario);
	}
	
	
	
}
