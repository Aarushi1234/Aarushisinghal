/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * 
 */
import java.io.BufferedWriter;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class Aes_m03254647 {
     KeyGenerator key;
    Cipher c;
    SecretKey sec;
    byte[] encrypted;
    IvParameterSpec ivParameterSpec, ivparameter;
    byte[] iv;
    SecretKeySpec s;
    SecureRandom rand;
    long encryptedtime;

    public void keygen() {
//function generating key of size 256 bits and intialization parameter of 128bits which is used for encryption and decryption .
        try {
            key = KeyGenerator.getInstance("AES");
            rand = new SecureRandom();
            key.init(256, rand);
            sec = key.generateKey();
            String s = DatatypeConverter.printHexBinary(sec.getEncoded());
            System.out.println("Key" +s);
           
            try {

                BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/alexbaer/desktop/key.txt"));
                writer.write(s);
                writer.close();
           } catch (Exception e) {

            }
        } catch (Exception e) {
            Logger.getLogger(Aesdemo.class.getName()).log(Level.SEVERE, null, e);
        }
    
    }
    public void Enc() {
//used for encryption of data by reading the key from the file and resulting ciphertext is written in cipher.txt file.
     StringBuilder text = new StringBuilder();
         StringBuilder ke = new StringBuilder();
        try {
            FileInputStream fis1 = new FileInputStream("/Users/alexbaer/desktop/key.txt");
            int y;
            do {
                y = fis1.read();
                if (y == -1) {
                    break;
                }
                ke.append((char)y);

            } while (true);
            System.out.println("key     " +ke.toString());

        } catch (Exception e) {

        }
        try {
            FileInputStream fis1 = new FileInputStream("/Users/alexbaer/desktop/plaintext.txt");
            int y;
            do {
                y = fis1.read();
                if (y == -1) {
                    break;
                }
                text.append((char) y);

            } while (true);
            System.out.println("plaintext Message     " + text);
        } catch (Exception e) {
            System.out.println("file doesnot exist" + e);
        }
        iv = rand.generateSeed(16);
        ivParameterSpec = new IvParameterSpec(iv);
        try{
                        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/alexbaer/desktop/iv.txt"));
                writer.write(DatatypeConverter.printHexBinary(ivParameterSpec.getIV()));
                writer.close();

        } catch (Exception e) {
            System.out.println("file doesnot exist" + e);
        }
        StringBuilder ivh= new StringBuilder();
        try{
            FileInputStream fis1 = new FileInputStream("/Users/alexbaer/desktop/iv.txt");
            int y;
            do {
                y = fis1.read();
                if (y == -1) {
                    break;
                }
                ivh.append((char) y);

            } while (true);
            System.out.println("ivh     " + ivh);
        } catch (Exception e) {
            System.out.println("file doesnot exist" + e);
        }  
        try {
        byte[] ivp= toByteArray(ivh.toString());
            ivparameter = new IvParameterSpec(ivp);

           byte []key1= toByteArray(ke.toString());
            s = new SecretKeySpec(key1, "AES");
            c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, s, ivparameter);
            encrypted = c.doFinal(text.toString().getBytes());
            String ci=DatatypeConverter.printHexBinary(encrypted);
           System.out.println("cipherText      " +ci);
           
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/alexbaer/desktop/ciphertext.txt"));
                writer.write(ci);
                writer.close();
        } catch (Exception e) {
            System.out.println("file doesnot exist" + e);

        }
        encryptedtime = System.nanoTime();
        System.out.println("time of encryption       "+encryptedtime);
    }
   
