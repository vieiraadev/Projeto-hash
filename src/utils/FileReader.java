package utils;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader {
    
    public static String[] readNames(String filename) {
        String[] names = new String[5000];
        int count = 0;
        
        try {
            BufferedReader reader = new BufferedReader(
                new java.io.FileReader(filename)
            );
            
            String line;
            while ((line = reader.readLine()) != null && count < 5000) {
                line = line.trim();
                if (!line.isEmpty()) {
                    names[count++] = line;
                }
            }
            
            reader.close();
            
            // Ajusta o array para o tamanho real lido
            if (count < 5000) {
                String[] actualNames = new String[count];
                for (int i = 0; i < count; i++) {
                    actualNames[i] = names[i];
                }
                return actualNames;
            }
            
            return names;
            
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
            return null;
        }
    }
}