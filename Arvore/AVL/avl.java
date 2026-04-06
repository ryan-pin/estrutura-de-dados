import java.util.ArrayList;

public class avl {
    int size = 0; 
    Node raiz = null; 

    public int size(){
        return this.size;
    }
    
    public boolean isRoot(Node no) {
        return no.getPai() == null;
    }

    public boolean isExternal(Node no) {
        return no.getFilhoE() == null && no.getFilhoD() == null;
    }
    
    public boolean isInternal(Node no) {
        return no.getFilhoE() != null || no.getFilhoD() != null;
    }
    
    public boolean isEmpty(){
        return this.size == 0;
    }

    public Node search(Node no, Object value) {
        if (no == null) {
            return null;
        }
        if (no.getValue() == value) {
            return no;
        } else if ((int) value < (int) no.getValue()) {
            return search(no.getFilhoE(), value);
        } else {
            return search(no.getFilhoD(), value);
        }
    }

    public int altura(Node no) {
        if (no == null) {
            return -1; 
        }
    
        int altura_direita = altura(no.filhoD);
        int altura_esquerda = altura(no.filhoE); 
    
        return 1 + Math.max(altura_direita, altura_esquerda);
    }
    

    public int depth(Node no) {
        if(isRoot(no)){
            return 0;
        }
        return 1 + depth(no.getPai());
    }

    public void insertAVL(Node no, Object value) {

        this.size++;
        if (this.raiz == null) {
            Node  new_no = new Node();
            new_no.setValue(value);
            this.raiz = new_no;
        } else {
            Node new_no = insert(this.raiz, value);
            insertCalculateFB(new_no);
        }

    }

    public Node insert(Node no, Object value) {
        if ((int) value < (int) no.getValue()) { // se o valor do novo nó for menor que o valor do no atual
            if (no.getFilhoE() == null) { // verifica se o filho esquerdo é nulo
                Node new_no = new Node(); // cria um novo nó
                new_no.setValue(value); // seta o valor do novo nó
                no.setFilhoE(new_no); // seta o filho esquerdo do nó atual (filho da esquerda) como o novo nó
                new_no.setPai(no); // seta o pai do novo nó como o nó atual (filho da esquerda)
                return new_no;
            } else {
                return insert(no.getFilhoE(), value); // se o filho esquerdo não for nulo (contém filhos), chama a função recursivamente
            }
        } else {
            if (no.getFilhoD() == null) { // verifica se o filho direito é nulo
                Node new_no = new Node(); // cria um novo nó
                new_no.setValue(value); // seta o valor do novo nó
                no.setFilhoD(new_no); // seta o filho direito do nó atual (filho da direita) como o novo nó
                new_no.setPai(no);
                return new_no; // seta o pai do novo nó como o nó atual (filho da direita)
            } else {
                return insert(no.getFilhoD(), value); // se o filho direito não for nulo (contém filhos), chama a função recursivamente
            }
        }
    }

    public void insertCalculateFB(Node no){
        if(isRoot(no)){
            return;
        }
        
        if(no == no.getPai().getFilhoE()){ 
            no.getPai().setFB(no.getPai().getFB() + 1);
        }else{
            no.getPai().setFB(no.getPai().getFB() - 1);
        }      

        if(no.getPai().getFB() == 0){ // pare o método, pois a árvore está balanceada (caso o fator de balanceamento do pai seja igual a 0 ou o nó atual seja a raiz)
            return;
        }else{
            // se o fator de balanceamento for 1 ou -1, chama a função recursivamente para o pai do nó atual 
            // a arvore ainda está balanceada
            if(no.getPai().getFB() == 1 || no.getPai().getFB() == -1){
                insertCalculateFB(no.getPai());
            }else{
                // se o fator de balanceamento for 2 ou -2, chama a função de balanceamento passando o pai do no atual
                // a arvore está desbalanceada
                balancear(no.getPai());
                return;
            }
        }

    }

   


