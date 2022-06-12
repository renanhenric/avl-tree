
public class InsercaoArvoreAVL
{
    No root;


    //****** Função para inserir nó na arvore AVL
    static No insertNo(int value,No no)
    {

        //****** Lógica de inserção normal
        if(no == null)
        {
            return (new No(value));
        }
        if(value > no.value)
        {
            no.filhoDir = insertNo(value,no.filhoDir);
            no.filhoDir.pais = no;
        }
        else if (value < no.value)
        {
            no.filhoEsq = insertNo(value,no.filhoEsq);
            no.filhoEsq.pais = no;
        }
        else
        {
            return no;
        }

        //****** Atualizar a altura do nó
        no.height = 1 + Math.max(getAlturaNo(no.filhoDir),getAlturaNo(no.filhoEsq));

        //****** Chamar a função para calcular a diferença de altura das subárvores direita e esquerda
        int diferenca = getAlturaDiferenca(no);

        if (diferenca > 1) // subaŕvore esquerda
        {
            if(value > no.filhoEsq.value)
            {
                // subaŕvore esquerda
                no.filhoEsq = rotateLeft(no.filhoEsq);
                return rotateRight(no);
            }
            if(value < no.filhoEsq.value)
            {
                //esquerda esquerda
                return rotateRight(no);
            }

        }
        if (diferenca < -1) // subarvore direita
        {
            if(value > no.filhoDir.value)
            {
                // direita direita
                return rotateLeft(no);
            }
            if(value < no.filhoDir.value)
            {
                // direita esquerda
                no.filhoDir = rotateRight(no.filhoDir);
                return rotateLeft(no);
            }
        }

        return no;  // retornando o no raiz
    }

    void preOrder(No no)
    {
        if (no != null) {

            System.out.print(no.value + " ");
            preOrder(no.filhoEsq);
            preOrder(no.filhoDir);
        }
    }

    static No rotateRight(No no)
    {
        No T1 = no.filhoEsq;
        No T2 = T1.filhoDir; // filho direito (subarvore)
        //logica rotacao :
        T1.filhoDir = no;
        no.filhoEsq = T2;
        // alterar pais :
        T1.pais = no.pais;
        no.pais = T1;
        if(T2 != null)
        {
            T2.pais = no;
        }
        //recalculando altura :
        no.height = Math.max(getAlturaNo(no.filhoEsq),getAlturaNo(no.filhoDir)) + 1;
        T1.height = Math.max(getAlturaNo(T1.filhoEsq),getAlturaNo(T1.filhoDir)) + 1;

        return T1; // retornar o novo nó raiz

    }

    static No rotateLeft(No no)
    {
        No T1 = no.filhoDir;
        No T2 = T1.filhoEsq;
        //logica rotacao :
        T1.filhoEsq = no;
        no.filhoDir = T2;
        //alterar pais :
        T1.pais = no.pais;
        no.pais = T1;
        if(T2 != null)
        {
            T2.pais = no;
        }
        //recalculando altura :
        no.height = Math.max(getAlturaNo(no.filhoEsq),getAlturaNo(no.filhoDir)) + 1;
        T1.height = Math.max(getAlturaNo(T1.filhoEsq),getAlturaNo(T1.filhoDir)) + 1;

        return T1; // / retornar o novo nó raiz

    }

    //******* Função para retornar a altura do nó - 0 para nós folha -
    static int getAlturaNo(No no)
    {
        if (no == null)
            return 0;

        return no.height;
    }

    //******* Função para retornar a diferença de altura das subárvores esquerda e direita do nó

    static int getAlturaDiferenca(No no)
    {
        if(no == null)
        {
            return 0;
        }
        return getAlturaNo(no.filhoEsq) - getAlturaNo(no.filhoDir);
    }

    static void printAlturaArvore(No no)
    {
        if(no == null)
        {
            System.out.println("***Atingiu o nó raiz da árvore***");
            return;
        }
        System.out.println("      "+getAlturaNo(no.filhoEsq)+"  -   "+no.value+"  -    "+getAlturaNo(no.filhoDir));
        printAlturaArvore(no.pais);
    }

    static No buscarNo(No no,int value)
    {
        if(no == null)
        {
            return null;
        }
        if(value == no.value)
        {
            return no;
        }
        else if (value > no.value)
        {
            return buscarNo(no.filhoDir,value);
        }
        else if (value < no.value)
        {
            return buscarNo(no.filhoEsq,value);
        }
        return null;
    }

    public static void main(String[] args)
    {
        AVLTreeInsert tree = new AVLTreeInsert();

        tree.root = insertNode(5,tree.root);
        tree.root = insertNode(2,tree.root);
        tree.root = insertNode(10,tree.root);
        tree.root = insertNode(15,tree.root);
        tree.root = insertNode(21,tree.root);
        tree.root = insertNode(56,tree.root);
        tree.root = insertNode(78,tree.root);
        tree.root = insertNode(11,tree.root);
        tree.root = insertNode(1,tree.root);
        tree.root = insertNode(64,tree.root);
        tree.root = insertNode(4,tree.root);
        tree.root = insertNode(22,tree.root);
        tree.root = insertNode(89,tree.root);
        tree.root = insertNode(18,tree.root);
        tree.root = insertNode(101,tree.root);
        tree.root = insertNode(45,tree.root);
        tree.root = insertNode(65,tree.root);
        tree.root = insertNode(34,tree.root);
        tree.root = insertNode(102,tree.root);
        Node last = fetchNode(tree.root,102);
        System.out.println("Left height - Node - Right height");
        printTreeHeight(last);
        System.out.println("\nPre-order traversal of the AVL Tree : ");
        tree.preOrder(tree.root);

    }

}