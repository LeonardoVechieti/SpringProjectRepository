package br.com.leonardovechieti.vendasproject.repository;
import br.com.leonardovechieti.vendasproject.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
