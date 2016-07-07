/*
Given an array with  elements, can you sort this array in ascending order using only one of the following operations?

Swap two elements.
Reverse one sub-segment.
Input Format 
The first line contains a single integer, , which indicates the size of the array. 
The next line contains  integers separated by spaces.

n  
d1 d2 ... dn  
Constraints 
 
  
All  are distinct.

Output Format 
1. If the array is already sorted, output yes on the first line. You do not need to output anything else.

If you can sort this array using one single operation (from the two permitted operations) then output yes on the first line and then:

a. If you can sort the array by swapping  and , output swap l r in the second line.  and  are the indices of the elements to be swapped, assuming that the array is indexed from  to .

b. Else if it is possible to sort the array by reversing the segment , output reverse l r in the second line.  and  are the indices of the first and last elements of the subsequence to be reversed, assuming that the array is indexed from  to .

 represents the sub-sequence of the array, beginning at index  and ending at index , both inclusive.

If an array can be sorted by either swapping or reversing, stick to the swap-based method.

If you cannot sort the array in either of the above ways, output no in the first line.

Sample Input #1

2  
4 2  
Sample Output #1

yes  
swap 1 2
Sample Input #2

3
3 1 2
Sample Output #2

no
Sample Input #3

6
1 5 4 3 2 6
Sample Output #3

yes
reverse 2 5
Explanation 
For #1, you can both swap(1, 2) and reverse(1, 2), but if you can sort the array using swap, output swap only. 
For #2, it is impossible to sort by one single operation (among those permitted). 
For #3, you can reverse the sub-array d[2...5] = "5 4 3 2", then the array becomes sorted.
 */
package solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author limei
 */
public class AlmostSorted {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int array[] = new int[number];
        for (int i=0; i<number; i++){
            array[i] = scanner.nextInt();
        }
        int copyArray[] = new int[number];
        System.arraycopy(array, 0, copyArray, 0, number);
        for (int j=0; j<number; j++)
            System.out.println(copyArray[j]);
        AlmostSorted s = new AlmostSorted();
        scanner.close();
        
        String swapString = s.swap(array); 
        if (swapString.equals("no")){
            int[] indexArray= {0, 0, 0};
            List<int[]> indexList = new ArrayList<>();
            indexList.add(indexArray);
            List<Boolean> isUpperList = new ArrayList<>();
            swapString = s.reverse(copyArray, indexList, isUpperList);
        } 
        System.out.println(swapString);
    }
    
    private void print(int rowIndex, int[] data){
        System.out.print("\t" +rowIndex + "  ");
        for (int i=0; i<data.length; i++){
            System.out.print(data[i] + "  ");
        }
        System.out.println();
    }
    
    public String reverse(int array[], List<int[]> indexList, List<Boolean> isUpperList){ 
        print(0, array);
        int i=0; 
        for (int[] member:indexList) {
            print(i, member);
            i++;
        }
        System.out.println(isUpperList);
        System.out.println();
        String output = "";
        int[] indexArray = indexList.get(indexList.size()-1);
        int gfi        =indexArray[0];
        int firstIndex =indexArray[1];
        int secondIndex=firstIndex+1;
        if (firstIndex==array.length-1 ){
            if (isUpperList.size()>3) {
                return "no";
            } else {
                return "reverse " + (gfi+1) + " " + (firstIndex+1);
            }
        }
        
        int member_f = array[firstIndex];
        int member_s = array[secondIndex];
        
        System.out.println("member_f=" +member_f + "; member_s=" + member_s + "; array["+gfi+"]" + array[gfi]);
        //indexArray[0]= secondIndex;
        
        indexArray[1]= secondIndex; 
        indexArray[2]= secondIndex;
        
        if (firstIndex==0) {
            boolean isUP = member_s >= member_f ? true : false;
            isUpperList.add(isUP);           
        } else {
            if (isUpperList.get(isUpperList.size()-1)) {//up
                if (member_f >= member_s) {   //down  
                    if (member_s < array[gfi]  ) {
                        return "no"; //
                    } 

                    isUpperList.add(false); 

                    int[] newIndexArray= new int[3];
                    newIndexArray[0]   = secondIndex;
                    newIndexArray[1]   = secondIndex;
                    indexList.add(newIndexArray);
                }
            } else {                                    //down
                if (member_f <= member_s) {
                    if (member_s > array[gfi]  ) {
                        return "no"; //
                    } 

                    isUpperList.add(true);

                    int[] newIndexArray= new int[3];
                    newIndexArray[0]   = secondIndex;
                    newIndexArray[1]   = secondIndex;
                    indexList.add(newIndexArray);
                    indexArray[1] = firstIndex;
                }
            }
        }        
        output = reverse(array, indexList, isUpperList);
        return output;
    }
    
    public String swap(int array[]){
        int firstIndex=0;
        int secondIndex=0;
        int swapNumber = 0;
        int number = array.length;
        for (int i=0; i<number-1; i++){
            for (int j=i+1; j<number; j++){
                if (array[i]>array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    swapNumber ++;
                }
                if (swapNumber==1){
                    firstIndex=i;
                    secondIndex=j;
                } 
                System.out.println(i + "; firstIndex=" +firstIndex + "; secondIndex=" 
                        + secondIndex + "; swapNumber=" + swapNumber 
                        + "; array[i]=" + array[i] + "; array[j]=" + array[j]);
                /*if (swapNumber>=2){
                    break;
                } */               
            }
        }
        String output = "";
        if (swapNumber==0){
            output = "yes";
        } else if (swapNumber==1) {
            output = "swap " + (firstIndex + 1)+" " + (secondIndex + 1);
        } else {
            output = "no";
        }
        return output;
    }
}
