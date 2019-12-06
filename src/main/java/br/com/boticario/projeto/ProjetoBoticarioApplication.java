package br.com.boticario.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan("br.com.boticario.projeto")
public class ProjetoBoticarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoBoticarioApplication.class, args);
	}
}
