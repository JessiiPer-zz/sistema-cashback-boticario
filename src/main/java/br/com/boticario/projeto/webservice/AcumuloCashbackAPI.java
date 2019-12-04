package br.com.boticario.projeto.webservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.boticario.projeto.dto.AcumuloCashbackDTO;

@FeignClient(name = "api-externa-boticario-acumulo-cashback", url = "${webservice.api.boticario.acumulo.cashback}")
public interface AcumuloCashbackAPI {
			
	@RequestMapping(method = RequestMethod.GET, produces = {"application/JSON"}, headers = "ZXPURQOARHiMc6Y0flhRC1LVlZQVFRnm")
	AcumuloCashbackDTO getAcumuloCashback(@RequestParam Long cpf);
}


