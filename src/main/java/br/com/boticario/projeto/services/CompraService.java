package br.com.boticario.projeto.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.boticario.projeto.entities.Compra;
import br.com.boticario.projeto.entities.enums.CompraStatus;
import br.com.boticario.projeto.repositories.CompraRepository;
import br.com.boticario.projeto.services.exception.DatabaseException;
import br.com.boticario.projeto.services.exception.ResourceNotFoundException;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;

	public List<Compra> findAll() {
		return compraRepository.findAll();
	}

	public Compra findById(Long id) {

		Optional<Compra> user = compraRepository.findById(id);
		return user.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Compra insert(Compra compra) {
		CompraStatus aux = CompraStatus.EM_VALIDAÇÃO;
		aux = compra.getRevendedor() != "153.509.460-56" ? CompraStatus.EM_VALIDAÇÃO : CompraStatus.APROVADO;
		compra.setCompraStatus(aux);
		compra.setData(Instant.now());
		return compraRepository.save(compra);
	}

	public Compra update(Long id, Compra compra) {
		try {
			Compra entity = compraRepository.getOne(id);
			if (entity.getCompraStatus().getCode() == 1) {
				updateData(entity, compra);
				return compraRepository.save(entity);
			} else
				throw new DatabaseException("Não é possível editar uma compra com o status 'Aprovado'."); // TODO
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public void delete(Long id) {
		try {
			compraRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("");
		}
		
	}

	private void updateData(Compra entity, Compra compra) {
		entity.setCodigo(compra.getCodigo());
		entity.setValor(compra.getValor());
	}
}
