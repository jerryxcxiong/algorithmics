/*
 * Suppose you have a String, , of length  that is indexed from  to . You also have some String, , that is the reverse of String .  is funny if the condition  is true for every character from  to .

Note: For some String ,  denotes the ASCII value of the  -indexed character in . The absolute value of an integer, , is written as .

Input Format

The first line contains an integer,  (the number of test cases). 
Each line  of the  subsequent lines contain a string, .

Constraints

Output Format

For each String  (where ), print whether it is  or  on a new line.

Sample Input

2
acxz
bcxz
Sample Output

Funny
Not Funny
Explanation

Test Case 0:  
 
 
 
As each comparison is equal, we print .

Test Case 1:  
, but  
At this point, we stop evaluating  (as ) and print .
 */
package solution;

import java.util.Scanner;

/**
 *
 * @author limei
 */
public class FunnyString {
    public static void main(String[] args) {
        FunnyString pm = new FunnyString();
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        String[] inputs;
        try (Scanner scanner = new Scanner(System.in)) {
            int number = scanner.nextInt();
            inputs = new String[number];
            for (int i = 0; i < number; i++) {
                inputs[i] = scanner.next();
            }
        }

        for (String member : inputs) {
            boolean isFunny = pm.isFunny(member);
            if (isFunny) {
                System.out.println("Funny");
            } else {
                System.out.println("Not Funny");
            }
            
        }
    }

    
    private boolean isFunny(String member) {
        boolean isFunny = true;
        char[] memberChar = member.toCharArray();
        

        int preIndex = 0;
        while (true) {
            int fixIndex = memberChar.length - 1 - preIndex;
            char preChar1 = memberChar[preIndex];
            char preChar2 = memberChar[preIndex+1];
            char fixChar1 = memberChar[fixIndex-1];
            char fixChar2 = memberChar[fixIndex];
            
            if (Math.abs(preChar2-preChar1)!=Math.abs(fixChar2-fixChar1)) {
                isFunny = false;
                break;
            }
            if (fixIndex-1<preIndex+1) {
                break;
            }
            preIndex ++;
            
        }
        //System.out.println(String.valueOf(memberChar, 0, memberChar.length));

        return isFunny;
    }

   
}
