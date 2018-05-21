package com.nmys.story.test_aes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {

    @Test
    public void m1() throws Exception {
        String aes = enAes("1", "0123456789abcdef");
        System.out.println("加密后的数据:"+aes);
        String deAes = deAes(aes, "0123456789abcdef");
        System.out.println("解密密后的数据:"+deAes);
    }

    @Test
    public void m2(){
        int cid = 2;
        int size = 603 % 20;
        size = size == 0 ? 1 : size;
        System.out.println(size);
    }

    public String enAes(String data, String salt) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(salt.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return new BASE64Encoder().encode(encryptedBytes);
    }

    public String deAes(String data, String salt) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(salt.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] cipherTextBytes = new BASE64Decoder().decodeBuffer(data);
        byte[] decValue = cipher.doFinal(cipherTextBytes);
        return new String(decValue);
    }

}