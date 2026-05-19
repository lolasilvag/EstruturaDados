package suporte.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Chamado {
    private static int contadorId = 1;

    private int id;
    private String titulo;
    private String descricao;
    private String setor;
    private String prioridade; // ALTA, MEDIA, BAIXA
    private String status;     // AGUARDANDO, EM_ATENDIMENTO, CONCLUIDO
    private LocalDateTime dataAbertura;
    private LocalDateTime dataAtendimento;
    private String tecnicoResponsavel;

    public Chamado(String titulo, String descricao, String setor, String prioridade) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.descricao = descricao;
        this.setor = setor;
        this.prioridade = prioridade.toUpperCase();
        this.status = "AGUARDANDO";
        this.dataAbertura = LocalDateTime.now();
    }

    public void atender(String tecnico) {
        this.status = "EM_ATENDIMENTO";
        this.tecnicoResponsavel = tecnico;
        this.dataAtendimento = LocalDateTime.now();
    }

    public void concluir() {
        this.status = "CONCLUIDO";
    }

    public int getPrioridadeValor() {
        return switch (prioridade) {
            case "ALTA"  -> 3;
            case "MEDIA" -> 2;
            default      -> 1;
        };
    }

    // Getters
    public int getId()                   { return id; }
    public String getTitulo()            { return titulo; }
    public String getDescricao()         { return descricao; }
    public String getSetor()             { return setor; }
    public String getPrioridade()        { return prioridade; }
    public String getStatus()            { return status; }
    public String getTecnicoResponsavel(){ return tecnicoResponsavel; }
    public LocalDateTime getDataAbertura(){ return dataAbertura; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format(
            "╔══════════════════════════════════════════╗\n" +
            "  ID: #%04d | Status: %-15s\n" +
            "  Título: %s\n" +
            "  Setor: %-20s Prioridade: %s\n" +
            "  Abertura: %s\n" +
            (tecnicoResponsavel != null ? "  Técnico: " + tecnicoResponsavel + "\n" : "") +
            "  Descrição: %s\n" +
            "╚══════════════════════════════════════════╝",
            id, status, titulo, setor, prioridade,
            dataAbertura.format(fmt), descricao
        );
    }
}
