package br.sc.senac.cadastroNotas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
public class CursoController {
	private final CursoRepository cursoRepository;

	CursoController(final CursoRepository cursoRepository) {
		this.cursoRepository = cursoRepository;
	}

	private static void updateEntityFromDTO(final CursoDTO cursoDTO, final CursoEntity cursoEntity) {
		cursoEntity.setnomeCurso(cursoDTO.getnomeCurso());
		cursoEntity.setperiodoCurso(cursoDTO.getperiodoCurso());
		cursoEntity.setcargaHoraria(cursoDTO.getcargaHoraria());
	}

	private static CursoEntity toEntity(final CursoDTO cursoDTO) {
		final String nomeCurso = cursoDTO.getnomeCurso();
		final String periodoCurso = cursoDTO.getperiodoCurso();
		final Long cargaHoraria = cursoDTO.getcargaHoraria();
		return new CursoEntity(nomeCurso, periodoCurso, cargaHoraria);
	}

	private static CursoDTO toDTO(final CursoEntity cursoEntity) {
		final Long id = cursoEntity.getcursoId();
		final String nomeCurso = cursoEntity.getnomeCurso();
		final String periodoCurso = cursoEntity.getperiodoCurso();
		final Long cargaHoraria = cursoEntity.getcargaHoraria();
		return new CursoDTO(id, nomeCurso, periodoCurso, cargaHoraria);
	}

	List<CursoDTO> getAllCursos() {
		final List<CursoDTO> curso = new ArrayList<>();

		final Iterable<CursoEntity> entities = this.cursoRepository.findAll();
		for (final CursoEntity cursoEntity : entities) {
			CursoDTO cursoDTO = CursoController.toDTO(cursoEntity);
			curso.add(cursoDTO);

		}
		return curso;
	}

	CursoDTO getCurso(final Long id) {
		final Optional<CursoEntity> optionalCurso = this.cursoRepository.findById(id);
		if (optionalCurso.isPresent()) {
			CursoEntity cursoEntity = optionalCurso.get();
			return CursoController.toDTO(cursoEntity);
		}
		return CursoDTO.NULL_VALUE;
	}

	CursoDTO removeCurso(final Long id) {
		final Optional<CursoEntity> optionalCurso = this.cursoRepository.findById(id);
		if (optionalCurso.isPresent()) {
			final CursoEntity cursoEntity = optionalCurso.get();
			this.cursoRepository.delete(cursoEntity);
			return CursoController.toDTO(cursoEntity);
		}
		return CursoDTO.NULL_VALUE;
	}

	Long insertCurso(final CursoDTO cursoDTO) {
		final CursoEntity cursoEntity = CursoController.toEntity(cursoDTO);
		this.cursoRepository.save(cursoEntity);
		return cursoEntity.getcursoId();
	}

	CursoDTO updateCurso(final Long id, final CursoDTO cursoDTO) {
		final Optional<CursoEntity> optionalCurso = this.cursoRepository.findById(id);
		if (optionalCurso.isPresent()) {
			final CursoEntity cursoEntity = optionalCurso.get();
			final CursoDTO oldCursoDTO = CursoController.toDTO(cursoEntity);
			CursoController.updateEntityFromDTO(cursoDTO, cursoEntity);
			this.cursoRepository.save(cursoEntity);
			return oldCursoDTO;
		}
		return CursoDTO.NULL_VALUE;
	}
	

}
