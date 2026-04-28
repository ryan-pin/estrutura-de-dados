package Arvore_RB;

import java.util.ArrayList;

public class ArvoreRB {

    int size = 0;

    Node raiz = null;

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isRoot(Node no) {
        return no != null && no.getPai() == null;
    }

    public boolean isExternal(Node no) {
        return no != null && no.getFilhoE() == null && no.getFilhoD() == null;
    }

    public boolean isInternal(Node no) {
        return no != null && (no.getFilhoE() != null || no.getFilhoD() != null);
    }

    public Node search(Node no, Object value) {
        if (no == null) {
            return null;
        }

        if ((int) value == (int) no.getValue()) {
            return no;
        } else if ((int) value < (int) no.getValue()) {
            return search(no.getFilhoE(), value);
        } else {
            return search(no.getFilhoD(), value);
        }
    }

    // INSERÇÃO RUBRO-NEGRA
    public void insertRB(Object value) {

        // Cria o novo nó
        Node newNode = new Node();
        newNode.setValue(value);

        // Todo novo nó entra vermelho
        newNode.setRubro(true);

        // Se a árvore estiver vazia, o novo nó vira raiz
        if (this.raiz == null) {
            this.raiz = newNode;
            this.raiz.setRubro(false); // raiz sempre será preta
            this.size++;
            return;
        }

        // Insere como em uma árvore binária de busca normal
        Node atual = this.raiz;
        Node pai = null;

        while (atual != null) {
            pai = atual;

            if ((int) value < (int) atual.getValue()) {
                atual = atual.getFilhoE();
            } else {
                atual = atual.getFilhoD();
            }
        }

        // Liga o novo nó ao pai
        newNode.setPai(pai);

        if ((int) value < (int) pai.getValue()) {
            pai.setFilhoE(newNode);
        } else {
            pai.setFilhoD(newNode);
        }

        this.size++;

        // Corrige possíveis violações das regras
        fixInsert(newNode);
    }

    // CORREÇÃO APÓS INSERÇÃO
    private void fixInsert(Node no) {

        // Enquanto o nó não for a raiz e o pai dele for vermelho
        while (no != this.raiz && no.getPai() != null && no.getPai().isRubro()) {

            Node pai = no.getPai();
            Node avo = pai.getPai();

            // Se o pai for filho da esquerda do avô
            if (pai == avo.getFilhoE()) {

                // O tio é o filho da direita do avô
                Node tio = avo.getFilhoD();

                // CASO 1:
                // Tio vermelho -> recolorir
                if (tio != null && tio.isRubro()) {
                    pai.setRubro(false);
                    tio.setRubro(false);
                    avo.setRubro(true);

                    // Sobe o problema para o avô
                    no = avo;
                }
                // CASO 2 e 3:
                // Tio preto ou nulo -> rotações
                else {
                    // CASO TRIÂNGULO: no é filho direito do pai
                    // Primeiro faz rotação à esquerda no pai
                    if (no == pai.getFilhoD()) {
                        no = pai;
                        rotacaoSE(no);
                        pai = no.getPai(); // atualiza referência
                        avo = pai.getPai();
                    }

                    // Agora virou CASO LINHA
                    // Recolorir
                    pai.setRubro(false);
                    avo.setRubro(true);

                    // Rotação à direita no avô
                    rotacaoSD(avo);
                }

            }
            // Se o pai for filho da direita do avô, faz o espelho dos casos
            else {

                // O tio é o filho da esquerda do avô
                Node tio = avo.getFilhoE();

                // CASO 1:
                if (tio != null && tio.isRubro()) {
                    pai.setRubro(false);
                    tio.setRubro(false);
                    avo.setRubro(true);

                    no = avo;
                }
                // CASO 2 e 3:
                else {
                    // CASO TRIÂNGULO: no é filho esquerdo do pai
                    // Primeiro faz rotação à direita no pai
                    if (no == pai.getFilhoE()) {
                        no = pai;
                        rotacaoSD(no);
                        pai = no.getPai(); // atualiza referência
                        avo = pai.getPai();
                    }

                    // Agora virou CASO LINHA
                    pai.setRubro(false);
                    avo.setRubro(true);

                    // Rotação à esquerda no avô
                    rotacaoSE(avo);
                }
            }
        }

        // A raiz sempre precisa ser preta
        this.raiz.setRubro(false);
    }

