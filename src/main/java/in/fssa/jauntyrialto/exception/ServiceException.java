package in.fssa.jauntyrialto.exception;

public class ServiceException extends Exception {
	/**
	 * Creates a new ServiceException with the provided detail message.
	 * 
	 * @param msg The extended message explaining the validation error
	 */
	public ServiceException(String msg) {
		super(msg);
	}

	/**
	 * Creates a new ServiceException with the specified cause.
	 * 
	 * @param ex
	 */
	public ServiceException(Throwable ex) {
		super(ex);
	}

	/**
	 * Creates a new ServiceException with the provided detail message and cause.
	 * 
	 * @param msg The extended message explaining the validation error
	 * @param ex
	 */
	public ServiceException(String msg, Throwable ex) {
		super(msg, ex);
	}
}
