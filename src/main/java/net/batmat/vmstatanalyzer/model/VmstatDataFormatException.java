package net.batmat.vmstatanalyzer.model;

public class VmstatDataFormatException extends Exception {

	private static final long serialVersionUID = 1L;

	public VmstatDataFormatException() {
		super();
	}

	public VmstatDataFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public VmstatDataFormatException(String message) {
		super(message);
	}

	public VmstatDataFormatException(Throwable cause) {
		super(cause);
	}
}
