package br.com.leonardovechieti.vendasproject;

import br.com.leonardovechieti.vendasproject.model.Cliente;
import br.com.leonardovechieti.vendasproject.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.leonardovechieti.vendasproject"})
@RestController
public class VendasProjectApplication {


	@GetMapping("/hello")
	public String helloWorld(){
		return "hello world";
	}

	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository repository){
		return args -> {
			System.out.println("Salvando cliente");
			Cliente cliente = new Cliente();
			cliente.setNome("Leonardo");
			repository.save(cliente);
			System.out.println("Cliente salvo com sucesso");
//			deletar
			repository.deleteId(1);

		};
	}















	public static void main(String[] args) {
		SpringApplication.run(VendasProjectApplication.class, args);
	}
}