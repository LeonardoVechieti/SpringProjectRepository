package br.com.leonardovechieti.vendasproject.repository;

import br.com.leonardovechieti.vendasproject.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.TypedQuery;
import java.util.List;
import javax.persistence.PersistenceContext;

import javax.persistence.EntityManager;


@Repository
public class ClienteRepository {



    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente save(Cliente cliente) {
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente update(Cliente cliente) {
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void delete(Cliente cliente) {
        cliente = entityManager.merge(cliente);
        entityManager.remove(cliente);
    }
    @Transactional
    public void deleteId(Integer id) {
       Cliente cliente = entityManager.find(Cliente.class, id);
         delete(cliente);
    }

    @Transactional
    public Cliente findById(Integer id) {
        return entityManager.find(Cliente.class, id);
    }

    @Transactional (readOnly = true)
    public List<Cliente> buscaPorNome(String nome) {
        String jpql = "select c from Cliente c where c.nome like :nome";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nome +"%");
        return query.getResultList();
    }

    @Transactional (readOnly = true)
    public List<Cliente> findAll() {
        String jpql = "select c from Cliente c";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        return query.getResultList();
    }




}
