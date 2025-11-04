import java.util.ArrayList;


public class ArrayListPilha {
   
    private ArrayList<Integer> arrayList;
    private int capacidade;
    private int tamanho;


    public ArrayListPilha(int capacidade) {
        this.arrayList = new ArrayList<>(capacidade);
        this.capacidade = capacidade;
        this.tamanho = 0;
    }


    private void aumentarCapacidade() {
        int novaCapacidade = capacidade * 2;
        ArrayList<Integer> novoArrayList = new ArrayList<>(novaCapacidade);
       
        for (int i = 0; i < tamanho; i++) {
            novoArrayList.add(arrayList.get(i));
        }
    }


    private void push(int valor) {
        if (tamanho >= capacidade) {
            aumentarCapacidade();
        }
        arrayList.add(valor);
        tamanho++;
    }


    private void pop() {
        if (tamanho == 0) {
            throw new RuntimeException("Pilha vazia");
        }
        arrayList.remove(tamanho - 1);
        tamanho--;
    }


    private int size() {
        return tamanho;
    }


    private boolean isEmpty() {
        return tamanho == 0;
    }


    private void mostrar(){
        for (int i = 0; i < tamanho; i++) {
            System.out.println(arrayList.get(i) + " ");
        }
    }


    private int top(){
        return arrayList.get(tamanho - 1);
    }


    public static class Main {
        public static void main(String[] args) {
            ArrayListPilha pilha = new ArrayListPilha(2);
            pilha.push(10);
            pilha.push(20);
            System.out.println(pilha.size());
            pilha.mostrar();
            System.out.println(pilha.top());
            pilha.pop();
            System.out.println(pilha.size());
           
            pilha.pop();
            pilha.mostrar();
            System.out.println(pilha.isEmpty());
        }
    }


}



