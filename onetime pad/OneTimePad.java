/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

import java.io.*;
import java.security.SecureRandom;
public class OneTimePad {

    StringBuilder binary = new StringBuilder();
    StringBuilder newkey = new StringBuilder();
    StringBuilder encryptioncipher = new StringBuilder();
    StringBuilder cipher = new StringBuilder();
    StringBuilder keyretrieved = new StringBuilder();
    StringBuilder decryptioncipher = new StringBuilder();
    StringBuilder frequencychecker = new StringBuilder();
    StringBuilder k = new StringBuilder();
    StringBuilder textfromfile = new StringBuilder();

    public void Enc() 
//this function is used to read key from key.txt file and plaintext from plaintext.txt file and then implement xor and generate cipher text which is stored in the ciphertext.txt file
    {
        try {
            System.out.println("message can't be more than 4 letters");
            FileInputStream fis1 = new FileInputStream("/Users/aarushisinghal/desktop/key.txt");
            int y;
            do {
                y = fis1.read();
                if (y ==-1) {
                    break;
                }
                k.append((char)y);
            } while (true);
            System.out.println("key  "+k);
        } catch (Exception e) {
            System.out.println("file doesnot exist" + e);
        }
        char[] a = {};
        char[] v = {};

        try {
            FileInputStream fis = new FileInputStream("/Users/aarushisinghal/desktop/plaintext.txt");

            do {
                int i = fis.read();
                if (i == -1) {
                    break;
                }
                String text = String.valueOf((char)i);
                textfromfile.append(text);
                    byte[] msg = text.getBytes();
                    for (byte b : msg) {
                        int val = b;
                        for (int j = 0; j < 8; j++) {
                            binary.append((val & 128) == 0 ? 0 : 1);
                            val <<= 1;
                        }
                    }                 
            } while (true);
             if (binary.length() ==k.length()){
            System.out.println("message  " +textfromfile);
            System.out.println("plaintext  " +binary);
             for (int g = 0; g < binary.length(); g++) {
            a = binary.toString().toCharArray();
        }
        for (int q = 0; q < k.length(); q++) {
            v = k.toString().toCharArray();
        }
        for (int x = 0; x <k.length(); x++) {
            if (a[x] == v[x]) {
                encryptioncipher.append("0");
            } else {
                encryptioncipher.append("1");
            }
        }
        try {
            FileOutputStream writer = new FileOutputStream("/Users/aarushisinghal/desktop/ciphertext.txt", true);
            PrintStream out = new PrintStream(writer, true);
            out.print(encryptioncipher);
        } catch (Exception e) {
            System.out.println("File doesnot exist" +e);
        }
        System.out.println("ciphertext " + encryptioncipher);
System.out.println("encryption time " +System.nanoTime());
    }       
           else {
                    System.out.println("length of the message is incorrect");
                }
        } catch (Exception e) {
            System.out.println("file doesnot exist");
        }
        
    }
    public void keygeneration()
//this function is used to generate random secure key with length betwee 1 to 128 bits and storing it in newkey.txt file
    {
        SecureRandom random = new SecureRandom();
        final byte[] key;
        int sp = random.nextInt(16);
        if (sp >= 1) {
            key = new byte[sp];
            random.nextBytes(key);
            for (byte b : key) {
                int val = b;
                for (int j = 0; j < 8; j++) {
                    newkey.append((val & 128) == 0 ? 0 : 1);
                    val <<= 1;
                }
            }
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/aarushisinghal/desktop/newkey.txt"));
                writer.write(newkey.toString());
                writer.close();
            } catch (Exception e) {
                System.out.println("file doesnot exist" + e);
            }
            System.out.println("random key generation  " +newkey);
        }
    }

    public void keyfrequencychecker() 
