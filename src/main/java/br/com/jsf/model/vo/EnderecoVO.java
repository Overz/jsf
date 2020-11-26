package br.com.jsf.model.vo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

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

	@NonNull
	@Basic
	@Column(name = "logradouro")
	private String logradouro;

	@Basic
	@Column(name = "numero")
	private String numero;

	@NonNull
	@Basic
	@Column(name = "bairro")
	private String bairro;

	@NonNull
	@Basic
	@Column(name = "cidade")
	private String cidade;

	@NonNull
	@Basic
	@Column(name = "estado")
	private String estado;

	@NonNull
	@Basic
	@Column(name = "cep")
	private String cep;

	@NonNull
	@Basic
	@Column(name = "complemento")
	private String complemento;

	@ManyToOne(
		targetEntity = FornecedorVO.class,
		cascade = { CascadeType.ALL },
		fetch = FetchType.LAZY
	)
	@JoinColumn(name = "idFornecedor")
	private FornecedorVO fornecedorVO;
}
