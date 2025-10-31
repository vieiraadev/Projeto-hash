package models;

public abstract class HashTable {
    protected static final int TABLE_SIZE = 32;
    protected Node[] table;
    protected int totalCollisions;
    protected int totalElements;
    protected int[] collisionsPerPosition;
    
    public HashTable() {
        this.table = new Node[TABLE_SIZE];
        this.totalCollisions = 0;
        this.totalElements = 0;
        this.collisionsPerPosition = new int[TABLE_SIZE];
    }
    
    // Método abstrato para função hash - cada subclasse implementa sua própria
    protected abstract int hashFunction(String key);
    
    // Inserção com tratamento de colisões usando encadeamento
    public void insert(String key) {
        int index = hashFunction(key);
        
        if (table[index] == null) {
            table[index] = new Node(key);
        } else {
            // Colisão detectada
            totalCollisions++;
            collisionsPerPosition[index]++;
            
            // Verifica se o elemento já existe
            Node current = table[index];
            while (current != null) {
                if (current.getData().equals(key)) {
                    return; // Elemento já existe, não insere duplicata
                }
                if (current.getNext() == null) {
                    break;
                }
                current = current.getNext();
            }
            
            // Adiciona no final da lista
            current.setNext(new Node(key));
        }
        
        totalElements++;
    }
    
    // Busca um elemento na tabela
    public boolean search(String key) {
        int index = hashFunction(key);
        
        Node current = table[index];
        while (current != null) {
            if (current.getData().equals(key)) {
                return true;
            }
            current = current.getNext();
        }
        
        return false;
    }
    
    // Retorna a distribuição das chaves na tabela
    public int[] getDistribution() {
        int[] distribution = new int[TABLE_SIZE];
        
        for (int i = 0; i < TABLE_SIZE; i++) {
            int count = 0;
            Node current = table[i];
            
            while (current != null) {
                count++;
                current = current.getNext();
            }
            
            distribution[i] = count;
        }
        
        return distribution;
    }
    
    // Getter
    public int getTotalCollisions() {
        return totalCollisions;
    }
    
    public int getTotalElements() {
        return totalElements;
    }
    
    public int[] getCollisionsPerPosition() {
        return collisionsPerPosition;
    }
    
    public void clear() {
        this.table = new Node[TABLE_SIZE];
        this.totalCollisions = 0;
        this.totalElements = 0;
        this.collisionsPerPosition = new int[TABLE_SIZE];
    }
}