package br.com.jsf.model.dto;

import br.com.jsf.model.vo.EnderecoVO;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

@Data
public class EnderecoDTO implements Serializable {
	@Expose
	@SerializedName("cep")
	private String cep;

	@Expose
	@SerializedName("logradouro")
	private String logradouro;

	@Expose
	@SerializedName("complemento")
	private String complemento;

	@Expose
	@SerializedName("bairro")
	private String bairro;

	@Expose
	@SerializedName("localidade")
	private String localidade;

	@Expose
	@SerializedName("uf")
	private String uf;

	@Expose
	@SerializedName("ibge")
	private String ibge;

	@Expose
	@SerializedName("gia")
	private String gia;

	@Expose
	@SerializedName("ddd")
	private String ddd;

	@Expose
	@SerializedName("siafi")
	private String siafi;

	public EnderecoVO getEnderecoVO() {
		return new EnderecoVO(
			this.logradouro,
			this.bairro,
			this.localidade,
			this.uf,
			this.cep,
			this.complemento
		);
	}
}
