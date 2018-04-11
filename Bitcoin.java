
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.math.BigInteger;
    import java.security.MessageDigest;
import java.util.Random;

import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aarushisinghal
 */
public class Bitcoin {
    String m;
    String s;
     StringBuilder k = new StringBuilder();
      StringBuilder g= new StringBuilder(); 
     StringBuilder binary = new StringBuilder();
   StringBuffer target = new StringBuffer();
    StringBuffer difftarget = new StringBuffer();
   StringBuffer target1 = new StringBuffer();
   StringBuffer solution1 = new StringBuffer();
      StringBuffer input = new StringBuffer();
       StringBuffer giveninput = new StringBuffer();
        StringBuffer word1 = new StringBuffer();
        StringBuffer word2 = new StringBuffer();
    public int computingPower(int m, int e)
    { int r=1;
    
    while(e!=0)
    {
        if(e%2==0)
        {m= m*m;
        e=e/2;
        }
        else
        {
            r= r*m;
            e=e-1;           
        }
     
    }
         return(r);
    }
   int difficulty; 

 public void targetfun(){ 
      try{

Scanner s=new Scanner(System.in);
System.out.println("enter the difficulty level");
 difficulty= s.nextInt();
 int a=computingPower(2,difficulty);
 System.out.println("power of 2     "+a);
 System.out.println("binary representation of power of 2    "+Integer.toBinaryString(a));
			for (int i = 0; i <difficulty; i++) {
                                target.append("0");
       
                            }
           
                            for(int i=difficulty-1;i<256;i++)
                            {
                                target.append("1");
                            }
               try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/aarushisinghal/desktop/target.txt"));
                writer.write(target.toString());
                writer.close();
            } catch (Exception e) {
                System.out.println("file doesnot exist" + e);
            }
			  
      } catch(Exception e)
      {}   
       }
   
 public void solutiongen(){
     String message = "";
      try {
            FileInputStream fis = new FileInputStream("/Users/aarushisinghal/desktop/target.txt");

            do {
                int i = fis.read();
                if (i == -1) {
                    break;
                }
              binary.append((char)i);
            }while (true);
            }catch(Exception e){}
       
                 System.out.println("target         "+binary);
         try {
            FileInputStream fis = new FileInputStream("/Users/aarushisinghal/desktop/input.txt");

            do {
                int i = fis.read();
                if (i == -1) {
                    break;
                }
                k.append((char)i);
            }while (true);
            }catch(Exception e){}
         
          String sol;
           
            
                 
        try {
            do{
                StringBuilder ch = new StringBuilder();
                Random rnd= new Random();
                 
        BigInteger solution = new BigInteger(32,rnd);
          BigInteger tar =new BigInteger(target.toString());
  
          BigInteger hashvalue ;
                
                   sol =solution.toString();
                  String word= k.toString()+sol;
             MessageDigest digest;	  
            digest = MessageDigest.getInstance("SHA-256");
            digest.update(word.getBytes());
            byte[] hash = digest.digest();
            
                    for (byte b : hash) {
                        int val = b;
                        for (int j = 0; j < 8; j++) {
                            ch.append((val & 128) == 0 ? 0 : 1);
                            val <<= 1;
                        }
                    }
           hashvalue =new BigInteger(ch.toString());
            
                 
             System.out.println("hashvalue      "+ch.toString()); 
            
           if(hashvalue.compareTo(tar)<=0)
           {
               break;
           }
           
            
            }while(true);
            
          
                    for (byte b : sol.getBytes()) {
                        int val = b;
                        for (int j = 0; j < 8; j++) {
                            g.append((val & 128) == 0 ? 0 : 1);
                            val <<= 1;
                        }
                    }
                    System.out.println("solution         "+g);
                    
            
               try {
               BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/aarushisinghal/desktop/solution.txt"));
               writer.write(g.toString());
               writer.close();
            } catch (Exception e) {
                System.out.println("file doesnot exist" + e);
            }    
            
        
    
        }
         catch(Exception e){}  }
 
 public void verification()
 {
     try {
            FileInputStream fis = new FileInputStream("/Users/aarushisinghal/desktop/input.txt");

            do {
                int i = fis.read();
                if (i == -1) {
                    break;
                }
              input.append((char)i);
            }while (true);
            }catch(Exception e){}
       
              System.out.println(input);
 
 
 try {
            FileInputStream fis = new FileInputStream("/Users/aarushisinghal/desktop/target.txt");

            do {
                int i = fis.read();
                if (i == -1) {
                    break;
                }
              target1.append((char)i);
            }while (true);
            }catch(Exception e){}
       
                 System.out.println(target);
 
 
 try {
            FileInputStream fis = new FileInputStream("/Users/aarushisinghal/desktop/solution.txt");

            do {
                int i = fis.read();
                if (i == -1) {
                    break;
                }
              solution1.append((char)i);
            }while (true);
            }catch(Exception e){}
       
                 System.out.println(solution1);
 
 String check= input.toString()+ solution1.toString();
  try {
             MessageDigest digest;	  
            digest = MessageDigest.getInstance("SHA-256");
            
            byte[] hash = digest.digest(check.getBytes());
                    for (byte b : hash) {
                        int val = b;
                        for (int j = 0; j < 8; j++) {
                            word1.append((val & 128) == 0 ? 0 : 1);
                            val <<= 1;
                        }    
                     }
                   
                   
                    BigInteger tar= new BigInteger(target1.toString(),2);
                     BigInteger hashvalue= new BigInteger(word1.toString(),2);
                     
                     if(hashvalue.compareTo(tar)<=0)
                     {
                         System.out.println("solution is valid    "+1);
                     }
                     else
                     {
                         System.out.println("solution is not valid   "+0);
                     }

  }catch(Exception e){}
 }
 public void differentsolution()
 {
      try {
            FileInputStream fis = new FileInputStream("/Users/aarushisinghal/desktop/input.txt");

            do {
                int i = fis.read();
                if (i == -1) {
                    break;
                }
              giveninput.append((char)i);
            }while (true);
            }catch(Exception e){}
       
              System.out.println(giveninput);
             
            StringBuffer difftarget1 = new StringBuffer(); 
             StringBuffer difftarget2 = new StringBuffer();
              StringBuffer difftarget3 = new StringBuffer();
               StringBuffer difftarget4 = new StringBuffer();
                StringBuffer difftarget5 = new StringBuffer();
               
           
                   
                        for (int j = 0; j <=21; j++) {
                                difftarget.append("0");
       
                            }
           
                            for(int f=22-1;f<256;f++)
                            {
                                 difftarget.append("1");
                            }
                            //System.out.println("first target      "+difftarget);
                             for (int j = 0; j <=22; j++) {
                                difftarget1.append("0");
       
                            }
           
                            for(int f=23;f<256;f++)
                            {
                                 difftarget1.append("1");
                            }
                           // System.out.println("second target     "+difftarget1);
                            for (int j = 0; j <=23; j++) {
                                difftarget2.append("0");
       
                            }
           
                            for(int f=24;f<256;f++)
                            {
                                 difftarget2.append("1");
                            }
                           // System.out.println("third target      "+difftarget2);
                              for (int j = 0; j <=24; j++) {
                                difftarget3.append("0");
       
                            }
           
                            for(int f=25;f<256;f++)
                            {
                                 difftarget3.append("1");
                            }
                           // System.out.println("fourth target     "+difftarget3);
                           
                              for (int j = 0; j <=25; j++) {
                                difftarget4.append("0");
       
                            }
           
                            for(int f=26;f<256;f++)
                            {
                                 difftarget4.append("1");
                            }
                            //System.out.println("fifth target      "+difftarget4);
                            for (int j = 0; j <=26; j++) {
                                difftarget5.append("0");
       
                            }
           
                            for(int f=27;f<256;f++)
                            {
                                 difftarget5.append("1");
                            }
                           //  System.out.println("sixth target      "+difftarget5);
                            
                            
                            
                   long start=   System.nanoTime(); 
                   System.out.println("starting time           "+start+    "nanoseconds");
              try {
                  
                  String sol;
            do{
                
            
                StringBuilder ch = new StringBuilder();
                Random rnd= new Random();
                 
        BigInteger solution = new BigInteger(32,rnd);
          BigInteger tar =new BigInteger(difftarget.toString());
  
          BigInteger hashvalue ;
                
                   sol =solution.toString();
                  String word= k.toString()+sol;
             MessageDigest digest;	  
            digest = MessageDigest.getInstance("SHA-256");
            
            byte[] hash = digest.digest(word.getBytes());
                    for (byte b : hash) {
                        int val = b;
                        for (int j = 0; j < 8; j++) {
                            ch.append((val & 128) == 0 ? 0 : 1);
                            val <<= 1;
                        }
                    }
           hashvalue =new BigInteger(ch.toString());
            
                 
             System.out.println("hashvalue      "+ch.toString()); 
            
           if(hashvalue.compareTo(tar)<=0)
           {
               break;
           }
           
            
            }while(true);
            System.out.println("solution   "+sol);
             }catch(Exception e){}
               
              
//             try {
//                    String sol;
//            do{
//             
//            
//                StringBuilder ch = new StringBuilder();
//                Random rnd= new Random();
//                 
//        BigInteger solution = new BigInteger(32,rnd);
//          BigInteger tar =new BigInteger(difftarget1.toString());
//  
//          BigInteger hashvalue ;
//                
//                   sol =solution.toString();
//                  String word= k.toString()+sol;
//             MessageDigest digest;	  
//            digest = MessageDigest.getInstance("SHA-256");
//            
//            byte[] hash = digest.digest(word.getBytes());
//                    for (byte b : hash) {
//                        int val = b;
//                        for (int j = 0; j < 8; j++) {
//                            ch.append((val & 128) == 0 ? 0 : 1);
//                            val <<= 1;
//                        }
//                    }
//           hashvalue =new BigInteger(ch.toString());
//            
//                 
//             System.out.println("hashvalue      "+ch.toString()); 
//            
//           if(hashvalue.compareTo(tar)<=0)
//           {
//               break;
//           }
//           
//            
//            }while(true);
//             System.out.println("solution   "+sol);
//             }catch(Exception e){}
//             try {
//                  String sol;
//            do{
//                
//            
//                StringBuilder ch = new StringBuilder();
//                Random rnd= new Random();
//                 
//        BigInteger solution = new BigInteger(32,rnd);
//          BigInteger tar =new BigInteger(difftarget2.toString());
//  
//          BigInteger hashvalue ;
//                
//                   sol =solution.toString();
//                  String word= k.toString()+sol;
//             MessageDigest digest;	  
//            digest = MessageDigest.getInstance("SHA-256");
//            
//            byte[] hash = digest.digest(word.getBytes());
//                    for (byte b : hash) {
//                        int val = b;
//                        for (int j = 0; j < 8; j++) {
//                            ch.append((val & 128) == 0 ? 0 : 1);
//                            val <<= 1;
//                        }
//                    }
//           hashvalue =new BigInteger(ch.toString());
//            
//                 
//             System.out.println("hashvalue      "+ch.toString()); 
//            
//           if(hashvalue.compareTo(tar)<=0)
//           {
//               break;
//           }
//           
//            
//            }while(true);
//             System.out.println("solution   "+sol);
//             }catch(Exception e){}
//                                         
//            try {
//                 String sol;
//            do{
//               
//            
//                StringBuilder ch = new StringBuilder();
//                Random rnd= new Random();
//                 
//        BigInteger solution = new BigInteger(32,rnd);
//          BigInteger tar =new BigInteger(difftarget3.toString());
//  
//          BigInteger hashvalue ;
//                
//                   sol =solution.toString();
//                  String word= k.toString()+sol;
//             MessageDigest digest;	  
//            digest = MessageDigest.getInstance("SHA-256");
//            
//            byte[] hash = digest.digest(word.getBytes());
//                    for (byte b : hash) {
//                        int val = b;
//                        for (int j = 0; j < 8; j++) {
//                            ch.append((val & 128) == 0 ? 0 : 1);
//                            val <<= 1;
//                        }
//                    }
//           hashvalue =new BigInteger(ch.toString());
//            
//                 
//             System.out.println("hashvalue      "+ch.toString()); 
//            
//           if(hashvalue.compareTo(tar)<=0)
//           {
//               break;
//           }
//           
//            
//            }while(true);
//             System.out.println("solution   "+sol);
//            }catch(Exception e){}
//             try {
//                  String sol;
//            do{
//               
//            
//                StringBuilder ch = new StringBuilder();
//                Random rnd= new Random();
//                 
//        BigInteger solution = new BigInteger(32,rnd);
//          BigInteger tar =new BigInteger(difftarget4.toString());
//  
//          BigInteger hashvalue ;
//                
//                   sol =solution.toString();
//                  String word= k.toString()+sol;
//             MessageDigest digest;	  
//            digest = MessageDigest.getInstance("SHA-256");
//            
//            byte[] hash = digest.digest(word.getBytes());
//                    for (byte b : hash) {
//                        int val = b;
//                        for (int j = 0; j < 8; j++) {
//                            ch.append((val & 128) == 0 ? 0 : 1);
//                            val <<= 1;
//                        }
//                    }
//           hashvalue =new BigInteger(ch.toString());
//            
//                 
//             System.out.println("hashvalue      "+ch.toString()); 
//            
//           if(hashvalue.compareTo(tar)<=0)
//           {
//               break;
//           }
//           
//            
//            }while(true);
//              System.out.println("solution   "+sol);}
//                    catch(Exception e){}
//                try {
//                  String sol;
//            do{
//               
//            
//                StringBuilder ch = new StringBuilder();
//                Random rnd= new Random();
//                 
//        BigInteger solution = new BigInteger(32,rnd);
//          BigInteger tar =new BigInteger(difftarget5.toString());
//  
//          BigInteger hashvalue ;
//                
//                   sol =solution.toString();
//                  String word= k.toString()+sol;
//             MessageDigest digest;	  
//            digest = MessageDigest.getInstance("SHA-256");
//            
//            byte[] hash = digest.digest(word.getBytes());
//                    for (byte b : hash) {
//                        int val = b;
//                        for (int j = 0; j < 8; j++) {
//                            ch.append((val & 128) == 0 ? 0 : 1);
//                            val <<= 1;
//                        }
//                    }
//           hashvalue =new BigInteger(ch.toString());
//            
//                 
//             System.out.println("hashvalue      "+ch.toString()); 
//            
//           if(hashvalue.compareTo(tar)<=0)
//           {
//               break;
//           }
//           
//            
//            }while(true);
//              System.out.println("solution   "+sol);}
//                    catch(Exception e){}
                long stop=  System.nanoTime();
               long total= stop-start;
               System.out.println("time              "+total+    "nanoseconds");
              
//                    
                    
                     
  }
      public static void main(String [] args)
      {
          Bitcoin b= new Bitcoin();
          b.targetfun(); 
          b.solutiongen();
         b.verification();
          //b.differentsolution();
          
      }
  }

    

