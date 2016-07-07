/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;


import java.io.*;
import java.util.*;

public class MaxProfitSolution {

    public static void main(String[] args) {
        MaxProfitSolution solution  =  new MaxProfitSolution();
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int patronNumber = scanner.nextInt();
        if (patronNumber<1 || patronNumber>5000) {
            System.out.println("Constraints broken: input for passengers is not valid!");
            System.exit(1);
        }
        int gramAmount = scanner.nextInt();
        if (gramAmount<1 || patronNumber>1000000) {
            System.out.println("Constraints broken: input for amount of gold is not valid!");
            System.exit(1);
        }
        
        //to create array for containing potential transaction
        int[][] pTransacts = new int[patronNumber][2];
        for (int i=0; i<patronNumber; i++){
            pTransacts[i][0] = scanner.nextInt();
            pTransacts[i][1] = scanner.nextInt();
        }
        scanner.close();
        
        
        if (solution.subTotal(pTransacts, 1) < gramAmount) {
            System.out.println("Got caught!");
        } else {
            int maxprofit = solution.figureMaxValue(pTransacts, 0, 1, gramAmount);
            if (maxprofit>0) {
                System.out.println(maxprofit);
            } else {
                System.out.println("Got caught!");
            }
        }
    }
    
    private int subTotal(int[][] pTransacts, int index) {
        int subTotal=0; 
        for (int i=0; i<pTransacts.length; i++){
            subTotal = subTotal + pTransacts[i][index];
        }
        return subTotal;
    }
    
    /**
     * to sort
     * @param data 
     */
    private void sort(int[][] data){
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
    
    Set<List<Integer>> premuteSet = new HashSet<>();
    
    
    public void permute(List<Integer> dataList, int k) {
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
    
    public List<Integer> extractDataList(int[][] data, int index){
        List<Integer> dataList = new ArrayList<>();
        for (int i=0; i<data.length; i++) {
            dataList.add(data[i][index]);
        }
        return dataList;
    }
    
    public void permuteArray(int[][] data, int permuteNumber, int baseIndex) {        
        List<Integer> dataList = extractDataList(data, baseIndex);
        permute( dataList, permuteNumber);
    }
    
    public int figureMaxValue(int[][] data, int permuteNumber, int baseIndex, int maxNumber) { 
        
        int maxAmount = 0;
        long t1 = System.nanoTime();
        permuteArray(data, permuteNumber, baseIndex);
        long t2 = System.nanoTime();
        System.out.println((t2-t1)/1000 + "; " + premuteSet.size());
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
                            if (data[k][1] == targetInt) {                                
                                subTotal = subTotal + data[k+repeatTime][0];                                
                                break;
                            }
                        }
                    }
                    if (subTotal>maxAmount) {
                        maxAmount=subTotal;
                    }
                    break;
                } else {
                    
                }
            }
        }
        return maxAmount;        
    }
    
    
}
