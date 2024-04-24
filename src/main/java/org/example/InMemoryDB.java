package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class InMemoryDB {
    ArrayList<KVPair> db = new ArrayList<>();
    boolean transaction = false;

    Integer get(String key) {
        if (db.isEmpty()) {
            return null;
        }
        // If the specified key exists, return the value
        for (int i = 0; i < db.size(); i++) {
            if (Objects.equals(db.get(i).getKey(), key)) {
                return db.get(i).getValue();
            }
        }
        // If it does not, return null
        return null;
    };

    void put(String key, int val) {
        // If the specified key exists, update the value
        boolean keyFound = false;
        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).getKey() == key) {
                db.get(i).setValue(val);
                keyFound = true;
            }
        }
        // If not, create a new key value pair
        if (keyFound == false) {
            db.add(new KVPair(key, val));
        }
    };

    void begin_transaction() {transaction = true;};

    void commit(ArrayList<String> keys, ArrayList<Integer> values) {
        for (int i = 0; i < keys.size(); i++) {
            put(keys.get(i), values.get(i));
        }
        transaction = false;
    };

    void rollback() { transaction = false;};
}
