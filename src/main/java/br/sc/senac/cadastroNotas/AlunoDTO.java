package br.sc.senac.cadastroNotas;

public class AlunoDTO {

	public static final AlunoDTO NULL_VALUE = new AlunoDTO(Long.valueOf(0), "","");

	private final Long id;
	private final String nomeAluno;
	private final String cpf;
	 
	
	public AlunoDTO(final Long id, final String nomeAluno, final String cpf) {
		this.id = id;
		this.nomeAluno = nomeAluno;
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public String getnomeAluno() {
		return nomeAluno;
	}

	public String getcpf() {
		return cpf;
	}
	
}
