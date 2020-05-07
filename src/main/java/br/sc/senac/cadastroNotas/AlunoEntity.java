package br.sc.senac.cadastroNotas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Aluno")
final class AlunoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4194869480873299820L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long alunoId;
	
	private String nomeAluno;
	private String cpf;
	// private java.Util.Set<MatriculaEntity>;
	
	protected AlunoEntity() {
	}

	public AlunoEntity(String nomeAluno, String cpf) {
		this.nomeAluno = nomeAluno;
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "AlunoEntity [alunoId=" + this.alunoId  + ", nomeAluno=" + this.nomeAluno + ",cpf=" + this.cpf + "]";
	}

	public long getalunoId() {
		return this.alunoId;
	}

	public String getnomeAluno() {
		return this.nomeAluno;
	}

	public void setnomeAluno(final String nomeAluno) {
		if (nomeAluno != null) {
			this.nomeAluno = nomeAluno;
		}
	}

	public String getcpf() {
		return this.cpf;
	}

	public void setcpf(final String cpf) {
		if (cpf != null) {
			this.cpf = cpf;
		}
	}
}