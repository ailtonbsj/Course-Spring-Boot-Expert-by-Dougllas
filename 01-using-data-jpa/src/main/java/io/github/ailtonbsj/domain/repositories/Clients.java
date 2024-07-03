package io.github.ailtonbsj.domain.repositories;

import io.github.ailtonbsj.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clients {
    private static final String INSERT = "insert into client (name) values (?)";
    private static final String SELECT_ALL = "select * from client";
    private static final String UPDATE = "update client set name = ? where id = ?";
    private static final String DELETE = "delete from client where id = ?";
    private static final String SELECT_BY_NAME = "select * from client where name like ?";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Client client){
        jdbcTemplate.update(INSERT, new Object[]{
                client.getName()
        });
    }

    public List<Client> getAll() {
        return jdbcTemplate.query(SELECT_ALL, getClientRowMapper());
    }

    public void update(Client client) {
        jdbcTemplate.update(UPDATE, new Object[]{
                client.getName(),
                client.getId()
        });
    }

    public void delete(Client client) {
        jdbcTemplate.update(DELETE, new Object[]{
                client.getId()
        });
    }

    public List<Client> findByName(String name){
        return jdbcTemplate.query(
                SELECT_BY_NAME,
                new Object[]{ "%" + name + "%" },
                getClientRowMapper()
        );
    }

    private static RowMapper<Client> getClientRowMapper() {
        return new RowMapper<Client>() {
            @Override
            public Client mapRow(ResultSet resultSet, int i) throws SQLException {
                Client c = new Client();
                c.setId(resultSet.getInt("id"));
                c.setName(resultSet.getString("name"));
                return c;
            }
        };
    }
}
