package in.fssa.jauntyrialto.util;

public class Logger {

	public void error(Exception e) {
		e.printStackTrace();
	}

	public void debug(Object e) {
		System.out.println(e);
	}
}
