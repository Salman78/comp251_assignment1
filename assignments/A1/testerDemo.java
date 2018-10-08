package A1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import A1.main.*;

public class testerDemo {
    public static void main(String[] args) {
        System.out.println("### This is the assignment tester ###");
        System.out.println("This is in no way a definitive test for correctness of your assignment");
        System.out.println("Please add extra tests here to test your own code");

        System.out.println("Running checks");
        Open_Addressing openMap = new Open_Addressing(10, 0);
        Chaining chainingMap = new Chaining(10, 0);

        int chain = chainingMap.chain(1);
        System.out.println("Chaining chain check:" + (chain == 30));

        chainingMap.insertKey(1);
        System.out.println("Chaining insert check" + (chainingMap.Table.get(30).size() == 1));

        int probe = openMap.probe(1, 1);
        System.out.println("Open Addressing probe check:" + (probe == 31));

        //openMap.insertKey(0);
        //System.out.println("Open Addressing insert check:" + (openMap.Table[0] == 0));

        Random rand = new Random();
        //int  n = rand.nextInt(50) + 1;
        
      
            openMap.insertKey(41);
            openMap.insertKey(37);
            openMap.insertKey(44);
            openMap.insertKey(1);
            openMap.insertKey(21);
            openMap.insertKey(40);
            openMap.insertKey(9);
            openMap.insertKey(45);
            openMap.insertKey(34);
            openMap.insertKey(6);
            openMap.insertKey(42);
            openMap.insertKey(2);
            openMap.insertKey(11);
            openMap.insertKey(61);
            openMap.insertKey(14);
            openMap.insertKey(31);
            openMap.insertKey(29);
            openMap.insertKey(19);
            openMap.insertKey(71);
            openMap.insertKey(73);
            openMap.insertKey(87);
            openMap.insertKey(85);
            openMap.insertKey(92);
            openMap.insertKey(91);
            openMap.insertKey(94);
            openMap.insertKey(99);
            openMap.insertKey(75);
            openMap.insertKey(79);
            openMap.insertKey(88);
            openMap.insertKey(86);
            openMap.insertKey(55);
            
            
            
            openMap.removeKey(45);
            System.out.println("Open Addressing remove check:" + (openMap.Table[24] != openMap.Table[24]));
            openMap.removeKey(9);
            System.out.println("Open Addressing remove check:" + (openMap.Table[17] != openMap.Table[17]));
            openMap.removeKey(44);
            System.out.println("Open Addressing remove check:" + (openMap.Table[25] != openMap.Table[25]));
            openMap.removeKey(86);
            System.out.println("Open Addressing remove check:" + (openMap.Table[23] != openMap.Table[23]));
            openMap.removeKey(29);
            System.out.println("Open Addressing remove check:" + (openMap.Table[18] != openMap.Table[18]));
            
            
            
            
        
        /*
        for(int x = 0; x <= (openMap.m-1); x++) {
            int deletedIndex = rand.nextInt((openMap.m-1)) + 1;
            openMap.removeKey(openMap.Table[deletedIndex]);            
            System.out.println("Open Addressing remove check:" + (openMap.Table[deletedIndex] != openMap.Table[deletedIndex]));
        }
        */
        //openMap.insertKey(5);
        //openMap.removeKey(5);
        //System.out.println("Open Addressing remove check:" + (openMap.Table[30] != 5));

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("n_comparison.csv")));
            String line;
            List<Integer> lengths = new LinkedList<>();
            String firstLine = "";
            while ((line = br.readLine()) != null) {
                if (firstLine.equals("")) {
                    firstLine = line;
                }
                lengths.add(line.split(",").length);
            }
            System.out.println("n_comparison file check:" + (firstLine.equals("Alpha,0.0625,0.125,0.1875,0.25,0.3125,0.375,0.4375,0.5,0.5625,0.625,0.6875,0.75,0.8125,0.875,0.9375,1.0")
                    && lengths.stream()
                            .map(l -> l == lengths.get(0))
                            .reduce((acc, x) -> acc && x)
                            .get()));
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("You have not created n_comparison.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("remove_collisions.csv")));
            String line;
            List<Integer> lengths = new LinkedList<>();
            while ((line = br.readLine()) != null) {
                lengths.add(line.split(",").length);
            }
            System.out.println("remove_collisions file check:" + lengths.stream()
                    .map(l -> l == lengths.get(0))
                    .reduce((acc, x) -> acc && x)
                    .get());
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("You have not created remove_collisions.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("w_comparison.csv")));
            String line;
            List<Integer> lengths = new LinkedList<>();
            while ((line = br.readLine()) != null) {
                lengths.add(line.split(",").length);
            }
            System.out.println("remove_collisions file check:" + lengths.stream()
                    .map(l -> l == lengths.get(0))
                    .reduce((acc, x) -> acc && x)
                    .get());
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("You have not created w_comparison.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

