package br.com.algafood.domain.model;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.algafood.core.Groups;
import br.com.algafood.core.Multiplo;
import br.com.algafood.core.TaxaFrete;
import br.com.algafood.core.TaxaZeroIncluiDescricao;
import lombok.Data;


@Entity
@Data
@TaxaZeroIncluiDescricao(taxaValidar = "taxaFrete", descricaoValidar = "nomeRestaurante", textoValidacao = "Frete Grátis",groups = Groups.CadastroRestaurante.class)
public class Restaurante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(groups = Groups.CadastroRestaurante.class)
	private String nomeRestaurante;
	
	@TaxaFrete(groups = Groups.CadastroRestaurante.class)
	@Multiplo(numero = 5, groups = Groups.CadastroRestaurante.class)
	@Column(name = "taxa_frete")
	private BigDecimal taxaFrete;

	@OneToMany(mappedBy = "restaurante")
	private List<Produto> listaProdutos;
	
	@ManyToOne
	@JoinColumn(name = "fk_cozinha")
	@JsonIgnoreProperties(value = "id", allowGetters = true)
	private Cozinha cozinha;

}
