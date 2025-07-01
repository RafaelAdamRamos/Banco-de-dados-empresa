package modelo;

public class Projeto {
    private int id;                // id_projeto
    private String nome;          // nome_projeto
    private String descricao;     // descrição do projeto
    private int idFuncionario;    // id_funcionario (referência à tabela funcionario)

    // Construtor vazio
    public Projeto() {}

    // Construtor completo (opcional)
    public Projeto(int id, String nome, String descricao, int idFuncionario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.idFuncionario = idFuncionario;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    // toString() (opcional para debug ou testes)
    @Override
    public String toString() {
        return "Projeto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", idFuncionario=" + idFuncionario +
                '}';
    }
}
