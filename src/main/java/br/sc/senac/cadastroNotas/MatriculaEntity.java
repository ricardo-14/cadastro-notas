package br.sc.senac.cadastroNotas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Matricula")
final class MatriculaEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8572413711777084859L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long matriculaId;

	private Long numeroMatricula;
	private CursoEntity curso;
	// private Set<CursoEntity> cursos;

	protected MatriculaEntity() {
	}

	public MatriculaEntity(Long numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}

	@Override
	public String toString() {
		return "MatriculaEntity [matriculaId=" + this.matriculaId + ", numeroMatricula=" + this.numeroMatricula + "]";
	}

	public Long getmatriculaId() {
		return this.matriculaId;
	}

	public Long getnumeroMatricula() {
		return this.numeroMatricula;
	}

	public void setnumeroMatricula(final Long numeroMatricula) {
		if (numeroMatricula != null) {
			this.numeroMatricula = numeroMatricula;
		}
	}

	public CursoEntity getCurso() {
		return curso;
	}

	public void setCurso(CursoEntity curso) {
		if (curso != null) {
			this.curso = curso;

		}
	}

}
