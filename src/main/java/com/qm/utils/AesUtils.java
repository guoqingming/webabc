package com.qm.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * @program: result-demo
 * @description: AES加密解密
 * @author: guoqingming
 * @create: 2018-12-15 15:42
 **/
public class AesUtils {


    private final static String key = "7c405d51b14393bd7ee898d96dfb3e19";


    private final static AES aes = SecureUtil.aes(key.getBytes());

    public static String encript(String content) {


        //加密
        byte[] encrypt = aes.encrypt(content);
        //解密
        byte[] decrypt = aes.decrypt(encrypt);

        //加密为16进制表示
        String encryptHex = aes.encryptHex(content);

        return encryptHex;
    }

    public static String decript(String content) {
        return aes.decryptStr(content);
    }

    public static void main(String[] args) {
        String value = "7c405d51b14393bd7ee898d96dfb3e19";
        System.out.println(value);
        AES aes = SecureUtil.aes(value.getBytes());
        String str = "hello world";
        String encripted = encript(str);
        System.out.println(encripted);
        String decript = decript(encripted);
        System.out.println(decript);
    }
}
