/*
 * Objective 
Today, we're working with binary numbers. Check out the Tutorial tab for learning materials and an instructional video!

Task 
Given a base- integer, , convert it to binary (base-). Then find and print the base- integer denoting the maximum number of consecutive 's in 's binary representation.

Input Format

A single integer, .

Constraints

Output Format

Print a single base- integer denoting the maximum number of consecutive 's in the binary representation of .

Sample Input 1

5
Sample Output 1

1
Sample Input 2

13
Sample Output 2

2
Explanation

Sample Case 1: 
The binary representation of  is , so the maximum number of consecutive 's is .

Sample Case 2: 
The binary representation of  is , so the maximum number of consecutive 's is .Tools | Templates
 * and open the template in the editor.
 */
package solution;

import java.util.Scanner;

/**
 *
 * @author limei
 */
public class BinaryNumber {
    public static void main(String[] args) {

        int input;
        try ( Scanner scanner = new Scanner(System.in)) {
            input = scanner.nextInt();
            scanner.close();
        }
        
        //Solution solution = new Solution();
        BinaryNumber solution = new BinaryNumber();
        solution.solution(input);       
    }
    
    private void solution(int input){
        StringBuilder binaryString = new StringBuilder();
        converDigitalToBinary(input, binaryString);
        
        String bs = binaryString.reverse().toString();
        int l = cutBinaryStringAtDiff(bs, 0);
        System.out.println("XXC: " + l);
        
        StringBuilder output = new StringBuilder();
        cutBinaryStringAtDiff(bs, output);
        
        String oString = output.reverse().toString();
        System.out.println("oString.length: " + oString.length()); 
        //System.out.println(convertBinaryStringToDigital(oString, 2.0)); 
        System.out.println(convertBinaryStringToDigital("110111111", 2.0)); 
        System.out.println(input + "; bs=" + bs + "; oString=" +  oString + "; final=" + convertBinaryStringToDigital(oString, 2.0));   
    }
    
    private int convertBinaryStringToDigital(String binaryString, double base){
        int digital = 0;
        for (int i=0; i<binaryString.length(); i++) {
            String subString = binaryString.substring(i, i+1);
            //System.out.println("TERSYT: " + binaryString + "" + i +"; " + subString);
            int bitInt = Integer.parseInt(subString);
            int powerValue = 0;
            if (bitInt>0) {
                powerValue = bitInt * (int) Math.pow(base, binaryString.length()-1-i);
                digital = digital + powerValue;
            }
        }
        return digital;
    }
    
    private int cutBinaryStringAtDiff(String binaryString, int length){
        int index = binaryString.indexOf("0");
        System.out.println("binaryString=" + binaryString +"; length=" + binaryString.length() +"; length=" + length +"; index=" + index);
        int updateInt;
        
        if (index>=0) {
            if (index==binaryString.length()-1) {
                updateInt = index;
            } else {
                binaryString = binaryString.substring(index+1);
                if (index<length) {
                    updateInt = length;
                } else {
                    updateInt = index;                    
                } 
                int result = cutBinaryStringAtDiff(binaryString, updateInt);
                if (updateInt<result) {
                    updateInt=result;
                }
            }                       
        } else {
            updateInt = binaryString.length();
        }
        return updateInt;
    }
    private void cutBinaryStringAtDiff(String binaryString, StringBuilder output){
        //System.out.println("t0: " + binaryString + "; output: " + output);
        if (binaryString.length()<1) {
            return;
        }        
        String secondString;
        if (binaryString.length()==1) {
            if (output.length()==0) {
                output.append(binaryString);
            } else {
                secondString = output.substring(output.length()-1);
                if (binaryString.equals(secondString)){ 
                    output.append(binaryString);
                } 
            }
            return;
        }        
        
        String firstString = binaryString.substring(0, 1);
        if (output.length()==0) {
            secondString = binaryString.substring(1, 2); 
            //System.out.println("\tAAA; " + firstString + "; " + secondString);
            output.append(firstString);
            if (firstString.equals(secondString)){  
                if (binaryString.length()>1) {
                    cutBinaryStringAtDiff(binaryString.substring(1), output);
                }                
            } 
        } else {
            secondString = output.substring(output.length()-1);
            //System.out.println("\tBBB; " + firstString + "; " + secondString + "; " + binaryString.substring(1));
            if (firstString.equals(secondString)){  
                output.append(firstString);
                cutBinaryStringAtDiff(binaryString.substring(1), output);
            } 
        }        
        
        
            
    }
    
    private void converDigitalToBinary(int digital, StringBuilder binaryString){
        if (digital<=1) {
            binaryString.append(digital);
            return;
        }
        
        int remain=digital%2;
        binaryString.append(remain);
        converDigitalToBinary(digital >> 1, binaryString);
    }
}
