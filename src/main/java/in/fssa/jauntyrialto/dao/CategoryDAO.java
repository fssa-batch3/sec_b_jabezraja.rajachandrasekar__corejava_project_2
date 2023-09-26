package in.fssa.jauntyrialto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.jauntyrialto.entity.CategoryEntity;
import in.fssa.jauntyrialto.entity.ProductEntity;
import in.fssa.jauntyrialto.exception.PersistenceException;
import in.fssa.jauntyrialto.interfaces.Category;
import in.fssa.jauntyrialto.interfaces.ProductInterfaces;
import in.fssa.jauntyrialto.util.ConnectionUtil;
import in.fssa.jauntyrialto.util.Logger;

public class CategoryDAO implements Category<CategoryEntity> {
	Logger logger = new Logger();

	/**
	 * 
	 * @param newCategory
	 * @throws PersistenceException
	 */
	public void create(CategoryEntity newCategory) throws PersistenceException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO categories (name) VALUES (?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);

			ps.setString(1, newCategory.getName());

			ps.executeUpdate();

			logger.debug("Category has been created successfully");

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 35", e);

		} finally {
			ConnectionUtil.close(connection, ps);
		}

	}

	/**
	 * 
	 * @param id
	 * @param updatedCategory
	 * @throws PersistenceException
	 */

	public void update(int id, CategoryEntity updatedCategory) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE categories SET name=? WHERE is_active=1 AND id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, updatedCategory.getName());

			ps.setInt(2, id);

			ps.executeUpdate();

			logger.debug("Category has been updated successfully");

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 72", e);

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * 
	 * @param id
	 * @throws PersistenceException
	 */

	public void delete(int id) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE categories SET is_active = 0 WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();

			logger.debug("Category has been deleted successfully");

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 104", e);

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * 
	 * @param name
	 * @throws PersistenceException
	 */

	public void checkCategoryExists(String name) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM categories WHERE is_active=1 AND name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, name);

			rs = ps.executeQuery();

			if (rs.next()) {
				throw new PersistenceException("This category name is already exists");
			}

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 141", e);

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}
	}

	public void checkCategoryNotExists(String name) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM categories WHERE is_active=1 AND name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, name);

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new PersistenceException("This category name is already exists");
			}

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 141", e);

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}
	}

	@Override
	public Set<CategoryEntity> findAll() throws PersistenceException {
		Set<CategoryEntity> categoryList = new HashSet<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM categories WHERE is_active=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				CategoryEntity category = new CategoryEntity();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setImg(rs.getString("image"));

				categoryList.add(category);

			}

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 56", e);

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return categoryList;
	}

}