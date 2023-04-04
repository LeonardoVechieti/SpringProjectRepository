package br.com.leonardovechieti.vendasproject;

import br.com.leonardovechieti.vendasproject.model.Cliente;
import br.com.leonardovechieti.vendasproject.model.Produto;
import br.com.leonardovechieti.vendasproject.repository.ClienteRepository;
import br.com.leonardovechieti.vendasproject.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.leonardovechieti.vendasproject"})
@RestController
public class VendasProjectApplication {


	@GetMapping("/hello")
	public String helloWorld(){
		return "hello world";
	}

	@Bean
	public CommandLineRunner init(@Autowired ClienteRepository repository, @Autowired ProdutoRepository produtoRepository){
		return args -> {
			System.out.println("Salvando cliente");
			Cliente cliente = new Cliente();
			cliente.setNome("Leonardo");
			cliente.setEmail("leonardo@outllok.com");
			cliente.setCpf("02762312019");
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
//			System.out.println("Buscando todos os clientes");
//			Iterable<Cliente> clientes = repository.findAll();
//			System.out.println("Clientes buscados com sucesso");
//			clientes.forEach(c -> System.out.println(c.getNome()));


			//Populando banco de dados
			Cliente cliente1 = new Cliente();
			cliente1.setNome("Leonardo");
			repository.save(cliente1);
			Cliente cliente2 = new Cliente();
			cliente2.setNome("Vechieti");
			repository.save(cliente2);
			Cliente cliente3 = new Cliente();
			cliente3.setNome("Leonardo Vechieti");
			repository.save(cliente3);
			//Produtos
			Produto produto1 = new Produto();
			produto1.setNome("Computador");
			produto1.setPreco(BigDecimal.valueOf(1000));
			produtoRepository.save(produto1);
			Produto produto2 = new Produto();
			produto2.setNome("Mouse");
			produto2.setPreco(BigDecimal.valueOf(100));
			produtoRepository.save(produto2);
			Produto produto3 = new Produto();
			produto3.setNome("Teclado");
			produto3.setPreco(BigDecimal.valueOf(200));
			produtoRepository.save(produto3);



		};
	}















	public static void main(String[] args) {
		SpringApplication.run(VendasProjectApplication.class, args);
	}
}