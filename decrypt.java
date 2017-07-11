import java.lang.*;
import java.io.*;
import java.nio.file.*;
import java.math.*;
public class decrypt
{
	public static String[] get_keys(String filename)
	{
		File file = new File(filename);
		// same hack as before, probably should make this array dynamic 
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
	public static int get_line_count(String filename)
	{
		File file = new File(filename);
		BufferedReader reader = null;
		int line_count = 0;
		try {
		    reader = new BufferedReader(new FileReader(file));
		    while (reader.readLine() != null) 
		        line_count++;
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
		    	e.printStackTrace();
		    }
		}
		return line_count;
	}
	public static void write_out_message(String filename,String message)
	{
		BufferedWriter bw = null;
		FileWriter fw = null;

		try
		{

			fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
			bw.write(message);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

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
		String crypto_file = "";
		String out_file = "";
		if(args.length == 3)
		{
			key_file = args[0];
			crypto_file = args[1];
			out_file = args[2];
		}
		File file = new File(crypto_file);
		String[] input = new String[get_line_count(crypto_file)];
		BufferedReader reader = null;
		int line_count = 0;	
		try {
			String line = "";
		    reader = new BufferedReader(new FileReader(file));
		    while ((line = reader.readLine()) != null) 
		    {
		        input[line_count++] = line;
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
		    	e.printStackTrace();
		    }
		}
		BigInteger cypher_text = new BigInteger("0");
		String[] keys = get_keys(key_file);
		BigInteger D = new BigInteger(keys[1]);
		BigInteger N = new BigInteger(keys[5]);
		int ascii_text = 0;
		String plain_text_message = "";

		for(int i = 0; i < line_count; i++)
		{
			cypher_text = new BigInteger(input[i]);
			cypher_text = cypher_text.modPow(D,N);
			ascii_text = Integer.valueOf(cypher_text.toString());
			plain_text_message += String.valueOf((char) ascii_text);
		}
		write_out_message(out_file,plain_text_message);
		
	}
}