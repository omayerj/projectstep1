package Exception.System;

import javax.management.RuntimeErrorException;

public class DAOException extends RuntimeErrorException {


	public DAOException( String message,Error e) {
		super(e, message);
	}
	public DAOException(Error e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

}
