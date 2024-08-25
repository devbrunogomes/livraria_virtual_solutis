package solutis.livrariavirtual_solutis.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import solutis.livrariavirtual_solutis.model.Livro;
import solutis.livrariavirtual_solutis.model.LivroEletronico;
import solutis.livrariavirtual_solutis.model.LivroImpresso;

public class LivroDAO {

    public void cadastrarLivro(Livro livro) {
        String sql = "INSERT INTO Livro (titulo, autores, editora, preco, tipo) VALUES (?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();  
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutores());
            pstmt.setString(3, livro.getEditora());
            pstmt.setFloat(4, livro.getPreco());
            pstmt.setString(5, livro instanceof LivroImpresso ? "impresso" : "eletronico");
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livro cadastrado com sucesso!");
            } else {
                System.out.println("Falha ao cadastrar o livro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM Livro";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Livro livro;
                String tipo = rs.getString("tipo");

                if (tipo.equals("impresso")) {
                    livro = new LivroImpresso(
                        rs.getLong("id"),
                        rs.getFloat("frete"),
                        rs.getInt("estoque"),
                        rs.getString("titulo"),
                        rs.getString("autores"),
                        rs.getString("editora"),
                        rs.getFloat("preco")
                    );
                } else {
                    livro = new LivroEletronico(
                        rs.getLong("id"),
                        rs.getInt("tamanho"),
                        rs.getString("titulo"),
                        rs.getString("autores"),
                        rs.getString("editora"),
                        rs.getFloat("preco")
                    );
                }

                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }

    public void atualizarLivro(Livro livro) {
        String sql = "UPDATE Livro SET titulo = ?, autores = ?, editora = ?, preco = ?, tipo = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutores());
            pstmt.setString(3, livro.getEditora());
            pstmt.setFloat(4, livro.getPreco());
            pstmt.setString(5, livro instanceof LivroImpresso ? "impresso" : "eletronico");
            pstmt.setLong(6, livro.getId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livro atualizado com sucesso!");
            } else {
                System.out.println("Falha ao atualizar o livro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarLivro(long id) {
        String sql = "DELETE FROM Livro WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livro deletado com sucesso!");
            } else {
                System.out.println("Falha ao deletar o livro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Livro buscarLivroPorId(long id) {
        String sql = "SELECT * FROM Livro WHERE id = ?";
        Livro livro = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("tipo");

                if (tipo.equals("impresso")) {
                    livro = new LivroImpresso(
                        rs.getLong("id"),
                        rs.getFloat("frete"),
                        rs.getInt("estoque"),
                        rs.getString("titulo"),
                        rs.getString("autores"),
                        rs.getString("editora"),
                        rs.getFloat("preco")
                    );
                } else if (tipo.equals("eletronico")) {
                    livro = new LivroEletronico(
                        rs.getLong("id"),
                        rs.getInt("tamanho"),
                        rs.getString("titulo"),
                        rs.getString("autores"),
                        rs.getString("editora"),
                        rs.getFloat("preco")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livro;
    }
}
