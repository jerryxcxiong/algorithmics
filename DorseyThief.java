/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class DorseyThief {

    public static void main(String[] args) {

        DorseyThief solution = new DorseyThief();

        //to fetch input data
        List<Object> inputList;
        inputList = solution.inputByScanner();
        int patronNumber = (int) inputList.get(0);
        int gramAmount = (int) inputList.get(1);
        int[][] pTransacts = (int[][]) inputList.get(2);

        if (solution.subTotal(pTransacts, 1) < gramAmount) {
            System.out.println("Got caught!");
            System.exit(0);
        } else if (solution.subTotal(pTransacts, 0) == gramAmount) {
            System.out.println("Got caught!");
            System.exit(0);
        } else {
            //to sort purchase plan for saving computing time            
            
            long t1 = System.nanoTime();       
            solution.sort(pTransacts);
            long t2 = System.nanoTime();
            System.out.println("T1: " + (t2-t1));
            t1 = System.nanoTime();
            //to calcute max profit amount
            int maxProfitAmount = solution.figureMaxValue(pTransacts, gramAmount);
            t2 = System.nanoTime();
            System.out.println("T2: " + (t2-t1));          
            
            

            

            //to print out result
            if (maxProfitAmount > 0) {
                System.out.println(maxProfitAmount);
            } else {
                System.out.println("Got caught!");
            }
        }

    }

    private List<Object> inputByScanner() {
        List<Object> inputList = new ArrayList<>();
        int patronNumber;
        int gramAmount;
        int[][] pTransacts;
        try (Scanner scanner = new Scanner(System.in)) {
            patronNumber = scanner.nextInt();
            int[] num = new int[patronNumber];
            Object[] tmp = new Object[patronNumber];
            if (patronNumber < 1 || patronNumber > 5000) {
                System.out.println("Constraints broken: input for passengers is not valid!");
                System.exit(1);
            }
            gramAmount = scanner.nextInt();
            if (gramAmount < 1 || patronNumber > 1000000) {
                System.out.println("Constraints broken: input for amount of gold is not valid!");
                System.exit(1);
            }   //to create array for containing potential transaction
            pTransacts = new int[patronNumber][2];
            for (int i = 0; i < patronNumber; i++) {
                pTransacts[i][0] = scanner.nextInt();
                pTransacts[i][1] = scanner.nextInt();
                num[i] = pTransacts[i][1];
                tmp[i] = pTransacts[i][1];
            }
        }

        inputList.add(patronNumber);
        inputList.add(gramAmount);
        inputList.add(pTransacts);
        return inputList;
    }

    private int subTotal(int[][] pTransacts, int index) {
        int subTotal = 0;
        for (int i = 0; i < pTransacts.length; i++) {
            subTotal = subTotal + pTransacts[i][index];
        }
        return subTotal;
    }

    /**
     * to sort
     *
     * @param data
     */
    private void sort(int[][] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[i][1] < data[j][1]) {
                    int tempDate = data[i][1];
                    data[i][1] = data[j][1];
                    data[j][1] = tempDate;

                    tempDate = data[i][0];
                    data[i][0] = data[j][0];
                    data[j][0] = tempDate;
                }
                if (data[i][1] == data[j][1] && data[i][0] < data[j][0]) {
                    int tempDate = data[i][0];
                    data[i][0] = data[j][0];
                    data[j][0] = tempDate;
                }
            }
        }
    }

    //to find out repeated times for each distinct time
    public List<Integer> extractDataList(int[][] data, int index, List<Integer> dataList) {
        List<Integer> outputList = new ArrayList<>();
        dataList.stream().forEach((member) -> {
            int amount = 0;
            for (int[] array : data) {
                if (array[index] == member) {
                    amount++;
                }
            }
            outputList.add(amount);
        });
        return outputList;
    }

    //to find out distinct data list
    public List<Integer> extractDataList(int[][] data, int index, int maxNumber) {
        Set<Integer> dataList = new TreeSet<>();
        for (int[] member : data) {
            if (member[index] <= maxNumber) {
                dataList.add(member[index]);
            }
        }

        List<Integer> outputList = new ArrayList<>();
        dataList.stream().forEach((member) -> {
            outputList.add(member);
        });
        return outputList;
    }
    List<Integer> repeatList = null;
    Set<List<Integer>> combinationSet = new TreeSet<>();
    
    /**
     * to find out purchase combination
     * @param dataList
     * @param timeList
     * @param maxNumber
     * @return 
     */
    public Set<List<Integer>> createListSet(List<Integer> dataList, List<Integer> timeList, int maxNumber) {
        List<Integer> tempCombination = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            int member = dataList.get(i);
            int loopNumber = maxNumber / member;
            if (loopNumber > timeList.get(i)) {
                loopNumber = timeList.get(i);
            }
            for (int j = 0; j < loopNumber; j++) {
                tempCombination.add(member);
            }
        }

        Set<List<Integer>> tempDataSet = new HashSet<>();

        int maxTimes = maxNumber / dataList.get(0);
