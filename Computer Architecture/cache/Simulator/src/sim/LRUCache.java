/**
 * Created by Shuxin on 2015/3/22.
 */

package sim;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends LinkedHashMap<String, String> {
    private static final long serialVersionID = 1L;
    private int capacity;

    public LRUCache(int capacity,float loadFactor) {
        super(capacity,loadFactor,true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String,String> eldest) {
        return size() > this.capacity;
    }

    protected boolean isKeyExist(String key) {
        return this.containsKey(key);
    }

    public static void main(String arg[]) {
        LRUCache[] lruCache = new LRUCache[2];
        for (int i = 0; i < 2; i++) {
            lruCache[i] = new LRUCache(4, 0.75f);
        }
        lruCache[0].put("12","Object1");
        lruCache[0].put("3a","Object3");
        lruCache[0].put("2qq","data");
        lruCache[0].put("2q8","data");
        lruCache[0].put("3a","data");
        String binstring = new BigInteger("12438581ab124347", 16).toString(2);
        System.out.println(binstring);
        System.out.println(lruCache[0]);
        String address = "7f588c";
        int length = address.length();
        System.out.println(lruCache[0].isKeyExist("12"));
        for (int i=0;i<12-length;i++) {
            address = address + "0";
            System.out.println(address+" "+address.length());
        }
        System.out.println(address+" "+address.length());
        String tag = address.substring(0,3);
        System.out.println("tag  "+tag);
        String offset = address.substring(3,address.length());
        System.out.println(offset);
        long decimalAddress = Integer.decode("0x"+tag);
        System.out.println(decimalAddress);
    }
}
