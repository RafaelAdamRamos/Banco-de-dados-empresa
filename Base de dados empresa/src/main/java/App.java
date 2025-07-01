import dao.*;
import modelo.*;
import util.Conexao;

import java.sql.Connection;
import java.util.List;

public class App {
    public static void main(String[] args) {

        // Conectando ao banco
        Connection conn = Conexao.conectar();
        if (conn == null) {
            System.out.println("Erro: conexão com o banco falhou.");
            return;
        }

        // Criando os DAOs
        PessoaDAO pessoaDAO = new PessoaDAO(conn);
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(conn);
        ProjetoDAO projetoDAO = new ProjetoDAO(conn);

        // ========== EXEMPLO 1: Cadastrar pessoa ==========
        Pessoa p = new Pessoa();
        p.setNome("João da Silva");
        p.setEmail("joao@email.com");
        pessoaDAO.inserir(p); // Pessoa será inserida

        // ========== EXEMPLO 2: Cadastrar funcionário ==========
        Funcionario f = new Funcionario();
        f.setId(p.getId()); // Agora pega o ID certo
        f.setMatricula("F001");
        f.setDepartamento("TI");
        funcionarioDAO.inserir(f);

        // ========== EXEMPLO 3: Cadastrar projeto ==========
        Projeto proj = new Projeto();
        proj.setNome("Sistema Interno");
        proj.setDescricao("Desenvolvimento do novo sistema da empresa.");
        proj.setIdFuncionario(13); // id_pessoa do João, agora funcionário
        projetoDAO.inserir(proj);

        // ========== EXEMPLO 4: Listar pessoas ==========
        System.out.println("\nPESSOAS:");
        List<Pessoa> pessoas = pessoaDAO.listarTodos();
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.getId() + " - " + pessoa.getNome() + " - " + pessoa.getEmail());
        }

        // ========== EXEMPLO 5: Listar funcionários ==========
        System.out.println("\nFUNCIONÁRIOS:");
        List<Funcionario> funcionarios = funcionarioDAO.listarTodos();
        for (Funcionario func : funcionarios) {
            System.out.println(func.getId() + " - " + func.getMatricula() + " - " + func.getDepartamento());
        }

        // ========== EXEMPLO 6: Listar projetos ==========
        System.out.println("\nPROJETOS:");
        List<Projeto> projetos = projetoDAO.listarTodos();
        for (Projeto projeto : projetos) {
            System.out.println(projeto.getId() + " - " + projeto.getNome() + " - " + projeto.getDescricao());
        }
    }}
