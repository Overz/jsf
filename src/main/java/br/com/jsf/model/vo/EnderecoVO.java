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
@Entity(name = "endereco")
@Table(name = "endereco")
public class EnderecoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Basic
	@Column(name = "logradouro", nullable = false)
	private String logradouro;

	@Basic
	@Column(name = "numero", nullable = false)
	private String numero;

	@Basic
	@Column(name = "bairro", nullable = false)
	private String bairro;

	@Basic
	@Column(name = "cidade", nullable = false)
	private String cidade;

	@Basic
	@Column(name = "estado", nullable = false)
	private String estado;

	@Basic
	@Column(name = "complemento", nullable = false)
	private String complemento;

	@JoinColumn(name = "idPessoa", referencedColumnName = "id", nullable = false)
	@OneToOne(
		mappedBy = "endereco",
		cascade = { CascadeType.ALL },
		targetEntity = PessoaVO.class
	)
	private PessoaVO pessoaVO;
}