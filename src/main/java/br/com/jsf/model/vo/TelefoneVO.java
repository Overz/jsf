package br.com.jsf.model.vo;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity(name = "telefone")
@Table(
	name = "telefone",
	indexes = { @Index(columnList = "numero", name = "ix_telefone_numero") }
)
public class TelefoneVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Basic
	@Column(name = "numero", nullable = false)
	private String numero;

	@Basic
	@Column(name = "tipo")
	private String tipo;

	@Basic
	@Column(name = "operadora")
	private String operadora;
}
