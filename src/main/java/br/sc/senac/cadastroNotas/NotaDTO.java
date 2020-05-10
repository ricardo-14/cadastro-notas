package br.sc.senac.cadastroNotas;

public class NotaDTO {
	
	public static final NotaDTO NULL_VALUE = new NotaDTO(Long.valueOf(0),Double.valueOf(0.0));

	private final Long id;
	private final Double nota;
	 
	
	public NotaDTO(final Long id, final Double nota) {
		this.id = id;
		this.nota = nota;
	}

	public Long getId() {
		return id;
	}

	public Double getnota() {
		return nota;
	}
}
