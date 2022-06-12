package com.Algorithms2;

import java.util.*;

public class StableMatching {
    public static void main(String[] args) {
        int preferList[][] = new int[][]  //first 4 lists for men, last 4 for women. So men are numbered from 0 to N-1 and women are numbered from N to 2*N – 1.
                {{7, 5, 6, 4},
                {5, 4, 6, 7},
                {4, 5, 6, 7},
                {4, 5, 6, 7},
                {0, 1, 3, 2},
                {0, 1, 2, 3},
                {0, 2, 3, 1},
                {1 ,0, 3, 2}};
        stableMarriage(preferList);
    }
    public static void stableMarriage(int[][] preferList){
        int n = preferList.length/2;
        int[] wPartner = new int[n]; //stores partner of women. wPartner[i] indicates partner assigned to woman N+i.
        boolean[] mEngaged = new boolean[n];  // initially all men and women are free
        Arrays.fill(wPartner, -1); //-1 means woman is free
        int freeMen = n;

        while(freeMen>0) { //until there exists free man
            int man;
            for (man = 0; man < n; man++){
                if (!mEngaged[man])  // pick the first free man
                    break;
            }
            //go through all women in currentMan's preferable list
            for(int i = 0; i<n && !mEngaged[man]; i++){
                int currentWoman = preferList[man][i];

                if(wPartner[currentWoman-n] == -1){ // if the picked woman from list is free, get engaged with her.
                    wPartner[currentWoman-n] = man;
                    mEngaged[man] = true;
                    freeMen--;
                }
                else{ // if picked woman is not free
                    int currentMan = wPartner[currentWoman-n]; //current engaged man of w
                    if(!wPrefersCurrentMan(preferList, currentMan, man, currentWoman)){
                        //if currentWoman prefers new man over current engaged man
                        //break engagement with w and m, engage w and new man
                         wPartner[currentWoman-n] = man;
                         mEngaged[currentMan] = false;
                         mEngaged[man] = true;
                    }
                }
            }
        }
        System.out.println("MEN\t WOMEN");
        for(int i=0;i<n;i++){
            System.out.println(wPartner[i] + " \t \t" + (i+n));
        }
    }
    //returns true if the woman prefers her currentMan over new engaged man
    public static boolean wPrefersCurrentMan(int[][] preferList, int current, int newMan, int woman){
        boolean prefersCurrent = false;
        int[] wPreferList = preferList[woman];
        for (int j : wPreferList) {
            if (j == current) {
                prefersCurrent = true;
                break;
            }
            if (j == newMan) {
                break;
            }
        }
        return prefersCurrent;
    }

}
