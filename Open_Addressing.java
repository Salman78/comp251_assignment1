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
        int hashValue = (((this.A*key) % (int)(Math.pow(2, this.w))) >> (w-r)) + i;
        for(int j=0; j<=m-1; j++) {
            if(hashValue > m-1) hashValue = hashValue - m;
            if(isSlotEmpty(hashValue)) {
                return hashValue;
            }
            hashValue++;
                       
        }
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
        int collision = 0;        
        int i =  0 + (int)(Math.random() * (((m-1) - 0) + 1));
        
        int initialHashValue = (((this.A*key) % (int)(Math.pow(2, this.w))) >> (w-r)) + i;
        if(initialHashValue > (m-1)) initialHashValue = initialHashValue - m;
        int finalHashValue = this.probe(key, i); //hardcoding i = 0;
        
        if(finalHashValue - initialHashValue < 0) collision = (finalHashValue - initialHashValue) + (m-1);
        else collision = (finalHashValue - initialHashValue);
        
        if(isSlotEmpty(finalHashValue)) {
            this.Table[finalHashValue] = key;
            return collision;
        }
        else return collision; //no empty slot found to insert value
    }

    /**
     * Removes key k from hash table. Returns the number of collisions
     * encountered
     */
    public int removeKey(int key) {
        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
        //first we traverse through the arraylist to find the element
        int collision = 0;
        int hashValue = this.search(key);
        if(hashValue % 10 != 0) {
            collision = hashValue;
            return collision; //unsuccessful search returning collision
        }
        //element found
        hashValue = hashValue/10; //normalizing the hashValue to get back the original value 
        
        this.Table[hashValue] = -10; //we mark this table as free deleted index by assining the value -10
        int i = 1;
        /*
        while(!(isSlotEmpty(hashValue + i) || hashValue + i <= (m-1))) { //we stop at next empty slot with value -1 or if index goes out of bound
            if(((this.A*this.Table[hashValue+i] % (int)(Math.pow(2, this.w))) >> (w-r)) == hashValue) { //check if current value can be moved to deleted index
                this.Table[hashValue] = this.Table[hashValue + i];
                this.Table[hashValue + i] = -10; //current value updated to new deleted free index
            }
            i++;
        }
          */
        boolean traverse = true;
        while(hashValue + i <= m-1) {
            if(!(isSlotEmpty(hashValue + i))) {
                if (((this.A * this.Table[hashValue + i] % (int) (Math.pow(2, this.w))) >> (w - r)) == hashValue) { //check if current value can be moved to deleted index
                    this.Table[hashValue] = this.Table[hashValue + i];
                    this.Table[hashValue + i] = -10; //current value updated to new deleted free index
                }
                i++;
            }
            else if(isSlotEmpty(hashValue + i)) {
                traverse = false;
            }
        }
        if(traverse == false) {
            return collision;
        }
        i = 0;
        while(i != hashValue) {
            if (((this.A * this.Table[i] % (int) (Math.pow(2, this.w))) >> (w - r)) == hashValue) { //check if current value can be moved to deleted index
                this.Table[hashValue] = this.Table[i];
                this.Table[i] = -10; //current value updated to new deleted free index
            }
            i++;
        }
        return collision;
    }
    //helper method
    public int search(int key) {
        int initialHashValue = ((this.A*key) % (int)(Math.pow(2, this.w))) >> (w-r);
        int collision = 0;
        while(!(isSlotEmpty(initialHashValue))) { //stops when encouters -1 i.e next empty slot from initialHashValue
            if(this.Table[initialHashValue] == key) {
                return 10*initialHashValue; //a successful search returns hashValue multiplied by ten
            }
            initialHashValue++;
            collision++;
            
        }
        return collision; //a failed search returns collision
    }

}
