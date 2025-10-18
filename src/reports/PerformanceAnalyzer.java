package reports;

import models.HashTable;

public class PerformanceAnalyzer {
    private long insertionTime1, insertionTime2;
    private long searchTime1, searchTime2;
    private int collisions1, collisions2;
    private String hashName1, hashName2;
    private boolean firstAnalyzed = false;
    
    public void analyzeTable(HashTable table, String[] names, String hashName) {
        // Limpa a tabela antes de começar
        table.clear();
        
        // Medição do tempo de inserção
        long startTime = System.nanoTime();
        for (String name : names) {
            if (name != null) {
                table.insert(name);
            }
        }
        long endTime = System.nanoTime();
        long insertionTime = endTime - startTime;
        
        // Medição do tempo de busca (busca por 10% dos nomes)
        int searchCount = names.length / 10;
        startTime = System.nanoTime();
        for (int i = 0; i < searchCount; i++) {
            if (names[i] != null) {
                table.search(names[i]);
            }
        }
        endTime = System.nanoTime();
        long searchTime = endTime - startTime;
        
        // Armazena dados para comparação
        if (!firstAnalyzed) {
            insertionTime1 = insertionTime;
            searchTime1 = searchTime;
            collisions1 = table.getTotalCollisions();
            hashName1 = hashName;
            firstAnalyzed = true;
        } else {
            insertionTime2 = insertionTime;
            searchTime2 = searchTime;
            collisions2 = table.getTotalCollisions();
            hashName2 = hashName;
        }
        
        // Imprime relatório da tabela atual
        printTableReport(table, hashName, insertionTime, searchTime);
    }
    
    private void printTableReport(HashTable table, String hashName, 
                                 long insertionTime, long searchTime) {
        System.out.println("Função Hash: " + hashName);
        System.out.println("-".repeat(40));
        
        System.out.println("\n1. ESTATÍSTICAS GERAIS:");
        System.out.println("   - Total de elementos inseridos: " + table.getTotalElements());
        System.out.println("   - Total de colisões: " + table.getTotalCollisions());
        System.out.printf("   - Taxa de colisão: %.2f%%\n", 
            (table.getTotalCollisions() * 100.0) / table.getTotalElements());
        
        System.out.println("\n2. TEMPOS DE EXECUÇÃO:");
        System.out.printf("   - Tempo de inserção: %.3f ms\n", insertionTime / 1_000_000.0);
        System.out.printf("   - Tempo de busca (10%% dos elementos): %.3f ms\n", 
            searchTime / 1_000_000.0);
        
        System.out.println("\n3. DISTRIBUIÇÃO DAS CHAVES:");
        int[] distribution = table.getDistribution();
        int emptySlots = 0;
        int maxChain = 0;
        double averageChain = 0;
        
        for (int i = 0; i < distribution.length; i++) {
            if (distribution[i] == 0) {
                emptySlots++;
            } else {
                averageChain += distribution[i];
                if (distribution[i] > maxChain) {
                    maxChain = distribution[i];
                }
            }
        }
        
        averageChain = averageChain / (distribution.length - emptySlots);
        
        System.out.println("   - Posições vazias: " + emptySlots + "/" + distribution.length);
        System.out.println("   - Posições ocupadas: " + (distribution.length - emptySlots));
        System.out.printf("   - Taxa de ocupação: %.2f%%\n", 
            ((distribution.length - emptySlots) * 100.0) / distribution.length);
        System.out.println("   - Maior encadeamento: " + maxChain + " elementos");
        System.out.printf("   - Tamanho médio de encadeamento: %.2f\n", averageChain);
        
        System.out.println("\n4. COLISÕES POR POSIÇÃO:");
        int[] collisionsPerPos = table.getCollisionsPerPosition();
        System.out.println("   Posição | Colisões | Elementos");
        System.out.println("   --------|----------|----------");
        for (int i = 0; i < collisionsPerPos.length; i++) {
            if (distribution[i] > 0) {
                System.out.printf("   %7d | %8d | %9d\n", 
                    i, collisionsPerPos[i], distribution[i]);
            }
        }
    }
    
    public void printComparison() {
        if (!firstAnalyzed) {
            System.out.println("Nenhuma análise foi realizada ainda.");
            return;
        }
        
        System.out.println("-".repeat(50));
        System.out.println("COMPARAÇÃO ENTRE AS FUNÇÕES HASH");
        System.out.println("-".repeat(50));
        
        System.out.println("\n1. COLISÕES:");
        System.out.printf("   - %s: %d colisões\n", hashName1, collisions1);
        System.out.printf("   - %s: %d colisões\n", hashName2, collisions2);
        
        String betterCollisions = collisions1 < collisions2 ? hashName1 : hashName2;
        double collisionDiff = Math.abs(collisions1 - collisions2) * 100.0 / 
                              Math.max(collisions1, collisions2);
        System.out.printf("   → Melhor: %s (%.1f%% menos colisões)\n", 
            betterCollisions, collisionDiff);
        
        System.out.println("\n2. TEMPO DE INSERÇÃO:");
        System.out.printf("   - %s: %.3f ms\n", hashName1, insertionTime1 / 1_000_000.0);
        System.out.printf("   - %s: %.3f ms\n", hashName2, insertionTime2 / 1_000_000.0);
        
        String betterInsertion = insertionTime1 < insertionTime2 ? hashName1 : hashName2;
        double insertionDiff = Math.abs(insertionTime1 - insertionTime2) * 100.0 / 
                              Math.max(insertionTime1, insertionTime2);
        System.out.printf("   → Melhor: %s (%.1f%% mais rápido)\n", 
            betterInsertion, insertionDiff);
        
        System.out.println("\n3. TEMPO DE BUSCA:");
        System.out.printf("   - %s: %.3f ms\n", hashName1, searchTime1 / 1_000_000.0);
        System.out.printf("   - %s: %.3f ms\n", hashName2, searchTime2 / 1_000_000.0);
        
        String betterSearch = searchTime1 < searchTime2 ? hashName1 : hashName2;
        double searchDiff = Math.abs(searchTime1 - searchTime2) * 100.0 / 
                           Math.max(searchTime1, searchTime2);
        System.out.printf("   → Melhor: %s (%.1f%% mais rápido)\n", 
            betterSearch, searchDiff);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("FIM DA ANÁLISE");
    }
}