package br.sc.senac.cadastroNotas;

public class NotasDTO {
	
	public static final NotasDTO NULL_VALUE = new NotasDTO(Long.valueOf(0), "","", Double.valueOf(0.0));

	private final Long id;
	private final String materia;
	private final String nome;
	private final Double nota;

	public NotasDTO(final Long id, final String materia, final String nome, final Double nota) {
		this.id = id;
		this.materia = materia;
		this.nome = nome;
		this.nota = nota;
	}

	public Long getId() {
		return id;
	}

	public String getMateria() {
		return materia;
	}

	public String getNome() {
		return nome;
	}
	
	public Double getNota() {
		return nota;
	}

}
