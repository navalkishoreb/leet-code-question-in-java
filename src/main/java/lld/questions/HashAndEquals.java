package lld.questions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashAndEquals {
    public static void main(String[] args) {

        defaultUser();
        userWithHash();
        userEquals();
        userEqualsAndHash();

        defaultUserMap();
        userHashMap();
        userEqualsMap();
        userEqualsAndHashMap();
    }

    private static void defaultUser() {
        Set<User> set = new HashSet<>();
        set.add(new User(1));
        set.add(new User(1));
        System.out.println(set.size());
        // different memmory location
        // hashcode is different
        // different object but logically same
    }

    private static void userWithHash() {
        Set<UserHash> set = new HashSet<>();
        set.add(new UserHash(1));
        set.add(new UserHash(1));
        System.out.println(set.size());
        // different memory locations
        // hashCode() is same → same bucket (collision)
        // equals() is NOT overridden → reference equality
        // equals() returns false
        // both objects are added
    }

    private static void userEquals() {
        Set<UserEquals> set = new HashSet<>();
        set.add(new UserEquals(1));
        set.add(new UserEquals(1));
        System.out.println(set.size());
        // different memory locations
        // default hashCode() → different values
        // different buckets
        // equals() is NEVER called
        // both objects are added
    }

    private static void userEqualsAndHash() {
        Set<UserEqualsAndHash> set = new HashSet<>();
        set.add(new UserEqualsAndHash(1));
        set.add(new UserEqualsAndHash(1));
        System.out.println(set.size());
        // different memory locations
        // hashCode() is same → same bucket
        // equals() returns true
        // objects are logically equal
        // second object is ignored (not added)
    }

    private static void defaultUserMap() {
        Map<User, String> map = new HashMap<>();
        map.put(new User(1), "admin");
        map.put(new User(1), "admin");
        System.out.println(map.size());
    }

    private static void userHashMap() {
        Map<UserHash, String> map = new HashMap<>();
        map.put(new UserHash(1), "admin");
        map.put(new UserHash(1), "admin");
        System.out.println(map.size());
     
    }

    private static void userEqualsMap() {
         Map<UserEquals, String> map = new HashMap<>();
        map.put(new UserEquals(1), "admin");
        map.put(new UserEquals(1), "admin");
        System.out.println(map.size());
     
    }

    private static void userEqualsAndHashMap() {
         Map<UserEqualsAndHash, String> map = new HashMap<>();
        map.put(new UserEqualsAndHash(1), "admin");
        map.put(new UserEqualsAndHash(1), "admin");
        System.out.println(map.size());
    }

}

class User {
    private int id;

    public User(int id) {
        this.id = id;
    }
}

class UserHash {
    private int id;

    public UserHash(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}

class UserEquals {
    private int id;

    public UserEquals(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserEquals))
            return false;
        UserEquals other = (UserEquals) obj;
        return this.id == other.id;
    }
}

class UserEqualsAndHash {
    private int id;

    public UserEqualsAndHash(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserEqualsAndHash))
            return false;
        UserEqualsAndHash other = (UserEqualsAndHash) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}