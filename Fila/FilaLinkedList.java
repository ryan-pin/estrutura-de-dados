package Fila;

public class FilaLinkedList {
    
    private class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head; 
    private Node tail;
    private int size;
    
    // Construtor
    public FilaLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    // Adiciona elemento no final da fila - O(1)
    public void enqueue(int elemento) {
        Node newNode = new Node(elemento);
        
        if (estaVazia()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    
    // Remove elemento do início da fila - O(1)
    public int dequeue() {
        if (estaVazia()) {
            throw new RuntimeException("Fila está vazia");
        }
        
        int data = head.data;
        head = head.next;
        
        if (head == null) {
            tail = null;
        }
        
        size--;
        return data;
    }
    
    public int primeiro() {
        if (estaVazia()) {
            throw new RuntimeException("Fila está vazia");
        }
        return head.data;
    }

    public int ultimo() {
        if (estaVazia()) {
            throw new RuntimeException("Fila está vazia");
        }
        return tail.data;
    }
    

    public boolean estaVazia() {
        return head == null; // ou size == 0
    }
    
    public int tamanho() {
        return size;
    }
    
    public void limpar() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public void mostrar() {
        if (estaVazia()) {
            System.out.println("Fila vazia");
            return;
        }
        
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            current = current.next;
        }
    }
    
    public boolean contem(int elemento) {
        Node current = head;
        while (current != null) {
            if (current.data == elemento) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    // Teste
    public static void main(String[] args) {
        
        FilaLinkedList fila = new FilaLinkedList();
        
        //Fila vazia
        System.out.println("Está vazia? " + fila.estaVazia());
        System.out.println("Tamanho: " + fila.tamanho());
        fila.mostrar();
        
        //Adicionando elementos
        fila.enqueue(10);
        fila.enqueue(20);
        fila.enqueue(30);
        fila.enqueue(40);
        fila.mostrar();
        System.out.println("Tamanho: " + fila.tamanho());
        
        //Visualizando elementos
        System.out.println("Primeiro (front): " + fila.primeiro());
        System.out.println("Último (rear): " + fila.ultimo());

        //Removendo elementos
        System.out.println("Removido: " + fila.dequeue() + " [O(1)]");
        System.out.println("Removido: " + fila.dequeue() + " [O(1)]");
        fila.mostrar();
        System.out.println("Tamanho: " + fila.tamanho());

        //Busca de elemento
        System.out.println("Contém 30? " + fila.contem(30));
        System.out.println("Contém 100? " + fila.contem(100));
        
        //Removendo todos da lista

        while (!fila.estaVazia()) {
            System.out.println("Removido: " + fila.dequeue());
        }
        fila.mostrar();
        System.out.println("Está vazia? " + fila.estaVazia());

    }
}