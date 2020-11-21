package br.com.jsf.model.vo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
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
	@Column(name = "logradouro")
	private String logradouro;

	@Basic
	@Column(name = "numero")
	private String numero;

	@Basic
	@Column(name = "bairro")
	private String bairro;

	@Basic
	@Column(name = "cidade")
	private String cidade;

	@Basic
	@Column(name = "estado")
	private String estado;

	@Basic
	@Column(name = "complemento")
	private String complemento;

	@ManyToOne(targetEntity = FornecedorVO.class, cascade = { CascadeType.ALL })
	@JoinColumn(name = "idFornecedor")
	private FornecedorVO fornecedorVO;
}
