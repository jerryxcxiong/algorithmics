/*
 * https://www.hackerrank.com/challenges/superman-celebrates-diwali?h_r=next-challenge&h_v=zen
 * 
 Superman has been invited to India to celebrate Diwali. Unfortunately, on his arrival he learns that he has been invited mainly to help rescue people from a fire accident that has happened in a posh residential locale of New Delhi, where rescue is proving to be especially difficult. As he reaches the place of the fire, before him there are buildings, each of the same height , which are on fire. Since it is Diwali, some floors of the buildings are empty as the occupants have gone elsewhere for celebrations. In his hurry to start the rescue Superman reaches the top of the building, but realizes that his jumping power is depleted and restricted due to change in his geographical setting. He soon understands the restrictions of his jumping power, and they are as follows:

 He can use the jumping power any number of times until he reaches the bottom floor, which means he can use the jumping power only until before he reaches the bottom (Ground floor), which means, once he reaches the bottom floor, he cannot move to the top floor again and try to save people. (In one single drop from the top to bottom)

 While switching buildings, he loses height  while jumping.

 The second restriction is explained below with an example.

 Assume . Now Superman is in the 2nd building 5th floor (, ). If he wants to switch to the fifth building (), he will lose height (), which means he will be at floor 3 at building 5 (, ). He can jump freely from the current floor to the floor below on the same building . That is, suppose if he is at , he can go to  without any restrictions. He cannot skip a floor while jumping in the same building. He can go to the floor below the current floor of the same building or use his jumping power, switch building, and lose height .

 Given the information about the occupied floors in each of the  buildings, help Superman to determine the maximum number of people he can save in one single drop from the top to the bottom floor with the given restrictions.

 Input Format

 Input starts with three values: the number of buildings , the height of the buildings , and the height Superman will lose when he switches buildings .

 These are followed by  lines. Each  line starts with a non negative integer  indicating how many people are in the th building. Each of the following  integers indicates that a person is at height  in the  buiding. Each of the following  integers are given and repetitions are allowed which means there can be more than one person in a floor.

 indicates building number and  indicates floor number. Building number will not be given; since  lines follow the first line, you can assume that the  line indicates the  building's specifications.

 Constraints 
 
 
 (for each , which means the maximum number of people in a particular building will not exceed ) 

 Output Format

 Output the maximum number of people Superman can save.

 Sample Input

 4 15 2 
 5 1 1 1 4 10
 8 9 5 7 7 3 9 8 8
 5 9 5 6 4 3
 0   
 Sample Output

 12
 Explanation

 Input starts with , ,  .

 lines follow. Each line describes building .

 Each line begins with , which denotes the number of persons in a particular building, followed by floor number, where each person resides. Floor number can repeat as any number of people can reside on a particular floor.

 I've attached a figure here to explain the sample test case.

 You can verify the first building's specifications with the figure.

 (Total number of persons in the first building), followed by 1 1 1 4 10(Floor numbers).

 floor = 3 persons.

 floor = 1 person.

 floor = 1 person.

 Similarly, the specifications for the other three buildings follow.

 The connected line shows the path which Superman can use to save the maximum number of people. In this case, that number is .

 You can also note in the figure that when he switches from Building 2 to Building 3, he loses height  (). Similarly, when he switches from Building 3 to Building 1 ,the same height loss happens as mentioned in the problem statement.
 */
package solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author limei
 */
public class SupermanRescue {

    private List<Object> inputByScanner_1() {
        List<Object> inputList = new ArrayList<>();
        int buildNumber;
        int heightNumber;
        int loseNumber;
        long t1 = System.nanoTime();
        Scanner scanner = new Scanner(System.in);
        buildNumber = scanner.nextInt();
        heightNumber = scanner.nextInt();
        int[][] peopleMap = new int[buildNumber][heightNumber + 1];
        loseNumber = scanner.nextInt();

        for (int i = 0; i < buildNumber; i++) {
            peopleMap[i][0] = scanner.nextInt();
            for (int j = 1; j <= peopleMap[i][0]; j++) {
                int floorNumber = scanner.nextInt();
                peopleMap[i][floorNumber]++;
            }
        }
        long t2 = System.nanoTime();
        System.out.println("Read Data in function: " + (t2 - t1) / 1000);
        inputList.add(buildNumber);
        inputList.add(heightNumber);
        inputList.add(loseNumber);
        inputList.add(peopleMap);
        return inputList;
    }

