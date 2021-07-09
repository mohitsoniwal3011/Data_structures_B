package com.company.sorting;

import java.util.Scanner;

public class ShellSortTest {
    public static void shellSort(int [] a){
        int temp,gap,i,j;
        for(gap=a.length/2 ;gap>0;gap=gap/2){
            for(j=gap;j<a.length;j++){
                for(i=j-gap;i<0;i++);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int i,j,n,gap,temp;
        System.out.print("Enter the number of elements: ");
        n=sc.nextInt();
        int [] a =new int[n];
        for(i=0;i<n;i++){
            System.out.print("Enter the element "+(i+1)+" = ");
            a[i]= sc.nextInt();
        }
        for(gap=n/2;gap>0;gap=gap/2){
            System.out.println("gap = "+gap);
            for(j=gap;j<n;j++){
                for(i=j-gap;i>=0;i=i-gap){
                    if(a[i]< a[i+gap]){
                        break;
                    }
                    else {
                        temp=a[i+gap];
                        a[i+gap]=a[i];
                        a[i]=temp;
                    }
                }
            }
        }
        System.out.println("after sort:");
        for(i=0;i<n;i++){
            System.out.print(a[i]+"  ");
        }

    }
}
