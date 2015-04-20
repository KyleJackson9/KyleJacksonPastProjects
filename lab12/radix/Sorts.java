/* Radix.java */

package radix;

/**
 * Sorts is a class that contains an implementation of radix sort.
 * @author Kyle Jackson
 */
public class Sorts {


    /**
     *  Sorts an array of int keys according to the values of <b>one</b>
     *  of the base-16 digits of each key. Returns a <b>NEW</b> array and
     *  does not modify the input array.
     *  
     *  @param key is an array of ints.  Assume no key is negative.
     *  @param whichDigit is a number in 0...7 specifying which base-16 digit
     *    is the sort key. 0 indicates the least significant digit which
     *    7 indicates the most significant digit
     *  @return an array of type int, having the same length as "keys"
     *    and containing the same keys sorted according to the chosen digit.
     **/
    public static int[] countingSort(int[] keys, int whichDigit) {
        //YOUR CODE HERE
        int[] b = keys;
        int[] out = keys;
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;
        int count7 = 0;
        int count8 = 0;
        int count9 = 0;
        for (int i = 0; i < keys.length; i++) {
            b[i] = b[i] << (whichDigit);
            b[i] = b[i] >>  (7-whichDigit);
            if (b[i] == 0) {
                count0++;
            }
            if (b[i] == 1) {
                count1++;
            }
                        if (b[i] == 2) {
                count2++;
            }
                        if (b[i] == 3) {
                count3++;
            }
                        if (b[i] == 4) {
                count4++;
            }
                        if (b[i] == 5) {
                count5++;
            }
                        if (b[i] == 6) {
                count6++;
            }
                        if (b[i] == 7) {
                count7++;
            }
                        if (b[i] == 8) {
                count8++;
            }
                        if (b[i] == 9) {
                count9++;
            }
        }
        count1 += count0;
        count2 += count1;
        count3 += count2;
        count4 += count3;
        count5 += count4;
        count6 += count5;
        count7 += count6;
        count8 += count7;
        count9 += count8;
        for(int i = 0; i < keys.length; i++) {
            if (b[i] == 0) {
                out[count0] = keys[i];
                count0++;
            }
            if (b[i] == 1) {
                out[count1] = keys[i];
                count1++;
            }
             if (b[i] == 2) {
                out[count2] = keys[i];
                count2++;
            }
             if (b[i] == 3) {
                out[count3] = keys[i];
                count3++;
            }
                        if (b[i] == 4) {
                            out[count4] = keys[i];
                count4++;
            }
                        if (b[i] == 5) {
                            out[count5] = keys[i];
                count5++;
            }
                        if (b[i] == 6) {
                            out[count6] = keys[i];
                count6++;
            }
                        if (b[i] == 7) {
                            out[count7] = keys[i];
                count7++;
            }
                        if (b[i] == 8) {
                            out[count8] = keys[i];
                count8++;
            }
                        if (b[i] == 9) {
                            out[count9] = keys[i];
                count9++;
            }


        }
        return out;


    }

    /**
     *  radixSort() sorts an array of int keys (using all 32 bits
     *  of each key to determine the ordering). Returns a <b>NEW</b> array
     *  and does not modify the input array
     *  @param key is an array of ints.  Assume no key is negative.
     *  @return an array of type int, having the same length as "keys"
     *    and containing the same keys in sorted order.
     **/
    public static int[] radixSort(int[] keys) {
        //YOUR CODE HERE
        int[] a = keys;
        a = countingSort(a, 0);
        a = countingSort(a, 1);
        a = countingSort(a, 2);
        a = countingSort(a, 3);
        a = countingSort(a, 4);
        a = countingSort(a, 5);
        a = countingSort(a, 6);
        a = countingSort(a, 7);
        return a;
    }

}
