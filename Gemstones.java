/*
 * John has discovered various rocks. Each rock is composed of various elements, and each element is represented by a lower-case Latin letter from 'a' to 'z'. An element can be present multiple times in a rock. An element is called a gem-element if it occurs at least once in each of the rocks.

Given the list of  rocks with their compositions, display the number of gem-elements that exist in those rocks.

Input Format

The first line consists of an integer, , the number of rocks. 
Each of the next  lines contains a rock's composition. Each composition consists of lower-case letters of English alphabet.

Constraints 
 
Each composition consists of only lower-case Latin letters ('a'-'z'). 
 length of each composition 

Output Format

Print the number of gem-elements that are common in these rocks. If there are none, print 0.

Sample Input

3
abcdde
baccd
eeabg
Sample Output

2
Explanation

Only "a" and "b" are the two kinds of gem-elements, since these are the only characters that occur in every rock's composition.
 */
package solution;

import java.util.Scanner;

/**
 *
 * @author limei
 */
public class Gemstones {
    static final char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Gemstones pm = new Gemstones();
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        String[] inputs;
        try (Scanner scanner = new Scanner(System.in)) {
            int number = scanner.nextInt();
            inputs = new String[number];
            for (int i = 0; i < number; i++) {
                inputs[i] = scanner.next();
            }
        }

        int elementNumber=0;
        for (char letter : letters) {
            boolean isElement=true;
            for (String member : inputs) {
                int index = member.indexOf(String.valueOf(letter));
                if (index<0) {
                    isElement=false;
                    break;
                }
            }
            if (isElement) {
                elementNumber++;
            }
        }
        System.out.println(elementNumber);
        
    }
}
