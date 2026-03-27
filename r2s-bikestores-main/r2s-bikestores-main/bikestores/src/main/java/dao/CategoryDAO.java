package dao;

import entity.Category;
import exception.DAOException;
import java.util.List;

public interface CategoryDAO {
    void insert(Category category) throws DAOException;
    void update(Category category) throws DAOException;
    void delete(int categoryId) throws DAOException;
    Category findById(int categoryId) throws DAOException;
    List<Category> findAll() throws DAOException;
}
