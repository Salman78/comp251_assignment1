package A1;

import java.util.*;
import static A1.main.*;

public class Chaining {

    public int m; // number of SLOTS AVAILABLE
    public int A; // the default random number
    int w;
    int r;
    public ArrayList<ArrayList<Integer>> Table;

    //Constructor for the class. sets up the data structure for you
    protected Chaining(int w, int seed) {
        this.w = w;
        this.r = (int) (w - 1) / 2 + 1;
        this.m = power2(r);
        this.Table = new ArrayList<ArrayList<Integer>>(m); //creating the table with m primary slots
        for (int i = 0; i < m; i++) {
            Table.add(new ArrayList<Integer>()); //creating reference to subordinate arraylists in each table slot
        }
        this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
    }

    /**
     * Implements the hash function h(k)
     */
    public int chain(int key) {
        //ADD YOUR CODE HERE (change return statement)
        int hashValue = 0;
        hashValue = ((this.A*key) % (int)(Math.pow(2, this.w))) >> (w-r); 
        return hashValue;
    }

    /**
     * Checks if slot n is empty
     */
    public boolean isSlotEmpty(int hashValue) {
        return Table.get(hashValue).size() == 0;
    }

    /**
     * Inserts key k into hash table. Returns the number of collisions
     * encountered
     */
    public int insertKey(int key) {
        //ADD YOUR CODE HERE (chane return statement)
        int hashValue = this.chain(key); 
        int collision = 0;
                
        collision = Table.get(hashValue).size(); //no. of collisions equal no. of elements in the given subordinate arraylist
        for(int i : Table.get(hashValue)) {
            if(i == key) {
                return -1;
            }
        }
        Table.get(hashValue).add(key);
        return collision;
    }

}
