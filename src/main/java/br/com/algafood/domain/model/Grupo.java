package br.com.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeGrupo;
	
	@ManyToMany
	@JoinTable(name = "grupo_permissao",
	joinColumns = @JoinColumn(name= "fk_grupo"),
	inverseJoinColumns = @JoinColumn(name="fk_permissao"))
	private List<Permissao> listaPermissoes = new ArrayList<Permissao>();
}
