package dao;

import modelo.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    private Connection conexao;

    public FuncionarioDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Regra 1: Só cadastra se o id existir
    public void inserir(Funcionario funcionario) {
        if (!pessoaExiste(funcionario.getId())) {
            System.out.println("Erro: Pessoa com ID " + funcionario.getId() + " não existe.");
            return;
        }

        if (!funcionario.getMatricula().matches("F\\d{3}")) {
            System.out.println("Erro: Matrícula inválida. Use o formato F000.");
            return;
        }

        String sql = "INSERT INTO funcionario (id, matricula, departamento) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, funcionario.getId());
            stmt.setString(2, funcionario.getMatricula());
            stmt.setString(3, funcionario.getDepartamento());
            stmt.executeUpdate();
            System.out.println("Funcionário cadastrado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir funcionário: " + e.getMessage());
        }
    }

    public Funcionario buscarPorId(int id) {
        String sql = "SELECT * FROM funcionario WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setMatricula(rs.getString("matricula"));
                f.setDepartamento(rs.getString("departamento"));
                return f;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionário: " + e.getMessage());
        }
        return null;
    }

    public List<Funcionario> listarTodos() {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionario";
        try (Statement stmt = conexao.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id"));
                f.setMatricula(rs.getString("matricula"));
                f.setDepartamento(rs.getString("departamento"));
                lista.add(f);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários: " + e.getMessage());
        }
        return lista;
    }

    // Regra 3: Não pode deletar funcionário com projeto vinculado
    public void deletar(int id) {
        if (funcionarioTemProjetos(id)) {
            System.out.println("Erro: Funcionário vinculado a projeto. Não pode ser excluído.");
            return;
        }

        String sql = "DELETE FROM funcionario WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Funcionário excluído com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar funcionário: " + e.getMessage());
        }
    }

    public void atualizar(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET matricula = ?, departamento = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getMatricula());
            stmt.setString(2, funcionario.getDepartamento());
            stmt.setInt(3, funcionario.getId());
            stmt.executeUpdate();
            System.out.println("Funcionário atualizado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    // Verifica se a pessoa existe na tabela pessoa
    private boolean pessoaExiste(int idPessoa) {
        String sql = "SELECT 1 FROM pessoa WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idPessoa);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    // Verifica se o funcionário está vinculado a algum projeto
    private boolean funcionarioTemProjetos(int idFuncionario) {
        String sql = "SELECT 1 FROM projeto WHERE id_funcionario = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idFuncionario);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    //Gera uma matricula automaticamente no modelo F(número de três dígitos) ex: F001
    public String gerarProximaMatricula() {
        String sql = "SELECT MAX(CAST(SUBSTRING(matricula, 2) AS UNSIGNED)) AS max_num FROM funcionario";
        try (Statement stmt = conexao.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                int ultimoNumero = rs.getInt("max_num");
                int proximo = ultimoNumero + 1;
                return String.format("F%03d", proximo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao gerar matrícula: " + e.getMessage());
        }
        return "F001"; // Caso não haja nenhum funcionário ainda
    }
}
