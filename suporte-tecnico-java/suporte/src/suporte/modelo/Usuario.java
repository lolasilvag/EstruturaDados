package suporte.modelo;

public class Usuario {
    private int id;
    private static int contadorId = 1;
    private String nome;
    private String setor;
    private String email;

    public Usuario(String nome, String setor, String email) {
        this.id = contadorId++;
        this.nome = nome;
        this.setor = setor;
        this.email = email;
    }

    public int getId()      { return id; }
    public String getNome() { return nome; }
    public String getSetor(){ return setor; }
    public String getEmail(){ return email; }

    @Override
    public String toString() {
        return String.format("  [#%d] %s | Setor: %s | Email: %s", id, nome, setor, email);
    }
}
