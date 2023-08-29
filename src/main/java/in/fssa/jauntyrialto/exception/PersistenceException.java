package in.fssa.jauntyrialto.exception;

import java.sql.SQLException;

public class PersistenceException extends Exception {

	/**
	 * Creates a new PersistenceException with the specified cause.
	 * 
	 * @param p The cause of the PersistenceException.
	 */
	public PersistenceException(SQLException p) {
		super(p);
	}

	/**
	 * Creates a new PersistenceException with the provided detail message and
	 * cause.
	 * 
	 * @param msg The extended message explaining the validation error.
	 * @param e   The cause of the PersistenceException.
	 */
	public PersistenceException(String msg, SQLException e) {
		super(msg, e);
	}

	/**
	 * Creates a new PersistenceException with the provided detail message.
	 * 
	 * @param msg The extended message explaining the validation error.
	 */
	public PersistenceException(String msg) {
		super(msg);
	}

}
