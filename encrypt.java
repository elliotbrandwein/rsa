import java.math.*;
import java.util.*;
import java.io.*;
import java.nio.*;
public class encrypt
{
	public static String get_message()
	{
		File file = new File("plain_text_message.txt");
		String output = "";
		BufferedReader reader = null;
		try 
		{
		    reader = new BufferedReader(new FileReader(file));
		    String line = null;
		    while ((line = reader.readLine()) != null) 
		    {
		        output += line;
		    }
		} 
		catch (FileNotFoundException e) 
		{
		    e.printStackTrace();
		}
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		finally 
		{
		    try 
		    {
		        if (reader != null) 
		        {
		            reader.close();
		        }
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		}
		return output;
	}
	public static String[] get_keys()
	{
		File file = new File("user1.txt");
		String[] output = new String[6];
		BufferedReader reader = null;
		try 
		{
			int index = 0;
		    reader = new BufferedReader(new FileReader(file));
		    String line = null;
		    while ((line = reader.readLine()) != null) 
		    {
		        output[index] = line;
		        index++;
		    }
		} 
		catch (FileNotFoundException e) 
		{
		    e.printStackTrace();
		}
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		finally 
		{
		    try 
		    {
		        if (reader != null) 
		        {
		            reader.close();
		        }
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		}
		return output;
	}
	public static void main (String args[])
	{
		String message = get_message();
		// keys has form: 
		//System.out.println("Private key:\n"+ d + "\n" + n + "\nPublic key:\n" + E + "\n" + n );
		String[] keys = get_keys();
		BigInteger letter = new BigInteger("0");
		BigInteger E = new BigInteger(keys[4]);
		BigInteger N = new BigInteger(keys[5]);
		for (int i = 0; i< message.length(); i++)
		{
			// System.out.print((int)x.charAt(i));
			letter = new BigInteger(String.valueOf((int)message.charAt(i)));
			letter = letter.modPow(E,N);
			// System.out.println("x");
			System.out.println(letter.toString());
		}
	    System.out.println();

	}
}