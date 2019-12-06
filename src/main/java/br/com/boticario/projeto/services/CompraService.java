package br.com.boticario.projeto.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.boticario.projeto.dto.CompraDTO;
import br.com.boticario.projeto.entities.Compra;
import br.com.boticario.projeto.entities.Revendedor;
import br.com.boticario.projeto.entities.enums.CompraStatus;
import br.com.boticario.projeto.mappers.CompraMapper;
import br.com.boticario.projeto.repositories.CompraRepository;
import br.com.boticario.projeto.repositories.RevendedorRepository;
import br.com.boticario.projeto.services.exception.DatabaseException;
import br.com.boticario.projeto.services.exception.ResourceNotFoundException;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private RevendedorRepository revendedorRepository;
	
	@Autowired
	private CompraMapper mapper;
	
	private Double porcentagem;
	private Double valorCashback;
	
	

	Calendar date = Calendar.getInstance();
	
	public Page<CompraDTO> listaCompras(Pageable pageable, String email) throws ParseException {
		
		Revendedor revendedor = revendedorRepository.findByEmail(email);
		
		List<CompraDTO> response = new ArrayList<>();
		CompraMapper mapper = new CompraMapper();
		
		List<Compra> lista = compraRepository.findByRevendedor_cpf(revendedor.getCpf());
		
		for(Compra obj : lista) {
			CompraDTO dto = new CompraDTO();
			response.add(mapper.compraEntityParaDTO(obj, dto));
		}
		
		int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > response.size() ? response.size() : (start + pageable.getPageSize());
		Page<CompraDTO> pages = new PageImpl<CompraDTO>(response.subList(start, end), pageable, response.size());
		
		return pages;
	}

	public Compra findById(Long id) {

		Optional<Compra> user = compraRepository.findById(id);
		return user.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public List<CompraDTO> insert(List<Compra> compra) throws ParseException {
		
		List<CompraDTO> response = new ArrayList<>();
		boolean verificaData = false;
		
		for(Compra obj : compra) {
			CompraDTO dto = new CompraDTO();
			CompraStatus aux = CompraStatus.EM_VALIDAÇÃO;
			aux = "15350946056".equals(obj.getRevendedor().replaceAll("[.-]", "")) ? CompraStatus.APROVADO : CompraStatus.EM_VALIDAÇÃO;
			obj.setValorCashback(calculaValorCashback(obj.getValor()));
			obj.setPorcentagemCashback(getPorcentagem());
			obj.setCompraStatus(aux);
			verificaData = obj.getData() == null? true : false;
			if(verificaData) {
				obj.setData(date);
			}
			Compra entity = compraRepository.save(obj);
			response.add(mapper.compraEntityParaDTO(entity, dto));
						
		}
		
		return response;
	}

	public Compra update(Long id, Compra compra) {
		
		if(id!=compra.getId()) {
			throw new DatabaseException("Id da url é diferente do body");
		}
		try {
			Compra entity = compraRepository.getOne(id);
			if (entity.getCompraStatus().getCode() == 1) {
				updateData(entity, compra);
				return compraRepository.save(entity);
			} else
				throw new DatabaseException("Não é possível editar uma compra com o status 'Aprovado'.");
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public void delete(Long id) {
		try {
			Optional<Compra> compra = compraRepository.findById(id);
			if(compra.get().getCompraStatus().getCode() == 1) {
				compraRepository.deleteById(id);
			}else throw new DatabaseException("Não é possível excluir uma compra com o status 'Aprovado'."); 
		
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void updateData(Compra entity, Compra compra) {
		entity.setCodigo(compra.getCodigo());
		entity.setValor(compra.getValor());
		entity.setValorCashback(calculaValorCashback(compra.getValor()));
		entity.setPorcentagemCashback(getPorcentagem());
		
	}
	
	private Double calculaValorCashback(Double valor) {
		if(valor <= 1000) {
			setPorcentagem(0.1);
			calculaCashback(valor,getPorcentagem());
			return valorCashback;
		}else if(valor > 1000 && valor <= 1500) {
			porcentagem = 0.15;
			calculaCashback(valor,porcentagem);
			return valorCashback;
		} else {
			porcentagem = 0.20;
			calculaCashback(valor,porcentagem);
			return valorCashback;
		}
		
	}
	
	private Double calculaCashback(Double valor, Double porcentagem) {
		valorCashback =  valor*porcentagem;
		return valorCashback;
	}

	public Double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(Double porcentagem) {
		this.porcentagem = porcentagem;
	}
}
