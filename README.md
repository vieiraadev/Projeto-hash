# Projeto: Implementação de Tabelas Hash

## 📌 Descrição

Este projeto implementa duas tabelas hash em Java, utilizando diferentes funções hash (divisão e multiplicação), e realiza uma análise comparativa da eficiência entre elas.

O repositório foi compactado em um único arquivo de referência através do Repomix, que unifica todos os arquivos-fonte em uma só representação para facilitar análise por sistemas de IA e automação.

## 🎯 Objetivo

O objetivo principal é comparar as duas funções hash considerando:

- Número de colisões
- Tempo de inserção e busca
- Distribuição das chaves na tabela

## 🗂 Estrutura do Repositório

```
src/
  models/
    HashTable.java
    HashTableDivision.java
    HashTableMultiplication.java
    Node.java
  reports/
    PerformanceAnalyzer.java
  utils/
    FileReader.java
  Main.java
.gitattributes
.gitignore
female_names.txt
```

## ⚙️ Componentes Principais

### models/
- **`HashTable.java`**: Classe abstrata que define a estrutura genérica de uma tabela hash.
- **`HashTableDivision.java`**: Implementa a função hash pelo método da divisão.
- **`HashTableMultiplication.java`**: Implementa a função hash pelo método da multiplicação (proporção áurea).
- **`Node.java`**: Estrutura de nó para encadeamento nas colisões.

### reports/
- **`PerformanceAnalyzer.java`**: Mede desempenho de inserção, busca e distribuição das chaves, gerando relatórios comparativos.

### utils/
- **`FileReader.java`**: Lê o arquivo `female_names.txt` (lista de 5000 nomes).

### Main.java
Executa o fluxo completo: leitura dos nomes, inserção nas tabelas, análise de performance e geração do relatório.

## ▶️ Como Executar o Projeto

### 1. Pré-requisitos

Certifique-se de ter o **Java Development Kit (JDK)** instalado em sua máquina.

### 2. Clonar ou Baixar o Repositório

Faça o download ou clone o repositório para sua máquina local.

### 3. Compilar

Na pasta raiz do projeto (`projeto-hash/`), execute:

```bash
javac src/Main.java src/models/*.java src/utils/*.java src/reports/*.java
```

Isso vai gerar os arquivos `.class` nas mesmas pastas dos arquivos `.java`.

### 5. Executar

Ainda na pasta raiz, execute:

```bash
java -cp src Main
```

## 📤 Fluxo do Programa

1. Leitura do arquivo `female_names.txt` (até 5000 nomes).
2. Inserção dos nomes em duas tabelas hash diferentes.
3. Medição de eficiência:
   - Colisões
   - Tempo de inserção e busca
   - Distribuição das chaves
4. Impressão de relatórios comparativos no console.

## 📊 Relatórios Gerados

O programa imprime:

- Total de colisões
- Taxa de colisão (%)
- Tempo de inserção e busca (ms)
- Distribuição das chaves nas posições da tabela
- Comparação entre os dois métodos hash

## 👩‍🏫 Referência Acadêmica

Este projeto faz parte da disciplina **Resolução de Problemas Estruturados em Computação (TDE 03)** da PUC-PR, com foco em Estruturas de Dados e Programação Orientada a Objetos.
