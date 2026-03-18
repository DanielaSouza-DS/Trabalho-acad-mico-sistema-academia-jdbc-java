package dados;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO implements Dao<Aluno> {
    private Connection con = null;

    public AlunoDAO() throws SQLException {
        con = DBConnection.getConnection();
        String sqlCreateTable = "CREATE TABLE ALUNO (ID INT AUTO_INCREMENT, NOME VARCHAR(50), CPF VARCHAR(15), EMAIL VARCHAR(50), DataNascimento VARCHAR(15), PESO INT, ALTURA INT);";
        PreparedStatement stmt = con.prepareStatement(sqlCreateTable);

        stmt.execute();
    }

    @Override
    public void salvar(Aluno aluno) throws SQLException {
        con = DBConnection.getConnection();
        String sql = "INSERT INTO ALUNO (Nome, CPF, DataNascimento, Email, Peso, Altura) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, aluno.getNome());
        stmt.setString(2, aluno.getCpf());
        stmt.setString(3, aluno.getDataNascimento());
        stmt.setString(4, aluno.getEmail());
        stmt.setDouble(5, aluno.getPeso());
        stmt.setDouble(6, aluno.getAltura());

        stmt.execute();
        JOptionPane.showMessageDialog(null, "Aluno salvo com sucesso!");

    }

    @Override
    public List<Aluno> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> lista = new ArrayList<>();

        String sql = "SELECT * FROM Alunos";

        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getInt("ID"),
                        rs.getString("Nome"),
                        rs.getString("CPF"),
                        rs.getString("DataNascimento"),
                        rs.getString("Email"),
                        rs.getDouble("Peso"),
                        rs.getDouble("Altura")
                );
                lista.add(aluno);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar alunos: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return lista;
    }

    @Override
    public void alterar(int id, Aluno aluno) {
        String sql = "UPDATE Alunos SET Nome=?, CPF=?, DataNascimento=?, Email=?, Peso=?, Altura=? WHERE ID=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getDataNascimento());
            stmt.setString(4, aluno.getEmail());
            stmt.setDouble(5, aluno.getPeso());
            stmt.setDouble(6, aluno.getAltura());
            stmt.setInt(7, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(null, "Aluno alterado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar aluno: " + e.getMessage());
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM Alunos WHERE ID=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(null, "Aluno deletado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar aluno: " + e.getMessage());
        }
    }
}
