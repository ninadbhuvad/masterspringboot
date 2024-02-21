package com.ninad.cards.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s not found with the given input data %s : '%s'", resourceName, fieldName, fieldValue));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1826425667154245118L;

}
