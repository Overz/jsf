package br.com.jsf.model.vo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "produto")
@Table(
	name = "produto",
	indexes = { @Index(columnList = "nome", name = "ix_produto_nome") }
)
public class ProdutoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Basic
	@Column(name = "nome", nullable = false)
	private String nome;

	@Basic
	@Column(name = "preco", nullable = false)
	private Double preco;

	@Basic
	@Column(name = "estoque", nullable = false)
	private Integer estoque;

	@Basic
	@Column(name = "codigo", nullable = false)
	private Integer codigo;

	@Basic
	@Column(name = "descricao")
	private String descricao;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idCategoria")
	private CategoriaVO categoriaVO;
}
