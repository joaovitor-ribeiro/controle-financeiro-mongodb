package controlefinanceiro.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import controlefinanceiro.dto.ganho.GanhoEntrada;
import controlefinanceiro.dto.ganho.GanhoSaida;
import controlefinanceiro.exception.ValidationException;
import controlefinanceiro.model.Categoria;
import controlefinanceiro.model.Ganho;
import controlefinanceiro.repository.CategoriaRepository;
import controlefinanceiro.repository.GanhoCustomRepository;
import controlefinanceiro.repository.GanhoRepository;
import controlefinanceiro.utils.Identification;

@Service
public class GanhoService {

	private final GanhoRepository ganhoRepository;
	
	private final GanhoCustomRepository ganhoCustomRepository;
	
	private final CategoriaRepository categoriaRepository;
	
	@Autowired
	public GanhoService(GanhoRepository ganhoRepository, 
						GanhoCustomRepository ganhoCustomRepository,
						CategoriaRepository categoriaRepository) {
		super();
		this.ganhoRepository       = ganhoRepository;
		this.ganhoCustomRepository = ganhoCustomRepository;
		this.categoriaRepository   = categoriaRepository;
	}

	public GanhoSaida inserir(GanhoEntrada entrada) {		
		// I N S E R T
		Categoria categoria = categoriaRepository.findById(entrada.categoria_id()).orElseThrow(() -> new ValidationException("Categoria não encontrada!"));
		Integer id          = Identification.getId(ganhoRepository);
		Ganho ganho         = ganhoRepository.insert(new Ganho(id, entrada, categoria));
		
		// S A Í D A
		return new GanhoSaida(ganho);
	}

	public GanhoSaida editar(Integer id, GanhoEntrada ganhoNovo) {
		// S A V E 
		Ganho ganho         = ganhoRepository.findById(id).orElseThrow(() -> new ValidationException("Ganho não foi encontrado para edição!"));
		Categoria categoria = categoriaRepository.findById(ganhoNovo.categoria_id()).orElseThrow(() -> new ValidationException("Categoria não encontrada!"));
		
		ganho.setDescricao(ganhoNovo.descricao());
		ganho.setCategoria(categoria);
		ganho.setValor(ganhoNovo.valor());
		ganho.setData(ganhoNovo.data());
		
		Ganho ganhoSalvo = ganhoRepository.save(ganho);
		
		// S A Í D A
		return new GanhoSaida(ganhoSalvo);
	}

	public void excluir(Integer id) {
		Ganho ganho = ganhoRepository.findById(id).orElseThrow(() -> new ValidationException("Ganho não foi encontrado para exclusão!"));
		ganhoRepository.delete(ganho);
	}

	public GanhoSaida retornarGanhoId(Integer id) {
		Ganho ganho = ganhoRepository.findById(id).orElseThrow(() -> new ValidationException("Ganho não encontrado!"));
		return new GanhoSaida(ganho);
	}
	
	public Page<GanhoSaida> listar(String descricao, List<Integer> categorias, Date dataInicial, Date dataFinal, Pageable paginacao) {
		if ( (descricao == null || descricao.isEmpty()) && (categorias == null || categorias.isEmpty()) && dataInicial == null && dataFinal == null )  {
			return ganhoCustomRepository.findAll(paginacao);
		}
		return ganhoCustomRepository.listar(descricao, categorias, dataInicial, dataFinal, paginacao);				
	}
	
}