public static byte[] toByteArray(String s) {
    return DatatypeConverter.parseHexBinary(s);

}
    public void decryption() {
// Decrypt the text from cipher file by reading the same key from KeyGen algorithm and result is stored in presult file.
    {  StringBuilder ke = new StringBuilder();
        try {
            FileInputStream fis1 = new FileInputStream("/Users/alexbaer/desktop/key.txt");
            int y;
            do {
                y = fis1.read();
                if (y == -1) {
                    break;
                }
                ke.append((char)y);

            } while (true);
            System.out.println("key     " +ke.toString());

        } catch (Exception e) {

        }
          StringBuilder ivh= new StringBuilder();
        try{
            FileInputStream fis1 = new FileInputStream("/Users/alexbaer/desktop/iv.txt");
            int y;
            do {
                y = fis1.read();
                if (y == -1) {
                    break;
                }
                ivh.append((char) y);

            } while (true);
            System.out.println("ivh     " + ivh);
        } catch (Exception e) {
            System.out.println("file doesnot exist" + e);
        }
        StringBuilder ci= new StringBuilder();
        try{
            
            FileInputStream fis1 = new FileInputStream("/Users/alexbaer/desktop/ciphertext.txt");
            int y;
            do {
                y = fis1.read();
                if (y == -1) {
                    break;
                }
                ci.append((char)y);

            } while (true);
            System.out.println("ciphertext    "+ ci);
        } catch (Exception e) {
            System.out.println("file doesnot exist"+ e);
        }
            
        try {

           byte[]key1= toByteArray(ke.toString());

            byte[] ivp= toByteArray(ivh.toString());
         
            ivparameter = new IvParameterSpec(ivp);

           byte [] data= toByteArray(ci.toString());
            s = new SecretKeySpec(key1, "AES");
            c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, s,ivparameter);
            byte[] textDecrypted = c.doFinal(data);
            System.out.println("Decrypted Message    "+new String(textDecrypted));
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/alexbaer/desktop/result.txt"));
            writer.write(new String(textDecrypted));
            writer.close();
        } catch (Exception e) {
        }
        long decryptiontime = System.nanoTime();
        System.out.println("decrypted time     " + decryptiontime);
        long averagetime = decryptiontime - encryptedtime;
        System.out.println("Average Time taken" + averagetime);
    }}

    public void cbcmode() {
//function to show encryption and decryption in cbc mode using same secret key and text from filedata.txt(plaintext file).
        StringBuilder str = new StringBuilder();
        try {
            FileInputStream fis1 = new FileInputStream("/Users/alexbaer/desktop/plaintext.txt");
            int y;
            do {
                y = fis1.read();
                if (y == -1) {
                    break;
                }
                str.append((char) y);
            } while (true);}
            catch(Exception e){}
         StringBuilder ke= new StringBuilder();
         try 
         {
            FileInputStream fis1 = new FileInputStream("/Users/alexbaer/desktop/key.txt");
            int y;
            do {
                y = fis1.read();
                if (y == -1) {
                    break;
                }
                ke.append((char)y);
            } while (true);
            }
            catch(Exception e){}
         StringBuilder ivh= new StringBuilder();
        try{
            FileInputStream fis1 = new FileInputStream("/Users/alexbaer/desktop/iv.txt");
            int y;
            do {
                y = fis1.read();
                if (y == -1) {
                    break;
                }
                ivh.append((char) y);

            } while (true);
            System.out.println("ivh     " + ivh);
        } catch (Exception e) {
            System.out.println("file doesnot exist" + e);
        }
           try{ 
               byte[]key1= toByteArray(ke.toString());
            byte[] ivp= toByteArray(ivh.toString());
         ivparameter = new IvParameterSpec(ivp);
            s = new SecretKeySpec(key1, "AES");
            c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, s,ivparameter);
            byte[] ciphercbc = c.doFinal(str.toString().getBytes());
            System.out.println("plaintext stored in file    " + str);
            System.out.println("cipher by cbc    " + DatatypeConverter.printHexBinary(ciphercbc));
            c.init(Cipher.DECRYPT_MODE, s,ivparameter);
            byte[] decrycbc = c.doFinal(ciphercbc);
            System.out.println("Decrypted by cbc   " +new String(decrycbc));

        } catch (Exception ex) {
            Logger.getLogger(Aesdemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        long encryptedtimecbc = System.nanoTime();
        System.out.println("encryption time by cbc     " + encryptedtimecbc);
        long decryptiontimecbc = System.nanoTime();
        System.out.println("decrypted time by cbc      " + decryptiontimecbc);
        long averagetimecbc = decryptiontimecbc - encryptedtimecbc;
        System.out.println("Average time by cbc      " + averagetimecbc);}

    public void ecbmode() {
//function to show encryption and decryption in ecb mode using same secret key and reading same data from filedata.txt
        StringBuilder plaintext = new StringBuilder();
        try {
            FileInputStream fis1 = new FileInputStream("/Users/alexbaer/desktop/plaintext.txt");
            int y;
            do {
                y = fis1.read();
                if (y == -1) {
                    break;
                }
                plaintext.append((char)y);
            } while (true);
            c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, sec);
            byte[] cipherecb = c.doFinal(plaintext.toString().getBytes());
            System.out.println("plaintext stored in file   " + plaintext);
            System.out.println("cipher by ecb    " +DatatypeConverter.printHexBinary(cipherecb));
            c.init(Cipher.DECRYPT_MODE, sec);
            byte[] decryecb = c.doFinal(cipherecb);
            System.out.println("Decrypted by ecb    " + new String(decryecb));

        } catch (Exception ex) {
            Logger.getLogger(Aesdemo.class.getName()).log(Level.SEVERE, null, ex);
        }

        long encryptedtimeecb = System.nanoTime();
        System.out.println("encryption time by ecb     " + encryptedtimeecb);
        long decryptiontimeecb = System.nanoTime();
        System.out.println("decrypted time by ecb      " + decryptiontimeecb);
        long averagetimeecb = decryptiontimeecb - encryptedtimeecb;
        System.out.println("Average time by ecb     " + averagetimeecb);
    }

    public static void main(String[] args) {
        Aes_m03254647 a = new Aes_m03254647();
        a.keygen();
        a.Enc();
        a.decryption();
       // a.cbcmode();
         //a.ecbmode();
    }
    
}
