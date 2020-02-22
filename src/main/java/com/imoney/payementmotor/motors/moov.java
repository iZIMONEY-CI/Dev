package com.imoney.payementmotor.motors;

import java.net.URL;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import static com.sun.deploy.trace.Trace.print;

public class moov {

    private static String GetToken(String order,String username,String password){
        try {
            String plaintext =order.concat(":").concat(username).concat(":").concat(password);
           // print("Plain Text = " + plaintext);
            byte plain[] = plaintext.getBytes("UTF-8");
            String hexKey = "746C633132333435746C633132333435746C633132333435746C633132333435";
            byte key[] = Hex.decode(hexKey);
           // print("Plain Hex = " + new String(plain));

            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(new byte[cipher.getBlockSize()]);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte encrypted[] = cipher.doFinal(plain, 0, plain.length);
           // print("Encrypted Hex = " + new String(Hex.encode(encrypted)));

            byte[] encode = Base64.encode(encrypted);
           // print("Token Text = " + new String(encode));
            return new String(encode);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public String MOOVCI(String reference,int montant,String numero){
        try{
            URL wsd1Url=new URL("https://154.0.24.150/OnlinePayment?wsdl");

        }catch (Exception ex){

        }

        return "";
    }
}
