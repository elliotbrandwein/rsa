import java.math.*;
import java.io.*;
import java.util.*;
import java.security.SecureRandom;
public class rsa
{

	public final static int KEY_SIZE = 1025;
	// I assume E is relativaly prime to our key because it's prime 
	// and our key is made of 2 primes that are larger then 65537
	public final static BigInteger E = new BigInteger("65537");


	public static BigInteger getPrimeNumber(int size, Random seed)
	{
		BigInteger output = BigInteger.probablePrime(size, seed);
		while(!output.isProbablePrime(40))
		{
			output = BigInteger.probablePrime(size, seed);
		}
		return output;
	}



	public static void  main (String args[])
	{
		// step 1: generate our two large primes
		BigInteger p = new BigInteger("0");
		BigInteger q = new BigInteger("0");
		Random seed_p = new SecureRandom();
		Random seed_q = new SecureRandom();
		p = getPrimeNumber(KEY_SIZE,seed_p);
		q = getPrimeNumber(KEY_SIZE,seed_q);

		// bonus step: make sure p! = q;
		while(p.compareTo(q) == 0)
		{
			q = getPrimeNumber(KEY_SIZE,seed_q);
		}

		//step 2: create our key n, such that n = p*q
		BigInteger n = p.multiply(q);

		//step 3: create a small int e such that it is relativly prime to phi(n)
		BigInteger phi = (p.subtract(new BigInteger("1")));
		phi = phi.multiply(q.subtract(new BigInteger("1")));
		// I will assume 65537 is always good, which will be our E  
		
		//step 4: get an integer d such that d * e === 1 mod(phi)
		BigInteger d = E.modInverse(phi);

		// hack: we output the keys and pipe via bash to file
		System.out.println("Private key:\n"+ d + "\n" + n + "\nPublic key:\n" + E + "\n" + n );
		
		
	}	
}