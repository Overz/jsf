package br.com.jsf.model.vo;

import static br.com.jsf.util.Constantes.FORNECEDOR_TOSTRING;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

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

	@CreationTimestamp
	@Column(name = "dtcadastro")
	private Date dtCadastro;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fornecedorVO")
	private List<ProdutoVO> produtoVOList;

	@OneToMany(
		mappedBy = "fornecedorVO",
		cascade = { CascadeType.ALL },
		targetEntity = EnderecoVO.class,
		fetch = FetchType.EAGER
	)
	private List<EnderecoVO> enderecoVOS;

	@Override
	public String toString() {
		if (FORNECEDOR_TOSTRING == 0) {
			return (
				super.toString() +
				", descricao: " +
				descricao +
				", dtCadastro: " +
				dtCadastro
			);
		}
		return ("ID: " + super.getId() + ", Nome: " + super.getNome());
	}
}
