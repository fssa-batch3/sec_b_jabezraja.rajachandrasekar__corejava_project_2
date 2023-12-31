package in.fssa.jauntyrialto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.jauntyrialto.entity.SubCategoryEntity;
import in.fssa.jauntyrialto.exception.PersistenceException;
import in.fssa.jauntyrialto.util.ConnectionUtil;
import in.fssa.jauntyrialto.util.Logger;

public class SubCategoryDAO {
	Logger logger = new Logger();

	/**
	 * 
	 * @param newSubCategory
	 * @throws PersistenceException
	 */

	public void create(SubCategoryEntity newSubCategory) throws PersistenceException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO sub_categories (name, category_id) VALUES (?,?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);

			ps.setString(1, newSubCategory.getName());
			ps.setInt(2, newSubCategory.getCategoryId());

			ps.executeUpdate();

			logger.debug("SubCategory has been created successfully");

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 39", e);

		} finally {
			ConnectionUtil.close(connection, ps);
		}

	}

	/**
	 * 
	 * @param id
	 * @param updatedSubCategory
	 * @throws PersistenceException
	 */

	public void update(int id, SubCategoryEntity updatedSubCategory) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE sub_categories SET name=? WHERE is_active=1 AND id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, updatedSubCategory.getName());

			ps.setInt(2, id);

			ps.executeUpdate();

			logger.debug("SubCategory has been updated successfully");

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 76", e);

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
			String query = "UPDATE sub_categories SET is_active = 0 WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();

			logger.debug("SubCategory has been deleted successfully");

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 107", e);

		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * 
	 * @param name
	 * @throws ValidationException
	 * @throws PersistenceException
	 */
	public void checkSubCategoryExists(String name) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM sub_categories WHERE is_active=1 AND name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, name);

			rs = ps.executeQuery();

			if (rs.next()) {
				throw new PersistenceException("This SubCategory name is already exists");
			}

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 143", e);

		} finally {
			ConnectionUtil.close(con, ps, rs);

		}
	}

	public void checkSubCategoryNotExists(String name) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM sub_categories WHERE is_active=1 AND name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, name);

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new PersistenceException("This SubCategory name is already exists");
			}

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 143", e);

		} finally {
			ConnectionUtil.close(con, ps, rs);

		}
	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 * @throws PersistenceException
	 */
	public void checkCategoryExists(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM categories WHERE is_active=1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				throw new PersistenceException("Category does not exists");
			}

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 180", e);

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}
	}

	public void checkCategoryNotExists(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM categories WHERE is_active=1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new PersistenceException("Category does not exists");
			}

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 180", e);

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}
	}

	public List<SubCategoryEntity> findAll() throws PersistenceException {

		List<SubCategoryEntity> SubCategoryList = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * FROM sub_categories WHERE is_active=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				SubCategoryEntity subCategory = new SubCategoryEntity();
				subCategory.setId(rs.getInt("id"));
				subCategory.setName(rs.getString("name"));
				subCategory.setCategoryId(rs.getInt("category_id"));
				SubCategoryList.add(subCategory);
			}

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 280", e);

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return SubCategoryList;
	}

}