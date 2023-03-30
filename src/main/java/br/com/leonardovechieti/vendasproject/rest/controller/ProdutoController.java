package br.com.leonardovechieti.vendasproject.rest.controller;

import br.com.leonardovechieti.vendasproject.model.Produto;
import br.com.leonardovechieti.vendasproject.repository.ProdutoRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    //Injeção de dependência
    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    //Liberar o acesso a todos os produtos
    @CrossOrigin (origins = "*")

    //Retorna todos os produtos
    @GetMapping("/")
    public List<Produto> getAll(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos;
    }

    //Retorna um produto pelo id
    @GetMapping("/{id}")
    public Produto getById(@PathVariable Integer id){
        return produtoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    //Salva um produto
    @PostMapping ("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto){
        return produtoRepository.save(produto);
    }

    //Atualiza um produto
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Produto produto) {
        produtoRepository.findById(id)
                .map(clienteExistente -> {
                    produto.setId(clienteExistente.getId());
                    produtoRepository.save(produto);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

    }

    //Deleta um produto
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        produtoRepository.findById(id)
                .map(cliente -> {
                    produtoRepository.delete(cliente);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }


    //Filtrar produtos
    @GetMapping("/search/")
    public List<Produto> find (Produto filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return produtoRepository.findAll(example);
    }


}