    private List<Object> inputByScanner() {
        List<Object> inputList = new ArrayList<>();
        int buildNumber;
        int heightNumber;
        int loseNumber;
        int[][] peopleMap;
        long t1 = System.nanoTime();
        try (Scanner scanner = new Scanner(System.in)) {
            buildNumber = scanner.nextInt();
            heightNumber = scanner.nextInt();
            loseNumber = scanner.nextInt();

            if (buildNumber < 1 || buildNumber > 1900) {
                System.out.println("Constraints broken: input for building number is not valid!");
                System.exit(1);
            }
            if (heightNumber < 1 || heightNumber > 1900) {
                System.out.println("Constraints broken: input for amount of floor's height is not valid!");
                System.exit(1);
            }
            peopleMap = new int[buildNumber][heightNumber + 1];

            if (loseNumber < 1 || loseNumber > 3450) {
                System.out.println("Constraints broken: input for amount of loses is not valid!");
                System.exit(1);
            }

            //to create array for containing potential transaction            
            for (int i = 0; i < buildNumber; i++) {
                peopleMap[i][0] = scanner.nextInt();
                for (int j = 1; j <= peopleMap[i][0]; j++) {
                    int floorNumber = scanner.nextInt();
                    peopleMap[i][floorNumber]++;
                }
            }
        }
        long t2 = System.nanoTime();
        System.out.println("Read Data in function: " + (t2 - t1) / 1000);
        inputList.add(buildNumber);
        inputList.add(heightNumber);
        inputList.add(loseNumber);
        inputList.add(peopleMap);
        return inputList;
    }

    public static void main(String[] args) {
        SupermanRescue sr = new SupermanRescue();
        long t1 = System.nanoTime();
        List<Object> inputs = sr.inputByScanner();
        long t2 = System.nanoTime();
        System.out.println("Read Data in Main: " + (t2 - t1) / 1000);

        int buildNumber = (int) inputs.get(0);
        int heightNumber = (int) inputs.get(1);
        int loseNumber = (int) inputs.get(2);
        int[][] peopleMap = (int[][]) inputs.get(3);

        for (int[] building : peopleMap) {
            for (int floors : building) {
                System.out.print(floors + " ");
            }
            System.out.println();
        }
        
        int[] location = {0, peopleMap[0].length-1};
        sr.selectFirstLocation(peopleMap, loseNumber, 0, location);
        System.out.println("location: " + location[0] + "；" + location[1]);
        
        location[0] = 1;
        location[1] = 7;
        sr.findNextLocation(peopleMap, loseNumber, location);
        System.out.println("location: " + location[0] + "；" + location[1]);
        System.exit(0);
        /*
        for (int i = heightNumber; i > 0; i--) {
            System.out.println(sr.sortByFloor(peopleMap, i));
        }
        System.out.println();
        
        
        int[] location = sr.selectFirstBuilding(peopleMap);
        System.out.println("location: " + location[0] + "；" + location[1]);
        //System.exit(0);
        */
        int maxPeople = sr.figureMapforMax(buildNumber, heightNumber, loseNumber, peopleMap);
        System.out.println("maxPeople=" + maxPeople);
    }

    private void print(int head, int[] datas) {
        System.out.print(head + "; ");
        for (int member : datas) {
            System.out.print(member + " ");
        }
        System.out.println();
    }

    private void print(int[] datas) {
        for (int member : datas) {
            System.out.print(member + " ");
        }
        System.out.println();
    }

