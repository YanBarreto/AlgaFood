package br.com.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;


@Data
@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	@CreationTimestamp
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataCriacao;
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataConfirmacao;
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataCancelamento;
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataEntrega;
	@Embedded
	private Endereco endereco;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
}
