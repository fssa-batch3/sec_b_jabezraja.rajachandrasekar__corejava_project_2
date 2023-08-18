package in.fssa.jauntyrialto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.jauntyrialto.entity.SubCategoryEntity;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.util.ConnectionUtil;

public class SubCategoryDAO {

	/**
	 * 
	 * @param newSubCategory
	 */

	public void create(SubCategoryEntity newSubCategory) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO sub_category (name, category_id) VALUES (?,?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);

			ps.setString(1, newSubCategory.getName());
			ps.setInt(2, newSubCategory.getCategory_id());

			ps.executeUpdate();

			System.out.println("SubCategory has been created successfully");

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
	 * @param updatedSubCategory
	 */

	public void update(int id, SubCategoryEntity updatedSubCategory) {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			String query = "UPDATE sub_category SET name=? WHERE is_active=1 AND id=?";

			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, updatedSubCategory.getName());

			ps.setInt(2, id);

			ps.executeUpdate();

			System.out.println("SubCategory has been updated successfully");

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
			String query = "UPDATE sub_category SET is_active = 0 WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();

			System.out.println("SubCategory has been deleted successfully");

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
	public void checkSubCategoryExists(String name) throws ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM sub_category WHERE is_active=1 AND name = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, name);

			rs = ps.executeQuery();

			if (rs.next()) {
				throw new ValidationException("This SubCategory name is already exists");
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

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public void checkCategoryExists(int id) throws ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM category WHERE is_active=1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (!rs.next()) {

				throw new ValidationException("Category does not exists");

			}

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}
	}
}