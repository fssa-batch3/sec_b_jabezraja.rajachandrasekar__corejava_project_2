package in.fssa.jauntyrialto.interfaces;

import java.util.Set;

import in.fssa.jauntyrialto.exception.PersistenceException;

public interface UserInterfaces<T> {
	
	public abstract Set<T> findAll() throws PersistenceException;

	public abstract void create(T object) throws PersistenceException;

	public abstract void update(int id, T object) throws PersistenceException;

	public abstract void delete(int id) throws PersistenceException;

	public abstract T findById(int id) throws PersistenceException;
}