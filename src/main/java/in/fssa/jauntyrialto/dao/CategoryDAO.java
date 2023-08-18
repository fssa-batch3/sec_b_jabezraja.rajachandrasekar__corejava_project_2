package in.fssa.jauntyrialto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import in.fssa.jauntyrialto.entity.CategoryEntity;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.util.ConnectionUtil;

public class CategoryDAO {
	/**
	 * 
	 * @param newCategory
	 */
	public void create(CategoryEntity newCategory) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO category (name) VALUES (?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);

			ps.setString(1, newCategory.getName());

			ps.executeUpdate();

			System.out.println("Category has been created successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);

		} catch (RuntimeException er) {
			er.printStackTrace();
			System.out.println(er.getMessage());
			throw new RuntimeException(er);

		} finally {
			ConnectionUtil.close(connection, ps);
		}

	}

	/**
	 * 
	 * @param id
	 * @param updatedCategory
	 */

	public void update(int id, CategoryEntity updatedCategory) {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE category SET name=? WHERE is_active=1 AND id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, updatedCategory.getName());

			ps.setInt(2, id);

			ps.executeUpdate();

			System.out.println("Category has been updated successfully");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);

		} catch (RuntimeException er) {
			er.printStackTrace();
			System.out.println(er.getMessage());
			throw new RuntimeException(er);

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * 
	 * @param id
	 */

	public void delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE category SET is_active = 0 WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();

			System.out.println("Category has been deleted successfully");

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);

		} catch (RuntimeException er) {

			er.printStackTrace();
			System.out.println(er.getMessage());
			throw new RuntimeException(er);

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * 
	 * @param name
	 * @throws ValidationException
	 */

	public void checkCategoryExists(String name) throws ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM category WHERE is_active=1 AND name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, name);

			rs = ps.executeQuery();

			if (rs.next()) {

				throw new ValidationException("This category name is already exists");

			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);

		} catch (RuntimeException er) {

			er.printStackTrace();
			System.out.println(er.getMessage());
			throw new RuntimeException(er);

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}
	}

}