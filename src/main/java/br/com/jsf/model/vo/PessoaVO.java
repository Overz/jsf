package br.com.jsf.model.vo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true, setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(
	name = "pessoa",
	indexes = { @Index(columnList = "nome", name = "ix_pessoa_nome") }
)
@Entity(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class PessoaVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Basic
	@Column(name = "nome")
	private String nome;

	@Basic
	@Column(name = "email")
	private String email;

	@Basic
	@Column(name = "telefone")
	private String telefone;
	//	@OneToOne(
	//		mappedBy = "pessoa",
	//		cascade = { CascadeType.ALL },
	//		targetEntity = EnderecoVO.class
	//	)
	//	private EnderecoVO enderecoVO;
}
