package io.github.ailtonbsj.domain.repositories;

import io.github.ailtonbsj.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clients {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void save(Client client){
        entityManager.persist(client);
    }

    @Transactional(readOnly = true)
    public List<Client> getAll() {
        return entityManager
                .createQuery("from Client", Client.class)
                .getResultList();
    }

    @Transactional
    public void update(Client client) {
        entityManager.merge(client);
    }

    @Transactional
    public void delete(Client client) {
        if(!entityManager.contains(client)) {
            client = entityManager.merge(client);
        }
        entityManager.remove(client);
    }

    @Transactional
    public void delete(Integer id) {
        Client client = entityManager.find(Client.class, id);
        delete(client);
    }

    @Transactional(readOnly = true)
    public List<Client> findByName(String name){
        String jpql = "select c from Client c where c.name like :name";
        TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
}
