import java.lang.*;
import java.io.*;
import java.nio.file.*;
import java.math.*;
public class DecryptFile
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
  
  public static void write_out_message(String message,String filename, BigInteger d, BigInteger n)
  {
    BufferedWriter bw = null;
    FileWriter fw = null;

    try
    {

      fw = new FileWriter(filename);
      bw = new BufferedWriter(fw);
      // this line actually does the decryption 
      message = new String((new BigInteger(message)).modPow(d, n).toByteArray());
      bw.write(message);

      
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
    System.out.println("Done");
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

    else
    {
      System.out.println("Incorrect number of args.\nPlease give the key file, then the encrypted file, then the output file.\nTerminating program");
      System.exit(0);
    }
    
    String[] keys = get_keys(key_file);
    BigInteger D = new BigInteger(keys[1]);
    BigInteger N = new BigInteger(keys[5]);
    ArrayList<Byte> crypto_message = get_message(crypto_file);
    write_out_message(crypto_message,out_file,D,N);
  }
}