package controller.exceptions;

import java.util.ArrayList;
import java.util.List;

public class UserException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_MESSAGE = "Erro usuário";

	private final String message;
	private final List<String> validationErrors;

	public UserException() {
		this(DEFAULT_MESSAGE);
	}

	public UserException(String message) {
		this.message = message;
		this.validationErrors = new ArrayList<>();

	}

	public void addValidationError(String error) {
		validationErrors.add(error);
	}

	public List<String> getValidationErrors() {
		return validationErrors;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getName()).append(": ").append(message);
		if (!validationErrors.isEmpty()) {
			sb.append("\nErros de validação:");
			for (String error : validationErrors) {
				sb.append("\n- ").append(error);
			}
		}
		return sb.toString();
	}
}
