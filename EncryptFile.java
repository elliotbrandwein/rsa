import java.math.*;
import java.util.*;
import java.io.*;
import java.nio.*;

public class EncryptFile {
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
          output[index++] = line;
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
  public static ArrayList<Byte> get_message(String filename)
  {
    ArrayList<Byte> list = new ArrayList<Byte>();
    try 
    {
      FileInputStream in = new FileInputStream(filename); 
      int c;
      while ((c = in.read()) != -1)
      {
        list.add( (byte) c );
      }
      in.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return list;
  }
  
  public static void write_cypto_message(ArrayList<Byte> message, String filename, BigInteger e, BigInteger n)
  {
    if(message.size() > 245)
    {
      System.out.println("this message is too big to be encrypted using RSA only");
      System.exit(1);
    }
    final String OUTFILE = ("encrypted_" + filename);
    System.out.println("Message was  " + message.size() + " byte(s)");
    try
    {
      FileOutputStream out = new FileOutputStream(OUTFILE);
      byte[] byteArray = new byte[message.size() + 1];
      int index = 1;
      for (byte b : message)
        byteArray[index++] = b;
      byteArray[0] = 0x01;
      out.write(new BigInteger(byteArray).modPow(e,n).toByteArray());
      out.close();
    } 
    catch (IOException error)
    {
      error.printStackTrace();
    } 
    System.out.println("Encryption complete");
  }
  
  public static void main(String[] args) throws IOException
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
    String[] keys = get_keys(key_file);
    BigInteger E = new BigInteger(keys[4]);
    BigInteger N = new BigInteger(keys[5]);
    ArrayList<Byte> message = get_message(message_file);
    write_cypto_message(message,message_file,E,N);  
  }
}