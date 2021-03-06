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
public class PermutationCombination_Itertor {

    public static void main(String[] args) {
        PermutationCombination_Itertor pi = new PermutationCombination_Itertor();
        // TODO Auto-generated method stub
        Object[] tmp = {1, 2, 3, 4, 5, 6};
        ArrayList<Object[]> rs=pi.RandomC(tmp, 4, 14);
        //ArrayList<Object[]> rs = pi.cmn(tmp, 4);
        for (int i = 0; i < rs.size(); i++) {
//            System.out.print(i+"=");
            int subtotal = 0;
            for (int j = 0; j < rs.get(i).length; j++) {
                subtotal = subtotal + (int)rs.get(i)[j];
                System.out.print(rs.get(i)[j] + ",");
            }
            System.out.print(" : " + subtotal );
            System.out.println();

        }
    }
    
    private void filterCombination(Object[] rawCombination, int maxAmount, ArrayList<Object[]> result){
        int sumAmount=0; 
        for (int i=0; i<rawCombination.length; i++) {
            int member = (int) rawCombination[i];
            System.out.print(member + "; " );
            sumAmount = sumAmount + member;
            if (sumAmount>maxAmount) {
                return;
            }
            if (sumAmount==maxAmount) {
                result.add(rawCombination);
                return;
            }
        }
        System.out.println( );
        
    }
    
    // 求一个数组的任意组合
    public ArrayList<Object[]> RandomC(Object[] source, int maxSelectObject, int maxAmount) {
        ArrayList<Object[]> result;
        result = new ArrayList<>();
        if (source.length == 1) {
            //result.add(source);
            filterCombination(source, maxAmount, result);
        } else {
            Object[] psource = new Object[source.length - 1];
            /*
            for (int i = 0; i < psource.length; i++) {
            psource[i] = source[i];
            }
             */
            System.arraycopy(source, 0, psource, 0, psource.length);
            result = RandomC(psource, maxSelectObject, maxAmount);
            int len = result.size();//fn组合的长度
            if (maxSelectObject>0 && len>maxSelectObject) {
                len = maxSelectObject;
            }
            //result.add((new Object[]{source[source.length - 1]}));
            filterCombination((new Object[]{source[source.length - 1]}), maxAmount, result);
            for (int i = 0; i < len; i++) {
                Object[] tmp = new Object[result.get(i).length + 1];
                for (int j = 0; j < tmp.length - 1; j++) {
                    tmp[j] = result.get(i)[j];
                }
                tmp[tmp.length - 1] = source[source.length - 1];
                //result.add(tmp);
                filterCombination(tmp, maxAmount, result);
            }

        }
        return result;
    }

    // 求一个数组的任意组合
    public ArrayList<Object[]> RandomC(Object[] source) {
        ArrayList<Object[]> result;
        result = new ArrayList<>();
        if (source.length == 1) {
            result.add(source);
        } else {
            Object[] psource = new Object[source.length - 1];
            for (int i = 0; i < psource.length; i++) {
                psource[i] = source[i];
            }
            result = RandomC(psource);
            int len = result.size();//fn组合的长度
            result.add((new Object[]{source[source.length - 1]}));
            for (int i = 0; i < len; i++) {
                Object[] tmp = new Object[result.get(i).length + 1];
                for (int j = 0; j < tmp.length - 1; j++) {
                    tmp[j] = result.get(i)[j];
                }
                tmp[tmp.length - 1] = source[source.length - 1];
                result.add(tmp);
            }

        }
        return result;
    }

    public ArrayList<Object[]> cmn(Object[] source, int n) {
        ArrayList<Object[]> result = new ArrayList<Object[]>();
        if (n == 1) {
            for (int i = 0; i < source.length; i++) {
                result.add(new Object[]{source[i]});

            }
        } else if (source.length == n) {
            result.add(source);
        } else {
            Object[] psource = new Object[source.length - 1];
            for (int i = 0; i < psource.length; i++) {
                psource[i] = source[i];
            }
            result = cmn(psource, n);
            ArrayList<Object[]> tmp = cmn(psource, n - 1);
            for (int i = 0; i < tmp.size(); i++) {
                Object[] rs = new Object[n];
                for (int j = 0; j < n - 1; j++) {
                    rs[j] = tmp.get(i)[j];
                }
                rs[n - 1] = source[source.length - 1];
                result.add(rs);
            }
        }
        return result;
    }
}
