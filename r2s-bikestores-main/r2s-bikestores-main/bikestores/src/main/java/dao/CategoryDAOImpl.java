package dao;

import entity.Category;
import exception.DAOException;
import util.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public void insert(Category category) throws DAOException {
        String sql = "INSERT INTO categories (category_name) VALUES (?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getCategoryName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Failed to insert category.", e);
        }
    }

    @Override
    public void update(Category category) throws DAOException {
        String sql = "UPDATE categories SET category_name=? WHERE category_id=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, category.getCategoryName());
            stmt.setInt(2, category.getCategoryId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Failed to update category.", e);
        }
    }

    @Override
    public void delete(int categoryId) throws DAOException {
        String sql = "DELETE FROM categories WHERE category_id=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Failed to delete category.", e);
        }
    }

    @Override
    public Category findById(int categoryId) throws DAOException {
        String sql = "SELECT * FROM categories WHERE category_id=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                );
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to find category by ID.", e);
        }
        return null;
    }

    @Override
    public List<Category> findAll() throws DAOException {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                ));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to get category list.", e);
        }
        return list;
    }
}