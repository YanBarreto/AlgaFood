package br.com.algafood.domain.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(value = {"senha"}, allowSetters =  true)
public class Usuario {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String email;
	private String senha;
	@CreationTimestamp()
	@Column(columnDefinition = "datetime")
	private OffsetDateTime dataCriacao;
	
	@ManyToMany
	@JoinTable(
			name="usuario_grupo",
			joinColumns = @JoinColumn(name="fk_usuario"),
			inverseJoinColumns = @JoinColumn(name="fk_grupo"))
	private List<Grupo> listaGrupos = new ArrayList<Grupo>();
	

}
