package suporte.sistema;

import suporte.estruturas.*;
import suporte.modelo.*;
import java.util.Scanner;

public class SistemaSuporteMenu {

    private FilaChamados fila = new FilaChamados();
    private PilhaHistorico pilha = new PilhaHistorico();
    private ListaUsuarios listaUsuarios = new ListaUsuarios();
    private ArvoreChamados arvore = new ArvoreChamados();
    private Scanner sc = new Scanner(System.in);

    public void iniciar() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE SUPORTE TÉCNICO DE INFORMÁTICA  ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        int opcao;
        do {
            exibirMenu();
            opcao = lerInt("Escolha uma opção");
            switch (opcao) {
                case 1 -> menuChamados();
                case 2 -> menuUsuarios();
                case 3 -> menuConsultas();
                case 4 -> menuHistorico();
                case 0 -> System.out.println("\n  Encerrando o sistema. Até logo!");
                default -> System.out.println("  Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("  MENU PRINCIPAL");
        System.out.println("══════════════════════════════════════════════");
        System.out.println("  [1] Gerenciar Chamados");
        System.out.println("  [2] Gerenciar Usuários");
        System.out.println("  [3] Consultas e Busca");
        System.out.println("  [4] Histórico de Atendimentos");
        System.out.println("  [0] Sair");
        System.out.println("══════════════════════════════════════════════");
    }

    // ─── CHAMADOS ─────────────────────────────────────────
    private void menuChamados() {
        int op;
        do {
            System.out.println("\n  ── CHAMADOS ──────────────────────────────");
            System.out.println("  [1] Abrir novo chamado");
            System.out.println("  [2] Atender próximo chamado da fila");
            System.out.println("  [3] Listar fila de espera");
            System.out.println("  [4] Concluir chamado em atendimento");
            System.out.println("  [0] Voltar");
            op = lerInt("Opção");
            switch (op) {
                case 1 -> abrirChamado();
                case 2 -> atenderProximo();
                case 3 -> fila.listar();
                case 4 -> concluirChamado();
            }
        } while (op != 0);
    }

    private void abrirChamado() {
        System.out.println("\n  ── NOVO CHAMADO ──────────────────────────");
        System.out.print("  Título: ");
        String titulo = sc.nextLine().trim();
        System.out.print("  Setor: ");
        String setor = sc.nextLine().trim();
        System.out.println("  Prioridade: [1] ALTA  [2] MEDIA  [3] BAIXA");
        int p = lerInt("  Escolha");
        String prioridade = switch (p) { case 1 -> "ALTA"; case 3 -> "BAIXA"; default -> "MEDIA"; };
        System.out.print("  Descrição: ");
        String desc = sc.nextLine().trim();

        Chamado c = new Chamado(titulo, desc, setor, prioridade);
        fila.enfileirar(c);
        arvore.inserir(c);
        System.out.println("\n  ✔ Chamado #" + c.getId() + " aberto e adicionado à fila!");
        System.out.println("  Posição na fila: " + fila.getTamanho());
    }

    private void atenderProximo() {
        if (fila.estaVazia()) {
            System.out.println("\n  Fila vazia — nenhum chamado aguardando.");
            return;
        }
        System.out.println("\n  Próximo chamado na fila:");
        System.out.println(fila.espiar());
        System.out.print("\n  Nome do técnico responsável: ");
        String tecnico = sc.nextLine().trim();
        Chamado c = fila.desenfileirar();
        c.atender(tecnico);
        System.out.println("\n  ✔ Chamado #" + c.getId() + " em atendimento por " + tecnico);
    }

    private void concluirChamado() {
        int id = lerInt("  ID do chamado a concluir");
        Chamado c = arvore.buscarPorId(id);
        if (c == null) {
            System.out.println("  Chamado não encontrado na árvore.");
            return;
        }
        if (!"EM_ATENDIMENTO".equals(c.getStatus())) {
            System.out.println("  Este chamado não está em atendimento. Status: " + c.getStatus());
            return;
        }
        c.concluir();
        pilha.empilhar(c);
        System.out.println("\n  ✔ Chamado #" + c.getId() + " concluído e registrado no histórico!");
    }

    // ─── USUÁRIOS ─────────────────────────────────────────
    private void menuUsuarios() {
        int op;
        do {
            System.out.println("\n  ── USUÁRIOS (Lista Ligada) ───────────────");
            System.out.println("  [1] Cadastrar usuário");
            System.out.println("  [2] Listar todos os usuários");
            System.out.println("  [3] Buscar usuário por nome");
            System.out.println("  [4] Remover usuário por ID");
            System.out.println("  [0] Voltar");
            op = lerInt("Opção");
            switch (op) {
                case 1 -> cadastrarUsuario();
                case 2 -> listaUsuarios.listar();
                case 3 -> {
                    System.out.print("  Nome: ");
                    String nome = sc.nextLine().trim();
                    Usuario u = listaUsuarios.buscarPorNome(nome);
                    System.out.println(u != null ? u : "  Usuário não encontrado.");
                }
                case 4 -> {
                    int id = lerInt("  ID do usuário");
                    System.out.println(listaUsuarios.remover(id)
                        ? "  Usuário removido." : "  Usuário não encontrado.");
                }
            }
        } while (op != 0);
    }

    private void cadastrarUsuario() {
        System.out.println("\n  ── CADASTRAR USUÁRIO ─────────────────────");
        System.out.print("  Nome: ");
        String nome = sc.nextLine().trim();
        System.out.print("  Setor: ");
        String setor = sc.nextLine().trim();
        System.out.print("  E-mail: ");
        String email = sc.nextLine().trim();
        listaUsuarios.inserir(new Usuario(nome, setor, email));
        System.out.println("  ✔ Usuário " + nome + " cadastrado!");
    }

    // ─── CONSULTAS ────────────────────────────────────────
    private void menuConsultas() {
        int op;
        do {
            System.out.println("\n  ── CONSULTAS (Árvore Binária) ────────────");
            System.out.println("  [1] Buscar chamado por ID");
            System.out.println("  [2] Listar todos os chamados (em-ordem)");
            System.out.println("  [3] Listar chamados por prioridade");
            System.out.println("  [0] Voltar");
            op = lerInt("Opção");
            switch (op) {
                case 1 -> {
                    int id = lerInt("  ID do chamado");
                    Chamado c = arvore.buscarPorId(id);
                    System.out.println(c != null ? c : "  Chamado #" + id + " não encontrado.");
                }
                case 2 -> arvore.listarEmOrdem();
                case 3 -> {
                    System.out.println("  [1] ALTA  [2] MEDIA  [3] BAIXA");
                    int p = lerInt("  Prioridade");
                    String pr = switch (p) { case 1 -> "ALTA"; case 3 -> "BAIXA"; default -> "MEDIA"; };
                    arvore.listarPorPrioridade(pr);
                }
            }
        } while (op != 0);
    }

    // ─── HISTÓRICO ────────────────────────────────────────
    private void menuHistorico() {
        int op;
        do {
            System.out.println("\n  ── HISTÓRICO (Pilha) ─────────────────────");
            System.out.println("  [1] Ver último chamado concluído (topo)");
            System.out.println("  [2] Listar todo o histórico");
            System.out.println("  [3] Total de chamados concluídos");
            System.out.println("  [0] Voltar");
            op = lerInt("Opção");
            switch (op) {
                case 1 -> {
                    Chamado topo = pilha.verTopo();
                    System.out.println(topo != null ? topo : "  Histórico vazio.");
                }
                case 2 -> pilha.listar();
                case 3 -> System.out.println("  Total concluídos: " + pilha.getTamanho());
            }
        } while (op != 0);
    }

    // ─── UTILITÁRIO ───────────────────────────────────────
    private int lerInt(String msg) {
        System.out.print("  " + msg + ": ");
        try {
            int v = Integer.parseInt(sc.nextLine().trim());
            return v;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
