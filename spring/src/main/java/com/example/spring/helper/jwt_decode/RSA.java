package com.example.spring.helper.jwt_decode;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
    public static final int VERSION = 1024;
    public static final BigInteger E = new BigInteger("65537");
    private static BigInteger p;
    private static BigInteger q;
    private static BigInteger n;
    private static BigInteger phiN;
    private static BigInteger d;

    public static void initialize() {
        p = BigInteger.probablePrime(VERSION / 2, new Random());
        q = BigInteger.probablePrime(VERSION / 2, new Random());
        n = p.multiply(q);
        phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        d = E.modInverse(phiN);
    }

    public  BigInteger encrytion(BigInteger message, BigInteger partnerN) {
        System.out.println(message.modPow(E, partnerN));
        return message.modPow(E, partnerN);
    }
    public BigInteger CalD(BigInteger phiN,BigInteger E){
        d = E.modInverse(phiN);
        return d;
    }
    public  BigInteger decrytion(BigInteger cipher) {
        System.out.println(cipher);
        return cipher.modPow(d, n);
    }

    public  BigInteger getN() {
        return n;
    }
    public BigInteger getPhiN(){
        return phiN;
    }
    public  BigInteger getE(){
        return E;
    }
    public BigInteger getD(){
        return d;
    }
    public  BigInteger changetoInt(String s) {
        s = s.toUpperCase();
        String s1 = "";
        for (int i = 0; i < s.length(); i++) {
            s1 += (int) s.charAt(i);

        }
        BigInteger E = new BigInteger(s1);
        return E;
    }

    public String changetoString(BigInteger bi) {
        String a = String.valueOf(bi);
        String s1 = "";
        int i = 0;
        while (true) {

            if (i == a.length()) {
                return s1.toLowerCase();
            }
            s1 += (char) Integer.parseInt(a.substring(i, i + 2));
            i += 2;
        }
    }
}
