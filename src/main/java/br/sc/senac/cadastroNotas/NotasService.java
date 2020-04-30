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
@RequestMapping("/api/v1/notas")
public class NotasService {

	private static final NotasDTO[] DEFAULT_NOTAS = new NotasDTO[] {
			new NotasDTO(Long.valueOf(0), "Geografia","João", Double.valueOf(5)),
			new NotasDTO(Long.valueOf(0), "Biologia","Pedro", Double.valueOf(6)),
			new NotasDTO(Long.valueOf(0), "Literatura","Marcos", Double.valueOf(7)) };

	private final NotasController notasController;

	NotasService(final NotasController notasController) {
		this.notasController = notasController;
		Arrays.asList(NotasService.DEFAULT_NOTAS).forEach(dto -> this.notasController.insertNotas(dto));
	}

	@GetMapping("/list")
	public List<NotasDTO> list() {
		return this.notasController.getAllNotas();
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<NotasDTO> getNotas(@PathVariable final Long id) {
		final NotasDTO notas = this.notasController.getNotas(id);
		if (notas.equals(NotasDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(notas, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<NotasDTO> removeNotas(@PathVariable final Long id) {
		final NotasDTO removedNotas = this.notasController.removeNotas(id);
		if (removedNotas.equals(NotasDTO.NULL_VALUE)) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(removedNotas, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<NotasDTO> updateNotas(@PathVariable final Long id, @RequestBody final NotasDTO notas) {
		final NotasDTO oldNotas = this.notasController.updateNotas(id, notas);
		if (oldNotas.equals(NotasDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(oldNotas, HttpStatus.OK);
	}

	@PostMapping
	public Long insertNotas(@RequestBody final NotasDTO notas) {
		return this.notasController.insertNotas(notas);
	}
}
