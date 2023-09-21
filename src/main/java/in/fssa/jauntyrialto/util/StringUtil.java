package in.fssa.jauntyrialto.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.jauntyrialto.exception.ValidationException;

public class StringUtil {
	/**
	 * 
	 * @param input
	 * @param inputName
	 * @throws ValidationException
	 */

	public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {
		if (input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be null or empty"));
		}
	}

	public static boolean isValid(String input) {
		if (input == null || "".equals(input.trim())) {
			return false;
		}
		return true;
	}

	public static boolean isInValid(String input) {
		if (input == null || "".equals(input.trim())) {
			return false;
		}
		return true;
	}

	public static void rejectIfInvalidName(String name, String inputName) throws ValidationException {

		String regex = "^[a-zA-Z]+(?:[' -][a-zA-Z]+){0,29}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name);
		if (!matcher.matches()) {
			throw new ValidationException(inputName.concat(
					" must contain only alphabets with minimum 3 letters can have characters like(',-) with a single space and followed by letters"));
		}
	}

	public static void rejectIfInvalidEmail(String email, String string) throws ValidationException {

		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			throw new ValidationException("Invalid Email Id");
		}
	}

	public static void rejectIfInvalidPassword(String password) throws ValidationException {

		String regex = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$";
		Pattern patter = Pattern.compile(regex);
		Matcher matcher = patter.matcher(password);
		if (!matcher.matches()) {
			throw new ValidationException("Password does not match the requested pattern");
		}
	}

}
