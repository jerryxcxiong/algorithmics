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
public class ConsecutiveSamecharactersRemover {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        String[] inputs ;
        try (Scanner scanner = new Scanner(System.in)) {
            int number = scanner.nextInt();
            inputs = new String[number];
            for (int i=0; i<number; i++) {
                inputs[i] = scanner.next().toUpperCase();
            }
        }
        
        for (String member: inputs) {
            int removedNumber=0;
            char[] memberChar = member.toCharArray();
            for (int i=0; i<memberChar.length-1; i++) {
                for (int j=i+1; j<memberChar.length; j++) {
                    if (memberChar[i] == memberChar[j]) {
                        removedNumber++;
                        break;
                    } else {
                        break;
                    }
                }
            }
            System.out.println(removedNumber);
        }
    }
}
