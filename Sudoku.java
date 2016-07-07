/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;

import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.*;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

/**
 *
 * @author limei
 */
public class Sudoku {
    
    /**
     * Print the specified Sudoku problem and its solution.  The
     * problem is encoded as specified in the class documentation
     * above.
     *
     * @param args The command-line arguments encoding the problem.
     */
    public static void main(String[] args) {
        int[][] matrix = parseInput();
        System.out.println("matrix.length=" + matrix.length);
        writeMatrix(matrix);
        //parseProblem(args);
        //System.exit(0);

       // writeMatrix(matrix);
        if (solve(0, 0, matrix))    // solves in place
            writeMatrix(matrix);
        else
            System.out.println("NONE");

        System.out.println("++++++++++++++");

      //  matrix[8][8] = 6;
       matrix[8][8] = 6;
        writeMatrix(matrix);
        System.out.println("SOLUTION IS: " + isValidSolution(matrix));
        System.out.println("New SOLUTION IS: " + isCorrectSolution(matrix));


    }

    static boolean solve(int i, int j, int[][] cells) {

        if (i == 9) {
            i = 0;
            if (++j == 9)
                return true;
        }
        if (cells[i][j] != 0)  // skip filled cells
            return solve(i + 1, j, cells);

        for (int val = 1; val <= 9; ++val) {
            if (legal(i, j, val, cells)) {
                cells[i][j] = val;
                System.out.println("cells["+i+"]["+j+"]=" + cells[i][j]);
                if (solve(i + 1, j, cells))
                    return true;
            }
        }
        cells[i][j] = 0; // reset on backtrack
        return false;
    }


    static boolean isValidSolution(int[][] cells) {

        System.out.println("HELLO");
        int i = cells.length ;
        int j = cells[0].length ;

        for (int l = 0; l < i; ++l) {
            for (int m = 0; m < j; ++m) {
                int val = cells[l][m];

               // System.out.println("[ROW]:"+l+"[COL]"+m+"VAL:"+val);

                if (!legal(l, m, val, cells)) {
                    System.out.println("NOT LEGAL ON ROW :"+l+", COL:"+m+" VALUE :"+val) ;
                    return false;
                }
            }
        }
        return true;
    }



    static boolean isCorrectSolution(int[][] board) {

        int noOfRows = board.length ;
        int noOfCols = board[0].length ;
        int boardSize=noOfRows;

        if(noOfCols!=noOfRows)
            return false;

        int innerBoardSize = getSquareRoot(noOfRows);
        //check Rows are Valid
        for (int i = 0; i < boardSize; i++){
            if (!checkRule(board[i]))
                return false;
        }

        System.out.println("ROWS ARE GOOD");
        //Check whether each column comply the rule
        for (int j = 0; j < boardSize; j++) {
            int[] boardCol = new int[boardSize];

            for (int i = 0; i < boardSize; i++) {
                boardCol[i] = board[i][j];
            }
            if (!checkRule(boardCol))
                return false;
        }
        System.out.println("COLS ARE GOOD");




//Check whether each 3 by 3 box has numbers 1 to 9
        for (int i = 0; i< innerBoardSize; i++) {
            int k = 0;
            int[] list = new int[boardSize];

            for (int row = i * innerBoardSize; row < (i*innerBoardSize + innerBoardSize); row++) {

             //   System.out.println("ROW : "+row);
                for (int column = i * innerBoardSize; column < (i* innerBoardSize +innerBoardSize); column++)   {
      //              System.out.println("COL : "+column);
        //            System.out.println("k : "+k);
                    list[k++] = board[row][column];
                   //  k++;
                }

            }
            if (!checkRule(list))
                return false;

            System.out.println("INNER BOARDS ARE GOOD");

        }
        return true;
    }




    public final static int getSquareRoot(long n)
    {
        if (n < 0)
            return -1;

        switch((int)(n & 0xF))
        {
            case 0: case 1: case 4: case 9:
            int tst = (int)Math.sqrt(n);

            if(tst*tst == n)
                return tst;
            else
                return -1;

            default:
                return -1;
        }
    }


