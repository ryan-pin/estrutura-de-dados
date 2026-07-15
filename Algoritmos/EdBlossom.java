import java.util.*;

public class EdBlossom {
    


    private int n; // Número de vértices
    private List<List<Integer>> adj; // Lista de adjacência do grafo
    private int[] match;  // match[i] armazena o parceiro do vértice i
    private int[] parent; // parent[i] armazena o pai do vértice i na árvore BFS
    private int[] base;   // base[i] armazena o super-vértice (raiz da flor) ao qual i pertence

    public EdBlossom(int n) {
        this.n = n;
        this.adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        this.match = new int[n];
        Arrays.fill(match, -1);
    }

    // Adiciona uma aresta não direcionada entre u e v
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    // Retorna o tamanho do emparelhamento máximo e preenche o array match
    public int maxMatching() {
        int matches = 0;
        for (int i = 0; i < n; i++) {
            // Se o vértice i não está emparelhado, tenta encontrar um caminho aumentante a partir dele
            if (match[i] == -1) {
                if (bfs(i)) {
                    matches++;
                }
            }
        }
        return matches;
    }

    // Busca em Largura (BFS) para encontrar um caminho aumentante a partir da raiz
    private boolean bfs(int root) {
        parent = new int[n];
        Arrays.fill(parent, -1);
        
        base = new int[n];
        for (int i = 0; i < n; i++) base[i] = i;

        boolean[] inQueue = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(root);
        inQueue[root] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : adj.get(u)) {
                // Ignora arestas dentro da mesma flor (super-vértice) ou a aresta de emparelhamento atual
                if (base[u] == base[v] || match[u] == v) continue;

                // Se v é a raiz ou v já foi visitado e está em um nível par (encontramos uma flor)
                if (v == root || (match[v] != -1 && parent[match[v]] != -1)) {
                    int lca = findLCA(root, u, v); // Encontra o ancestral comum mais baixo (base da flor)
                    
                    // Contrai o ciclo ímpar (flor) em um super-vértice
                    contractBlossom(u, lca, v, queue, inQueue);
                    contractBlossom(v, lca, u, queue, inQueue);
                } 
                // Se v não foi visitado
                else if (parent[v] == -1) {
                    parent[v] = u; // u é o pai de v no caminho alternante
                    
                    if (match[v] == -1) {
                        // Vértice livre encontrado! Encontramos um caminho aumentante.
                        augmentPath(v);
                        return true;
                    } else {
                        // v já está emparelhado, adiciona o parceiro de v à fila para continuar a busca alternada
                        queue.add(match[v]);
                        inQueue[match[v]] = true;
                    }
                }
            }
        }
        return false;
    }

    // Inverte as arestas ao longo do caminho aumentante encontrado
    private void augmentPath(int v) {
        while (v != -1) {
            int pv = parent[v]; // Vértice anterior no caminho
            int nextV = match[pv]; // Salva o parceiro antigo de pv
            
            // Emparelha v com pv
            match[v] = pv;
            match[pv] = v;
            
            // Move para o próximo par não resolvido no caminho
            v = nextV;
        }
    }

    // Encontra o Menor Ancestral Comum (LCA) que servirá como a base da nova flor
    private int findLCA(int root, int u, int v) {
        boolean[] inPath = new boolean[n];
        
        // Sobe a árvore a partir de u até a raiz, marcando o caminho
        while (true) {
            u = base[u];
            inPath[u] = true;
            if (u == root) break;
            u = parent[match[u]]; // Sobe dois passos: pelo emparelhamento e depois pelo pai
        }
        
        // Sobe a árvore a partir de v até encontrar o primeiro vértice já marcado
        while (true) {
            v = base[v];
            if (inPath[v]) return v; // O primeiro vértice em comum é o LCA
            v = parent[match[v]];
        }
    }

    // Contrai os vértices de uma flor (ciclo ímpar) em torno de sua base (LCA)
    private void contractBlossom(int u, int lca, int v, Queue<Integer> queue, boolean[] inQueue) {
        while (base[u] != lca) {
            parent[u] = v; // Cria uma ponte bidirecional no ciclo
            int matchU = match[u];
            
            // Se o parceiro de u não estava na fila, ele agora se torna um nó de exploração válido
            if (!inQueue[matchU]) {
                queue.add(matchU);
                inQueue[matchU] = true;
            }
            
            // Atualiza a base (super-vértice) de u e seu parceiro
            base[u] = lca;
            base[matchU] = lca;
            
            // Move para o próximo vértice em direção ao LCA
            v = matchU;
            u = parent[v];
        }
    }

    // Método principal para testar o algoritmo
    public static void main(String[] args) {
        // Exemplo: Grafo com 6 vértices
        EdBlossom graph = new EdBlossom(6);
        
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0); // Ciclo ímpar (0-1-2) aqui
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        int maxMatches = graph.maxMatching();
        System.out.println("Tamanho do emparelhamento maximo: " + maxMatches);
        
        System.out.println("Pares emparelhados:");
        for (int i = 0; i < 6; i++) {
            if (graph.match[i] != -1 && i < graph.match[i]) {
                System.out.println(i + " - " + graph.match[i]);
            }
        }
    }
}
