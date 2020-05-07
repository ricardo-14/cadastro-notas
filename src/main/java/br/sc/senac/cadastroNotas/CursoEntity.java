package br.sc.senac.cadastroNotas;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Curso")
final class CursoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3251383068839051828L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cursoId;

	private String nomeCurso;

	private String periodoCurso;

	private Long cargaHoraria;

	protected CursoEntity() {
	}

	public CursoEntity(String nomeCurso, String periodoCurso, Long cargaHoraria) {
		this.nomeCurso = nomeCurso;
		this.periodoCurso = periodoCurso;
		this.cargaHoraria = cargaHoraria;
	}

	@Override
	public String toString() {
		return "CursoEntity [cursoId=" + this.cursoId + ", nomeCurso=" + this.nomeCurso + ", periodoCurso="
				+ this.periodoCurso + ", cargaHoraria=" + this.cargaHoraria + "]";
	}

	public Long getcursoId() {
		return this.cursoId;
	}

	public String getnomeCurso() {
		return this.nomeCurso;
	}

	public void setnomeCurso(final String nomeCurso) {
		if (nomeCurso != null) {
			this.nomeCurso = nomeCurso;
		}
	}

	public String getperiodoCurso() {
		return this.periodoCurso;
	}

	public void setperiodoCurso(final String periodoCurso) {
		if (periodoCurso != null) {
			this.periodoCurso = periodoCurso;
		}
	}

	public Long getcargaHoraria() {
		return this.cargaHoraria;
	}

	public void setcargaHoraria(final Long cargaHoraria) {
		if (cargaHoraria != null) {
			this.cargaHoraria = cargaHoraria;
		}
	}

}
