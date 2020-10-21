import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyHashMapNegativeTests {

    private MyHashMap<Integer, String> myHashMapWithNull() {
        MyHashMap<Integer, String> hashmap = new MyHashMap<>();
        hashmap.put(null, null);
        hashmap.put(null, null);
        return hashmap;
    }

    private MyHashMap<Integer, String> getHashMap() {
        MyHashMap<Integer, String> hashMap = new MyHashMap<>();
        for (int i = 0; i < 10; i++) {
            hashMap.put(i, generateRandomValue());
        }
        return hashMap;
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNullKeysAndNullValues() {
        assertEquals(2, myHashMapWithNull().size());
    }

    @Test
    public void checkThatKeyExist() {
        assertFalse(getHashMap().containsKey(11));
    }

    @Test
    public void removeKey() {
        assertNull(getHashMap().remove(20));
    }

    @Test
    public void getTest() {
        assertNull(getHashMap().get(12));
    }

    @Test
    public void clearTest() {
        getHashMap().clear();
        assertNotEquals(2, getHashMap().size());
    }

    private String generateRandomValue() {
        return RandomStringUtils.randomAlphanumeric(8);
    }
}
