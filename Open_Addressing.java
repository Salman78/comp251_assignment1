package A1;

import static A1.main.*;

public class Open_Addressing {

    public int m; // number of SLOTS AVAILABLE
    public int A; // the default random number
    int w;
    int r;
    public int[] Table;

    //Constructor for the class. sets up the data structure for you
    protected Open_Addressing(int w, int seed) {

        this.w = w;
        this.r = (int) (w - 1) / 2 + 1;
        this.m = power2(r);
        this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
        this.Table = new int[m];
        //empty slots are initalized as -1, since all keys are positive
        for (int i = 0; i < m; i++) {
            Table[i] = -1;
        }

    }

    /**
     * Implements the hash function g(k)
     */
    public int probe(int key, int i) {
        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
        int hashValue = ((((this.A*key) % (int)(Math.pow(2, this.w))) >> (w-r)) + i) % (int) (Math.pow(2, r));
              
        return hashValue;
    }

    /**
     * Checks if slot n is empty
     */
    public boolean isSlotEmpty(int hashValue) {
        return Table[hashValue] == -1;
    }

    /**
     * Inserts key k into hash table. Returns the number of collisions
     * encountered
     */
    public int insertKey(int key) {
        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
            
        int finalHashValue = 0;
        int i = 0;
        
        for (i = 0; i <= m - 1; i++) {
            finalHashValue = this.probe(key, i);
            //checks for duplicates
            if (Table[finalHashValue] == key) { //even if the slot is empty, the key wouldn't equal -1
                return -1;
            }
            else if(isSlotEmpty(finalHashValue)) {
                this.Table[finalHashValue] = key;
                return i;
            }
        }
        return i; //i = collision
        
    }

    /**
     * Removes key k from hash table. Returns the number of collisions
     * encountered
     */
    public int removeKey(int key) {
        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
        
        int i = 0;
        int deletedHashValue = 0;
        for(i=0; i <= m-1; i++) {
            deletedHashValue = this.search(key, i);
            if (isSlotEmpty(deletedHashValue)) {
                return i; //i = no. of collisions
            }
            else if(Table[deletedHashValue] == key) {
                Table[deletedHashValue] = -2;
                return i;
            }
        }
        return i;

    }
    //helper method
    public int search(int key, int i) {
        int hashValue = ((((this.A * key) % (int) (Math.pow(2, this.w))) >> (w - r)) + i) % (int) (Math.pow(2, r));

        return hashValue;
    }

}
