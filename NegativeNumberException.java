/**
 * COMI2510 - Advanced Java Programming
 * 
 * Class which handles a negative number when 
 * it is not applicable in a certain context.
 * 
 * @author Dylan Grandjean
 *
 */
public class NegativeNumberException extends Exception
{
	/**
	 * Constructor which displays generic error message.
	 */
	public NegativeNumberException()
	{
		super("Error: Negative Fibonacci numbers. Cannot enter a negative number.");
	}
	
	/**
	 * Constructor which displays the cause of the error.
	 * @param number - User's invalid input.
	 */
	public NegativeNumberException(int number)
	{
		super("Error: Negative Fibonacci number. Cannot enter a negative number. Number: " + number);
	}
}
