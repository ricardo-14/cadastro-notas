package br.sc.senac.cadastroNotas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class MatriculaController {
	private final MatriculaRepository matriculaRepository;

	MatriculaController(final MatriculaRepository matriculaRepository) {
		this.matriculaRepository = matriculaRepository;
	}

	private static void updateEntityFromDTO(final MatriculaDTO matriculaDTO, final MatriculaEntity matriculaEntity) {
		matriculaEntity.setnumeroMatricula(matriculaDTO.getnumeroMatricula());
	}

	private static MatriculaEntity toEntity(final MatriculaDTO matriculaDTO) {
		final Long numeroMatricula = matriculaDTO.getnumeroMatricula();
		return new MatriculaEntity(numeroMatricula);
	}

	private static MatriculaDTO toDTO(final MatriculaEntity matriculaEntity) {
		final Long id = matriculaEntity.getmatriculaId();
		final Long numeroMatricula = matriculaEntity.getnumeroMatricula();
		return new MatriculaDTO(id, numeroMatricula);
	}

	List<MatriculaDTO> getAllMatriculas() {
		final List<MatriculaDTO> matricula = new ArrayList<>();

		final Iterable<MatriculaEntity> entities = this.matriculaRepository.findAll();
		for (final MatriculaEntity matriculaEntity : entities) {
			MatriculaDTO matriculaDTO = MatriculaController.toDTO(matriculaEntity);
			matricula.add(matriculaDTO);

		}
		return matricula;
	}

	MatriculaDTO getMatricula(final Long id) {
		final Optional<MatriculaEntity> optionalMatricula = this.matriculaRepository.findById(id);
		if (optionalMatricula.isPresent()) {
			MatriculaEntity matriculaEntity = optionalMatricula.get();
			return MatriculaController.toDTO(matriculaEntity);
		}
		return MatriculaDTO.NULL_VALUE;
	}

	MatriculaDTO removeMatricula(final Long id) {
		final Optional<MatriculaEntity> optionalMatricula = this.matriculaRepository.findById(id);
		if (optionalMatricula.isPresent()) {
			final MatriculaEntity matriculaEntity = optionalMatricula.get();
			this.matriculaRepository.delete(matriculaEntity);
			return MatriculaController.toDTO(matriculaEntity);
		}
		return MatriculaDTO.NULL_VALUE;
	}

	Long insertMatricula(final MatriculaDTO matriculaDTO) {
		final MatriculaEntity matriculaEntity = MatriculaController.toEntity(matriculaDTO);
		this.matriculaRepository.save(matriculaEntity);
		return matriculaEntity.getmatriculaId();
	}

	MatriculaDTO updateMatricula(final Long id, final MatriculaDTO matriculaDTO) {
		final Optional<MatriculaEntity> optionalMatricula = this.matriculaRepository.findById(id);
		if (optionalMatricula.isPresent()) {
			final MatriculaEntity matriculaEntity = optionalMatricula.get();
			final MatriculaDTO oldMatriculaDTO = MatriculaController.toDTO(matriculaEntity);
			MatriculaController.updateEntityFromDTO(matriculaDTO, matriculaEntity);
			this.matriculaRepository.save(matriculaEntity);
			return oldMatriculaDTO;
		}
		return MatriculaDTO.NULL_VALUE;
	}
	


}
