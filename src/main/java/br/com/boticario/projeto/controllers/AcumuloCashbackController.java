package br.com.boticario.projeto.controllers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.boticario.projeto.dto.AcumuloCashbackDTO;
import br.com.boticario.projeto.services.AcumuloCashbackService;

@RestController
@RequestMapping(value="/cashback")
public class AcumuloCashbackController {
	
	@Autowired
	private AcumuloCashbackService acumuloCashbackService;
	
	@GetMapping
	public AcumuloCashbackDTO exibirAcumuloCashback(@RequestParam(value = "cpf", required = true) Long cpf) throws ParseException{
		return acumuloCashbackService.cashback(cpf);
		
	}

}
