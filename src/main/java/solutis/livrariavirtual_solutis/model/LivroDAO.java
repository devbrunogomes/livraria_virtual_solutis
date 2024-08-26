package solutis.livrariavirtual_solutis.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import solutis.livrariavirtual_solutis.Livro;
import solutis.livrariavirtual_solutis.LivroEletronico;
import solutis.livrariavirtual_solutis.LivroImpresso;

public class LivroDAO {

    private Connection conn;

    public LivroDAO(Connection conn) {
        this.conn = conn;
    }

    public void cadastrarLivroImpresso(LivroImpresso livro) {
        String sql = "INSERT INTO Livro (titulo, autores, editora, preco, tipo, estoque, tamanho, frete) VALUES (?,?,?,?,?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutores());
            pstmt.setString(3, livro.getEditora());
            pstmt.setFloat(4, livro.getPreco());
            pstmt.setString(5, livro instanceof LivroImpresso ? "impresso" : "eletronico");
            pstmt.setInt(6, livro.getEstoque());
            pstmt.setInt(7, 0);
            pstmt.setFloat(8, livro.getFrete());

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

    public void cadastrarLivroEletronico(LivroEletronico livro) {
        String sql = "INSERT INTO Livro (titulo, autores, editora, preco, tipo, estoque, tamanho, frete) VALUES (?,?,?,?,?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutores());
            pstmt.setString(3, livro.getEditora());
            pstmt.setFloat(4, livro.getPreco());
            pstmt.setString(5, livro instanceof LivroEletronico ? "eletronico" : "impresso");
            pstmt.setInt(6, 0);
            pstmt.setInt(7, livro.getTamanho());
            pstmt.setFloat(8, 0);

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

    public List<Livro> listarLivros(String tipoFiltro) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM  Livro WHERE tipo = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tipoFiltro);
            ResultSet rs = pstmt.executeQuery();

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

    public List<Livro> listarTodosLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM Livro";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Livro livro;
                String tipo = rs.getString("tipo");

                // Verifica o tipo de livro e instancia a classe correta
                if (tipo.equals("impresso")) {
                    livro = new LivroImpresso(
                            rs.getLong("id"),
                            0,
                            0,
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
        for (Livro livro : livros) {
            System.out.println(livro);
        }
        return livros;
    }

    /* public void atualizarLivro(Livro livro) {
        String sql = "UPDATE Livro SET titulo = ?, autores = ?, editora = ?, preco = ?, tipo = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

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

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

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

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

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
    }*/
}
