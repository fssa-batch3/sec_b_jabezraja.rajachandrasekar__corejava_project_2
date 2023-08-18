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
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.interfaces.ProductInterfaces;
import in.fssa.jauntyrialto.util.ConnectionUtil;

public class ProductDAO implements ProductInterfaces<ProductEntity> {
	/**
	 * @return
	 */

	@Override
	public Set<ProductEntity> findAllProducts() {

		Set<ProductEntity> productList = new HashSet<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM product WHERE is_active=1";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				ProductEntity product = new ProductEntity();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setSub_category_id(rs.getInt("SubCategory_id"));

				productList.add(product);

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

		return productList;
	}

	/**
	 * @return
	 */
	@Override
	public Set<ProductEntity> findByCategoryId(int id) {

		Set<SubCategoryEntity> subCategoryId = new HashSet<>();

		Set<ProductEntity> productListByCateId = new HashSet<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM sub_category WHERE is_active=1 && category_id = ?";
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

		for (SubCategoryEntity scid : subCategoryId) {

			con = null;
			ps = null;
			rs = null;

			try {

				String query = "SELECT * FROM product WHERE is_active=1 && sub_category_id = ?";
				con = ConnectionUtil.getConnection();
				ps = con.prepareStatement(query);
				ps.setInt(1, id);
				rs = ps.executeQuery();

				while (rs.next()) {

					ProductEntity product = new ProductEntity();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setSub_category_id(rs.getInt("SubCategory_id"));
					product.setPrice(rs.getDouble("price"));

					productListByCateId.add(product);

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

		return productListByCateId;

	}

	/**
	 * @return
	 */

	@Override
	public Set<ProductEntity> findBySubCategoryId(int id) {

		Set<ProductEntity> productListBySubCateId = new HashSet<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM product WHERE is_active=1 && sub_category_id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {

				ProductEntity product = new ProductEntity();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setSub_category_id(rs.getInt("SubCategory_id"));
				product.setPrice(rs.getDouble("price"));

				productListBySubCateId.add(product);

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

		return productListBySubCateId;

	}

	/**
	 * @param
	 */
	@Override
	public void create(ProductEntity newProduct) {

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO product (name, description, Sub_category_id, price) VALUES (?, ?, ?, ?)";
			connection = ConnectionUtil.getConnection();
			ps = connection.prepareStatement(query);

			ps.setString(1, newProduct.getName());
			ps.setString(2, newProduct.getDescription());
			ps.setInt(3, newProduct.getSub_category_id());
			ps.setDouble(4, newProduct.getPrice());

			ps.executeUpdate();

			System.out.println("Product has been created successfully");

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
	 * @param
	 */

	@Override
	public void update(int id, ProductEntity updatedProduct) {

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

			if (updatedProduct.getSub_category_id() > 0) {
				queryBuilder.append("sub_category_id = ?, ");
				values.add(updatedProduct.getSub_category_id());
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
			System.out.println("Product has been updated successfully");

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);

		} catch (RuntimeException er) {

			er.printStackTrace();
			System.out.println(er.getMessage());
			throw new RuntimeException(er);

		} finally {

			ConnectionUtil.close(conn, ps);

		}

	}

	/**
	 * @param
	 */

	@Override
	public void delete(int id) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE product SET is_active = 0 WHERE is_active = 1 AND id = ?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, id);
			ps.executeUpdate();

			System.out.println("Product has been deleted successfully");

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
	 * @throws ValidationException
	 */

	public void checkSubCategoryExists(int id) throws ValidationException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			String query = "SELECT * FROM sub_category  WHERE is_active=1 AND id=?";
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (!rs.next()) {
				throw new ValidationException("sub_category id does not exists");
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