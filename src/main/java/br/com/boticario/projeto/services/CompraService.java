package br.com.boticario.projeto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.boticario.projeto.entities.Compra;
import br.com.boticario.projeto.entities.enums.CompraStatus;
import br.com.boticario.projeto.repositories.CompraRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	public List<Compra> findAll(){
		return compraRepository.findAll();
	}
	
	public Compra insert(Compra compra){
		CompraStatus aux = CompraStatus.EM_VALIDAÇÃO;
		aux = compra.getRevendedor() != "153.509.460-56" ? CompraStatus.EM_VALIDAÇÃO : CompraStatus.APROVADO;
		compra.setCompraStatus(aux);
		return compraRepository.save(compra);
	}
}
