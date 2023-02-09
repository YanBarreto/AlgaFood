package br.com.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.algafood.domain.model.Usuario;
import br.com.algafood.domain.service.UsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;

	@GetMapping
	private List<Usuario> buscarTodos(){
		return usuarioService.buscarTodosUsuarios();		
	}
	
	@PostMapping
	private Usuario criarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.salvarUsuario(usuario);
	}
	
	
}
