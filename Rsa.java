/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
import javax.crypto.KeyGenerator;
import javax.crypto.Cipher;
import javax.crypto.Mac;
/**
 *
 * @author aarushi
 */
public class Rsa {
    
    
//    BigInteger pow(BigInteger base) {
//        return base.multiply(base);
//     
//   
//  
//}
//    
//BigInteger j(BigInteger base)       
//{
// BigInteger a = BigInteger.ONE;
//        BigInteger b = new BigInteger(base.shiftRight(5).add(new BigInteger("8")).toString());
//        while(b.compareTo(a) >= 0) {
//          BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
//          if(mid.multiply(mid).compareTo(base) > 0) b = mid.subtract(BigInteger.ONE);
//          else a = mid.add(BigInteger.ONE);
//        }
//        return a.subtract(BigInteger.ONE);
//      }




    public static void main(String[] args)
    {
        BigInteger p;
        BigInteger q;
        BigInteger e,d,cip,h,l;
        BigInteger k, phin;
        int y;
        System.out.println("Enter your msg to be encrypted!");
        Scanner s= new Scanner(System.in);
        BigInteger msg= s.nextBigInteger();
     //   String msg= s.next();
 //byte[] bytemsg=msg.getBytes();
   //    BigInteger m=new BigInteger(bytemsg);
        Rsa r= new Rsa();
        
   long stime=System.nanoTime();
   Random rnd = new Random();
   int x= rnd.nextInt(1024)+900;
   y= rnd.nextInt(1148)+1024;
   
   p=BigInteger.probablePrime(x,rnd);
   q=BigInteger.probablePrime(y, rnd);
   
   
    k=(p.multiply(q));
   
   phin=(p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
   e=BigInteger.valueOf(65537);
   
     d =e.modInverse(phin);
     //h=r.pow(m);
     
   cip=msg.modPow(e, k).modPow(e, k);
   long etime=System.nanoTime()-stime;
   System.out.println(cip);
   System.out.println("Time for encryption : "+etime);
   long dstime=System.nanoTime();
    msg=cip.modPow(d, k).modPow(d, k);
   //l=r.j(m);
   
   //String u=l.toString();
    System.out.println("\n"+msg);
   long detime=System.nanoTime()-dstime;
   //System.out.println("\n"+l);
   System.out.println("Time for decryption : "+detime);
  
    }
}
    


