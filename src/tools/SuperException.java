package tools;

public class SuperException extends Exception {

	private String superErrorMessage;
	
	public SuperException(String errorMessage) {
		superErrorMessage = errorMessage;
	}

	public String getSuperErrorMessage(){
		return new String("Super error : " + superErrorMessage);
	}
	
}
