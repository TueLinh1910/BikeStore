package dao;

import entity.Customer;
import exception.DAOException;
import java.util.List;

public interface CustomerDAO {
    void insert(Customer customer) throws DAOException;
    void update(Customer customer) throws DAOException;
    void delete(int customerId) throws DAOException;
    Customer findById(int customerId) throws DAOException;
    List<Customer> findAll() throws DAOException;
}