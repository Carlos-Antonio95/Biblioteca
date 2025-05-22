package exception;

public class ExceptionSQL extends RuntimeException {
	public ExceptionSQL(String mensagem) {
		super(mensagem);

	}
	
	public  ExceptionSQL(String mensagem, Throwable causa) {
		super(mensagem);
	}
	
	
}
