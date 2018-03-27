/**
 * COMI2510 - Advanced Java Programming
 * 
 * Program which will write the first 20
 * Fibonacci numbers onto a file to save time,
 * and retrieve them at will.
 * 
 * @author Dylan Grandjean
 *
 */
import java.io.*;
import javax.swing.JOptionPane;

public class BinaryFibonacci
{
	//Fields
	static int num;
	
	/**
	 * main method - get user input and
	 * call on other methods to process
	 * input.
	 * @param args - Array of String
	 */
	public static void main(String[] args) throws NegativeNumberException, IOException
	{
		RandomAccessFile randomFile = new RandomAccessFile("Fibonacci.dat", "rw");
		String input;
		boolean keepGoing = true;
		
		writeNum(randomFile);
		
		do
		{		
			//Prompt user for input
			input = JOptionPane.showInputDialog(null, "Enter the amount of fibonacci\nnumbers you would like me to add up together", "Fibonacci's", JOptionPane.QUESTION_MESSAGE);
		
			//Test for valid input
			while(!checkString(input))
				input = JOptionPane.showInputDialog(null, "Please enter a valid NUMBER", "Error", JOptionPane.ERROR_MESSAGE);
			
			num = fib(num, randomFile);
			
			JOptionPane.showMessageDialog(null,  String.format("Fibonacci number found: %d", num));
			num = JOptionPane.showConfirmDialog(null, "Would you like to enter a different number?");
			if(num == 1 || num == 2)
				System.exit(0);	
		} while(keepGoing);
		randomFile.close();
	}
	
	/**
	 * writeNum -- writes the first 20 Fibonacci
	 * nmbers onto a file.
	 * @throws IOException
	 */
	static public void writeNum(RandomAccessFile randomFile) throws IOException
	{
		int sum = 0;
		int last = 1;
		int current = 0;
		
		for(int i = 0; i < 20; i++)
		{	
			if(i == 0)
				randomFile.writeInt(0);
			else
			{
				sum = last + current;
				last = current;
				current = sum;

				randomFile.writeInt(sum);
			}
		}
	}
	
	/**
	 * checkString method - checks a string to assess its 
	 * content and whether it can be converted to an int or not.
	 * @param in - user input.
	 * @return Whether the string can be converted or not.
	 */
	public static boolean checkString(String in) throws NegativeNumberException
	{
		try
		{
			//Handle closing or canceling
			if(in == null)
				System.exit(0);
			
			//Attempts to convert String to int
			num = Integer.parseInt(in);
			
			if(num < 0)
				throw new NegativeNumberException(num);
			else
				return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	
	/**
	 * fib method returns the Fibonacci number at the
	 * index the user entered. If the index is under 20,
	 * the value will be retrieved from a random access file,
	 * otherwise it will be calculated.
	 * @param n - Index of Fibonacci's number.
	 * @param randomFile - RandomAccessFile class
	 * @return The Fibonacci number at the nth index.
	 * @throws IOException
	 */
	public static int fib(int n, RandomAccessFile randomFile) throws IOException
	{
		if(n < 20)
		{
			randomFile.seek(n * 4);
			return randomFile.readInt();
		}
		else
		{
			int sum = 0;
			int last = 2584;
			int current = 4181;
			
			for(int i = 0; i < (n - 19); i++)
			{
				sum = last + current;
				last = current;
				current = sum;
			}
			return sum;
		}
			
	}
}
