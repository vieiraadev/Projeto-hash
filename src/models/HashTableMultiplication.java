package models;

public class HashTableMultiplication extends HashTable {
    private static final double A = 0.6180339887; // Constante da proporção áurea
    
    @Override
    protected int hashFunction(String key) {
        if (key == null || key.isEmpty()) {
            return 0;
        }
        
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += key.charAt(i) * (i + 1); // Peso baseado na posição
        }
        
        // Método da multiplicação: h(k) = floor(m * (k * A mod 1))
        double value = Math.abs(sum) * A;
        double fractionalPart = value - Math.floor(value);
        
        return (int) Math.floor(TABLE_SIZE * fractionalPart);
    }
}
