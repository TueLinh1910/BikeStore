package dao;

import entity.Brand;
import exception.DAOException;
import util.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandDAOImpl implements BrandDAO {

    @Override
    public void insert(Brand brand) throws DAOException {
        String sql = "INSERT INTO brands (brand_name) VALUES (?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, brand.getBrandName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Failed to insert brand.", e);
        }
    }

    @Override
    public void update(Brand brand) throws DAOException {
        String sql = "UPDATE brands SET brand_name=? WHERE brand_id=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, brand.getBrandName());
            stmt.setInt(2, brand.getBrandId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Failed to update brand.", e);
        }
    }

    @Override
    public void delete(int brandId) throws DAOException {
        String sql = "DELETE FROM brands WHERE brand_id=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, brandId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Failed to delete brand.", e);
        }
    }

    @Override
    public Brand findById(int brandId) throws DAOException {
        String sql = "SELECT * FROM brands WHERE brand_id=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, brandId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Brand(rs.getInt("brand_id"), rs.getString("brand_name"));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to find brand by ID.", e);
        }
        return null;
    }

    @Override
    public List<Brand> findAll() throws DAOException {
        List<Brand> list = new ArrayList<>();
        String sql = "SELECT * FROM brands";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Brand(rs.getInt("brand_id"), rs.getString("brand_name")));
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to get brand list.", e);
        }
        return list;
    }
}