package br.com.jsf.model.vo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
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

	@NonNull
	@Basic
	@Column(name = "nome", nullable = false)
	private String nome;

	@NonNull
	@Basic
	@Column(name = "preco", nullable = false)
	private Double preco;

	@NonNull
	@Basic
	@Column(name = "estoque", nullable = false)
	private Integer estoque;

	@NonNull
	@Basic
	@Column(name = "codigo", nullable = false)
	private Integer codigo;

	@NonNull
	@Basic
	@Column(name = "descricao")
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCategoria")
	private CategoriaVO categoriaVO;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idFornecedor")
	private FornecedorVO fornecedorVO;
}
