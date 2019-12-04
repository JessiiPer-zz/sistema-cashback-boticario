package br.com.boticario.projeto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.boticario.projeto.dto.AcumuloCashbackDTO;
import br.com.boticario.projeto.webservice.AcumuloCashbackAPI;

@Service
public class AcumuloCashbackService {

	@Autowired
	private AcumuloCashbackAPI apiExterna;
	
	public AcumuloCashbackDTO cashback(Long cpf) {
			
		return apiExterna.getAcumuloCashback(cpf);
	}

}
