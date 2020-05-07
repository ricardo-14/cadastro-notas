package br.sc.senac.cadastroNotas;

public class MatriculaDTO {

	public static final MatriculaDTO NULL_VALUE = new MatriculaDTO(Long.valueOf(0), Long.valueOf(0));

	private final Long id;
	private final Long numeroMatricula;
	 
	
	public MatriculaDTO(final Long id, final Long numeroMatricula) {
		this.id = id;
		this.numeroMatricula = numeroMatricula;
	}

	public Long getId() {
		return id;
	}

	public Long getnumeroMatricula() {
		return numeroMatricula;
	}
}
