package br.com.jsf.model.vo;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true, setterPrefix = "set")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "fornecedor")
@Table(name = "fornecedor")
@PrimaryKeyJoinColumn(name = "id")
public class FornecedorVO extends PessoaVO {
	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name = "descricao")
	private String descricao;

	@Basic
	@Column(name = "dtcadastro")
	private Date dtCadastro;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fornecedorVO")
	private List<ProdutoVO> produtoVOList;

	@OneToMany(
		mappedBy = "fornecedorVO",
		cascade = { CascadeType.ALL },
		targetEntity = EnderecoVO.class
	)
	private List<EnderecoVO> enderecoVOS;

	@Override
	public String toString() {
		return (
			super.toString() +
			", descricao: " +
			descricao +
			", dtCadastro: " +
			dtCadastro
		);
	}
}
