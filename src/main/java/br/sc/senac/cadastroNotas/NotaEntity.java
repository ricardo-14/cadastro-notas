package br.sc.senac.cadastroNotas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Nota")
final class NotaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6975690106696086544L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long notaId;
	
	private Double nota;
	private AlunoEntity aluno;
	// private Set<AlunoEntity> aluno;

	
	
	protected NotaEntity() {
	}
	
	public NotaEntity(Double nota) {
		this.nota = nota;
	}
	
	@Override
	public String toString() {
		return "NotaEntity [notaId=" + this.notaId + ", nota=" + this.nota + "]";
	}
	
	public Long getnotaId() {
		return this.notaId;
	}

	public Double getnota() {
		return this.nota;
	}

	public void setnota(final Double nota) {
		if (nota != null) {
			this.nota = nota;
		}
	}

	public AlunoEntity getAluno() {
		return aluno;
	}

	public void setAluno(AlunoEntity aluno) {
		if (aluno != null) {
			this.aluno = aluno;

		}
	}
}
