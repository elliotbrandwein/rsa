import java.math.*;
import java.util.*;
import java.io.*;
import java.nio.*;

public class encrypt_file {
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
    // final String OUTFILE = ( "encrypted_" + filename );
    final String OUTFILE = ( "test.txt");
    try
    {
      FileOutputStream out = new FileOutputStream(OUTFILE);
      
      byte[] byteArray = new byte[message.size()];
      int index = 0;
      for (byte b : message)
      {
        byteArray[index++] = b;
      }
      // cannot test this works until decrypt works
      // crypt_message = (new BigInteger(byteArray).modPow(e, n);
      // String crypt_message = (new BigInteger(message.getBytes())).modPow(e, n).toString();
      // byteArray = new BigInteger(byteArray).toString().getBytes();
      BigInteger test = new BigInteger(byteArray);
      byte[] array = test.toByteArray();
      array = Arrays.copyOfRange(array, 0, array.length); 
      byte temp = array[1];
      // array[0] = array[1];
      // array[1] = temp;     
      out.write(temp);
      System.out.println("Done");
      out.close();
    } 
    catch (IOException error)
    {
      error.printStackTrace();
    } 

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
    ArrayList<Byte> message; 
    message = get_message(message_file);
    write_cypto_message(message,message_file,E,N);
    // try
    // {
    //   out = new FileOutputStream(new String("encrypted_" + message_file));
    //   message  = get_message(message_file);
    //   for (int i = 0; i < message.size(); i++) {
    //     if(i % 100 == 0) System.out.println(message.get(i));
    //   out.write(message.get(i));
    // }      
    
  
  }
}