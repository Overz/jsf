package br.com.jsf.model.vo;

import java.util.Date;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true, setterPrefix = "set")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "fornecedor")
@Table(
	name = "fornecedor",
	indexes = { @Index(columnList = "nome", name = "ix_fornecedor_nome") }
)
@PrimaryKeyJoinColumn(name = "id")
public class FornecedorVO extends PessoaVO {
	private static final long serialVersionUID = 1L;

	@Basic
	@Column(name = "descricao")
	private String descricao;

	@Basic
	@Column(name = "dtcadastro")
	private Date dtCadastro;
}
