package br.com.leonardovechieti.vendasproject.rest.controller;

import br.com.leonardovechieti.vendasproject.model.Cliente;
import br.com.leonardovechieti.vendasproject.repository.ClienteRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/clientes")
public class ClienteController {

    //Injeção de dependência
    private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    //Liberar o acesso a todos os clientes
    @CrossOrigin (origins = "*")

    //Retorna todos os clientes
    @GetMapping("/")
    public List<Cliente> getAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    //Retorna um cliente pelo id
    @GetMapping("/{id}")
    public Cliente getById(@PathVariable Integer id){
        return clienteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    //Salva um cliente
    @PostMapping ("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente){
        return clienteRepository.save(cliente);
   }

    //Atualiza um cliente
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
        clienteRepository.findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clienteRepository.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

    //Deleta um cliente
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        clienteRepository.findById(id)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }


    @GetMapping("/search/")
    public List<Cliente> find (Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return clienteRepository.findAll(example);
    }
}