    static boolean checkRule(int[] boardPortion){

        int boardLength = boardPortion.length;
        System.out.println("BOARD LENGTH:"+boardLength);
        int[] temp = Arrays.copyOf(boardPortion,boardPortion.length)           ;
        Arrays.sort(temp);

        for(int i=0;i<boardLength;i++){
           // System.out.println(i);
            if( temp[i]!=i+1) {
                System.out.println(temp[i]+" i:"+i+1);
                 return false;
            }
        }
        return true;
    }


    static boolean legal(int i, int j, int val, int[][] cells) {
        for (int k = 0; k < 9; ++k) { // row
            if (k!=i && val == cells[k][j])
                return false;
        }

        for (int k = 0; k < 9; ++k) // col
            if (k!=j && val == cells[i][k])
                return false;

        int boxRowOffset = (i / 3) * 3;
        int boxColOffset = (j / 3) * 3;
        for (int k = 0; k < 3; ++k) // box
            for (int m = 0; m < 3; ++m)
                if (((boxRowOffset + k) !=i) && (boxColOffset+m!=j) && val == cells[boxRowOffset + k][boxColOffset + m])
                    return false;

        return true; // no violations, so it's legal
    }
    
    static int[][] parseInput() {
        int[][] problem = new int[9][9]; 
        //int index=0;
        try (Scanner scanner = new Scanner(System.in)) { 
            for (int index = 0; index < 81; index++) {
            //while (scanner.hasNext()) {
                int xIndex = index/9;
                int yIndex = index%9;
                String inputString = scanner.next().replaceAll(",", "").trim();
                if (inputString.length()>0) {
                    problem[xIndex][yIndex] = Integer.parseInt(inputString);
                    System.out.println("inputString: " + inputString 
                            + "; index=" + index + "; xIndex=" + xIndex + "; yIndex=" 
                            + yIndex + "; problem["+xIndex+"]["+yIndex+"]=" + problem[xIndex][yIndex]);
                }
                
            }
        }
        return problem;
    }

    static int[][] parseProblem(String[] args) {
        int[][] problem = new int[9][9]; // default 0 vals
        for (int n = 0; n < args.length; ++n) {
            int i = Integer.parseInt(args[n].substring(0, 1));
            int j = Integer.parseInt(args[n].substring(1, 2));
            int val = Integer.parseInt(args[n].substring(2, 3));
            problem[i][j] = val;
        }
        return problem;
    }