    public Node rotacaoSE(Node no) {
        Node sucessor = no.getFilhoD()  ; // 4

        // Ajusta o no do sucessor
        if(no.getPai() != null){
            sucessor.setPai(no.getPai());

            if (no == no.getPai().getFilhoE()) { // se o no desb. for filho esquerdo do seu no
                no.getPai().setFilhoE(sucessor); // o filho esquerdo do no do no desb. será o filho direito do no desb.

            } else {
                no.getPai().setFilhoD(sucessor); // o filho direito do no do no desb. será o filho direito do no desb.
            }

        }else{
            sucessor.setPai(null);
            this.raiz = sucessor;
        }
        no.setPai(sucessor);

        no.setFilhoD(sucessor.getFilhoE());

        if (sucessor.getFilhoE() != null) {
            sucessor.getFilhoE().setPai(no); //
        }
        sucessor.setFilhoE(no);
       
        // FB_B_novo= FB_B + 1 - min(FB_A, 0);
        // FB_A_novo= FB_A + 1 + max(FB_B_novo, 0);
        // FB_B = no
        // FA_A = sucessor
        // FA_NOVO_B = sucessor
        // FB_NOVO_A = no

        int FB_NOVO_B = no.getFB() + 1 - Math.min(sucessor.getFB(), 0);
        int FB_NOVO_A = sucessor.getFB() + 1 + Math.max(FB_NOVO_B, 0);

        no.setFB(FB_NOVO_B);
        sucessor.setFB(FB_NOVO_A);

        return sucessor;
    }

    public Node rotacaoSD(Node no) {
        Node sucessor = no.getFilhoE(); // O sucessor é o filho esquerdo
    
        // Ajusta o no do sucessor
        if (no.getPai() != null) {
            sucessor.setPai(no.getPai());
    
            if (no == no.getPai().getFilhoE()) { // Se o nó desbalanceado for filho esquerdo do seu no
                no.getPai().setFilhoE(sucessor); // O filho esquerdo do no do nó desbalanceado será o filho esquerdo do nó desbalanceado
            } else {
                no.getPai().setFilhoD(sucessor); // O filho direito do no do nó desbalanceado será o filho esquerdo do nó desbalanceado
            }
        } else {
            sucessor.setPai(null); // O sucessor se torna a nova raiz
            this.raiz = sucessor;

        }
    
        no.setPai(sucessor);
    
        // Ajusta os filhos após a rotação
        no.setFilhoE(sucessor.getFilhoD()); // O filho direito do sucessor se torna o filho esquerdo do no
        if (sucessor.getFilhoD() != null) {
            sucessor.getFilhoD().setPai(no); // Ajusta o no do antigo filho direito do sucessor
        }
        sucessor.setFilhoD(no); // O antigo no se torna o filho direito do sucessor
    

        // FB_B_novo= FB_B - 1 - max(FB_A, 0);
        // FB_A_novo= FB_A - 1 + min(FB_B_novo, 0);
        // FB_B = no
        // FA_A = sucessor
        // FA_NOVO_B = sucessor
        // FB_NOVO_A = no

        int FB_NOVO_B = no.getFB() - 1 - Math.max(sucessor.getFB(), 0);
        int FB_NOVO_A = sucessor.getFB() - 1 + Math.min(FB_NOVO_B, 0);

        no.setFB(FB_NOVO_B);
        sucessor.setFB(FB_NOVO_A);

        // Retorna o sucessor, que agora é o novo nó raiz da subárvore
        return sucessor;
    }
    
    public Node rotacaoDE(Node no){
        Node filho = no.getFilhoD(); // pega o filho a direita para fazer uma rotação simples a direita
        rotacaoSD(filho);
        return rotacaoSE(no); // pega o pai do filho a esquerda e faz uma rotação simples a esquerda

        //     7 
        //   /   \
        //  6     8 no - pai do filho esquerdo
        //       /
        //     10 filho esquerdo
        //       \
        //        9

    }

