package Vetor;

public class LinkedListVector {
    
    private class Node {
        int data;
        Node next;
        Node prev;
        
        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    private Node head;  // Primeiro nó
    private Node tail;  // Último nó
    private int size;   // Tamanho atual
    
    public LinkedListVector() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    // Obter nó em índice específico - O(n)
    private Node getNode(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        
        Node current;
        
        // começa do lado mais próximo
        if (indice < size / 2) {
            current = head;
            for (int i = 0; i < indice; i++) {
                current = current.next;
            }
        } else {
            // começa do fim
            current = tail;
            for (int i = size - 1; i > indice; i--) {
                current = current.prev;
            }
        }
        
        return current;
    }
    
    // Adiciona elemento no final - O(1)
    public void add(int elemento) {
        Node newNode = new Node(elemento);
        
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // O(1)
    public void addPrimeiro(int elemento) {
        Node newNode = new Node(elemento);
        
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }
    
    //  - O(n)
    public void addAtRank(int indice, int elemento) {
        if (indice < 0 || indice > size) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        
        if (indice == 0) {
            addPrimeiro(elemento);
            return;
        }
        if (indice == size) {
            add(elemento);
            return;
        }
        
        Node newNode = new Node(elemento);
        Node current = getNode(indice);
        
        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;
        
        size++;
    }
    
    // Remove ultimo elemento - O(1)
    public int remove() {
        if (isEmpty()) {
            throw new RuntimeException("Vector está vazio");
        }
        
        int data = tail.data;
        
        if (size == 1) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return data;
    }
    
    // O(1)
    public int removePrimeiro() {
        if (isEmpty()) {
            throw new RuntimeException("Vector está vazio");
        }
        
        int data = head.data;
        
        if (size == 1) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return data;
    }
    
    // O(n)
    public int removeAtRank(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        
        if (indice == 0) {
            return removePrimeiro();
        }
        if (indice == size - 1) {
            return remove();
        }
        
        Node current = getNode(indice);
        int data = current.data;
        
        current.prev.next = current.next;
        current.next.prev = current.prev;
        
        size--;
        return data;
    }
    
    // O(n)
    public int getAtRank(int indice) {
        return getNode(indice).data;
    }
    
    // O(n)
    public void replaceAtRank(int indice, int elemento) {
        getNode(indice).data = elemento;
    }
    
    // Busca elemento e retorna indice - O(n)
    public int indexOf(int elemento) {
        Node current = head;
        int index = 0;
        
        while (current != null) {
            if (current.data == elemento) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1; // Não esta no vector
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
        head = null;
        tail = null;
        size = 0;
    }
    
    // O(n)
    public void mostrar() {
        if (isEmpty()) {
            System.out.println("Vector vazio");
            return;
        }
        
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }
    
    public static void main(String[] args) { 
        LinkedListVector vector = new LinkedListVector();
        
        System.out.println(vector.isEmpty());
        System.out.println(vector.size());
        vector.mostrar();
        
        vector.add(10);
        vector.add(20);
        vector.add(30);
        vector.add(40);
        vector.add(50);
        vector.mostrar();
        
        vector.addPrimeiro(5);
        vector.addPrimeiro(0);
        vector.mostrar();
        
        // vector.addAtRank(3, 15);
        // vector.addAtRank(0, -5);
        // vector.addAtRank(vector.size(), 60);
        // vector.mostrar();
        
        // vector.replaceAtRank(1, 100);
        // vector.replaceAtRank(5, 200);
        // vector.mostrar();    
    }
}
