package in.fssa.jauntyrialto.util;

import in.fssa.jauntyrialto.exception.ValidationException;

public class IntUtil {
	/**
	 * 
	 * @param id
	 * @param inputName
	 * @throws ValidationException
	 */
	public static void rejectIfInvalidId(int id, String inputName) throws ValidationException {
		if (id <= 0) {
			throw new ValidationException(inputName.concat(" cannot be zero or below zero"));
		}
	}

	public static void rejectIfInvalidNumber(long number, String inputNumber) throws ValidationException {
		if (number < 6000000001l || number > 9999999999l) {
			throw new ValidationException(inputNumber.concat(" must start between 6 - 9 and have total of 10 digits"));
		}
	}
}