//for checking frequency of each random 3 bit key which is randomly generated and checking whether it is uniformly sistributed or not
    {
        SecureRandom random = new SecureRandom();
        int sp = random.nextInt(999) + 100;
        String o = Integer.toString(sp);
        if (o.length() == 3) {
            while (sp != 0) {
                if (sp % 2 == 0) {
                    frequencychecker.append("0");
                    sp = sp / 10;
                } else {
                    frequencychecker.append("1");
                    sp = sp / 10;
                }
            }
        }
        System.out.println("3 bit key " + frequencychecker.append("   "));
        int count = 0;
        String text = frequencychecker.toString();
        try {
            String[] keys = text.split("   ");
            String[] uniqueKeys;
            uniqueKeys = getUniqueKeys(keys);
            for (String key : uniqueKeys) {
                for (String s : keys) {
                    if (key.equals(s)) {
                        count++;
                    }
                }
                System.out.println("Count of [" + key + "] is : " + count);
                count = 0;
            }
        } catch (Exception e) {
        }
    }
    public String[] getUniqueKeys(String[] keys) {
        String[] uniqueKeys = new String[keys.length];
        uniqueKeys[0] = keys[0];
        int uniqueKeyIndex = 1;
        boolean keyAlreadyExists = false;
        for (int i = 1; i < keys.length; i++) {
            for (int j = 0; j <= uniqueKeyIndex; j++) {
                if (keys[i].equals(uniqueKeys[j])) {
                    keyAlreadyExists = true;
                }
            }
            if (!keyAlreadyExists) {
                uniqueKeys[uniqueKeyIndex] = keys[i];
                uniqueKeyIndex++;
            }
            keyAlreadyExists = false;
        }
        return uniqueKeys;
    }

    public void decryption() 
//this function reads the input from ciphertext.txt and secret key from key.txt and implement exclusive or on it to retrieve secure message and store it in result.txt 
    {
        try {
            FileInputStream fis1 = new FileInputStream("/Users/aarushisinghal/desktop/ciphertext.txt");
            FileInputStream fis2 = new FileInputStream("/Users/aarushisinghal/desktop/key.txt");
            do {
                int i = fis1.read();
                if (i == -1) {
                    break;
                }
                cipher.append((char) i);
                int y = fis2.read();
                if (y == -1) {
                    break;
                }
                keyretrieved.append((char) y);
            } while (true);
            System.out.println("ciphertext " + cipher);
            System.out.println("keyretrieved  " + keyretrieved);
            char[] a = {};
            char[] v = {};
            for (int g = 0; g < cipher.length(); g++) {
                a = cipher.toString().toCharArray();
            }
            for (int q = 0; q < keyretrieved.length(); q++) {
                v = keyretrieved.toString().toCharArray();
            }
            if (cipher.length() == keyretrieved.length()) {
                for (int x = 0; x < keyretrieved.length(); x++) {
                    if (a[x] == v[x]) {
                        decryptioncipher.append("0");
                    } else {
                        decryptioncipher.append("1");
                    }
                }
            } else {
                System.out.println("ERROR");
            }
        } catch (Exception e) {
            System.out.println("file doesnot exist" + e);
        }
        String text = decryptioncipher.toString();
        try {
            String message = "";
            for (int i = 0; i < (text.length()) / 8; i++) {
                int a = Integer.parseInt(text.substring(8 * i, (i + 1) * 8), 2);
                message += (char) (a);
            }
            FileOutputStream fos3 = new FileOutputStream("/Users/aarushisinghal/desktop/result.txt", true);
            PrintStream out = new PrintStream(fos3, true);
            System.out.println("decrypted text  " + decryptioncipher);
            System.out.println("decrypted message  " + message);
            out.println("message " + message);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] arg) {
        OneTimePad op = new OneTimePad();
        op.Enc();
        //op.keygeneration();
        op.decryption();
//        for (int t = 0; t < 5000; t++) {
//            op.keyfrequencychecker();
//        }
       // This commeneted part is to check uniform distribution of key for 3 bt key length
    }
}