    private void findNextLocation(int[][] peopleMap, int loseNumber, int[] location){        
        int inputBN = location[0];
        int inputFN = location[1]-1;    
        int cpn   = peopleMap[inputBN][inputFN];
        int cpn_sb= subTotalForFloorSection(peopleMap, inputFN, inputBN, loseNumber);        
        int jumpFN = inputFN - loseNumber;
        if (jumpFN<1) {
            jumpFN = 1;            
        }
        
        List<List<Integer>> sortByList = sortByFloor(peopleMap, jumpFN);
        List<Integer> maxPB = sortByList.get(0);
        System.out.println("location[0]=" + location[0]+ "; location[1]=" + location[1]
                + "; cpn=" + cpn + "; cpn_sb=" + cpn_sb
                + "; jumpFN=" + jumpFN + "; sortByList=" + sortByList + "; maxPB=" 
                + maxPB );
        if (maxPB.contains(inputBN)) { //means same building
            if (cpn<=0) {
                location[1] = inputFN - 1;
                findNextLocation(peopleMap, loseNumber, location);
            }
        } else {            
            int jumpBN = 0;
            int jpn_sb = 0;
            for (int jumpBNTemp : maxPB){
                int jpn_sbTemp= subTotalForFloorSection(peopleMap, inputFN, jumpBNTemp, loseNumber);
                if (jpn_sbTemp>=jumpBN) {
                    jumpBN = jumpBNTemp;
                    jpn_sb = jpn_sbTemp;
                }                
            }
            int jpn   = peopleMap[jumpBN][jumpFN];
            if (cpn < jpn || jpn_sb <= jpn) {
                location[0] = jumpBN;
                location[1] = inputFN; 
                findNextLocation(peopleMap, loseNumber, location);
            }            
        }        
    }  
    
    private void selectFirstLocation(int[][] peopleMap, int loseNumber, 
            int preAmount, int[] location){
        
        int inputBN = location[0];
        int inputFN = location[1];        
        int cpn   = peopleMap[inputBN][inputFN];
        int cpn_sb= subTotalForFloorSection(peopleMap, inputFN, inputBN, loseNumber);        
        int jumpFN = inputFN - loseNumber;
        if (jumpFN<1) {
            jumpFN = 1;            
        }
        
        List<List<Integer>> sortByList = sortByFloor(peopleMap, jumpFN);
        List<Integer> maxPB = sortByList.get(0);
        System.out.println("location[0]=" + location[0]+ "; location[1]=" + location[1]
                + "; preAmount=" + preAmount + "; cpn=" + cpn + "; cpn_sb=" + cpn_sb
                + "; jumpFN=" + jumpFN + "; sortByList=" + sortByList + "; maxPB=" 
                + maxPB );
        if (maxPB.contains(inputBN)) { //means same building
            if (cpn<=0) {
                location[1] = inputFN - 1;
                selectFirstLocation(peopleMap, loseNumber, cpn, location);
            }
        } else {            
            int jumpBN = 0;
            int jpn_sb = 0;
            for (int jumpBNTemp : maxPB){
                int jpn_sbTemp= subTotalForFloorSection(peopleMap, inputFN, jumpBNTemp, loseNumber);
                if (jpn_sbTemp>=jumpBN) {
                    jumpBN = jumpBNTemp;
                    jpn_sb = jpn_sbTemp;
                }                
            }
            int jpn   = peopleMap[jumpBN][jumpFN];
            if (cpn < jpn || jpn_sb <= jpn) {
                location[0] = jumpBN;
                location[1] = inputFN; 
                selectFirstLocation(peopleMap, loseNumber, jpn, location);
            }            
        }        
    }
    
    

