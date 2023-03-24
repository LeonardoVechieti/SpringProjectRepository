package br.com.leonardovechieti.vendasproject.repository;
import br.com.leonardovechieti.vendasproject.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    //Todos os produtos
    @GetMapping("/produtos")
    List<Produto> findAll();
}
