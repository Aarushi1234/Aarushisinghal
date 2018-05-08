/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author aarushisinghal
 */
public class Rsa {
    private static String bytesToString(byte[] encrypted) {
 
		String test = "";
 
		for (byte b : encrypted) {
 
			test += Byte.toString(b);
 
		}
 
		return test;
 
    }
    public static void main(String[] args) {
        
    //Declarations
    BigInteger p;
    BigInteger q;
    BigInteger k;
    BigInteger phin, d;
    BigInteger e;
    BigInteger cip;
    String stringmsg;
    byte[] bytemsg;
    long stime;
    byte [] bytecip;
    String stringcip;
    String privatekey;
    long etime;
    byte[] privatebyte;
    BigInteger Priv;
    byte[] decryptedmsg;
    long detime;
    String dmsg;

    Scanner in =new Scanner(System.in);
    
    System.out.println("Enter your msg to be encrypted!");
        
        stringmsg=in.nextLine();
        //BigInteger msg=in.nextBigInteger();'
        System.out.println("Your Message : "+stringmsg);
        bytemsg=stringmsg.getBytes();
        //System.out.println("Byte nsg : "+bytemsg);
        stime=System.nanoTime();
    Random rnd = new Random();
    p=BigInteger.probablePrime(1024, rnd);
    q=BigInteger.probablePrime(1024, rnd);
    
    k=p.multiply(q);
      phin=(p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
    
    e=BigInteger.valueOf(65537);
    
    bytecip=new BigInteger(bytemsg).modPow(e, k).toByteArray();
    stringcip=bytesToString(bytecip);
    etime=System.nanoTime()-stime;
        //System.out.println(stringcip);
        System.out.println("Time for encryption : "+etime+" nano secs");
            long dstime=System.nanoTime();
            d = e.modInverse(phin);
            
            //byte[] byteprivatekey= d.toByteArray();
            //String privatekey = bytesToString(byteprivatekey);
            //System.out.println("private key1 : "+privatekey);
            System.out.println("value of bigdecimal d : "+d);
            //String stringprivatekey = new String(byteprivatekey);
            //System.out.println("private key : "+stringprivatekey);
            
                    privatekey = d.toString(32);
                    
            System.out.println("Encoded private key : "+privatekey);
            
            
            
            // privatebyte = privatekey.getBytes();
            // Priv = new BigInteger(privatebyte);
            //System.out.println("Private key d generated from hexadecimal : "+Priv);
            BigInteger receivedkey = new BigInteger(privatekey, 32);
            //System.out.println("private key : "+receivedkey);  
            
            
       decryptedmsg=new BigInteger(bytecip).modPow(d, k).toByteArray();
       detime=System.nanoTime()-dstime;
       dmsg=new String(decryptedmsg);
       if(d==receivedkey){
                System.out.println("Successful");
            }
        //System.out.println("decrypted bytes to string: "+bytesToString(decryptedmsg));
        System.out.println("decrypted string: "+ dmsg);
        System.out.println("Time for decryption : "+detime+" nano secs");
        }
}

    

