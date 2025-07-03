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

        //Cadastrar pessoa
        Pessoa p = new Pessoa();
        p.setNome("João da Silva"); // Inserir info
        p.setEmail("joao@email.com"); // Inserir info
        pessoaDAO.inserir(p);

        //Cadastrar funcionário
        Funcionario f = new Funcionario();
        f.setId(p.getId());
        f.setMatricula(funcionarioDAO.gerarProximaMatricula()); //Matrícula é gerada automaticamente no modelo F(número de três dígitos) ex: F001
        f.setDepartamento("TI"); // Inserir info
        funcionarioDAO.inserir(f);

        //Cadastrar projeto
        Projeto proj = new Projeto();
        proj.setNome("Sistema Interno"); // Inserir info
        proj.setDescricao("Desenvolvimento do novo sistema da empresa."); // Inserir info
        proj.setIdFuncionario(f.getId());
        projetoDAO.inserir(proj);

        // Listar pessoas
        System.out.println("\nPESSOAS:");
        List<Pessoa> pessoas = pessoaDAO.listarTodos();
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.getId() + " - " + pessoa.getNome() + " - " + pessoa.getEmail());
        }

        //Listar funcionários
        System.out.println("\nFUNCIONÁRIOS:");
        List<Funcionario> funcionarios = funcionarioDAO.listarTodos();
        for (Funcionario func : funcionarios) {
            System.out.println(func.getId() + " - " + func.getMatricula() + " - " + func.getDepartamento());
        }

        //Listar projetos
        System.out.println("\nPROJETOS:");
        List<Projeto> projetos = projetoDAO.listarTodos();
        for (Projeto projeto : projetos) {
            System.out.println(projeto.getId() + " - " + projeto.getNome() + " - " + projeto.getDescricao());
        }
    }
}
