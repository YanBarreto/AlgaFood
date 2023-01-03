package br.com.algafood.domain.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.algafood.Groups;
import lombok.Data;


@Entity
@Data
public class Restaurante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(groups = Groups.CadastroRestaurante.class)
	private String nomeRestaurante;

	@OneToMany(mappedBy = "restaurante")
	private List<Produto> listaProdutos;

}
