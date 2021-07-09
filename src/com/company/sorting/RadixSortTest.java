package com.company.sorting;

import java.util.Queue;
import java.util.Scanner;

class RadixSort{

    public void radix_sort(int[] arr){
        int digits=maxDig(arr);
        int[] temp=new int[arr.length];
        int i;
        for(i=1;i<= digits;i++){
            countSort(arr,temp,i);
        }
        display(arr);
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
        String num =Integer.toString(number);
        if(k > num.length()){
            return 0;
        }else {
            return Integer.parseInt(num.charAt(num.length()-1-(k-1))+"");
        }
    }
}

public class RadixSortTest {
    public static void main(String[] args) {
        RadixSort radixSort=new RadixSort();
        int[] a;
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of elements : ");
        int n= sc.nextInt();
        a=new int[n];
        for(int i=0;i<a.length;i++){
            System.out.print("Enter element "+(i+1)+" = ");
            a[i]= sc.nextInt();
        }
        radixSort.radix_sort(a);
        radixSort.display(a);

    }
}