    // ROTAÇÃO SIMPLES À ESQUERDA
    public Node rotacaoSE(Node no) {
        // O filho da direita sobe
        Node sucessor = no.getFilhoD();

        // O filho esquerdo do sucessor passa a ser o filho direito de no
        no.setFilhoD(sucessor.getFilhoE());

        if (sucessor.getFilhoE() != null) {
            sucessor.getFilhoE().setPai(no);
        }

        // Ajusta o pai do sucessor
        sucessor.setPai(no.getPai());

        // Se no era raiz, o sucessor vira raiz
        if (no.getPai() == null) {
            this.raiz = sucessor;
        }
        // Senão, liga o sucessor ao pai antigo de no
        else if (no == no.getPai().getFilhoE()) {
            no.getPai().setFilhoE(sucessor);
        } else {
            no.getPai().setFilhoD(sucessor);
        }

        // Faz no virar filho esquerdo do sucessor
        sucessor.setFilhoE(no);
        no.setPai(sucessor);

        return sucessor;
    }

    // ROTAÇÃO SIMPLES À DIREITA
    public Node rotacaoSD(Node no) {
        // O filho da esquerda sobe
        Node sucessor = no.getFilhoE();

        // O filho direito do sucessor passa a ser o filho esquerdo de no
        no.setFilhoE(sucessor.getFilhoD());

        if (sucessor.getFilhoD() != null) {
            sucessor.getFilhoD().setPai(no);
        }

        // Ajusta o pai do sucessor
        sucessor.setPai(no.getPai());

        // Se no era raiz, o sucessor vira raiz
        if (no.getPai() == null) {
            this.raiz = sucessor;
        }
        // Senão, liga o sucessor ao pai antigo de no
        else if (no == no.getPai().getFilhoE()) {
            no.getPai().setFilhoE(sucessor);
        } else {
            no.getPai().setFilhoD(sucessor);
        }

        // Faz no virar filho direito do sucessor
        sucessor.setFilhoD(no);
        no.setPai(sucessor);

        return sucessor;
    }

    // PERCURSO EM ORDEM
    public void Emordem(Node n, ArrayList<Node> s) {
        if (n != null) {
            Emordem(n.getFilhoE(), s);
            s.add(n);
            Emordem(n.getFilhoD(), s);
        }
    }

    // ALTURA DA ÁRVORE
    public int altura(Node no) {
        if (no == null) {
            return -1;
        }

        int alturaDireita = altura(no.getFilhoD());
        int alturaEsquerda = altura(no.getFilhoE());

        return 1 + Math.max(alturaDireita, alturaEsquerda);
    }

    // PROFUNDIDADE DE UM NÓ
    public int depth(Node no) {
        if (no == null) {
            return -1;
        }

        if (isRoot(no)) {
            return 0;
        }

        return 1 + depth(no.getPai());
    }

    public void removeRB(Object value) {

        Node no = search(this.raiz, value);

        if (no == null)
            return;

        deleteNode(no);
    }

    private void deleteNode(Node no) {

        Node substituto;

        // CASO: nó tem 2 filhos
        if (no.getFilhoE() != null && no.getFilhoD() != null) {

            // pega o sucessor (menor da direita)
            Node aux = no.getFilhoD();
            while (aux.getFilhoE() != null) {
                aux = aux.getFilhoE();
            }

            // troca valores
            no.setValue(aux.getValue());

            // agora remove o sucessor
            no = aux;
        }

        // Agora o nó tem no máximo 1 filho
        substituto = (no.getFilhoE() != null) ? no.getFilhoE() : no.getFilhoD();

        // Se o substituto não for null
        if (substituto != null) {

            // liga o substituto ao pai
            substituto.setPai(no.getPai());

            if (no.getPai() == null) {
                this.raiz = substituto;
            } else if (no == no.getPai().getFilhoE()) {
                no.getPai().setFilhoE(substituto);
            } else {
                no.getPai().setFilhoD(substituto);
            }

            // Se o nó removido era preto, precisa corrigir
            if (!no.isRubro()) {
                fixDelete(substituto);
            }

        } else {
            // Caso: nó folha

            if (no.getPai() == null) {
                this.raiz = null;
            } else {

                // Se era preto → duplo negro, precisa corrigir
                if (!no.isRubro()) {
                    fixDelete(no);
                }

                // remove do pai
                if (no == no.getPai().getFilhoE()) {
                    no.getPai().setFilhoE(null);
                } else {
                    no.getPai().setFilhoD(null);
                }
            }
        }

        size--;
    }

