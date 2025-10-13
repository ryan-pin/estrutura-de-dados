package Fila;

import java.util.Vector;

public class FilaVector {
    
    private Vector<Integer> vector;
    
    // Construtor
    public FilaVector() {
        this.vector = new Vector<>();
    }
    
    
    // Adiciona elemento no final da fila - O(1)
    public void enqueue(int elemento) {
        vector.add(elemento);
    }
    
    // Remove elemento do início da fila - O(n) pior caso
    public int dequeue() {
        if (estaVazia()) {
            throw new RuntimeException("Fila está vazia");
        }
        return vector.remove(0);
    }
    
    // Visualiza o primeiro - O(1)
    public int primeiro() {
        if (estaVazia()) {
            throw new RuntimeException("Fila está vazia");
        }
        return vector.get(0);
    }
    
    // Visualiza o último - O(1)
    public int ultimo() {
        if (estaVazia()) {
            throw new RuntimeException("Fila está vazia");
        }
        return vector.get(vector.size() - 1);
    }
    
    // Verifica se a fila está vazia - O(1)
    public boolean estaVazia() {
        return vector.isEmpty();
    }
    
    // Retorna o tamanho da fila - O(1)
    public int tamanho() {
        return vector.size();
    }
    
    // Limpa todos os elementos da fila - O(1)
    public void limpar() {
        vector.clear();
    }
    
    // Mostra todos os elementos da fila - O(n)
    public void mostrar() {
        if (estaVazia()) {
            System.out.println("Fila vazia");
            return;
        }
        
        for (int i = 0; i < vector.size(); i++) {
            System.out.print(vector.get(i));
            if (i < vector.size() - 1) {
                System.out.print(", ");
            }
        }
    }
    
    // Verifica se um elemento esta na fila - O(n)
    public boolean contem(int elemento) {
        return vector.contains(elemento);
    }
    
    // Teste
    public static void main(String[] args) {
        
        FilaVector fila = new FilaVector();
        
        // Teste Fila vazia
        System.out.println("Está vazia? " + fila.estaVazia());
        System.out.println("Tamanho: " + fila.tamanho());
        
        // Teste Adicionando elementos
        fila.enqueue(10);
        fila.enqueue(20);
        fila.enqueue(30);
        fila.enqueue(40);
        System.out.println("Tamanho: " + fila.tamanho());
        
        // Teste Visualizando elementos
        System.out.println("Primeiro: " + fila.primeiro());
        System.out.println("Último: " + fila.ultimo());
        fila.mostrar();
        
        // Teste Removendo elementos
        System.out.println("Removido: " + fila.dequeue());
        System.out.println("Removido: " + fila.dequeue());
        fila.mostrar();
        System.out.println("Tamanho: " + fila.tamanho());
        
        
        // Teste Buscar de elemento
        System.out.println("Contém 30? " + fila.contem(30));
        System.out.println("Contém 100? " + fila.contem(100));

        // Teste Limpando fila
        fila.limpar();
        fila.mostrar();
        System.out.println("Está vazia? " + fila.estaVazia());
    }
}
