package dao;

import modelo.Projeto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {

    private Connection conexao;

    public ProjetoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Regra 2: Só cria projeto se funcionário existir
    public void inserir(Projeto projeto) {
        if (!funcionarioExiste(projeto.getIdFuncionario())) {
            System.out.println("Erro: Funcionário com ID " + projeto.getIdFuncionario() + " não existe.");
            return;
        }

        String sql = "INSERT INTO projeto (nome_projeto, descricao, id_funcionario) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setInt(3, projeto.getIdFuncionario());
            stmt.executeUpdate();
            System.out.println("Projeto criado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir projeto: " + e.getMessage());
        }
    }

    public Projeto buscarPorId(int id) {
        String sql = "SELECT * FROM projeto WHERE id_projeto = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Projeto p = new Projeto();
                p.setId(rs.getInt("id_projeto"));
                p.setNome(rs.getString("nome_projeto"));
                p.setDescricao(rs.getString("descricao"));
                p.setIdFuncionario(rs.getInt("id_funcionario"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar projeto: " + e.getMessage());
        }
        return null;
    }

    public List<Projeto> listarTodos() {
        List<Projeto> lista = new ArrayList<>();
        String sql = "SELECT * FROM projeto";
        try (Statement stmt = conexao.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Projeto p = new Projeto();
                p.setId(rs.getInt("id_projeto"));
                p.setNome(rs.getString("nome_projeto"));
                p.setDescricao(rs.getString("descricao"));
                p.setIdFuncionario(rs.getInt("id_funcionario"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar projetos: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Projeto projeto) {
        String sql = "UPDATE projeto SET nome_projeto = ?, descricao = ?, id_funcionario = ? WHERE id_projeto = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setInt(3, projeto.getIdFuncionario());
            stmt.setInt(4, projeto.getId());
            stmt.executeUpdate();
            System.out.println("Projeto atualizado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar projeto: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM projeto WHERE id_projeto = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Projeto deletado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar projeto: " + e.getMessage());
        }
    }

    private boolean funcionarioExiste(int idFuncionario) {
        String sql = "SELECT 1 FROM funcionario WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idFuncionario);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
}