/*
        System.out.println("maxTimes=" + maxTimes + "; maxNumber=" + maxNumber
                + "; dataList.get(0)=" + dataList.get(0) + "; dataList: " + dataList);
*/
        for (int i = 1; i <= maxTimes; i++) {
            Set<List<Integer>> subCombintation = createCombination(tempCombination, i, maxNumber);
            tempDataSet.addAll(subCombintation);
        }

        return tempDataSet;

    }

    // to find out combination 
    public Set<List<Integer>> createDataSet(List<Integer> dataList, List<Integer> timeList, int maxNumber) {
        List<Integer> tempCombination = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            int member = dataList.get(i);
            int loopNumber = maxNumber / member;
            if (loopNumber > timeList.get(i)) {
                loopNumber = timeList.get(i);
            }
            for (int j = 0; j < loopNumber; j++) {
                tempCombination.add(member);
            }
        }

        Set<List<Integer>> tempDataSet = new HashSet<>();

        int maxTimes = maxNumber / dataList.get(0);
/*
        System.out.println("maxTimes=" + maxTimes + "; maxNumber=" + maxNumber
                + "; dataList.get(0)=" + dataList.get(0) + "; dataList: " + dataList);
*/
        for (int i = 1; i <= maxTimes; i++) {
            ArrayList<Object[]> subCombintation = createCombination(tempCombination.toArray(), i);

            for (Object[] memberObj : subCombintation) {
                int tempTotal = 0;
                for (int j = 0; j < memberObj.length; j++) {
                    int member = (int) memberObj[j];
                    tempTotal = tempTotal + member;

                    if (tempTotal == maxNumber) {
                        List<Integer> newObj = new ArrayList<>();
                        for (int k = 0; k <= j; k++) {
                            newObj.add((int) memberObj[k]);
                        }
                        tempDataSet.add(newObj);
                        break;
                    }

                    if (tempTotal > maxNumber) {
                        break;
                    }

                }
            }

        }

        return tempDataSet;

    }

    /**
     *
     * @param data
     * @param maxNumber
     * @return
     */
    public int figureMaxValue(int[][] data, int maxNumber) {
        long t1 = System.nanoTime();       
        List<Integer> dataLists = extractDataList(data, 1, maxNumber);
        long t2 = System.nanoTime();
        System.out.println("\tT1: " + (t2-t1) + "; size=" + dataLists.size());
        
        t1 = System.nanoTime();       
        List<Integer> timeList = extractDataList(data, 1, dataLists);
        t2 = System.nanoTime();
        System.out.println("\tT2: " + (t2-t1) + "; size=" + timeList.size());
        
        t1 = System.nanoTime();        
        //Set<List<Integer>> combinedSet = createDataSet(dataLists, timeList, maxNumber);
        Set<List<Integer>> combinedSet = createListSet(dataLists, timeList, maxNumber);
        t2 = System.nanoTime();
        System.out.println("\tT3: " + (t2-t1)+ "; size=" + combinedSet.size());

        int maxAmount = 0;

        for (List<Integer> dataList : combinedSet) {
            //System.out.println("dataList: " + dataList);
            HashMap<Integer, Integer> patronMap = new HashMap<>();
            int subTotal = 0;
            for (Integer member : dataList) {
                int repeatTime = 0;
                if (patronMap.containsKey(member)) {
                    repeatTime = patronMap.get(member) + 1;
                    patronMap.put(member, repeatTime);
                } else {
                    patronMap.put(member, repeatTime);
                }
                //System.out.print("\t" + member + "; repeatTime=" + repeatTime);
                for (int k = 0; k < data.length; k++) {
                    if (data[k][1] == member) {
                        subTotal = subTotal + data[k + repeatTime][0];
                        /*
                         System.out.print("; k=" + k + "; data["
                         + (k + repeatTime) + "][0]=" + data[k + repeatTime][0] 
                         + "; subTotal" + subTotal + "; maxAmount" + maxAmount);
                         */
                        break;
                    }
                }
                //System.out.println();
                if (subTotal > maxAmount) {
                    maxAmount = subTotal;
                }
            }
        }
        return maxAmount;
    }
    
    

    /**
     * 
     * @param source
     * @param maxNumber
     * @param result 
     */
    private void verifyData(List<Integer> source, int maxNumber, Set<List<Integer>> result){               
        List<Integer> newList = new ArrayList<>();
        
        int subTotal=0; 
        for (Integer member : source) {
            subTotal = subTotal + member;
            newList.add(member);
            if (subTotal == maxNumber) {
                result.add(newList);
                break;
            }

            if (subTotal > maxNumber) {
                break;
            }
        }        
    }
    /**
     *
     * @param source
     * @param n
     * @return
     */
    public Set<List<Integer>> createCombination(List<Integer> source, int n, int limitation) {
        Set<List<Integer>> result = new HashSet<>();
        if (n == 1) {
            for (Integer member : source) {
                List<Integer> input = new ArrayList<>();
                input.add(member);
                verifyData(input, limitation, result);
                //result.add(new Object[]{member});
            }
        } else if (source.size() == n) {
            //result.add(source);
            verifyData(source, limitation, result);
        } else {
            if (source.size() > 0) {
                List<Integer> psource = new ArrayList<>();
                for (int i=0; i<source.size()-1; i++) {
                    psource.add(source.get(i));
                }
                //Object[] psource = new Object[source.length - 1];
                //System.arraycopy(source, 0, psource, 0, psource.length);
                result = createCombination(psource, n,limitation);
                Set<List<Integer>> tmp = createCombination(psource, n - 1, limitation);
                for (List<Integer> member : tmp) {
                    verifyData(member, limitation, result);
                }
            }

        }
        return result;
    }
    
    /**
     *
     * @param source
     * @param n
     * @return
     */
    public ArrayList<Object[]> createCombination(Object[] source, int n) {
        ArrayList<Object[]> result = new ArrayList<>();
        if (n == 1) {
            for (Object member : source) {
                result.add(new Object[]{member});
            }
        } else if (source.length == n) {
            result.add(source);
        } else {
            if (source.length > 0) {
                Object[] psource = new Object[source.length - 1];
                System.arraycopy(source, 0, psource, 0, psource.length);
                result = createCombination(psource, n);
                ArrayList<Object[]> tmp = createCombination(psource, n - 1);
                for (Object[] member : tmp) {
                    Object[] rs = new Object[n];
                    System.arraycopy(member, 0, rs, 0, n - 1);
                    rs[n - 1] = source[source.length - 1];
                    result.add(rs);
                }
            }

        }
        return result;
    }

}
