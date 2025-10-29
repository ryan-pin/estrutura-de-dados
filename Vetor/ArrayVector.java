package Vetor;

public class ArrayVector {
    
    private int[] array;
    private int size;       
    private int capacidade;    
    
    public ArrayVector() {
        this(10);
    }
    
    public ArrayVector(int capacidadeInicial) {
        this.capacidade = capacidadeInicial;
        this.array = new int[capacidade];
        this.size = 0;
    }
    
    // O(n)
    private void aumentarCapacidade() {
        int novaCapacidade = capacidade * 2;
        int[] novoArray = new int[novaCapacidade];

        for (int i = 0; i < size; i++) {
            novoArray[i] = array[i];
        }
   
        array = novoArray;
        capacidade = novaCapacidade;
    }
    
    
    // Adiciona elemento no final - O(1) amortizado
    public void add(int elemento) {
        if (size >= capacidade) {
            aumentarCapacidade();
        }
        array[size] = elemento;
        size++;
    }
    
    // Adiciona elemento no indice - O(n)
    public void addAtRank(int indice, int elemento) {      
        if (size >= capacidade) {
            aumentarCapacidade();
        }
        
        // Move elementos para a direita
        for (int i = size; i > indice; i--) {
            array[i] = array[i - 1];
        }
        
        array[indice] = elemento;
        size++;
    }
    
    // Remove elemento do final - O(1)
    public int remove() {
        if (isEmpty()) {
            throw new RuntimeException("Vector está vazio");
        }
        
        int elemento = array[size - 1];
        size--;
        return elemento;
    }
    
    // Remove elemento no indice - O(n)
    public int removeAtRank(int indice) {    
        int elemento = array[indice];
        
        // Move elementos para a esquerda
        for (int i = indice; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        
        size--;
        return elemento;
    }
    
    // O(1)
    public int getAtRank(int indice) {
        return array[indice];
    }
    
    // O(1)
    public void replaceAtRank(int indice, int elemento) {
        array[indice] = elemento;
    }
  
    // O(1)
    public boolean isEmpty() {
        return size == 0;
    }
    
    // O(1)
    public int size() {
        return size;
    }
    
    // O(1)
    public int capacidade() {
        return capacidade;
    }
    
    // O(1)
    public void clear() {
        size = 0;
    }
    
    // Mostra todos os elementos - O(n)
    public void mostrar() {
        if (isEmpty()) {
            System.out.println("Vector vazio");
            return;
        }
        
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
    }
    
    // Teste da implementação
    public static void main(String[] args) {
        ArrayVector vector = new ArrayVector(5);
        
        System.out.println(vector.isEmpty());
        System.out.println(vector.size());
        System.out.println(vector.capacidade());
        vector.mostrar();
        
        vector.add(10);
        vector.add(20);
        vector.add(30);
        vector.add(40);
        vector.add(50);
        vector.mostrar();
        
        vector.add(60);
        System.out.println();
        System.out.println("nova capacidade " + vector.capacidade());

        // vector.addAtRank(0, 5); 
        // vector.addAtRank(3, 25);
        // vector.mostrar();
        
        // System.out.println(vector.getAtRank(0));
        // System.out.println(vector.getAtRank(4));

        // vector.replaceAtRank(1, 15);
        // vector.replaceAtRank(5, 35);
        // vector.mostrar();

        // System.out.println(vector.removeLast());
        // System.out.println(vector.removeAtRank(2));
        // vector.mostrar();
    }
}