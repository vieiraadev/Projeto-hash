package models;

public class HashTableDivision extends HashTable {
    
    @Override
    protected int hashFunction(String key) {
        if (key == null || key.isEmpty()) {
            return 0;
        }
        
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += key.charAt(i);
        }
        
        // Método da divisão: h(k) = k mod m
        return Math.abs(sum) % TABLE_SIZE;
    }
}
