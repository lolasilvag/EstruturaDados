package suporte.estruturas;

import suporte.modelo.Usuario;

/**
 * LISTA LIGADA (Linked List)
 * Usada para armazenar os usuários/solicitantes cadastrados no sistema.
 * Permite inserção, remoção e busca por ID ou nome.
 */
public class ListaUsuarios {

    private static class No {
        Usuario dado;
        No proximo;
        No(Usuario dado) { this.dado = dado; }
    }

    private No cabeca;
    private int tamanho;

    public void inserir(Usuario usuario) {
        No novo = new No(usuario);
        if (cabeca == null) {
            cabeca = novo;
        } else {
            No atual = cabeca;
            while (atual.proximo != null) atual = atual.proximo;
            atual.proximo = novo;
        }
        tamanho++;
    }

    public boolean remover(int id) {
        if (cabeca == null) return false;
        if (cabeca.dado.getId() == id) {
            cabeca = cabeca.proximo;
            tamanho--;
            return true;
        }
        No atual = cabeca;
        while (atual.proximo != null) {
            if (atual.proximo.dado.getId() == id) {
                atual.proximo = atual.proximo.proximo;
                tamanho--;
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public Usuario buscarPorId(int id) {
        No atual = cabeca;
        while (atual != null) {
            if (atual.dado.getId() == id) return atual.dado;
            atual = atual.proximo;
        }
        return null;
    }

    public Usuario buscarPorNome(String nome) {
        No atual = cabeca;
        while (atual != null) {
            if (atual.dado.getNome().equalsIgnoreCase(nome)) return atual.dado;
            atual = atual.proximo;
        }
        return null;
    }

    public boolean estaVazia() { return cabeca == null; }
    public int getTamanho()    { return tamanho; }

    public void listar() {
        if (estaVazia()) {
            System.out.println("  Nenhum usuário cadastrado.");
            return;
        }
        System.out.println("  Usuários cadastrados:");
        No atual = cabeca;
        while (atual != null) {
            System.out.println(atual.dado);
            atual = atual.proximo;
        }
    }
}
