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
@RequestMapping("/api/v1/nota")
public class NotaService {
	
	private static final NotaDTO[] DEFAULT_NOTA = new NotaDTO[] {
			new NotaDTO(Long.valueOf(0),Double.valueOf(8)),
			new NotaDTO(Long.valueOf(0),Double.valueOf(7)),
			new NotaDTO(Long.valueOf(0),Double.valueOf(6)) };

	private final NotaController notaController;

	NotaService(final NotaController notaController) {
		this.notaController = notaController;
		Arrays.asList(NotaService.DEFAULT_NOTA).forEach(dto -> this.notaController.insertNota(dto));
	}

	@GetMapping("/list")
	public List<NotaDTO> list() {
		return this.notaController.getAllNotas();
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<NotaDTO> getNota(@PathVariable final Long id) {
		final NotaDTO nota = this.notaController.getNota(id);
		if (nota.equals(NotaDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(nota, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<NotaDTO> removeNota(@PathVariable final Long id) {
		final NotaDTO removedNota = this.notaController.removeNota(id);
		if (removedNota.equals(NotaDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(removedNota, HttpStatus.OK);
	}

	@PutMapping("/att/{id}")
	public ResponseEntity<NotaDTO> updateNota(@PathVariable final Long id, @RequestBody final NotaDTO nota) {
		final NotaDTO oldNota = this.notaController.updateNota(id, nota);
		if (oldNota.equals(NotaDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(oldNota, HttpStatus.OK);
	}

	@PostMapping("/add")
	public Long insertNota(@RequestBody final NotaDTO nota) {
		return this.notaController.insertNota(nota);
	}


}
