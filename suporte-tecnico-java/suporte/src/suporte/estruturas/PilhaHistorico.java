package suporte.estruturas;

import suporte.modelo.Chamado;

/**
 * PILHA (Stack) — estrutura LIFO
 * Usada para registrar o histórico de chamados atendidos/concluídos.
 * O último chamado concluído fica no topo da pilha.
 */
public class PilhaHistorico {

    private static class No {
        Chamado dado;
        No abaixo;
        No(Chamado dado) { this.dado = dado; }
    }

    private No topo;
    private int tamanho;

    public void empilhar(Chamado chamado) {
        No novo = new No(chamado);
        novo.abaixo = topo;
        topo = novo;
        tamanho++;
    }

    public Chamado desempilhar() {
        if (estaVazia()) return null;
        Chamado dado = topo.dado;
        topo = topo.abaixo;
        tamanho--;
        return dado;
    }

    public Chamado verTopo() {
        return estaVazia() ? null : topo.dado;
    }

    public boolean estaVazia() { return topo == null; }
    public int getTamanho()    { return tamanho; }

    public void listar() {
        if (estaVazia()) {
            System.out.println("  Histórico vazio — nenhum chamado concluído ainda.");
            return;
        }
        System.out.println("  Histórico de chamados concluídos (mais recente primeiro):");
        No atual = topo;
        int pos = 1;
        while (atual != null) {
            System.out.println("  [" + pos++ + "] " + atual.dado);
            atual = atual.abaixo;
        }
    }
}
