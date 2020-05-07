package br.sc.senac.cadastroNotas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Avaliacao")
final class AvaliacaoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 693094373890395043L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long avaliacaoId;

	private String tituloAvaliacao;
	private String descricaoAvaliacao;
	private String dataAvaliacao;
	// private java.Util.Set<NotasEntity>;
	// private java.Util.Set<CursoEntity>;

	protected AvaliacaoEntity() {
	}

	public AvaliacaoEntity(String tituloAvaliacao, String descricaoAvaliacao, String dataAvaliacao) {
		this.tituloAvaliacao = tituloAvaliacao;
		this.descricaoAvaliacao = descricaoAvaliacao;
		this.dataAvaliacao = dataAvaliacao;
	}

	@Override
	public String toString() {
		return "AvaliacaoEntity [avaliacaoId=" + this.avaliacaoId + ", tituloAvaliacao=" + this.tituloAvaliacao
				+ ", descricaoAvaliacao=" + this.descricaoAvaliacao + ", dataAvaliacao=" + this.dataAvaliacao + "]";
	}

	public long getavaliacaoId() {
		return this.avaliacaoId;
	}

	public String gettituloAvaliacao() {
		return this.tituloAvaliacao;
	}

	public void settituloAvaliacao(final String tituloAvaliacao) {
		if (tituloAvaliacao != null) {
			this.tituloAvaliacao = tituloAvaliacao;
		}
	}

	public String getdescricaoAvaliacao() {
		return this.descricaoAvaliacao;
	}

	public void setdescricaoAvaliacao(final String descricaoAvaliacao) {
		if (descricaoAvaliacao != null) {
			this.descricaoAvaliacao = descricaoAvaliacao;
		}
	}

	public String getdataAvaliacao() {
		return this.dataAvaliacao;
	}

	public void setdataAvaliacao(final String dataAvaliacao) {
		if (dataAvaliacao != null) {
			this.dataAvaliacao = dataAvaliacao;
		}
	}

}
