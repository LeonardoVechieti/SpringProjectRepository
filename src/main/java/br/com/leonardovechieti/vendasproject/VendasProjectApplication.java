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
			System.out.println("Buscando cliente");
			Cliente clienteBuscado = repository.findById(1).orElseThrow();
			System.out.println("Cliente buscado com sucesso");
			System.out.println(clienteBuscado.getNome());
			System.out.println("Atualizando cliente");
			clienteBuscado.setNome("Leonardo Vechieti");
			repository.save(clienteBuscado);
			System.out.println("Cliente atualizado com sucesso");
			System.out.println("Buscando cliente");
			Cliente clienteAtualizado = repository.findById(1).orElseThrow();
			System.out.println("Cliente buscado com sucesso");
			System.out.println(clienteAtualizado.getNome());
			System.out.println("Deletando cliente");
			repository.delete(clienteAtualizado);
			System.out.println("Cliente deletado com sucesso");
			System.out.println("Buscando todos os clientes");
			Iterable<Cliente> clientes = repository.findAll();
			System.out.println("Clientes buscados com sucesso");
			clientes.forEach(c -> System.out.println(c.getNome()));




		};
	}















	public static void main(String[] args) {
		SpringApplication.run(VendasProjectApplication.class, args);
	}
}