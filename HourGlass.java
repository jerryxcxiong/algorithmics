/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;

import java.util.Scanner;

/**
 *
 * @author limei
 */
public class HourGlass {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int arr[][] = new int[6][6];
        for(int i=0; i < 6; i++){
            for(int j=0; j < 6; j++){
                arr[i][j] = in.nextInt();
            }
        }        
        in.close();
        
        HourGlass s = new HourGlass();
        int[] indexs = {0, 0, Integer.MIN_VALUE};
        //int row   =0;
        //int column=0; 
        //int maxAmount = 0;
        s.createHourGlass( arr, indexs);
        System.out.println(indexs[2]);
    }
    
    private void createHourGlass(int[][] arr, int[] indexs) {
        int row      = indexs[0];
        int column   = indexs[1]; 
        int maxAmount= indexs[2];
        //System.out.println("row=" + row + "; column=" + column + "; maxAmount=" + maxAmount + "; arr["+row+"].length=" + arr[row].length);
        //int output=0;

        
        for(int i=0; i < arr.length; i++){
            if (arr.length-i<3 ) {
                break ;
            } 
            indexs[0] = i;
            for(int j=0; j < arr[i].length; j++){
                if (arr[row].length-j<3) {
                    break;
                }
                indexs[1] = j;
                int[][] newArray = hourGlass(arr, indexs);
                int sumAmount = sum(newArray);
                if (sumAmount>indexs[2]) {
                    maxAmount = sumAmount;
                }                
                indexs[2] = maxAmount;
                System.out.println("row=" + row + "; column=" + column + "; maxAmount=" + maxAmount+ "; sumAmount=" + sumAmount);
            }
        }
    }
    
    private int[][] hourGlass(int[][] arr, int[] indexs) {
        int row      = indexs[0];
        int column   = indexs[1]; 
        int maxAmount= indexs[2];  
        
        int[][] newArray = new int[3][3];
        for(int i=0; i <= 2; i++){
            for(int j=0; j <= 2; j++){
                newArray[i][j] = arr[row+i][column+j];
                if (i==1 && j == 0 || i==1 && j == 2) {
                    newArray[i][j] = 0;
                }
                System.out.print(newArray[i][j]+"\t");
            }
            System.out.println();
        }
        return newArray;
    } 
    
    private int sum(int[][] arr) {
        int output = 0;        
        for(int i=0; i <= 2; i++){
            for(int j=0; j <= 2; j++){
                output=output+arr[i][j];
            }
        }
        return output;
    }
}