    public Node rotacaoDD(Node no){
        Node filho = no.getFilhoE(); // pega o filho a esquerda para fazer uma rotação simples a esquerda
        Node sucessor = rotacaoSE(filho);
        return rotacaoSD(sucessor.getPai()); // pega o pai do filho a direita e faz uma rotação simples a direita

        //                 12 
        //   /                            \
        //  8 no - pai do filho direito   13
        //   \
        //    10 filho direito
        //   /
        //  9 

    }



    public void balancear(Node no){
        if (no.getFB() == -2) {
            if (no.getFilhoD().getFB() <= 0 ) {
                System.out.println("rotacaoSE");
                rotacaoSE(no);
                
            } else { 
                System.out.println("rotacaoDE");
                rotacaoDE(no);

            }
        } else if (no.getFB() == 2) {
            if ( no.getFilhoE().getFB() >= 0) {
                System.out.println("rotacaoSD");
                rotacaoSD(no);
            } else {
                System.out.println("rotacaoDD");
                rotacaoDD(no);
            }
        }
    }

  

// ***************************************************************************************************** //
// | Tipo    | Primeira Rotação | Segunda Rotação | Fator de Balanceamento | Sinal do Filho com Maior Altura |
// |---------|------------------|-----------------|-------------------------|---------------------------------|
// | Dupla   | Esquerda         | Direita         | -2                      | FD+                             |
// | Dupla   | Direita          | Esquerda        | +2                      | FE-                             |
// | Simples | Esquerda         | N/A             | -2                      | FD-                             |
// | Simples | Direita          | N/A             | +2                      | FE+                             |

    public  void printTree(Node n, String prefix, boolean isLeft) {
        if (n != null) {
            if(n.getPai() == null){            
                System.out.println(prefix + "\n   " + n.getValue() + ": " + n.getFB() );
            }else{
                System.out.println(prefix + (isLeft ? "├──E " : "└──D ") + n.getValue() + ": " + n.getFB());
            }
            printTree(n.getFilhoE(), prefix + (isLeft ? "│   " : "    "), true);
            printTree(n.getFilhoD(), prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    public void Emordem(Node n, ArrayList<Node> s) {
        if (n != null) {
            Emordem(n.getFilhoE(), s);  
            s.add(n);
            Emordem(n.getFilhoD(), s);  
        }
    }

    public void mostrarArvore(ArrayList<Node> nodes, avl arvore) {
        int altura = altura(arvore.raiz) + 1;

        Node[][] matriz = new Node[altura][size()];
    
        // Inicializar a matriz com espaços
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < this.size; j++) {
                Node vazio = new Node();
                vazio.setValue(" ");
                matriz[i][j] = vazio;
            }
        }
    
        // Preencher a matriz com valores dos nós conforme a altura
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < this.size; j++) {
                Node noAtual = nodes.get(j);
                if (arvore.depth(noAtual) == i) {
                    matriz[i][j] = noAtual;
                }
            }
        }
    
