package br.sc.senac.cadastroNotas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class NotaController {
	
	private final NotaRepository notaRepository;

	NotaController(final NotaRepository notaRepository) {
		this.notaRepository = notaRepository;
	}

	private static void updateEntityFromDTO(final NotaDTO notaDTO, final  NotaEntity notaEntity) {
		notaEntity.setnota(notaDTO.getnota());
	}

	private static NotaEntity toEntity(final NotaDTO notaDTO) {
		final Double nota = notaDTO.getnota();
		return new NotaEntity(nota);
	}

	private static NotaDTO toDTO(final NotaEntity notaEntity) {
		final Long id = notaEntity.getnotaId();
		final Double nota = notaEntity.getnota();
		return new NotaDTO(id, nota);
	}

	List<NotaDTO> getAllNotas() {
		final List<NotaDTO> nota = new ArrayList<>();

		final Iterable<NotaEntity> entities = this.notaRepository.findAll();
		for (final NotaEntity notaEntity : entities) {
			NotaDTO notaDTO = NotaController.toDTO(notaEntity);
			nota.add(notaDTO);

		}
		return nota;
	}

	NotaDTO getNota(final Long id) {
		final Optional<NotaEntity> optionalNota = this.notaRepository.findById(id);
		if (optionalNota.isPresent()) {
			NotaEntity notaEntity = optionalNota.get();
			return NotaController.toDTO(notaEntity);
		}
		return NotaDTO.NULL_VALUE;
	}

	NotaDTO removeNota(final Long id) {
		final Optional<NotaEntity> optionalNota = this.notaRepository.findById(id);
		if (optionalNota.isPresent()) {
			final NotaEntity notaEntity = optionalNota.get();
			this.notaRepository.delete(notaEntity);
			return NotaController.toDTO(notaEntity);
		}
		return NotaDTO.NULL_VALUE;
	}

	Long insertNota(final NotaDTO notaDTO) {
		final NotaEntity notaEntity = NotaController.toEntity(notaDTO);
		this.notaRepository.save(notaEntity);
		return notaEntity.getnotaId();
	}

	NotaDTO updateNota(final Long id, final NotaDTO notaDTO) {
		final Optional<NotaEntity> optionalNota = this.notaRepository.findById(id);
		if (optionalNota.isPresent()) {
			final NotaEntity notaEntity = optionalNota.get();
			final NotaDTO oldNotaDTO = NotaController.toDTO(notaEntity);
			NotaController.updateEntityFromDTO(notaDTO, notaEntity);
			this.notaRepository.save(notaEntity);
			return oldNotaDTO;
		}
		return NotaDTO.NULL_VALUE;
	}

}
