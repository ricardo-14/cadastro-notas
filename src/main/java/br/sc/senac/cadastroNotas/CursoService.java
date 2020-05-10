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
@RequestMapping("/api/v1/curso")
public class CursoService {
	
	private static final CursoDTO[] DEFAULT_CURSO = new CursoDTO[] {
			new CursoDTO(Long.valueOf(0), "Direito","Matutino", Long.valueOf(1100)),
			new CursoDTO(Long.valueOf(0), "Biologia","Vespertino", Long.valueOf(2000)),
			new CursoDTO(Long.valueOf(0), "Informatica","Noturno", Long.valueOf(800)) };

	private final CursoController cursoController;

	CursoService(final CursoController cursoController) {
		this.cursoController = cursoController;
		Arrays.asList(CursoService.DEFAULT_CURSO).forEach(dto -> this.cursoController.insertCurso(dto));
	}

	@GetMapping("/list")
	public List<CursoDTO> list() {
		return this.cursoController.getAllCursos();
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<CursoDTO> getCurso(@PathVariable final Long id) {
		final CursoDTO curso = this.cursoController.getCurso(id);
		if (curso.equals(CursoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(curso, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CursoDTO> removeCurso(@PathVariable final Long id) {
		final CursoDTO removedCurso = this.cursoController.removeCurso(id);
		if (removedCurso.equals(CursoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(removedCurso, HttpStatus.OK);
	}

	@PutMapping("/att/{id}")
	public ResponseEntity<CursoDTO> updateCurso(@PathVariable final Long id, @RequestBody final CursoDTO curso) {
		final CursoDTO oldCurso = this.cursoController.updateCurso(id, curso);
		if (oldCurso.equals(CursoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(oldCurso, HttpStatus.OK);
	}

	@PostMapping("/add")
	public Long insertCurso(@RequestBody final CursoDTO curso) {
		return this.cursoController.insertCurso(curso);
	}

}
