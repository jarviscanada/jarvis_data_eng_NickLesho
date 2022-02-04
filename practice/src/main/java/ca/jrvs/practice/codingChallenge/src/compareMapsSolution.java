package ca.jrvs.practice.codingChallenge;
import java.util.HashMap;

/*
Big-O: O(n) where n is the number of key-value pairs in m1
Justification: a comparison is made within equals() for each key-value pair in m1 with m2.
To get a key or value in a Map is an operation with O(1) time complexity.
 */
public class compareMapsSolution {
    public <K,V> boolean compareMaps(HashMap<K,V> m1, HashMap<K,V> m2){
        return m1.equals(m2);
    }

}
