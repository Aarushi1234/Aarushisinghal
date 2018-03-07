/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

import java.nio.charset.StandardCharsets;

import java.security.SecureRandom;

import javax.crypto.BadPaddingException;


import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;

import javax.crypto.spec.SecretKeySpec;


/**
 *
 * @author aarushisinghal
 */
public class Aes {
     StringBuilder text = new StringBuilder();
        StringBuilder key = new StringBuilder();
        StringBuilder intiv = new StringBuilder();
    Cipher c;
   
    byte[] iv = new byte[64/Byte.SIZE];
      byte[] aes256KeyData = new byte[128/Byte.SIZE];
      byte[] plaintext;
      SecretKey aesKey;
       byte[] encrypted;
      
      
    public void Enc() {
       
     
        try {

            FileInputStream fis1 = new FileInputStream("/Users/aarushisinghal/desktop/filedata.txt");
            int y;
            do {
                y = fis1.read();
                if (y == -1) {
                    break;
                }
                text.append((char)y); 

            } while (true);
            System.out.println("plaintext    "+text);
        } catch (Exception e) {
            System.out.println("file doesnot exist" + e);
        }
        try {

            FileInputStream fis1 = new FileInputStream("/Users/aarushisinghal/desktop/keyGen.txt");
            int y;
            do {
                y = fis1.read();
                if (y == -1) {
                    break;
                }
                key.append((char) y);
            } while (true);
            System.out.println("key      "+key);
            
        } catch (Exception e) {
            System.out.println("file doesnot exist" + e);
        }
        
        final SecureRandom prng = new SecureRandom();
        
        prng.nextBytes(iv);
        final SecretKey initialVector = new SecretKeySpec(iv, "Intialization Vector");

     //   System.out.println("iv      " +toHex(initialVector.getEncoded()));
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/aarushisinghal/desktop/iv.txt"));
            writer.write(toHex(initialVector.getEncoded()));
            writer.close();
        } catch (Exception e) {
            System.out.println("file doesnot exist" + e);

        }
       
         try {

            FileInputStream fis1 = new FileInputStream("/Users/aarushisinghal/desktop/iv.txt");
            int y;
            do {
                y = fis1.read();
                if (y == -1) {
                    break;
                }
                 intiv.append((char) y);
            } while (true);
            System.out.println("IV      " +intiv);
            
        } catch (Exception e) {
            System.out.println("file doesnot exist" + e);
        }
        
         
        // plaintext= text.toString().getBytes(StandardCharsets.UTF_8);    
        try{ 
              byte[] startingkey= key.toString().getBytes(StandardCharsets.UTF_8);
         aesKey = new SecretKeySpec(startingkey,"AES" );
            c=Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE,aesKey);
          encrypted =c.doFinal((text.toString().getBytes(StandardCharsets.UTF_8)));
            System.out.println("cipher    "+encrypted);
           }catch(Exception e){
        System.out.print(e);
    }}
      
   

    public void keyGen() {
        
final SecureRandom prng = new SecureRandom();
       
        prng.nextBytes(aes256KeyData);
         SecretKey aesKey = new SecretKeySpec(aes256KeyData, "AES");
         System.out.println(aesKey.getFormat());
         System.out.println(aesKey.getEncoded());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/aarushisinghal/desktop/keyGen.txt"));
            writer.write(toHex(aesKey.getEncoded()));
            writer.close();
        } catch (Exception e) {
            System.out.println("file doesnot exist" + e);

        }}
   
    public String toHex(final byte[] data) {
         StringBuilder sb = new StringBuilder(data.length*2);
        for (final byte b : data) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();

    }

    public void decrypt() throws IllegalBlockSizeException, BadPaddingException  {
        
         try { 
              byte[] startingkey= key.toString().getBytes(StandardCharsets.UTF_8);
              SecretKeySpec secretkey = new SecretKeySpec(startingkey,"AES" );
            
        Cipher cipher = Cipher.getInstance("AES");
    
             cipher.init(Cipher.DECRYPT_MODE, secretkey);
             plaintext = c.doFinal(encrypted); 
           System.out.println("plaintext"  +new String(plaintext));
           
         } catch (Exception e) {
         
         }
    }
 
    public static void main(String abc[]) throws IllegalBlockSizeException, BadPaddingException  {
        Aes a = new Aes();
        a.keyGen();

       a.Enc();
       
        a.decrypt();
    }

}
