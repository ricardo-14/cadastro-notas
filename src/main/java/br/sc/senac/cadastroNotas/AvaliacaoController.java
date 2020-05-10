package br.sc.senac.cadastroNotas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
public class AvaliacaoController {
	
	private final AvaliacaoRepository avaliacaoRepository;

	AvaliacaoController(final AvaliacaoRepository avaliacaoRepository) {
		this.avaliacaoRepository = avaliacaoRepository;
	}

	private static void updateEntityFromDTO(final AvaliacaoDTO avaliacaoDTO, final AvaliacaoEntity avaliacaoEntity) {
		avaliacaoEntity.settituloAvaliacao(avaliacaoDTO.gettituloAvaliacao());
		avaliacaoEntity.setdescricaoAvaliacao(avaliacaoDTO.getdescricaoAvaliacao());
		avaliacaoEntity.setdataAvaliacao(avaliacaoDTO.getdataAvaliacao());
	}

	private static AvaliacaoEntity toEntity(final AvaliacaoDTO avaliacaoDTO) {
		final String tituloAvaliacao = avaliacaoDTO.gettituloAvaliacao();
		final String descricaoAvaliacao = avaliacaoDTO.getdescricaoAvaliacao();
		final String dataAvaliacao = avaliacaoDTO.getdataAvaliacao();
		return new AvaliacaoEntity(tituloAvaliacao, descricaoAvaliacao, dataAvaliacao);
	}

	private static AvaliacaoDTO toDTO(final AvaliacaoEntity avaliacaoEntity) {
		final Long id = avaliacaoEntity.getavaliacaoId();
		final String tituloAvaliacao = avaliacaoEntity.gettituloAvaliacao();
		final String descricaoAvaliacao = avaliacaoEntity.getdescricaoAvaliacao();
		final String dataAvaliacao = avaliacaoEntity.getdataAvaliacao();
		return new  AvaliacaoDTO(id, tituloAvaliacao, descricaoAvaliacao, dataAvaliacao);
	}

	List<AvaliacaoDTO> getAllAvaliacoes() {
		final List<AvaliacaoDTO> avaliacao = new ArrayList<>();

		final Iterable<AvaliacaoEntity> entities = this.avaliacaoRepository.findAll();
		for (final AvaliacaoEntity avaliacaoEntity : entities) {
			AvaliacaoDTO avaliacaoDTO = AvaliacaoController.toDTO(avaliacaoEntity);
			avaliacao.add(avaliacaoDTO);

		}
		return avaliacao;
	}

	AvaliacaoDTO getAvaliacao(final Long id) {
		final Optional<AvaliacaoEntity> optionalAvaliacao = this.avaliacaoRepository.findById(id);
		if (optionalAvaliacao.isPresent()) {
			AvaliacaoEntity avaliacaoEntity = optionalAvaliacao.get();
			return AvaliacaoController.toDTO(avaliacaoEntity);
		}
		return AvaliacaoDTO.NULL_VALUE;
	}

	AvaliacaoDTO removeAvaliacao(final Long id) {
		final Optional<AvaliacaoEntity> optionalAvaliacao = this.avaliacaoRepository.findById(id);
		if (optionalAvaliacao.isPresent()) {
			final AvaliacaoEntity avaliacaoEntity = optionalAvaliacao.get();
			this.avaliacaoRepository.delete(avaliacaoEntity);
			return AvaliacaoController.toDTO(avaliacaoEntity);
		}
		return AvaliacaoDTO.NULL_VALUE;
	}

	Long insertAvaliacao(final AvaliacaoDTO avaliacaoDTO) {
		final AvaliacaoEntity avaliacaoEntity = AvaliacaoController.toEntity(avaliacaoDTO);
		this.avaliacaoRepository.save(avaliacaoEntity);
		return avaliacaoEntity.getavaliacaoId();
	}

	AvaliacaoDTO updateAvaliacao(final Long id, final AvaliacaoDTO avaliacaoDTO) {
		final Optional<AvaliacaoEntity> optionalAvaliacao = this.avaliacaoRepository.findById(id);
		if (optionalAvaliacao.isPresent()) {
			final AvaliacaoEntity avaliacaoEntity = optionalAvaliacao.get();
			final AvaliacaoDTO oldAvaliacaoDTO = AvaliacaoController.toDTO(avaliacaoEntity);
			AvaliacaoController.updateEntityFromDTO(avaliacaoDTO, avaliacaoEntity);
			this.avaliacaoRepository.save(avaliacaoEntity);
			return oldAvaliacaoDTO;
		}
		return AvaliacaoDTO.NULL_VALUE;
	}
	

}
