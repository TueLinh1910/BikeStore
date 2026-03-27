package dao;

import entity.Brand;
import exception.DAOException;
import java.util.List;

public interface BrandDAO {
    void insert(Brand brand) throws DAOException;
    void update(Brand brand) throws DAOException;
    void delete(int brandId) throws DAOException;
    Brand findById(int brandId) throws DAOException;
    List<Brand> findAll() throws DAOException;
}