/**
 * Created by Shuxin on 2015/3/21.
 */

package sim;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import sim.LRUCache;

public class Simulator {

    static int blockSize = 32;  // 32 bytes
    static int addressBits = 48;    // 48 bits = 12(hex)

    public static void cacheSimulator(String filename, int cacheSize, int associativity, String allocation) throws IOException {

        //Read the file into buffer
        FileInputStream inputStream = new FileInputStream(filename);
        BufferedReader file = new BufferedReader(new InputStreamReader(new DataInputStream(inputStream)));

        // File variables
        String oneLine;

        // Check if cacheSize is correct
        if (!(cacheSize == 4 || cacheSize == 8 || cacheSize == 16 || cacheSize == 32 || cacheSize == 64)) {
            System.out.println("Error: cache size is wrong.");
            return;
        }

        // Check if associativity is correct (the number of ways)
        if (!(associativity == 1 || associativity == 2 || associativity == 4 || associativity == 8 || associativity == 16)) {
            System.out.println("Error:associativity is wrong.");
            return;
        }

        // Check if allocation is correct (wa=write allocate, wna=write non_allocate)
        if (!(allocation.equals("wa") || allocation.equals("wna"))) {
            System.out.println("Error: allocation is wrong.");
            return;
        }

        // cache variables
        cacheSize = cacheSize * 1024;   // kB -> B
        int setNum = cacheSize / (associativity * blockSize);
        int blockOffsetBits = (int)(Math.log(blockSize) / Math.log(2));
        int indexBits = (int)(Math.log(setNum) / Math.log(2));
        int tagBits = addressBits - blockOffsetBits - indexBits;

        // Simulate cache using LRU replacement policy
        LRUCache [] cache = new LRUCache[setNum];
        for (int i=0; i<setNum; i++) {
            cache[i] = new LRUCache(associativity,0.75f);
        }

        // Hit/Miss variables
        int total = 0;
        int totalMiss = 0;
        int readMiss = 0;
        int writeMiss = 0;
        boolean isMiss = false;

        while ((oneLine = file.readLine()) != null) {

            // Get data
            String command = oneLine.substring(0,1);    // Read or Write
            String address = oneLine.substring(2,oneLine.length());

            // Address that are shorter in length should be zero-extended
            if(address.length() < 12) {
                int temp = address.length();
                for (int i=0;i<12-temp;i++) {
                    address = address + "0";
                }
            }

            // Decode the block address from raw address
            String binAddress = new BigInteger(address, 16).toString(2);
            String tag = binAddress.substring(0,tagBits);
            String index = binAddress.substring(tagBits,tagBits+indexBits);
            int setAddress = Integer.parseInt(index, 2);

            total++;

            // Check if Read/Write misses
            if(!(cache[setAddress].isKeyExist(tag))) {
                totalMiss++;
                isMiss = true;
                if(command.equals("R")) {   // if Read misses
                    readMiss++;
                }else if (command.equals("W")) {    // if Write misses
                    writeMiss++;
                }
            }

            // do nothing if Write misses when the policy is write no-allocate
            // otherwise, add tag to the exact set
            if(!(isMiss && command.equals("W") && allocation.equals("wna"))) {
                cache[setAddress].put(tag, "code");
            }

        }

        // Print information
        System.out.println("Cache configuration:"+ cacheSize/1024 +"-KB cache, "+associativity+"-way set, "+((allocation.equals("wa"))?"write allocate":"write no-allocate"));
        System.out.printf("Total miss rate: %.4f\n", (totalMiss + 0.0) / total);
        System.out.printf("Read miss rate: %.4f\n", (readMiss + 0.0) / total);
        System.out.printf("Write miss rate: %.4f\n", (writeMiss + 0.0) / total);

        file.close();
    }

    public static void main(String args[]) throws IOException {

        int cacheSize = Integer.parseInt(args[1]);
        int associativity = Integer.parseInt(args[2]);

        if(args.length != 4) {
            System.out.println("Usage: java Simulator <trace file> <cache size/kB> <associativity> <allocation>");
            return;
        }
        else {
            cacheSimulator(args[0],cacheSize,associativity,args[3]);
        }
    }
}
