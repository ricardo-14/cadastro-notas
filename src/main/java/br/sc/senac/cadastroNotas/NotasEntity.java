package br.sc.senac.cadastroNotas;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Notas")
final class NotasEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8326061886992404496L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;

	private String materia;
	
	private String nome;

	private Double nota;

	protected NotasEntity() {
	}

	public NotasEntity(String materia, String nome, Double nota) {
		this.materia = materia;
		this.nome = nome;
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "NotasEntity [Id=" + this.Id + ", materia=" + this.materia + ", nome=" + this.nome + ", nota=" + this.nota + "]";
	}

	public Long getId() {
		return this.Id;
	}

	public String getMateria() {
		return this.materia;
	}

	public void setMateria(final String materia) {
		if (materia != null) {
			this.materia = materia;
		}
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		if (nome != null) {
			this.nome = nome;
		}
	}
	
	public Double getNota() {
		return this.nota;

	}

	public void setNota(final Double nota) {
		if (nota != null) {
			this.nota = nota;
		}
	}
}