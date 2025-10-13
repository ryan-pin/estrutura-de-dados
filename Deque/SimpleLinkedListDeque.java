public class SimpleLinkedListDeque {
    
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
    private int tamanho;
    
    public SimpleLinkedListDeque() {
        this.head = null;
        this.tail = null;
        this.tamanho = 0;
    }
    
    // Operação O(1)
    public void inserirInicio(int item) {
        Node newNode = new Node(item);
        
        if (estaVazio()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        tamanho++;
    }
    
    // Operação O(1) 
    public void inserirFim(int item) {
        Node newNode = new Node(item);
        
        if (estaVazio()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        tamanho++;
    }
    
    // Operação O(1) 
    public int removerInicio() {
        if (estaVazio()) {
            throw new RuntimeException("Deque está vazio");
        }
        
        int data = head.data;
        head = head.next;
        
        if (head == null) { 
            tail = null;
        }
        
        tamanho--;
        return data;
    }
    
    // Operação O(n)
    public int removerFim() {
        if (estaVazio()) {
            throw new RuntimeException("Deque está vazio");
        }
        
        int data = tail.data;
        
        if (tamanho == 1) {
            head = tail = null;
        } else {
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }
        
        tamanho--;
        return data;
    }
    
    // Operação O(1)
    public int primeiro() {
        if (estaVazio()) {
            throw new RuntimeException("Deque está vazio");
        }
        return head.data;
    }
    
    // Operação O(1)
    public int ultimo() {
        if (estaVazio()) {
            throw new RuntimeException("Deque está vazio");
        }
        return tail.data;
    }
    
    // Operação O(1)
    public boolean estaVazio() {
        return tamanho == 0;
    }
    
    // Operação O(1)
    public int tamanho() {
        return tamanho;
    }

    public void mostrar() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
      
    // Teste
    public static void main(String[] args) {
        SimpleLinkedListDeque deque = new SimpleLinkedListDeque();
        
        deque.inserirInicio(10);
        deque.inserirFim(20);
        deque.inserirInicio(5);
        deque.inserirFim(30);

        deque.mostrar();

        System.out.println(deque.tamanho());
        
        System.out.println(deque.primeiro());
        System.out.println(deque.ultimo());
        
        System.out.println(deque.removerInicio());
        
        System.out.println(deque.removerFim());
        
        System.out.println(deque.tamanho());

        deque.mostrar();

        // O problema esta na remoção no fim, que é O(n)
        // Já que é necessário percorrer a lista para chegar ao penultimo nó e atualizar o ponteiro
    }
}