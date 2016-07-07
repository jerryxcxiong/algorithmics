package solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author limei
 */
public class PalindromesMaker {

    static final char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static void main(String[] args) {
        PalindromesMaker pm = new PalindromesMaker();
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
            int removedNumber = pm.palindromes(member);
            System.out.println(removedNumber);
        }
    }

    private int calcutePD(char[] memberChar, int preIndex, int fixIndex) {
        char preChar = memberChar[preIndex];
        char fixChar = memberChar[fixIndex];
        int preInt = fetchIndex(preChar);
        int fixInt = fetchIndex(fixChar);

        if (preInt > fixInt) {
            memberChar[preIndex] = memberChar[fixIndex];
        } else if (preInt < fixInt) {
            memberChar[fixIndex] = memberChar[preIndex];
        } else {

        }

        int result = Math.abs(fixInt - preInt);
        /*
        System.out.println("preChar=" + preChar + "; preInt="
                + preInt + "; fixChar=" + fixChar + "; fixInt=" + fixInt + "; result=" + result);
        */
        return result;
    }

    private int palindromes(String member) {
        int changeNumber = 0;
        char[] memberChar = member.toCharArray();

        int preIndex = 0;
        while (true) {
            int fixIndex = memberChar.length - 1 - preIndex;
            char preChar = memberChar[preIndex];
            char fixChar = memberChar[fixIndex];
            int postionDeta;
            int indexDeta = fixIndex - preIndex;

            if (indexDeta > 1) {
                postionDeta = calcutePD(memberChar, preIndex, fixIndex);
                changeNumber = changeNumber + postionDeta;
                preIndex++;
            } else if (indexDeta == 1) {
                postionDeta = calcutePD(memberChar, preIndex, fixIndex);
                changeNumber = changeNumber + postionDeta;
                break;
            } else {
                break;
            }
        }
        //System.out.println(String.valueOf(memberChar, 0, memberChar.length));

        return changeNumber;
    }

    private int fetchIndex(char source) {
        int index = 0;
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == source) {
                index = i;
                break;
            }
        }
        return index;
    }
}
