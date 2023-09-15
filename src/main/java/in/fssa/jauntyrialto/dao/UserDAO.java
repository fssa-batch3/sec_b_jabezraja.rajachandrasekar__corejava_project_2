package in.fssa.jauntyrialto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.fssa.jauntyrialto.entity.UserEntity;
import in.fssa.jauntyrialto.exception.PersistenceException;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.interfaces.UserInterfaces;
import in.fssa.jauntyrialto.util.ConnectionUtil;
import in.fssa.jauntyrialto.util.Logger;

public class UserDAO implements UserInterfaces<UserEntity> {
	Logger logger = new Logger();

	@Override
	public Set<UserEntity> findAll() throws PersistenceException {
		Set<UserEntity> userList = new HashSet<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT (id, name, email, phone) FROM users";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				UserEntity user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getLong("phone"));

				userList.add(user);
			}

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 47", e);

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return userList;
	}

	@Override
	public void create(UserEntity newUser) throws PersistenceException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO users (name, email, phone, password, confirm_password) VALUES (?,?,?,?,?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);

			ps.setString(1, newUser.getName());
			ps.setString(2, newUser.getEmail());
			ps.setLong(3, newUser.getPhone());
			ps.setString(4, newUser.getPassword());
			ps.setString(5, newUser.getConfirmPassword());

			ps.executeUpdate();

			logger.debug(newUser.getName() + "'s Account in JR has been created successfully");

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query", e);

		} finally {
			ConnectionUtil.close(connection, ps);
		}
	}

	@Override
	public void update(int id, UserEntity updateUser) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			StringBuilder queryBuilder = new StringBuilder("UPDATE users SET ");
			List<Object> values = new ArrayList<>();

			if (updateUser.getName() != null) {
				queryBuilder.append("name = ?, ");
				values.add(updateUser.getName());
			}

			if (updateUser.getPhone() > 6000000000L && updateUser.getPhone() < 9999999999L) {
				queryBuilder.append("phone = ?, ");
				values.add(updateUser.getPhone());
			}

			if (updateUser.getPassword() != null) {
				queryBuilder.append("password = ?, ");
				values.add(updateUser.getPassword());
			}

			if (updateUser.getConfirmPassword() != null) {
				queryBuilder.append("confirm_password = ?, ");
				values.add(updateUser.getConfirmPassword());
			}

			queryBuilder.setLength(queryBuilder.length() - 2);
			queryBuilder.append(" WHERE is_active = 1 AND id = ?");
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(queryBuilder.toString());

			for (int i = 0; i < values.size(); i++) {
				ps.setObject(i + 1, values.get(i));
			}
			ps.setInt(values.size() + 1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 132", e);

		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	@Override
	public void delete(int id) throws PersistenceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE users SET is_active = 0 WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 155", e);

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	@Override
	public UserEntity findById(int id) throws PersistenceException {
		UserEntity user = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT (id, name, email, phone) FROM users WHERE is_active=1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getInt("phone"));
			}

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 188", e);

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return user;
	}

	public void checkUserExists(String email) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserEntity user = null;

		try {

			String query = "SELECT email FROM users WHERE is_active=1 AND email=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();

			while (rs.next()) {
				throw new ValidationException("This user is already exist");
			}

		} catch (SQLException e) {
			logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}

	public void checkUserExistsForUpdate(String email) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserEntity user = null;

		try {

			String query = "SELECT email FROM users WHERE is_active=1 AND email=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();

			while (!rs.next()) {
				throw new ValidationException("User does not exist");
			}

		} catch (SQLException e) {
			logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}

	public void checkUserExistsWithId(int id) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserEntity user = null;

		try {

			String query = "SELECT id, email, name, phone, password, is_active FROM users WHERE is_active=1 AND id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getLong("phone"));
				user.setPassword(rs.getString("password"));
			} else {
				throw new ValidationException("User does not exist");
			}

		} catch (SQLException e) {
			logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}

	}

	public UserEntity checkUserExistsWithEmailForLogin(String email) throws PersistenceException, ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserEntity user = null;

		try {

			String query = "SELECT (id, email, phone, password) FROM users WHERE is_active=1 AND email=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();

			if (rs.next()) {
				user = new UserEntity();
				user.setId(rs.getInt("id"));
				user.setPhone(rs.getLong("number"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));

			} else {
				throw new ValidationException("User does not exist");
			}

		} catch (SQLException e) {
			logger.error(e);
			throw new PersistenceException(e.getMessage());

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}
		return user;

	}

}
