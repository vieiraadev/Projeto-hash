import models.*;
import utils.FileReader;
import reports.PerformanceAnalyzer;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE TABELAS HASH ===\n");
        
        // Leitura do arquivo
        String[] names = FileReader.readNames("female_names.txt");
        if (names == null) {
            System.err.println("Erro ao ler o arquivo!");
            return;
        }
        
        System.out.println("Total de nomes lidos: " + names.length);
        System.out.println("\n=== INICIANDO TESTES ===\n");
        
        // Criação das tabelas hash
        HashTable table1 = new HashTableDivision();
        HashTable table2 = new HashTableMultiplication();
        
        // Análise de performance
        PerformanceAnalyzer analyzer = new PerformanceAnalyzer();
        
        // Teste da primeira tabela (Método da Divisão)
        System.out.println(">>> TABELA 1: Método da Divisão <<<");
        analyzer.analyzeTable(table1, names, "Divisão");
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Teste da segunda tabela (Método da Multiplicação)
        System.out.println(">>> TABELA 2: Método da Multiplicação <<<");
        analyzer.analyzeTable(table2, names, "Multiplicação");
        
        // Comparação final
        System.out.println("\n=== RESUMO COMPARATIVO ===");
        analyzer.printComparison();
    }
}