package com.ninad.cards.exceptions;

public class CardAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -485547418583790323L;

	public CardAlreadyExistsException(String message) {
		super(message);
	}

}
