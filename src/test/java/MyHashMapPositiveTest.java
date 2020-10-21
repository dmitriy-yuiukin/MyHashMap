import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


public class MyHashMapPositiveTest {

    private MyHashMap<String, Integer> getHashMap() {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();
        myHashMap.put("Value", 21);
        myHashMap.put("Jenifer", 21);
        myHashMap.put("Maria", 30);
        myHashMap.put("Anna", 25);
        myHashMap.put("World", 1);

        return myHashMap;
    }

    private MyHashMap<String, Integer> otherHashMap = new MyHashMap<>();

    @Test
    public void putTest() {
        assertEquals(5, getHashMap().size());
    }

    @Test
    public void getTest() {
        assertEquals((Integer) 21, getHashMap().get("Value"));
    }

    @Test
    public void updateTest() {
        otherHashMap.put("Jonn", 21);
        assertEquals((Integer) 21, otherHashMap.get("Jonn"));
        otherHashMap.put("Jonn", 24);
        assertEquals((Integer) 24, otherHashMap.get("Jonn"));
    }

    @Test
    public void removeTest() {
        otherHashMap.put("Anna", 30);
        otherHashMap.put("Bob", 32);
        assertEquals(2, otherHashMap.size());
        otherHashMap.remove("Anna");
        assertEquals(1, otherHashMap.size());
    }

    @Test
    public void containsKey() {
        assertTrue(getHashMap().containsKey("World"));
    }

}
