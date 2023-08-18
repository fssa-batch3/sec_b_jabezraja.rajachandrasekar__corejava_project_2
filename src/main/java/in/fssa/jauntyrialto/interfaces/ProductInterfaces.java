package in.fssa.jauntyrialto.interfaces;

import java.util.Set;

public interface ProductInterfaces<T> {
	public abstract Set<T> findAllProducts();

	public abstract void create(T object);

	public abstract void update(int id, T object);

	public abstract void delete(int id);

	public abstract Set<T> findByCategoryId(int id);

	public abstract Set<T> findBySubCategoryId(int id);
}
