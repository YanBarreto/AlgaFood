package br.com.algafood.domain.model;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import br.com.algafood.core.Groups;
import br.com.algafood.core.TaxaFrete;
import lombok.Data;


@Entity
@Data
public class Restaurante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(groups = Groups.CadastroRestaurante.class)
	private String nomeRestaurante;
	
	@TaxaFrete(groups = Groups.CadastroRestaurante.class)
	@Column(name = "taxa_frete")
	private BigDecimal taxaFrete;

	@OneToMany(mappedBy = "restaurante")
	private List<Produto> listaProdutos;

}
