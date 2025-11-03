package Lista;

public class ArrayBasedList {
    
    private int[] array;
    private int size;
    private int capacidade;
    
    public ArrayBasedList(int capacidadeInicial) {
        this.capacidade = capacidadeInicial;
        this.array = new int[capacidade];
        this.size = 0;
    }
    
    private void aumentarCapacidade() {
        int novaCapacidade = capacidade * 2;
        int[] novoArray = new int[novaCapacidade];
        
        for (int i = 0; i < size; i++) {
            novoArray[i] = array[i];
        }
        
        array = novoArray;
        capacidade = novaCapacidade;
    }
    
    // O(n)
    public void insertAfter(int posicao, int elemento) {    
        if (size >= capacidade) {
            aumentarCapacidade();
        }
        
        // Reposiciona os elementos
        for (int i = size; i > posicao + 1; i--) {
            array[i] = array[i - 1];
        }
        
        array[posicao + 1] = elemento;
        size++;
    }
    
    // O(n)
    public void insertBefore(int posicao, int elemento) {      
        if (size >= capacidade) {
            aumentarCapacidade();
        }

        // Reposiciona os elementos
        for (int i = size; i > posicao; i--) {
            array[i] = array[i - 1];
        }
        
        array[posicao] = elemento;
        size++;
    }
    
    // O(n)
    public void insertFirst(int elemento) {
        if (size >= capacidade) {
            aumentarCapacidade();
        }
        
        // Reposiciona os elementos
        for (int i = size; i > 0; i--) {
            array[i] = array[i - 1];
        }
        
        array[0] = elemento;
        size++;
    }
    
    // O(1)
    public void insertLast(int elemento) {
        if (size >= capacidade) {
            aumentarCapacidade();
        }
        
        array[size] = elemento;
        size++;
    }
    
    // O(n)
    public int remove(int posicao) {
        if (isEmpty()) {
            throw new RuntimeException("Lista vazia");
        }
        
        int elemento = array[posicao];

        // Reposiciona os elementos
        for (int i = posicao; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        
        size--;
        return elemento;
    }
    
    // O(1)
    public int first() {
        if (isEmpty()) {
            throw new RuntimeException("Lista vazia");
        }
        return array[0];
    }
    
    // O(1)
    public int last() {
        if (isEmpty()) {
            throw new RuntimeException("Lista vazia");
        }
        return array[size - 1];
    }
    
    // Retorna a próxima posição - O(1)
    public int after(int posicao) {
        if (posicao < 0 || posicao >= size - 1) {
            throw new IndexOutOfBoundsException("Não há próxima posição");
        }
        return posicao + 1;
    }
    
    // Retorna a posição anterior - O(1)
    public int before(int posicao) {
        if (posicao <= 0 || posicao >= size) {
            throw new IndexOutOfBoundsException("Não há posição anterior");
        }
        return posicao - 1;
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
    public void clear() {
        size = 0;
    }
    
    // Mostra todos os elementos - O(n)
    public void mostrar() {      
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }

    }
    
    // Teste
    public static void main(String[] args) {        
        ArrayBasedList lista = new ArrayBasedList(5);
        
        lista.insertLast(10);
        lista.insertLast(20);
        lista.insertLast(30);
        lista.insertLast(40);
        lista.mostrar();
        
        lista.insertFirst(5);
        lista.insertFirst(0);
        lista.mostrar();
    
        lista.insertAfter(2, 15);
        lista.mostrar();

        lista.insertBefore(4, 25);
        lista.mostrar();
        
        System.out.println(lista.remove(0));
        lista.mostrar();
    }
}