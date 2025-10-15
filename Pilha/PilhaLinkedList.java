public class PilhaLinkedList {
    
    private class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node top; 
    private int size;
    
    public PilhaLinkedList() {
        this.top = null;
        this.size = 0;
    }
    
    public void push(int elemento) {
        Node newNode = new Node(elemento);
        newNode.next = top;
        top = newNode;
        size++;
    }
    
    public int pop() {
        if (estaVazia()) {
            throw new RuntimeException("Pilha está vazia");
        }
        
        int data = top.data;
        top = top.next;
        size--;
        return data;
    }
    
    public int topo() {
        if (estaVazia()) {
            throw new RuntimeException("Pilha está vazia");
        }
        return top.data;
    }
    
    public boolean estaVazia() {
        return size == 0;
    }
    
    public int tamanho() {
        return size;
    }
    
    public void limpar() {
        top = null;
        size = 0;
    }
    
    public void mostrar() {
        if (estaVazia()) {
            System.out.println("Pilha vazia");
            return;
        }
        
        Node current = top;
        while (current != null) {
            System.out.print(current.data);
            current = current.next;
        }
        System.out.println();
    }
    
    // Teste
    public static void main(String[] args) {
        
        PilhaLinkedList pilha = new PilhaLinkedList();
        
        //Pilha vazia
        System.out.println("Está vazia? " + pilha.estaVazia());
        System.out.println("Tamanho: " + pilha.tamanho());
        pilha.mostrar();
        
        //Adicionando elementos
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);
        pilha.push(40);
        pilha.mostrar();
        System.out.println("Tamanho: " + pilha.tamanho());
        
        //Visualizando topo
        System.out.println("Topo: " + pilha.topo());
        
        //Removendo elementos
        System.out.println("Removido: " + pilha.pop());
        System.out.println("Removido: " + pilha.pop());
        pilha.mostrar();
        System.out.println("Tamanho: " + pilha.tamanho());
        
        
        // Demonstração LIFO
        System.out.println("\n=== DEMONSTRAÇÃO LIFO ===");
        PilhaLinkedList pilhaLIFO = new PilhaLinkedList();
        System.out.println("Adicionando: 1, 2, 3, 4, 5");
        pilhaLIFO.push(1);
        pilhaLIFO.push(2);
        pilhaLIFO.push(3);
        pilhaLIFO.push(4);
        pilhaLIFO.push(5);

        pilhaLIFO.mostrar();
        
        System.out.print("Removendo (LIFO): ");
        while (!pilhaLIFO.estaVazia()) {
            System.out.print(pilhaLIFO.pop() + " ");
        }
        pilhaLIFO.mostrar();
        System.out.println("\nÚltimo a entrar, primeiro a sair!");
    }
}