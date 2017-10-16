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
  public static List<Byte> get_message(String filename)
  {
    List<Byte> list = new ArrayList<Byte>();
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
  public static void main(String[] args) throws IOException
  {
    // String key_file = "";
    // String message_file = "";
    // if(args.length == 2)
    // {
    //   key_file = args[0];
    //   message_file = args[1];
    // }
    // else
    // {
    //   System.out.println("Incorrect number of args.\nPlease give the key file, then the message file.\nTerminating program");
    //   System.exit(0);
    // }

    // String[] keys = get_keys(key_file);
    // BigInteger letter = new BigInteger("0");
    // BigInteger E = new BigInteger(keys[4]);
    // BigInteger N = new BigInteger(keys[5]);
     
    String message_file = "face.jpg";

    FileOutputStream out = null;
    List<Byte> message; 
    try
    {
      out = new FileOutputStream(new String("encrypted_" + message_file));
      message  = get_message(message_file);
      for (int i = 0; i < message.size(); i++) {
        if(i % 100 == 0) System.out.println(message.get(i));
      out.write(message.get(i));
    }      
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      if (out != null) 
      {
      out.close();
      }
    }
  }
}