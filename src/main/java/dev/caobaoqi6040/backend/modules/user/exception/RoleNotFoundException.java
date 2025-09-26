package dev.caobaoqi6040.backend.modules.user.exception;

public class RoleNotFoundException extends RuntimeException {
	public RoleNotFoundException(String message) {
		super(message);
	}
}
