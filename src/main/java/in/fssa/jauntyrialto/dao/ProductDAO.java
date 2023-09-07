package in.fssa.jauntyrialto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.fssa.jauntyrialto.entity.ProductEntity;
import in.fssa.jauntyrialto.entity.SubCategoryEntity;
import in.fssa.jauntyrialto.exception.PersistenceException;
import in.fssa.jauntyrialto.interfaces.ProductInterfaces;
import in.fssa.jauntyrialto.util.ConnectionUtil;
import in.fssa.jauntyrialto.util.Logger;

//class ProductDAOImpl implements ProductDAO<Product>
public class ProductDAO implements ProductInterfaces<ProductEntity> {
	Logger logger = new Logger();

	/**
	 * @return
	 * @throws PersistenceException
	 */

	@Override
	public Set<ProductEntity> findAllProducts() throws PersistenceException {

		Set<ProductEntity> productList = new HashSet<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM products WHERE is_active=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				ProductEntity product = new ProductEntity();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setSubCategoryId(rs.getInt("sub_category_id"));
				product.setPrice(rs.getDouble("price"));

				productList.add(product);

			}

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 56", e);

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return productList;
	}

	/**
	 * @return
	 * @throws PersistenceException
	 */
	@Override
	public Set<ProductEntity> findByCategoryId(int id) throws PersistenceException {

		Set<SubCategoryEntity> subCategoryId = new HashSet<>();

		Set<ProductEntity> productListByCateId = new HashSet<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM sub_categories WHERE is_active=1 AND category_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				SubCategoryEntity subCatId = new SubCategoryEntity();

				subCatId.setId(rs.getInt("id"));

				subCategoryId.add(subCatId);

			}

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 101", e);

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		for (SubCategoryEntity scid : subCategoryId) {

			con = null;
			ps = null;
			rs = null;

			try {

				String query = "SELECT * FROM products WHERE is_active=1 AND sub_category_id = ?";
				con = ConnectionUtil.getConnection();
				ps = con.prepareStatement(query);
				ps.setInt(1, scid.getId());
				rs = ps.executeQuery();

				while (rs.next()) {

					ProductEntity product = new ProductEntity();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setSubCategoryId(rs.getInt("SubCategory_id"));
					product.setPrice(rs.getDouble("price"));

					productListByCateId.add(product);

				}

			} catch (SQLException e) {
				logger.error(e);
				logger.debug(e.getMessage());
				throw new PersistenceException("Error while executing SQL query in line number 137", e);

			} finally {
				ConnectionUtil.close(con, ps, rs);
			}

		}

		return productListByCateId;

	}

	/**
	 * @return
	 * @throws PersistenceException
	 */

	@Override
	public Set<ProductEntity> findBySubCategoryId(int id) throws PersistenceException {

		Set<ProductEntity> productListBySubCateId = new HashSet<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM products WHERE is_active=1 AND sub_category_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				ProductEntity product = new ProductEntity();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setSubCategoryId(rs.getInt("sub_category_id"));
				product.setPrice(rs.getDouble("price"));

				productListBySubCateId.add(product);

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

	/**
	 * @param
	 * @throws PersistenceException
	 */
	@Override
	public void create(ProductEntity newProduct) throws PersistenceException {

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO products (name, description, Sub_category_id, price) VALUES (?, ?, ?, ?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);

			ps.setString(1, newProduct.getName());
			ps.setString(2, newProduct.getDescription());
			ps.setInt(3, newProduct.getSubCategoryId());
			ps.setDouble(4, newProduct.getPrice());

			ps.executeUpdate();

			System.out.println("Product has been created successfully");

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 224", e);

		} finally {
			ConnectionUtil.close(connection, ps);
		}

	}

	/**
	 * @param
	 * @throws PersistenceException
	 */

	@Override
	public void update(int id, ProductEntity updatedProduct) throws PersistenceException {

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			StringBuilder queryBuilder = new StringBuilder("UPDATE products SET ");
			List<Object> values = new ArrayList<>();

			if (updatedProduct.getName() != null) {
				queryBuilder.append("name = ?, ");
				values.add(updatedProduct.getName());
			}

			if (updatedProduct.getDescription() != null) {
				queryBuilder.append("description = ?, ");
				values.add(updatedProduct.getDescription());
			}

			if (updatedProduct.getSubCategoryId() > 0) {
				queryBuilder.append("sub_category_id = ?, ");
				values.add(updatedProduct.getSubCategoryId());
			}

			if (updatedProduct.getPrice() > 0) {
				queryBuilder.append("price = ?, ");
				values.add(updatedProduct.getPrice());
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
			logger.debug("Product has been updated successfully");

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 278", e);

		} finally {

			ConnectionUtil.close(conn, ps);

		}

	}

	/**
	 * @param
	 * @throws PersistenceException
	 */

	@Override
	public void delete(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE products SET is_active = 0 WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);
			ps.executeUpdate();

			logger.debug("Product has been deleted successfully");

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 312", e);

		} finally {
			ConnectionUtil.close(con, ps);
		}

	}

	/**
	 * 
	 * @param id
	 * @throws PersistenceException
	 */

	public void checkSubCategoryExists(int id) throws PersistenceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM sub_categories  WHERE is_active=1 AND id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (!rs.next()) {
				throw new PersistenceException("sub_category id does not exists");
			}
		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 389", e);

		} finally {

			ConnectionUtil.close(con, ps, rs);

		}
	}

	@Override
	public ProductEntity findProductById(int id) throws PersistenceException {
		ProductEntity productByProductId = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM products WHERE is_active=1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				productByProductId = new ProductEntity();
				productByProductId.setId(rs.getInt("id"));
				productByProductId.setName(rs.getString("name"));
				productByProductId.setDescription(rs.getString("description"));
				productByProductId.setSubCategoryId(rs.getInt("sub_category_id"));
				productByProductId.setPrice(rs.getDouble("price"));

			}

		} catch (SQLException e) {
			logger.error(e);
			logger.debug(e.getMessage());
			throw new PersistenceException("Error while executing SQL query in line number 387", e);

		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return productByProductId;
	}

}