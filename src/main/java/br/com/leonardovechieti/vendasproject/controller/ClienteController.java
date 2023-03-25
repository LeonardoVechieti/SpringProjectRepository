package br.com.leonardovechieti.vendasproject.controller;

import br.com.leonardovechieti.vendasproject.model.Cliente;
import br.com.leonardovechieti.vendasproject.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
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
    @ResponseBody
    public List<Cliente> getAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes;
    }

    //Retorna um cliente pelo id
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getById(@PathVariable Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Salva um cliente
    @PostMapping ("/")
    @ResponseBody
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    //Atualiza um cliente
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> update(@PathVariable Integer id, @RequestBody Cliente cliente){
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()){
            cliente.setId(clienteExistente.get().getId());
            clienteRepository.save(cliente);
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Deleta um cliente
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> delete(@PathVariable Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()){
            clienteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
