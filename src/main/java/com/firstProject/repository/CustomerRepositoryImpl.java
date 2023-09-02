package com.firstProject.repository;

import com.firstProject.model.Customer;
import com.firstProject.model.CustomerStatus;
import com.firstProject.repository.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private static final String CUSTOMER_TABLE_NAME = "customer";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public void createCustomer(Customer customer) {
        String sql = "INSERT INTO " + CUSTOMER_TABLE_NAME + " " + "(first_name, last_name, email, status) values (?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getCustomerStatus().name()
        );
    }

    @Override
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE " + CUSTOMER_TABLE_NAME + " SET first_name=?, last_name=?, email=?, status=? WHERE id=?";
        jdbcTemplate.update(
            sql,
            customer.getFirstName(),
            customer.getLastName(),
            customer.getEmail(),
            customer.getCustomerStatus().name(),
            customer.getId()
        );
    }

    @Override
    public void deleteCustomerById(Long id) {
        String sql = "DELETE FROM " + CUSTOMER_TABLE_NAME + " WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Customer getCustomerById(Long id) {
        String sql = "SELECT * FROM " + CUSTOMER_TABLE_NAME + " WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, customerMapper, id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Empty Data Warning");
            return null;
        }
    }

    @Override
    public List<Customer> getAllCustomersByFirstName(String firstName) {
        String sql = "SELECT * FROM " + CUSTOMER_TABLE_NAME + " WHERE first_name = ?";
        try {
            return jdbcTemplate.query(sql, customerMapper, firstName);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Empty Data Warning");
            return null;
        }
    }

    @Override
    public List<Long> getAllCustomerIdsByFirstName(String firstName) {
        String sql = "SELECT c.id FROM " + CUSTOMER_TABLE_NAME + " AS c WHERE first_name = ?";
        try {
           return jdbcTemplate.queryForList(sql, Long.class, firstName);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Empty Data Warning");
            return null;
        }
    }

    @Override
    public List<Customer> getAllCustomersByStatus(CustomerStatus customerStatus) {
        String sql = "SELECT * FROM " + CUSTOMER_TABLE_NAME + " WHERE status = ?";
        try {
            return jdbcTemplate.query(sql, customerMapper, customerStatus.name());
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Empty Data Warning");
            return null;
        }
    }
}














