package dev.caobaoqi6040.backend.modules.user.exception;

public class PermissionNotFoundException extends RuntimeException {
	public PermissionNotFoundException(String message) {
		super(message);
	}
}
