package br.sc.senac.cadastroNotas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
final class NotasController {

	private final NotasRepository notasRepository;

	NotasController(final NotasRepository notasRepository) {
		this.notasRepository = notasRepository;
	}

	private static void updateEntityFromDTO(final NotasDTO notasDTO, final NotasEntity notasEntity) {
		notasEntity.setMateria(notasDTO.getMateria());
		notasEntity.setNome(notasDTO.getNome());
		notasEntity.setNota(notasDTO.getNota());
	}

	private static NotasEntity toEntity(final NotasDTO notasDTO) {
		final String materia = notasDTO.getMateria();
		final String nome = notasDTO.getNome();
		final Double nota = notasDTO.getNota();
		return new NotasEntity(materia, nome, nota);
	}

	private static NotasDTO toDTO(final NotasEntity notasEntity) {
		final Long id = notasEntity.getId();
		final String materia = notasEntity.getMateria();
		final String nome = notasEntity.getNome();
		final Double nota = notasEntity.getNota();
		return new NotasDTO(id, materia, nome, nota);
	}

	List<NotasDTO> getAllNotas() {
		final List<NotasDTO> notas = new ArrayList<>();

		final Iterable<NotasEntity> entities = this.notasRepository.findAll();
		for (final NotasEntity notasEntity : entities) {
			NotasDTO notasDTO = NotasController.toDTO(notasEntity);
			notas.add(notasDTO);

		}
		return notas;
	}

	NotasDTO getNotas(final Long id) {
		final Optional<NotasEntity> optionalNotas = this.notasRepository.findById(id);
		if (optionalNotas.isPresent()) {
			NotasEntity notasEntity = optionalNotas.get();
			return NotasController.toDTO(notasEntity);
		}
		return NotasDTO.NULL_VALUE;
	}

	NotasDTO removeNotas(final Long id) {
		final Optional<NotasEntity> optionalNotas = this.notasRepository.findById(id);
		if (optionalNotas.isPresent()) {
			final NotasEntity notasEntity = optionalNotas.get();
			this.notasRepository.delete(notasEntity);
			return NotasController.toDTO(notasEntity);
		}
		return NotasDTO.NULL_VALUE;
	}

	Long insertNotas(final NotasDTO notasDTO) {
		final NotasEntity notasEntity = NotasController.toEntity(notasDTO);
		this.notasRepository.save(notasEntity);
		return notasEntity.getId();
	}

	NotasDTO updateNotas(final Long id, final NotasDTO notasDTO) {
		final Optional<NotasEntity> optionalNotas = this.notasRepository.findById(id);
		if (optionalNotas.isPresent()) {
			final NotasEntity notasEntity = optionalNotas.get();
			final NotasDTO oldNotasDTO = NotasController.toDTO(notasEntity);
			NotasController.updateEntityFromDTO(notasDTO, notasEntity);
			this.notasRepository.save(notasEntity);
			return oldNotasDTO;
		}
		return NotasDTO.NULL_VALUE;
	}

}
