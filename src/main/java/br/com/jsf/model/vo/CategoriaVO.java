package br.com.jsf.model.vo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity(name = "categoria")
@Table(
	name = "categoria",
	indexes = { @Index(columnList = "nome", name = "ix_categoria_nome") }
)
public class CategoriaVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Basic
	@Column(name = "nome", nullable = false)
	private String nome;

	@Basic
	@Column(name = "descricao")
	private String descricao;

	@OneToMany(mappedBy = "categoriaVO")
	private List<ProdutoVO> produtoVO;
}
