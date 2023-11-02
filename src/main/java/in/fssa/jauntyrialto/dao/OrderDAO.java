package in.fssa.jauntyrialto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.fssa.jauntyrialto.entity.OrderEntity;
import in.fssa.jauntyrialto.enums.OrderStatus;
import in.fssa.jauntyrialto.exception.PersistenceException;
import in.fssa.jauntyrialto.util.ConnectionUtil;
import in.fssa.jauntyrialto.util.Logger;

public class OrderDAO {
	Logger logger = new Logger();

	public void create(OrderEntity newOrder) throws PersistenceException {

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO orders (id, product_id, user_id, qty, total, land_mark, pincode, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);

			ps.setInt(1, newOrder.getId());
			ps.setInt(2, newOrder.getProductId());
			ps.setInt(3, newOrder.getUserId());
			ps.setInt(4, newOrder.getQty());
			ps.setDouble(5, newOrder.getTotal());
			ps.setString(6, newOrder.getLandMark());
			ps.setInt(7, newOrder.getPinCode());
			ps.setString(8, newOrder.getAddress());

			ps.executeUpdate();

			logger.debug("Order has been created successfully");

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 224", e);

		} finally {
			ConnectionUtil.close(connection, ps);
		}

	}

	public void update(int id, OrderEntity updatedOrder) throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			StringBuilder queryBuilder = new StringBuilder("UPDATE orders SET ");
			List<Object> values = new ArrayList<>();

			if (updatedOrder.getAddress() != null) {
				queryBuilder.append("address = ?, ");
				values.add(updatedOrder.getAddress());
			}

			if (updatedOrder.getLandMark() != null) {
				queryBuilder.append("land_mark = ?, ");
				values.add(updatedOrder.getLandMark());
			}

			if (updatedOrder.getPinCode() > 0) {
				queryBuilder.append("pincode = ?, ");
				values.add(updatedOrder.getPinCode());
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
			logger.debug("Order has been updated successfully");

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 278", e);

		} finally {

			ConnectionUtil.close(conn, ps);

		}

	}

	public void delete(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE orders SET is_active = 0 WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);
			ps.executeUpdate();

			logger.debug("order has been deleted successfully");

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 312", e);

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	public OrderEntity getOrderById(int orderId) throws PersistenceException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		OrderEntity order = null;

		try {
			conn = ConnectionUtil.getConnection();
			String query = "SELECT * FROM orders WHERE id = ? AND is_active = 1";
			ps = conn.prepareStatement(query);
			ps.setInt(1, orderId);
			rs = ps.executeQuery();

			if (rs.next()) {
				order = new OrderEntity();
				order.setId(rs.getInt("id"));
				order.setProductId(rs.getInt("product_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setQty(rs.getInt("qty"));
				order.setTotal(rs.getDouble("total"));
				order.setLandMark(rs.getString("land_mark"));
				order.setPinCode(rs.getInt("pincode"));
				order.setAddress(rs.getString("address"));
				String orderStatusValue = rs.getString("order_status").toUpperCase();
				order.setOrderStatus(OrderStatus.valueOf(orderStatusValue));
				order.setOrderDate(rs.getTimestamp("order_date"));
			}

		} catch (SQLException e) {
			// Handle any exceptions
			throw new PersistenceException("Error while retrieving order by ID", e);
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}

		return order;
	}

	public Set<OrderEntity> findOrdersByUserId(int id) throws PersistenceException {

		Set<OrderEntity> productListBySubCateId = new HashSet<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM orders WHERE is_active=1 AND user_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				OrderEntity order = new OrderEntity();
				order.setId(rs.getInt("id"));
				order.setProductId(rs.getInt("product_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setQty(rs.getInt("qty"));
				order.setTotal(rs.getDouble("total"));
				order.setLandMark(rs.getString("land_mark"));
				order.setPinCode(rs.getInt("pincode"));
				order.setAddress(rs.getString("address"));
				String orderStatusValue = rs.getString("order_status").toUpperCase();
				order.setOrderStatus(OrderStatus.valueOf(orderStatusValue));
				order.setOrderDate(rs.getTimestamp("order_date"));

				productListBySubCateId.add(order);

			}

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 187", e);

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return productListBySubCateId;

	}

}
