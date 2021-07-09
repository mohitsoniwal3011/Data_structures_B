package com.company.sorting;

import java.util.Scanner;

class RadixSort2 extends RadixSort{
    public void radix_sort(int[] arr){
        int digits=maxDig(arr);
        int[] temp=new int[arr.length];
        int [] sign=new int[2];
        int i;
        for(i=1;i<= digits;i++){
            countSort(arr,temp,i);
        }
        countSortOnSign(arr,temp);
        System.out.println("Sorted array is:");
        display(arr);
    }
    public void countSortOnSign(int [] arr,int [] temp){
        int i;
        int[] sign=new int[2];
        for(i=0;i< arr.length;i++){
            if(arr[i]< 0){ sign[0]++; }
            else { sign[1]++; }
        }
        sign[1]=sign[1]+sign[0];
        int k=sign[0]-1;
        for(i= arr.length-1;i>= 0;i--){
            if(arr[i] >= 0){ temp[--sign[1]]=arr[i]; }
            else { temp[k-(--sign[0])]=arr[i]; }
        }
        for(i=0;i<arr.length;i++){
            arr[i]=temp[i];
        }
    }
    public void countSort(int [] arr,int[] temp,int digPlace ){
        int i,dig;
        int x;
        int [] count =new int[10];
        for(i=0;i<arr.length;i++){
            count[digit(arr[i],digPlace)]++;
        }
        for(i=1;i<count.length;i++){
            count[i]=count[i]+count[i-1];
        }
        for(i= arr.length-1;i>= 0;i--){
            dig=digit(arr[i],digPlace);
            temp[--count[dig]]=arr[i];
        }
        for(i=0;i<arr.length;i++){
            arr[i]=temp[i];
        }
    }
    public void display(int [] a){
        for (int j : a) {
            System.out.print(j + "      ");
        }
        System.out.println("");
    }
    public int maxDig(int[] a){
        int max=-100000;
        for(int i=0;i<a.length;i++){
            if(a[i] > max){
                max=a[i];
            }
        }
        return Integer.toString(max).length();
    }
    public int digit(int number ,int k){
        String num =Integer.toString(Math.abs(number));
        if(k > num.length()){
            return 0;
        }else {
            return Integer.parseInt(num.charAt(num.length()-1-(k-1))+"");
        }
    }
}

public class RadixSortTest2 {
    public static void main(String[] args) {
        int[] a;
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of elements : ");
        int n= sc.nextInt();
        a=new int[n];
        for(int i=0;i<a.length;i++){
            System.out.print("Enter element "+(i+1)+" = ");
            a[i]= sc.nextInt();
        }
        RadixSort2 radixSort2=new RadixSort2();
        radixSort2.radix_sort(a);
    }
}