    public int figureMapforMax(int buildNumber, int heightNumber, int loseNumber, int[][] peopleMap) {
        int maxAmount = 0;
        int[] location = {0, peopleMap[0].length-1};
        selectFirstLocation(peopleMap, loseNumber, 0, location);
        int pBuildNumber = location[0];
        System.out.println("location: " + location[0] + "；" + location[1]);
        boolean isFirst = true;
        for (int fn = location[1]; fn > 0; fn--) {
            List<List<Integer>> sortByList_1 = sortByFloor(peopleMap, fn);     
            
            int cBuildNumber;
            List<Integer> moreSet = sortByList_1.get(0);
            System.out.println(fn + "; " + sortByList_1 + "; moreSet.contains("
                    + pBuildNumber+") ?" + moreSet.contains(pBuildNumber));
            if (moreSet.contains(pBuildNumber)) {
                cBuildNumber = pBuildNumber;
                int pa_cbf = peopleMap[cBuildNumber][fn]; //to get people in current build and floor
                int nextFloor = fn - loseNumber;
                
                if (nextFloor<1){
                    nextFloor=1;
                }
                
                int pa_tbf = subTotalForFloorSection(peopleMap, fn, cBuildNumber, loseNumber);
                List<List<Integer>> sortByList_2 = sortByFloor(peopleMap, nextFloor);
                System.out.print("\tcBuildNumber=" + cBuildNumber + "; fn="
                    + fn+"; pa_cbf=" + pa_cbf +"; nextFloor=" + nextFloor 
                    +"; pa_tbf=" + pa_tbf +"; sortByList_2=" + sortByList_2 
                    +"; maxAmount=" + maxAmount  );
                if (sortByList_2.get(0).contains(cBuildNumber)) {
                    maxAmount = maxAmount + pa_cbf;
                    //break;
                }                    

                int dumpBuildNumber = 0;
                for (int nbn : sortByList_2.get(0) ){
                    dumpBuildNumber = nbn;
                    //break;
                }
                int pa_nbf = peopleMap[dumpBuildNumber][nextFloor]; //to get people in next build and floor
                System.out.print("; dumpBuildNumber=" + dumpBuildNumber + "; nextFloor="
                    + nextFloor+"; pa_nbf=" + pa_nbf +"\n"   );
                if (pa_nbf<=pa_cbf) { //keep same build if pa_cbf >= pa_nbf
                    maxAmount = maxAmount + pa_cbf;
                    //break;
                } else {
                    if (pa_nbf < pa_tbf){//keep same build if pa_tbf > pa_nbf
                        maxAmount = maxAmount + pa_cbf;
                        //break;
                    } else {
                        // dump to next build pa_tbf <= pa_nbf
                        maxAmount = maxAmount + pa_nbf;
                        pBuildNumber=dumpBuildNumber;
                        fn = nextFloor;
                        System.out.println("\t\tpBuildNumber=" + pBuildNumber + "; fn="
                            + fn+"; maxAmount=" + maxAmount  );
                        //break;
                    }
                } 
            } else {
                //dump
                System.out.println(moreSet + " did not contains("+pBuildNumber+")");
            }            
            isFirst = false;
        }
        return maxAmount;
    }

    /**
     *
     * @param peopleMap
     * @param floor
     * @param bn
     * @param loseNumber
     * @return
     */
    public int subTotalForFloorSection(int[][] peopleMap, int floor, int bn, int loseNumber) {
        int output = 0;
        for (int i = 0; i <= loseNumber; i++) {
            int fn = floor - i;
            if (fn<=1){
                break;
            }
            output = output + peopleMap[bn][fn];
        }
        return output;
    }

    public List<List<Integer>> sortByFloor(int[][] peopleMap, int floor) {
        int[] outputList = new int[peopleMap.length];

        for (int i = 0; i < peopleMap.length; i++) {
            outputList[i] = peopleMap[i][floor];
        }
        //sort(outputList);
        return sort(outputList);
    }

    private List<List<Integer>> sort(int[] data) {
        int[] temp = (int[]) data.clone();
        int[] output = new int[data.length];

        for (int i = 0; i < temp.length - 1; i++) {
            for (int j = i + 1; j < temp.length; j++) {
                if (temp[i] < temp[j]) {
                    int tempDate = temp[i];
                    temp[i] = temp[j];
                    temp[j] = tempDate;

                    tempDate = output[i];
                    output[i] = j;
                    output[j] = tempDate;
                } 
            }
        }
        List<List<Integer>> o = new ArrayList<>();
        boolean isSame = false;
        List<Integer> memberList = null;
        for (int i = 0; i < output.length - 1; i++) {
            if (isSame) {
                if (temp[i] == temp[i + 1]) {
                    memberList = o.get(o.size() - 1);
                    memberList.add(output[i + 1]);
                    isSame = true;
                } else {
                    memberList = new ArrayList<>();
                    memberList.add(output[i]);
                    o.add(memberList);
                    isSame = false;
                    if (i == output.length - 2) {
                        memberList = new ArrayList<>();
                        memberList.add(output[output.length - 1]);
                        o.add(memberList);
                    }
                }
            } else {
                memberList = new ArrayList<>();
                memberList.add(output[i]);
                o.add(memberList);
                if (temp[i] == temp[i + 1]) {
                    memberList.add(output[i + 1]);
                    isSame = true;
                } else {
                    isSame = false;
                    if (i == output.length - 2) {
                        memberList = new ArrayList<>();
                        memberList.add(output[output.length - 1]);
                        o.add(memberList);
                    }
                }
            }

        }

        return o;
    }
}