    static void writeMatrix(int[][] solution) {
        for (int i = 0; i < solution.length; i++) {
            if (i % 3 == 0)
                System.out.println(" -----------------------");
            for (int j = 0; j < solution[i].length; ++j) {
                if (j % 3 == 0) 
                    System.out.print("| ");
                System.out.print(solution[i][j] == 0
                        ? " "
                        : Integer.toString(solution[i][j]));

                System.out.print(' ');
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }
    
    public static void main_1(String[] args){
        Sudoku sudoku = new Sudoku();
        int[] xxc  = {1, 3, 1, 8, 3, 2, 9, 7};
        sudoku.sort( xxc);
        for (int i=0; i<xxc.length; i++) 
            System.out.print(xxc[i]+ " ");
        System.out.println();
        System.out.println("{1, 4}, {3,2}, {1, 2}, {8,3}, {3,8}, {2,7}, {9,2}, {7, 3}");
        System.out.println();
        //int[][] data  = {{1, 4}, {3,2}, {1, 2}, {8,3}, {3,8}, {2,7}, {9,2}, {7, 3}};
        int[][] data  = {{460, 4}, {590,6}, {550, 5}, {590,5}};
        sudoku.sort( data);
        for (int i=0; i<data.length; i++) 
            System.out.println(data[i][0]+ " " + data[i][1]);
        
        System.out.println();
        sudoku.permuteArray(data, 0, 1);
        System.out.println(sudoku.premuteSet.size());
        System.out.println("");
        System.out.println(sudoku.filterTargetList(data, 0, 1, 10));  
        
        System.out.println("");
        System.out.println(sudoku.figureValueSet(data, 0, 1, 10));  
        
        
        System.out.println("");
        System.out.println(sudoku.figureMaxValue(data, 0, 1, 10)); 
        System.out.println("");
        for (int i=0; i<data.length; i++) 
            System.out.println(data[i][0]+ " " + data[i][1]);
        
    }
    
    
    public void sort(int[] data){
        for (int i=0; i<data.length-1; i++) {
            for (int j=i+1; j<data.length; j++) {
                if (data[i]<data[j]) {
                    int tempDate = data[i];
                    data[i]=data[j];
                    data[j]=tempDate;
                }
            }
        }
    }
    
    public void sort(int[][] data){
        for (int i=0; i<data.length-1; i++) {
            for (int j=i+1; j<data.length; j++) {
                if (data[i][1]<data[j][1]) {
                    int tempDate = data[i][1];
                    data[i][1]=data[j][1];
                    data[j][1]=tempDate;
                    
                    tempDate = data[i][0];
                    data[i][0]=data[j][0];
                    data[j][0]=tempDate;
                } 
                if (data[i][1]==data[j][1] && data[i][0]<data[j][0]) {
                    int tempDate = data[i][0];
                    data[i][0]=data[j][0];
                    data[j][0]=tempDate;
                } 
            }
        }
    }
    
    public List<Integer> extractDataList(int[][] data, int index){
        List<Integer> dataList = new ArrayList<>();
        for (int i=0; i<data.length; i++) {
            dataList.add(data[i][index]);
        }
        return dataList;
    }
    
    
    public List<List<Integer>> extractData(SortedSet<Integer> dataSet, int maxNumber){
        List<List<Integer>> returnList = new ArrayList<>();
        
        List<Integer> subList = null;
        Iterator iterator = dataSet.iterator();
        while (iterator.hasNext()){
            int currentInt = ((Integer)iterator.next());
            if (currentInt>maxNumber) {
                continue;
            }
            //returnList.add(data[i][1]);
        }
        
        return returnList;
    }
    
    Set<List<Integer>> premuteSet = new HashSet<>();
    
    private void permute(List<Integer> dataList, int k) {
        for (int i = k; i < dataList.size(); i++) {
            Collections.swap(dataList, i, k);
            permute(dataList, k + 1);
            Collections.swap(dataList, k, i);
        }
        if (k == dataList.size() - 1) {
            List<Integer> newObject = new ArrayList<>();
            newObject.addAll(dataList);
            premuteSet.add(newObject);
        }
    }
    
    private void permuteArray(int[][] data, int permuteNumber, int baseIndex) {        
        List<Integer> dataList = extractDataList(data, baseIndex);
        permute( dataList, permuteNumber);
    }
    
    private int figureMaxValue(int[][] data, int permuteNumber, int baseIndex, int maxNumber) { 
        
        int maxAmount = 0;
        permuteArray(data, permuteNumber, baseIndex);
        
        for (List<Integer> dataList : premuteSet) {
            int sumNumber = 0;
            for (int i=0; i<dataList.size(); i++) {
                Integer member = dataList.get(i);
                sumNumber=sumNumber+member;
                if (sumNumber>maxNumber){
                    break;
                } else if (sumNumber==maxNumber){
                    HashMap<Integer, Integer> patronMap = new HashMap<>();
                    int subTotal = 0;
                    for (int j=0; j<=i; j++) {
                        int targetInt = dataList.get(j);
                        int repeatTime = 0;
                        if (patronMap.containsKey(targetInt)) {
                            repeatTime = patronMap.get(targetInt) + 1;
                            patronMap.put(targetInt, repeatTime);
                        } else {
                            patronMap.put(targetInt, repeatTime);
                        }
                        
                        for (int k=0; k<data.length; k++) {
                            //System.out.println(k + "; " + (k+repeatTime) + "; " + targetInt);
                            System.out.println("k=" + k+", repeatTime=" + repeatTime+", targetInt=" + targetInt+", data["
                                    + (k+repeatTime) + "][0]=" + data[k+repeatTime][0]+", data["
                                    + (k+repeatTime) + "][1]=" + data[k+repeatTime][1]);
                            if (data[k][1] == targetInt) {                                
                                subTotal = subTotal + data[k+repeatTime][0];                                
                                break;
                            }
                        }
                    }
                    if (subTotal>maxAmount) {
                        maxAmount=subTotal;
                    }
                    System.out.println("maxAmount=" + maxAmount);
                    break;
                } else {
                    
                }
            }
        }
        return maxAmount;        
    }
    
    private Set<Integer> figureValueSet(int[][] data, int permuteNumber, int baseIndex, int maxNumber) {  
        Set<Integer> amountSet = new TreeSet<>();
        Set<List<Integer>> filtedSet = new HashSet<>();
        permuteArray(data, permuteNumber, baseIndex);
        premuteSet.stream().forEach(new Consumer<List<Integer>>() {

            public void accept(List<Integer> dataList) {
                int sumNumber = 0;
                for (int i=0; i<dataList.size(); i++) {
                    Integer member = dataList.get(i);
                    sumNumber=sumNumber+member;
                    if (sumNumber>maxNumber){
                        break;
                    } else if (sumNumber==maxNumber){
                        List<Integer> finalList = new ArrayList<>();
                        int subTotal = 0;
                        for (int j=0; j<=i; j++) {
                            int targetInt = dataList.get(j);
                            finalList.add(targetInt);
                            for (int[] data1 : data) {
                                if (data1[1] == targetInt) {
                                    subTotal = subTotal + data1[0];
                                    break;
                                }
                            }
                        }
                        amountSet.add(subTotal);
                        filtedSet.add(finalList);
                        break;
                    } else {
                        
                    }
                }
            }
        });
        return amountSet;        
    }
    
    private Set<List<Integer>> filterTargetList(int[][] data, int permuteNumber, int baseIndex, int maxNumber) {  
        Set<Integer> amountSet = new TreeSet<>();
        Set<List<Integer>> filtedSet = new HashSet<>();
        permuteArray(data, permuteNumber, baseIndex);
        premuteSet.stream().forEach(new Consumer<List<Integer>>() {
    //private Set<List<Integer>> filterTargetList(int[][] data, int k, int maxNumber) {  
        //Set<Integer> amountSet = new TreeSet<>();
        //Set<List<Integer>> filtedSet = new HashSet<>();
        //permute(data, k);
        //premuteSet.stream().forEach(new Consumer<List<Integer>>() {

            public void accept(List<Integer> dataList) {
                int sumNumber = 0;
                for (int i=0; i<dataList.size(); i++) {
                    Integer member = dataList.get(i);
                    sumNumber=sumNumber+member;
                    if (sumNumber>maxNumber){
                        break;
                    } else if (sumNumber==maxNumber){
                        List<Integer> finalList = new ArrayList<>();
                        int subTotal = 0;
                        for (int j=0; j<=i; j++) {
                            int targetInt = dataList.get(j);
                            finalList.add(targetInt);
                            for (int[] data1 : data) {
                                if (data1[1] == targetInt) {
                                    subTotal = subTotal + data1[0];
                                    break;
                                }
                            }
                        }
                        amountSet.add(subTotal);
                        filtedSet.add(finalList);
                        break;
                    } else {
                        
                    }
                }
            }
        });
        return filtedSet;        
    }
    
    public static void test(String []argh){
        HashMap<String, Integer> phoneBook = new HashMap<>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int i = 0; i < n; i++){
            String name = in.next();
            int phone = in.nextInt();
            phoneBook.put(name, phone);
            // Write code here
        }
        while(in.hasNext()){
            String s = in.next();
            // Write code here
            if (phoneBook.keySet().contains(s) ){
                int phone =  phoneBook.get(s);
                System.out.println(s+"="+phone);
            } else {
                System.out.println("Not found");
            }
        }
        in.close();
    }
    
    
}
