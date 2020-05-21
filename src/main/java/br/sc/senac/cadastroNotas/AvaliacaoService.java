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
@RequestMapping("/api/v1/avaliacao")
public class AvaliacaoService {
	
	private static final AvaliacaoDTO[] DEFAULT_AVALIACAO = new AvaliacaoDTO[] {
			new AvaliacaoDTO(Long.valueOf(0), "AV1","Avaliação unidade 1", "01/01/2020"),
			new AvaliacaoDTO(Long.valueOf(0), "AV2","Avaliação unidade 2", "02/01/2020"),
			new AvaliacaoDTO(Long.valueOf(0), "AV3","Avaliação unidade 3", "03/01/2020") };

	private final AvaliacaoController avaliacaoController;

	AvaliacaoService(final AvaliacaoController avaliacaoController) {
		this.avaliacaoController = avaliacaoController;
		Arrays.asList(AvaliacaoService.DEFAULT_AVALIACAO).forEach(dto -> this.avaliacaoController.insertAvaliacao(dto));
	}

	@GetMapping("/list")
	public List<AvaliacaoDTO> list() {
		return this.avaliacaoController.getAllAvaliacoes();
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<AvaliacaoDTO> getAvaliacao(@PathVariable final Long id) {
		final AvaliacaoDTO avaliacao = this.avaliacaoController.getAvaliacao(id);
		if (avaliacao.equals(AvaliacaoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(avaliacao, HttpStatus.OK);
	}

	@DeleteMapping("/del/{id}")
	public ResponseEntity<AvaliacaoDTO> removeAvaliacao(@PathVariable final Long id) {
		final AvaliacaoDTO removedAvaliacao = this.avaliacaoController.removeAvaliacao(id);
		if (removedAvaliacao.equals(AvaliacaoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(removedAvaliacao, HttpStatus.OK);
	}

	@PutMapping("/att/{id}")
	public ResponseEntity<AvaliacaoDTO> updateAvaliacao(@PathVariable final Long id, @RequestBody final AvaliacaoDTO avaliacao) {
		final AvaliacaoDTO oldAvaliacao = this.avaliacaoController.updateAvaliacao(id, avaliacao);
		if (oldAvaliacao.equals(AvaliacaoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(oldAvaliacao, HttpStatus.OK);
	}

	@PostMapping("/add")
	public Long insertAvaliacao(@RequestBody final AvaliacaoDTO avaliacao) {
		return this.avaliacaoController.insertAvaliacao(avaliacao);
	}

}
