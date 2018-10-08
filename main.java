package A1;

import A1.Chaining.*;
import A1.Open_Addressing.*;
import java.io.*;
import java.util.*;

public class main {

    /**
     * Calculate 2^w
     */
    public static int power2(int w) {
        return (int) Math.pow(2, w);
    }

    /**
     * Uniformly generate a random integer between min and max, excluding both
     */
    public static int generateRandom(int min, int max, int seed) {
        Random generator = new Random();
        //if the seed is equal or above 0, we use the input seed, otherwise not.
        if (seed >= 0) {
            generator.setSeed(seed);
        }
        int i = generator.nextInt(max - min - 1);
        return i + min + 1;
    }

    /**
     * export CSV file
     */
    public static void generateCSVOutputFile(String filePathName, ArrayList<Double> alphaList, ArrayList<Double> avColListChain, ArrayList<Double> avColListProbe) {
        File file = new File(filePathName);
        FileWriter fw;
        try {
            fw = new FileWriter(file);
            int len = alphaList.size();
            fw.append("Alpha");
            for (int i = 0; i < len; i++) {
                fw.append("," + alphaList.get(i));
            }
            fw.append('\n');
            fw.append("Chain");
            for (int i = 0; i < len; i++) {
                fw.append("," + avColListChain.get(i));
            }
            fw.append('\n');
            fw.append("Open Addressing");
            for (int i = 0; i < len; i++) {
                fw.append(", " + avColListProbe.get(i));
            }
            fw.append('\n');
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        /*===========PART 1 : Experimenting with n===================*/
        //Initializing the three arraylists that will go into the output 
        ArrayList<Double> alphaList = new ArrayList<Double>();
        ArrayList<Double> avColListChain = new ArrayList<Double>();
        ArrayList<Double> avColListProbe = new ArrayList<Double>();

        //Keys to insert into both hash tables
        int[] keysToInsert = {164, 127, 481, 132, 467, 160, 205, 186, 107, 179,
            955, 533, 858, 906, 207, 810, 110, 159, 484, 62, 387, 436, 761, 507,
            832, 881, 181, 784, 84, 133, 458, 36};

        //values of n to test for in the experiment
        int[] nList = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32};
        //value of w to use for the experiment on n
        int w = 10;

        for (int n : nList) { //loops through the entire list

            //initializing two hash tables with a seed
            Chaining MyChainTable = new Chaining(w, 137);
            Open_Addressing MyProbeTable = new Open_Addressing(w, 137);

            /*Use the hash tables to compute the average number of 
                        collisions over keys keysToInsert, for each value of n. 
                        The format of the three arraylists to fillis as follows:
                        
                        alphaList = arraylist of all tested alphas 
                                   (corresponding to each tested n)
                        avColListChain = average number of collisions for each
                                         Chain experiment 
                                         (make sure the order matches alphaList)
                        avColListProbe =  average number of collisions for each
                                         Linear Probe experiment
                                           (make sure the order matches)
                        The CSV file will output the result which you can visualize
             */
            //ADD YOUR CODE HERE
            double colChain_task1 = 0;
            double colProbe_task1 = 0;
            for(int i=0; i < n; i++) {
                colChain_task1 = colChain_task1 + (double) MyChainTable.insertKey(keysToInsert[i]);
                colProbe_task1 = colProbe_task1 + (double) MyProbeTable.insertKey(keysToInsert[i]);
                
            }
            alphaList.add((double) n / (double) MyChainTable.m); //alpha will be same for both hashTable
            avColListChain.add(colChain_task1 / (double) n);
            avColListProbe.add(colProbe_task1 / (double) n);
        }
        //need to erase this block of code
        System.out.println("alpha list");
        for(double x : alphaList) {
            System.out.println(x);
        }
        System.out.println("chain list");
        for(double x : avColListChain) {
            System.out.println(x);
        }
        System.out.println("probe list");
        for(double x : avColListProbe) {
            System.out.println(x);
        }
        generateCSVOutputFile("n_comparison.csv", alphaList, avColListChain, avColListProbe);

        /*===========    PART 2 : Test removeKey  ===================*/
 /* In this exercise, you apply your removeKey method on an example.
        Make sure you use the same seed, 137, as you did in part 1. You will
        be penalized if you don't use the same seed.
         */
        //Please not the output CSV will be slightly wrong; ignore the labels.
        ArrayList<Double> removeCollisions = new ArrayList<Double>();
        ArrayList<Double> removeIndex = new ArrayList<Double>();
        int[] keysToRemove = {6, 8, 164, 180, 127, 3, 481, 132, 4, 467, 5, 160,
            205, 186, 107, 179};

        //ADD YOUR CODE HERE
        
        Open_Addressing probeTable = new Open_Addressing(w, 137);
        for(int x=0; x < 16; x++) {
            probeTable.insertKey(keysToInsert[x]);
        }
        for(int x=0; x < keysToRemove.length; x++) {
            removeCollisions.add((double) probeTable.removeKey(keysToRemove[x]));
            removeIndex.add((double) keysToRemove[x]);
        }
        //need to remove this block of code
        System.out.println("new probe table of 16 elements");
        for(int x : probeTable.Table) {
            System.out.println(x);
        }
        System.out.println("no of collisions in each removekey attempted");
        for(double x : removeCollisions) {
            System.out.println(x);
        }
        System.out.println("removeIndex");
        for(double x : removeIndex) {
            System.out.println(x);
        }
        generateCSVOutputFile("remove_collisions.csv", removeIndex, removeCollisions, removeCollisions);

        /*===========PART 3 : Experimenting with w===================*/

 /*In this exercise, the hash tables are random with no seed. You choose 
                values for the constant, then vary w and observe your results.
         */
        //generating random hash tables with no seed can be done by sending -1
        //as the seed. You can read the generateRandom method for detail.
        //randomNumber = generateRandom(0,55,-1);
        //Chaining MyChainTable = new Chaining(w, -1);
        //Open_Addressing MyProbeTable = new Open_Addressing(w, -1);
        //Lists to fill for the output CSV, exactly the same as in Task 1.
        ArrayList<Double> alphaList2 = new ArrayList<Double>();
        ArrayList<Double> avColListChain2 = new ArrayList<Double>();
        ArrayList<Double> avColListProbe2 = new ArrayList<Double>();

        //ADD YOUR CODE HERE
        w = 12;
        int noOfW = 20;
        int n = 30;
        int simulation = 10;
                        
        for(int x=0; x < noOfW; x++) { //for each w
            Chaining chainTable_task3 = new Chaining(w, -1);
            Open_Addressing probeTable_task3 = new Open_Addressing(w, -1);
            Double[] averageSimulationColChain = new Double[simulation];
            Double[] averageSimulationColProbe = new Double[simulation];
            
            for(int y=0; y<simulation; y++) { //for each different A 
                double colChain_task3 = 0;
                double colProbe_task3 = 0;
                
                for(int z=0; z<n; z++) { //filling up both tables with n = 30 keys
                    colChain_task3 = colChain_task3 + (double) chainTable_task3.insertKey(main.generateRandom(0, 500, -1)); //collision in each insertion
                    colProbe_task3 = colProbe_task3 + (double) probeTable_task3.insertKey(main.generateRandom(0, 500, -1)); // ||
                }                                
                averageSimulationColChain[y] = colChain_task3 / (double) n;   //average no of collisions in each of 10 simulations in chaining                             
                averageSimulationColProbe[y] = colProbe_task3 / (double) n;   //average no of collisions in each of 10 simulations in open addressing
            }
            //summing up the averaged collision value from the entire array in Chaining
            double sum = 0;
            for(int i=0; i<averageSimulationColChain.length; i++) {                
                sum = sum + averageSimulationColChain[i];
            }
            avColListChain2.add(sum / (double) simulation); //extracting the finalRepresentative average of 10 simulation pack
            //summing up the averaged collision value from the entire array in open-addressing
            sum = 0;
            for (int i = 0; i < averageSimulationColProbe.length; i++) {
                sum = sum + averageSimulationColProbe[i];
            }
            avColListProbe2.add(sum / (double) simulation); //extracting the finalRepresentative average of 10 simulation pack
            
            w+=2; //incrementing w by 2
            
        }
                
        generateCSVOutputFile("w_comparison.csv", alphaList2, avColListChain2, avColListProbe2);

    }

}
