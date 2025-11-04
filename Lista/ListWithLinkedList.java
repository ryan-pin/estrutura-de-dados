package Lista;

public class ListWithLinkedList {
    
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
    
    private Node head; 
    private Node tail; 
    private int size;  
    
    public ListWithLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    // O(1)
    public int size() {
        return size;
    }
    
    // O(1)
    public boolean isEmpty() {
        return size == 0;
    }
    
    // O(1)
    public boolean isFirst(Node node) {
        return node == head;
    }
    
    // O(1)
    public boolean isLast(Node node) {
        return node == tail;
    }
    
    // O(1)
    public int first() {
        if (isEmpty()) {
            throw new RuntimeException("Lista está vazia");
        }
        return head.data;
    }
    
    // O(1)
    public int last() {
        if (isEmpty()) {
            throw new RuntimeException("Lista está vazia");
        }
        return tail.data;
    }
    
    // O(1)
    public int replaceElement(Node node, int newElement) {      
        int oldElement = node.data;
        node.data = newElement;
        return oldElement;
    }
    
    // O(1)
    public void swapElements(Node node1, Node node2) {      
        int temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }
    
    // O(1)
    public Node insertBefore(Node node, int elemento) {
        if (node == null) {
            throw new RuntimeException("Nó não pode ser null");
        }
        
        Node newNode = new Node(elemento);
        
        if (node == head) {
            // Inserir antes do primeiro
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            // Inserir no meio
            newNode.next = node;
            newNode.prev = node.prev;
            node.prev.next = newNode;
            node.prev = newNode;
        }
        
        size++;
        return newNode;
    }
    
    // O(1)
    public Node insertAfter(Node node, int elemento) {
        if (node == null) {
            throw new RuntimeException("Nó não pode ser null");
        }
        
        Node newNode = new Node(elemento);
        
        if (node == tail) {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        } else {
            newNode.prev = node;
            newNode.next = node.next;
            node.next.prev = newNode;
            node.next = newNode;
        }
        
        size++;
        return newNode;
    }
    
    // O(1)
    public Node insertFirst(int elemento) {
        Node newNode = new Node(elemento);
        
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        
        size++;
        return newNode;
    }
    
    // O(1)
    public Node insertLast(int elemento) {
        Node newNode = new Node(elemento);
        
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        
        size++;
        return newNode;
    }
    
    // O(1)
    public int remove(Node node) {
        if (isEmpty()) {
            throw new RuntimeException("Lista está vazia");
        }
        
        int elemento = node.data;
        
        if (size == 1) {
            head = tail = null;
        } else if (node == head) {
            head = head.next;
            head.prev = null;
        } else if (node == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        
        size--;
        return elemento;
    }
    
    // Limpa a lista - O(1)
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // O(n)
    public void mostrar() {
        if (isEmpty()) {
            System.out.println("Lista vazia");
            return;
        }
        
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" <-> ");
            }
            current = current.next;
        }
    }
    
    public static void main(String[] args) {
        
        ListWithLinkedList lista = new ListWithLinkedList();
        
        Node node1 = lista.insertFirst(10);
        Node node2 = lista.insertLast(20);
        Node node3 = lista.insertLast(30);
        lista.insertFirst(5);
        lista.mostrar();
        
        System.out.println(lista.first());
        System.out.println(lista.last());
        
        lista.insertAfter(node1, 15);
        lista.insertBefore(node2, 18);
        lista.mostrar();
        

        int old = lista.replaceElement(node1, 100);
        System.out.println("Elemento antigo " + old);
        lista.mostrar();
        

        lista.swapElements(node1, node3);
        lista.mostrar();
    }
}
