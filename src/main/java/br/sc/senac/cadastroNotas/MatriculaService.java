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
@RequestMapping("/api/v1/matricula")
public class MatriculaService {

	private static final MatriculaDTO[] DEFAULT_MATRICULA = new MatriculaDTO[] {
			new MatriculaDTO(Long.valueOf(0), Long.valueOf(1234)),
			new MatriculaDTO(Long.valueOf(0), Long.valueOf(4567)),
			new MatriculaDTO(Long.valueOf(0), Long.valueOf(8910)) };

	private final MatriculaController matriculaController;

	MatriculaService(final MatriculaController matriculaController) {
		this.matriculaController = matriculaController;
		Arrays.asList(MatriculaService.DEFAULT_MATRICULA).forEach(dto -> this.matriculaController.insertMatricula(dto));
	}

	@GetMapping("/list")
	public List<MatriculaDTO> list() {
		return this.matriculaController.getAllMatriculas();
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<MatriculaDTO> getMatricula(@PathVariable final Long id) {
		final MatriculaDTO matricula = this.matriculaController.getMatricula(id);
		if (matricula.equals(MatriculaDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(matricula, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MatriculaDTO> removeMatricula(@PathVariable final Long id) {
		final MatriculaDTO removedMatricula = this.matriculaController.removeMatricula(id);
		if (removedMatricula.equals(MatriculaDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(removedMatricula, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MatriculaDTO> updateMatricula(@PathVariable final Long id, @RequestBody final MatriculaDTO matricula) {
		final MatriculaDTO oldMatricula = this.matriculaController.updateMatricula(id, matricula);
		if (oldMatricula.equals(MatriculaDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(oldMatricula, HttpStatus.OK);
	}

	@PostMapping
	public Long insertMatricula(@RequestBody final MatriculaDTO matricula) {
		return this.matriculaController.insertMatricula(matricula);
	}
}