        // Exibir a matriz
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < this.size; j++) {
                if(matriz[i][j].getValue() == " "){
                    System.out.print("  ");
                }else
                if(matriz[i][j] == this.raiz)
                System.out.print(matriz[i][j].getValue());
                else
                System.out.print(matriz[i][j].getValue() + " ");

            }
            System.out.println();
        }
    }

    public void mostrarArvoreDev(ArrayList<Node> nodes, avl arvore) {
        int altura = altura(arvore.raiz) + 1;

        Node[][] matriz = new Node[altura][size()];
    
        // Inicializar a matriz com espaços
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < this.size; j++) {
                Node vazio = new Node();
                vazio.setValue(" ");
                matriz[i][j] = vazio;
            }
        }
    
        // Preencher a matriz com valores dos nós conforme a altura
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < this.size; j++) {
                Node noAtual = nodes.get(j);
                if (arvore.depth(noAtual) == i) {
                    matriz[i][j] = noAtual;
                }
            }
        }
        // Exibir a matriz
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < this.size; j++) {
                if(matriz[i][j].getValue() == " "){
                    System.out.print("  ");
                }else
                if(matriz[i][j] == this.raiz)
                System.out.print(matriz[i][j].getValue() + ":" + matriz[i][j].getFB() + " ");
                else if(matriz[i][j].getPai().getFilhoE() == matriz[i][j])
                System.out.print("E-" + matriz[i][j].getValue() + ":" + matriz[i][j].getFB() + " ");
                else
                System.out.print("D-" + matriz[i][j].getValue() + ":" + matriz[i][j].getFB() + " ");

            }
            System.out.println();
        }
    }

    // remoção: remove de qualquer lugar
    // 3 casos podem ocorrer
    // 1 - Remover um nó com nenhum filho
    //    dizer ao pai daquele nó que ele não tem mais aquele filho
    //    setendo o filho como null
    // 2 - quando o nó tem 1 filho
    //      Insere o filho do nó a ser removido como filho do pai do nó a ser removido
    //      2 pai do 4 e 4 pai no 5, se quero remover o 4.
    //      então o pai do 5 agora é o dois e o filho do dois agora é o 5.
    // 3 - remover um nó com 2 filhos
    //      remover a raiz. 
    //      pega o nó sucessor (maior) e desse nó maior pega o nó menor possível
    //      vai indo para os filhos a esquerda até encontrar um nó null, quando encontrar, o pai desse
    //      nó null é o sucessor


    public Node removeNo(Node no, Object value) {
        Node aux = new Node();
        aux.setValue(value);
        remove(search(no, value));
        return  aux;
    }

    public void remove(Node no) {
        this.size--;
        if (no.getFilhoE() == null && no.getFilhoD() == null) {
                if (no == this.raiz) {
                    this.raiz = null;
                }
              
                if(no.getPai().getFilhoD().getValue() == no.getValue()){
                    no.getPai().setFilhoD(null);
                    no.getPai().setFB(no.getPai().getFB() + 1);

                }else{
                    no.getPai().setFilhoE(null);
                    no.getPai().setFB(no.getPai().getFB() - 1);
                }
               
        } else if (no.getFilhoE() == null) {
                System.out.println("Nesse ");
               
                if (no == this.raiz) {
                    this.raiz = no.getFilhoD();
                    this.raiz.setPai(null);
                } else  if(no.getPai().getFilhoD().getValue() == no.getValue()){
                    no.getPai().setFilhoD(no.getFilhoD());
                    no.getPai().setFB(no.getPai().getFB() + 1);

                }else{
                    no.getPai().setFilhoE(no.getFilhoD());
                    no.getPai().setFB(no.getPai().getFB() - 1);
                }

            } else if (no.getFilhoD() == null) {
                Node auxi = no;
                no = no.getFilhoE();
                no.setPai(auxi.getPai());
                if (auxi == this.raiz) {
                    this.raiz = no;
                } else if(no.getPai().getFilhoD() == no){
                    no.getPai().setFilhoD(no);
                    no.getPai().setFB(no.getPai().getFB() + 1);

                }else{
                    no.getPai().setFilhoE(no);
                    no.getPai().setFB(no.getPai().getFB() - 1);
                }
            } else {

                Node auxi = no.getFilhoD();
                while (auxi.getFilhoE() != null) {
                    auxi = auxi.getFilhoE();
                }
                
                no.setValue(auxi.getValue());
                if(auxi.getPai().getFilhoD() == auxi){
                    removeCalculateFB(auxi);
                    auxi.getPai().setFilhoD(null);


                }else{
                    removeCalculateFB(auxi);
                    auxi.getPai().setFilhoE(null);

                }

            }
    }

    public void removeCalculateFB(Node no){
        if(isRoot(no)){
            return;
        }
        
        if(no == no.getPai().getFilhoE()){ 
            no.getPai().setFB(no.getPai().getFB() - 1);
        }else{
            no.getPai().setFB(no.getPai().getFB() + 1);
        }      

        if(no.getPai().getFB() == -2 || no.getPai().getFB() == 2){ 
            balancear(no.getPai());
            return;
        } else if(no.getPai().getFB() != 0){ 
            return;
        }

    }
}