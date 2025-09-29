public class DoubleLinkedListDeque<T> {
    
    private class Node {
        T data;
        Node next;
        Node prev;
        
        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    private Node head;
    private Node tail;
    private int tamanho;

    public DoubleLinkedListDeque() {
        this.head = null;
        this.tail = null;
        this.tamanho = 0;
    }
    
    // Adiciona no início
    // Operação O(1)
    public void inserirInicio(T item) {
        Node newNode = new Node(item);
        
        if (estaVazio()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        tamanho++;
    }
    
    // Adiciona no final
    // Operação O(1)
    public void inserirFim(T item) {
        Node newNode = new Node(item);
        
        if (estaVazio()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        tamanho++;
    }
    
    // Remove do início
    // Operação O(1)
    public T removerInicio() {
        if (estaVazio()) {
            throw new RuntimeException("Deque vazio");
        }
        
        T data = head.data;
        
        if (tamanho == 1) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        tamanho--;
        return data;
    }
    
    // Remove do final
    // Operação O(1)
    public T removerFim() {
        if (estaVazio()) {
            throw new RuntimeException("Deque vazio");
        }
        
        T data = tail.data;
        
        if (tamanho == 1) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        tamanho--;
        return data;
    }
    
    // Operação O(1)
    public T primeiro() {
        if (estaVazio()) {
            throw new RuntimeException("Deque vazio");
        }
        return head.data;
    }
    
    // Operação O(1)
    public T ultimo() {
        if (estaVazio()) {
            throw new RuntimeException("Deque vazio");
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
    
    
    // Teste
    public static void main(String[] args) {
        
        DoubleLinkedListDeque<Integer> deque = new DoubleLinkedListDeque<>();
        
        System.out.println(deque.estaVazio());
        System.out.println(deque.tamanho());
        
        deque.inserirInicio(10);
        deque.inserirFim(20);
        deque.inserirInicio(5);
        deque.inserirFim(30);
        
        System.out.println(deque.tamanho());
        
        System.out.println(deque.primeiro());
        System.out.println(deque.ultimo());
        
        System.out.println(deque.removerInicio());
        System.out.println(deque.removerFim());
        
        System.out.println(deque.tamanho());
        
        // Todas as operações são O(1) em uma deque com lista duplamente ligada
        }
    }