    private void fixDelete(Node no) {

        while (no != raiz && (no == null || !no.isRubro())) {

            Node pai = no.getPai();

            // Se no é filho esquerdo
            if (no == pai.getFilhoE()) {

                Node irmao = pai.getFilhoD();

                // CASO 1: irmão vermelho
                if (irmao != null && irmao.isRubro()) {
                    irmao.setRubro(false);
                    pai.setRubro(true);
                    rotacaoSE(pai);
                    irmao = pai.getFilhoD();
                }

                // CASO 2: irmão preto com filhos pretos
                if ((irmao.getFilhoE() == null || !irmao.getFilhoE().isRubro()) &&
                        (irmao.getFilhoD() == null || !irmao.getFilhoD().isRubro())) {

                    irmao.setRubro(true);
                    no = pai;

                } else {

                    // CASO 3: irmão preto com filho esquerdo vermelho
                    if (irmao.getFilhoD() == null || !irmao.getFilhoD().isRubro()) {

                        if (irmao.getFilhoE() != null)
                            irmao.getFilhoE().setRubro(false);

                        irmao.setRubro(true);
                        rotacaoSD(irmao);
                        irmao = pai.getFilhoD();
                    }

                    // CASO 4: irmão preto com filho direito vermelho
                    irmao.setRubro(pai.isRubro());
                    pai.setRubro(false);

                    if (irmao.getFilhoD() != null)
                        irmao.getFilhoD().setRubro(false);

                    rotacaoSE(pai);
                    no = raiz;
                }

            } else {
                // ESPERELHO (lado direito)

                Node irmao = pai.getFilhoE();

                if (irmao != null && irmao.isRubro()) {
                    irmao.setRubro(false);
                    pai.setRubro(true);
                    rotacaoSD(pai);
                    irmao = pai.getFilhoE();
                }

                if ((irmao.getFilhoE() == null || !irmao.getFilhoE().isRubro()) &&
                        (irmao.getFilhoD() == null || !irmao.getFilhoD().isRubro())) {

                    irmao.setRubro(true);
                    no = pai;

                } else {

                    if (irmao.getFilhoE() == null || !irmao.getFilhoE().isRubro()) {

                        if (irmao.getFilhoD() != null)
                            irmao.getFilhoD().setRubro(false);

                        irmao.setRubro(true);
                        rotacaoSE(irmao);
                        irmao = pai.getFilhoE();
                    }

                    irmao.setRubro(pai.isRubro());
                    pai.setRubro(false);

                    if (irmao.getFilhoE() != null)
                        irmao.getFilhoE().setRubro(false);

                    rotacaoSD(pai);
                    no = raiz;
                }
            }
        }

        if (no != null)
            no.setRubro(false);
    }

    // IMPRESSÃO SIMPLES DA ÁRVORE
    public void printTree(Node n, String prefix, boolean isLeft) {
        if (n != null) {
            if (n.getPai() == null) {
                System.out.println(prefix + n.getValue() + (n.isRubro() ? " (R)" : " (P)"));
            } else {
                System.out.println(
                        prefix + (isLeft ? "├──E " : "└──D ") + n.getValue() + (n.isRubro() ? " (R)" : " (P)"));
            }

            printTree(n.getFilhoE(), prefix + (isLeft ? "│   " : "    "), true);
            printTree(n.getFilhoD(), prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    // -------------------------------------------------------------
    // MOSTRAR A ÁRVORE EM ORDEM
    // -------------------------------------------------------------

    public void mostrarEmOrdem() {
        ArrayList<Node> nodes = new ArrayList<>();
        Emordem(this.raiz, nodes);

        for (Node n : nodes) {
            System.out.print(n.getValue() + (n.isRubro() ? "(R) " : "(P) "));
        }
        System.out.println();
    }
}