import java.math.*;
import java.util.*;
import java.io.*;
import java.nio.*;
public class encrypt
{
	public static String get_message(String filename)
	{
		File file = new File(filename);
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
		    } 
		    catch (IOException e) 
		    {
		    	e.printStackTrace();
		    }
		}
		return output;
	}
	public static String[] get_keys(String filename)
	{
		File file = new File(filename);
		// this is a bit of a hack, but whatever 
		String[] output = new String[10];
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
		    } 
		    catch (IOException e) 
		    {
		    	e.printStackTrace();
		    }
		}
		return output;
	}
	public static void write_cypto_message(String message, BigInteger e, BigInteger n)
	{
		final String OUT_FILE = "encrypted_message.txt";
		BufferedWriter bw = null;
		FileWriter fw = null;

		try
		{
			// here is where i create encrypt the message
			String crypt_message = (new BigInteger(message.getBytes())).modPow(e, n).toString();
			fw = new FileWriter(OUT_FILE);
			bw = new BufferedWriter(fw);
			bw.write(crypt_message);

			System.out.println("Done");

		} catch (IOException error) {

			error.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	public static void main (String args[])
	{
		String key_file = "";
		String message_file = "";
		if(args.length == 2)
		{
			key_file = args[0];
			message_file = args[1];
		}
		else
		{
			System.out.println("Incorrect number of args.\nPlease give the key file, then the message file.\nTerminating program");
			System.exit(0);
		}

		String message = get_message(message_file);
		String[] keys = get_keys(key_file);
		BigInteger letter = new BigInteger("0");
		BigInteger E = new BigInteger(keys[4]);
		BigInteger N = new BigInteger(keys[5]);
		
		write_cypto_message(message,E,N);	   
	}
}