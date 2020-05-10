package br.sc.senac.cadastroNotas;

public class AvaliacaoDTO {

	public static final AvaliacaoDTO NULL_VALUE = new AvaliacaoDTO(Long.valueOf(0), "","", "");

	private final Long id;
	private final String tituloAvaliacao;
	private final String descricaoAvaliacao;
	private final String dataAvaliacao;
	 
	
	public AvaliacaoDTO(final Long id, final String tituloAvaliacao, final String descricaoAvaliacao, final String dataAvaliacao) {
		this.id = id;
		this. tituloAvaliacao =  tituloAvaliacao;
		this.descricaoAvaliacao = descricaoAvaliacao;
		this.dataAvaliacao = dataAvaliacao;
	}

	public Long getId() {
		return id;
	}

	public String gettituloAvaliacao() {
		return tituloAvaliacao;
	}

	public String getdescricaoAvaliacao() {
		return descricaoAvaliacao;
	}
	
	public String getdataAvaliacao() {
		return dataAvaliacao;
	}
}
