package br.sc.senac.cadastroNotas;

public class CursoDTO {
	
	public static final CursoDTO NULL_VALUE = new CursoDTO(Long.valueOf(0), "","", Long.valueOf(0));

	private final Long id;
	private final String nomeCurso;
	private final String periodoCurso;
	private final Long cargaHoraria;
	 
	
	public CursoDTO(final Long id, final String nomeCurso, final String periodoCurso, final Long cargaHoraria) {
		this.id = id;
		this.nomeCurso = nomeCurso;
		this.periodoCurso = periodoCurso;
		this.cargaHoraria = cargaHoraria;
	}

	public Long getId() {
		return id;
	}

	public String getnomeCurso() {
		return nomeCurso;
	}

	public String getperiodoCurso() {
		return periodoCurso;
	}
	
	public Long getcargaHoraria() {
		return cargaHoraria;
	}

}
