import java.lang.*;
import java.io.*;
import java.nio.file.*;
import java.math.*;
public class decrypt
{
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
		    }
		}
		return output;
	}
	public static void main (String args[])
	{
		File file = new File("input.txt");
		String input = "";
		BufferedReader reader = null;
		try {
		    reader = new BufferedReader(new FileReader(file));
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        input += line;
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (reader != null) {
		            reader.close();
		        }
		    } catch (IOException e) {
		    }
		}
		// we create a new byte array that has all the bytes of our input
		// StringBuilder x = new StringBuilder("");
		// int string = 0;
		// for(int i = 0; i < input.length() ; i++)
		// {
		// 	if(input.charAt(i) != 'x')
		// 	{
		// 		if(string == 0){
		// 			string += Character.getNumericValue((input.charAt(i)));
		// 		}
		// 		else
		// 		{
		// 			string *= 10;
		// 			string += Character.getNumericValue((input.charAt(i)));
		// 		}
		// 	}
		// 	else
		// 	{
		// 		x.append((char)string);
		// 		string = 0;
		// 	}
		// }
		
		BigInteger cypher_text = new BigInteger(input);
		String[] keys = get_keys();
		BigInteger D = new BigInteger(keys[1]);
		BigInteger N = new BigInteger(keys[5]);
		cypher_text = cypher_text.modPow(D,N);
		System.out.println(cypher_text.toString());
		int text = Integer.valueOf(cypher_text.toString());
		System.out.println((char) text);
	}
}