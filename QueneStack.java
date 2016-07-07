/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;

import java.util.*;

/**
 *
 * @author limei
 */
public class QueneStack {
    // Write your code here.
    private Stack<Character> stack = new Stack<>();
    private Queue<Character> queue = new LinkedList<>();
    
    //method pushes a character onto a stack.
    public void pushCharacter(char ch) {
        stack.push(ch);
    }
    //method enqueues a character in the  instance variable.
    public void enqueueCharacter(char ch) {
        queue.add(ch);
    }
    //method that pops and returns the character at the top of the  instance variable.
    public char popCharacter() {
        return stack.pop();
    }
    //method that dequeues and returns the first character in the  instance variable.
    public char dequeueCharacter() {
        return queue.remove();
    }
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        scan.close();

        // Convert input String to an array of characters:
        char[] s = input.toCharArray();

        // Create a Solution object:
        QueneStack p = new QueneStack();

        // Enqueue/Push all chars to their respective data structures:
        for (char c : s) {
            p.pushCharacter(c);
            p.enqueueCharacter(c);
        }

        // Pop/Dequeue the chars at the head of both data structures and compare them:
        boolean isPalindrome = true;
        for (int i = 0; i < s.length/2; i++) {
            char stackMember = p.popCharacter();
            char queueMember = p.dequeueCharacter();
            System.out.println("stackMember=" + stackMember + "; queueMember=" + queueMember);
            if (stackMember != queueMember) {
                isPalindrome = false;                
                break;
            }
        }

        //Finally, print whether string s is palindrome or not.
        System.out.println( "The word, " + input + ", is " 
                           + ( (!isPalindrome) ? "not a palindrome." : "a palindrome." ) );
    }
}
