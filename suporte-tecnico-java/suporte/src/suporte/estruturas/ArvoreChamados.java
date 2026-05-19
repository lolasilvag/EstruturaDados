package suporte.estruturas;

import suporte.modelo.Chamado;

/**
 * ÁRVORE BINÁRIA DE BUSCA (BST)
 * Organiza e busca chamados pelo ID.
 * ID menor vai para a esquerda, maior vai para a direita.
 * Permite busca eficiente O(log n) em média.
 */
public class ArvoreChamados {

    private static class No {
        Chamado dado;
        No esquerda, direita;
        No(Chamado dado) { this.dado = dado; }
    }

    private No raiz;

    public void inserir(Chamado chamado) {
        raiz = inserirRec(raiz, chamado);
    }

    private No inserirRec(No no, Chamado chamado) {
        if (no == null) return new No(chamado);
        if (chamado.getId() < no.dado.getId())
            no.esquerda = inserirRec(no.esquerda, chamado);
        else if (chamado.getId() > no.dado.getId())
            no.direita = inserirRec(no.direita, chamado);
        return no;
    }

    public Chamado buscarPorId(int id) {
        return buscarRec(raiz, id);
    }

    private Chamado buscarRec(No no, int id) {
        if (no == null) return null;
        if (id == no.dado.getId()) return no.dado;
        if (id < no.dado.getId()) return buscarRec(no.esquerda, id);
        return buscarRec(no.direita, id);
    }

    /** Percurso em-ordem (in-order): lista chamados em ordem crescente de ID */
    public void listarEmOrdem() {
        if (raiz == null) {
            System.out.println("  Nenhum chamado registrado na árvore.");
            return;
        }
        System.out.println("  Todos os chamados (ordenados por ID):");
        emOrdemRec(raiz);
    }

    private void emOrdemRec(No no) {
        if (no == null) return;
        emOrdemRec(no.esquerda);
        System.out.println(no.dado);
        emOrdemRec(no.direita);
    }

    /** Lista chamados por prioridade ALTA */
    public void listarPorPrioridade(String prioridade) {
        System.out.println("  Chamados com prioridade " + prioridade + ":");
        filtrarPrioridadeRec(raiz, prioridade.toUpperCase());
    }

    private void filtrarPrioridadeRec(No no, String prioridade) {
        if (no == null) return;
        filtrarPrioridadeRec(no.esquerda, prioridade);
        if (no.dado.getPrioridade().equals(prioridade))
            System.out.println(no.dado);
        filtrarPrioridadeRec(no.direita, prioridade);
    }

    public boolean estaVazia() { return raiz == null; }
}
