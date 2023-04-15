class Node {
    int val;             // valor
    Node left;           // filho esquerdo
    Node right;          // filho direito
    int height;          // altura do nó
    
    Node(int val) {
        this.val = val;
        height = 1;
    }
}

public class Solution {
    Node root;
    
    int height(Node n) {  // obter a altura de um nó
        if (n == null) {
            return 0;
        }
        return n.height;
    }
    
    int get_balance(Node n) {  // obter o fator de balanceamento de um nó
        if (n == null) {
            return 0;
        }
        return height(n.left) - height(n.right);
    }
    
    Node right_rotate(Node y) {  // rotação à direita
        Node x = y.left;
        Node T2 = x.right;
        
        // rotação
        x.right = y;
        y.left = T2;
        
        // atualizar alturas
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        
        // retorna a nova raiz da subárvore
        return x;
    }
    
    Node left_rotate(Node x) {  // rotação à esquerda
        Node y = x.right;
        Node T2 = y.left;
        
        // rotação
        y.left = x;
        x.right = T2;
        
        // atualizar alturas
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        
        // retorna a nova raiz da subárvore
        return y;
    }
    
    Node insert(Node node, int val) {  // inserir um novo valor na árvore
        if (node == null) {
            return new Node(val);
        }
        
        if (val < node.val) {
            node.left = insert(node.left, val);
        } else if (val > node.val) {
            node.right = insert(node.right, val);
        } else {  // o valor já existe
            return node;
        }
        
        // atualizar a altura do nó atual
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        
        // obter o fator de balanceamento do nó atual
        int balance = get_balance(node);
        
        // caso 1: rotação à direita
        if (balance > 1 && val < node.left.val) {
            return right_rotate(node);
        }
        
        // caso 2: rotação à esquerda
        if (balance < -1 && val > node.right.val) {
            return left_rotate(node);
        }
        
        // caso 3: rotação dupla à direita
        if (balance > 1 && val > node.left.val) {
            node.left = left_rotate(node.left);
            return right_rotate(node);
        }
        
        // caso 4: rotação dupla à esquerda
        if (balance < -1 && val < node.right.val) {
            node.right = right_rotate(node.right);
            return left_rotate(node);
        }
        
        // retorna o nó atual (sem rotações)
        return node;
        }

        void printInOrdem() {
            printInOrdemRecursivamente(root);
        }
        
        void printInOrdemRecursivamente(Node node) {
            if (node != null) {
                printInOrdemRecursivamente(node.left);
                System.out.print(node.val + " ");
                printInOrdemRecursivamente(node.right);
            }
        }
        

        public static void main(String[] args) {
            Solution tree = new Solution();
        
            // Insere alguns valores na árvore
            tree.root = tree.insert(tree.root, 10);
            tree.root = tree.insert(tree.root, 20);
            tree.root = tree.insert(tree.root, 30);
            tree.root = tree.insert(tree.root, 40);
            tree.root = tree.insert(tree.root, 50);
            tree.root = tree.insert(tree.root, 25);
        
            // Imprime a árvore em ordem
            System.out.println("Árvore AVL em ordem: ");
            tree.printInOrdem();
        }
        

    }
