package suporte.estruturas;

import suporte.modelo.Chamado;

/**
 * FILA (Queue) — estrutura FIFO
 * Usada para organizar os chamados aguardando atendimento.
 * O primeiro chamado aberto é o primeiro a ser atendido.
 */
public class FilaChamados {

    private static class No {
        Chamado dado;
        No proximo;
        No(Chamado dado) { this.dado = dado; }
    }

    private No inicio;
    private No fim;
    private int tamanho;

    public void enfileirar(Chamado chamado) {
        No novo = new No(chamado);
        if (fim != null) fim.proximo = novo;
        fim = novo;
        if (inicio == null) inicio = novo;
        tamanho++;
    }

    public Chamado desenfileirar() {
        if (estaVazia()) return null;
        Chamado dado = inicio.dado;
        inicio = inicio.proximo;
        if (inicio == null) fim = null;
        tamanho--;
        return dado;
    }

    public Chamado espiar() {
        return estaVazia() ? null : inicio.dado;
    }

    public boolean estaVazia() { return inicio == null; }
    public int getTamanho()    { return tamanho; }

    public void listar() {
        if (estaVazia()) {
            System.out.println("  Fila vazia — nenhum chamado aguardando.");
            return;
        }
        System.out.println("  Chamados na fila (ordem de atendimento):");
        No atual = inicio;
        int pos = 1;
        while (atual != null) {
            System.out.println("  Posição " + pos++ + ":");
            System.out.println(atual.dado);
            atual = atual.proximo;
        }
    }
}
