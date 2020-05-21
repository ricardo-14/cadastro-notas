package br.sc.senac.cadastroNotas;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/aluno")
public class AlunoService {
	private static final AlunoDTO[] DEFAULT_ALUNO = new AlunoDTO[] {
			new AlunoDTO(Long.valueOf(0), "Ricardo","Matutino"),
			new AlunoDTO(Long.valueOf(0), "João","Vespertino"),
			new AlunoDTO(Long.valueOf(0), "Claudio","Noturno") };

	private final AlunoController alunoController;

	AlunoService(final AlunoController alunoController) {
		this.alunoController = alunoController;
		Arrays.asList(AlunoService.DEFAULT_ALUNO).forEach(dto -> this.alunoController.insertAluno(dto));
	}

	@GetMapping("/list")
	public List<AlunoDTO> list() {
		return this.alunoController.getAllAlunos();
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<AlunoDTO> getAluno(@PathVariable final Long id) {
		final AlunoDTO aluno = this.alunoController.getAluno(id);
		if (aluno.equals(AlunoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(aluno, HttpStatus.OK);
	}

	@DeleteMapping("/del/{id}")
	public ResponseEntity<AlunoDTO> removeAluno(@PathVariable final Long id) {
		final AlunoDTO removedAluno = this.alunoController.removeAluno(id);
		if (removedAluno.equals(AlunoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(removedAluno, HttpStatus.OK);
	}

	@PutMapping("/att/{id}")
	public ResponseEntity<AlunoDTO> updateAluno(@PathVariable final Long id, @RequestBody final AlunoDTO aluno) {
		final AlunoDTO oldAluno = this.alunoController.updateAluno(id, aluno);
		if (oldAluno.equals(AlunoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(oldAluno, HttpStatus.OK);
	}

	@PostMapping("/add")
	public Long insertAluno(@RequestBody final AlunoDTO aluno) {
		return this.alunoController.insertAluno(aluno);
	}

}
