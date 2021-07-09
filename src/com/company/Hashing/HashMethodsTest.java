package com.company.Hashing;

class HashMethods{
    public int midSquare(int key){
        if(key < 100){
            return Integer.parseInt(Integer.toString(key*key).substring(0,3));
        }
        if(key > 10000){
            String num =Integer.toString(key);
            num=num.substring(num.length()-4);
            key =Integer.parseInt(num);
        }
        System.out.println("Key value is "+key);
        int squared=key*key;
        return Integer.parseInt(Integer.toString(squared).substring(2,5));
    }
    public int foldingMethod(int key){
        return 0;
    }
}

public class HashMethodsTest {
    public static void main(String[] args) {
        //System.out.println(Integer.MAX_VALUE);
        HashMethods hashMethods =new HashMethods();
        System.out.println(hashMethods.midSquare(1337));
        System.out.println(hashMethods.midSquare(1273));
        System.out.println(hashMethods.midSquare(1391));
        System.out.println(hashMethods.midSquare(1026));
        System.out.println(hashMethods.midSquare(23451337));
        System.out.println(hashMethods.midSquare(22));
    }

}
