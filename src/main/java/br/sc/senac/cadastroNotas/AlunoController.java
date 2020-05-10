package br.sc.senac.cadastroNotas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
public class AlunoController {

	private final AlunoRepository alunoRepository;

	AlunoController(final AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	private static void updateEntityFromDTO(final AlunoDTO alunoDTO, final  AlunoEntity alunoEntity) {
		alunoEntity.setnomeAluno(alunoDTO.getnomeAluno());
		alunoEntity.setcpf(alunoDTO.getcpf());
	}

	private static AlunoEntity toEntity(final AlunoDTO alunoDTO) {
		final String nomeAluno = alunoDTO.getnomeAluno();
		final String cpf = alunoDTO.getcpf();
		return new AlunoEntity(nomeAluno, cpf);
	}

	private static AlunoDTO toDTO(final AlunoEntity alunoEntity) {
		final Long id = alunoEntity.getalunoId();
		final String nomeAluno = alunoEntity.getnomeAluno();
		final String cpf = alunoEntity.getcpf();
		return new AlunoDTO(id, nomeAluno, cpf);
	}

	List<AlunoDTO> getAllAlunos() {
		final List<AlunoDTO> aluno = new ArrayList<>();

		final Iterable<AlunoEntity> entities = this.alunoRepository.findAll();
		for (final AlunoEntity alunoEntity : entities) {
			AlunoDTO alunoDTO = AlunoController.toDTO(alunoEntity);
			aluno.add(alunoDTO);

		}
		return aluno;
	}

	AlunoDTO getAluno(final Long id) {
		final Optional<AlunoEntity> optionalAluno = this.alunoRepository.findById(id);
		if (optionalAluno.isPresent()) {
			AlunoEntity alunoEntity = optionalAluno.get();
			return AlunoController.toDTO(alunoEntity);
		}
		return AlunoDTO.NULL_VALUE;
	}

	AlunoDTO removeAluno(final Long id) {
		final Optional<AlunoEntity> optionalAluno = this.alunoRepository.findById(id);
		if (optionalAluno.isPresent()) {
			final AlunoEntity alunoEntity = optionalAluno.get();
			this.alunoRepository.delete(alunoEntity);
			return AlunoController.toDTO(alunoEntity);
		}
		return AlunoDTO.NULL_VALUE;
	}

	Long insertAluno(final AlunoDTO alunoDTO) {
		final AlunoEntity alunoEntity = AlunoController.toEntity(alunoDTO);
		this.alunoRepository.save(alunoEntity);
		return alunoEntity.getalunoId();
	}

	AlunoDTO updateAluno(final Long id, final AlunoDTO alunoDTO) {
		final Optional<AlunoEntity> optionalAluno = this.alunoRepository.findById(id);
		if (optionalAluno.isPresent()) {
			final AlunoEntity alunoEntity = optionalAluno.get();
			final AlunoDTO oldAlunoDTO = AlunoController.toDTO(alunoEntity);
			AlunoController.updateEntityFromDTO(alunoDTO, alunoEntity);
			this.alunoRepository.save(alunoEntity);
			return oldAlunoDTO;
		}
		return AlunoDTO.NULL_VALUE;
	}
}
