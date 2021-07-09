package com.company.sorting;

import java.util.Scanner;

public class BubbleSort{
    public static int max=100;
    public static void main(String[] args) {
        int n,i,j,temp=0;
        int [] a;
        Scanner sc =new Scanner(System.in);
        System.out.print("Enter number of elements = ");
        n=sc.nextInt();
        a=new int[n];
        boolean xchange;
        for(i=0;i<a.length;i++){
            System.out.print("Enter element "+(i+1)+" = ");
            a[i]= sc.nextInt();
        }
        //System.out.println("");
        for(i=0;i<a.length-1;i++){
            xchange=false;
            for(j=0;j<a.length-1-i;j++){
                if(a[j]>a[j+1]){
                    temp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                    xchange =true;
                }
            }
            if(!xchange){
                break;
            }
        }
        System.out.println("Sorted list:");
        for(i=0;i<a.length;i++){
            System.out.print(a[i]+"    ");
        }
    }
}
