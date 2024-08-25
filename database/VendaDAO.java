package solutis.livrariavirtual_solutis.database;

import solutis.livrariavirtual_solutis.Venda;
import solutis.livrariavirtual_solutis.model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

    private Connection conn;

    public VendaDAO() throws SQLException {
        this.conn = DatabaseConnection.getConnection(); 
    }

    public void adicionarVenda(Venda venda) {
        String sql = "INSERT INTO vendas (numero, cliente, valor) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venda.getNumero());
            stmt.setString(2, venda.getCliente());
            stmt.setFloat(3, venda.getValor());

            stmt.executeUpdate();

            for (Livro livro : venda.getLivrosASeremVendidos()) {
                adicionarLivroVendido(venda.getNumero(), livro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
          
        }
    }

    private void adicionarLivroVendido(int vendaNumero, Livro livro) {
        String sql = "INSERT INTO livros_vendidos (venda_numero, livro_id) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vendaNumero);
            stmt.setLong(2, livro.getId()); 

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
       
        }
    }

    public Venda buscarVendaPorNumero(int numero) {
        Venda venda = null;
        String sql = "SELECT * FROM vendas WHERE numero = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    venda = new Venda();
                    venda.setNumero(rs.getInt("numero"));
                    venda.setCliente(rs.getString("cliente"));
                    venda.setValor(rs.getFloat("valor"));
                    venda.setLivrosASeremVendidos(buscarLivrosVendidos(numero));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
           
        }

        return venda;
    }

    private List<Livro> buscarLivrosVendidos(int vendaNumero) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros_vendidos WHERE venda_numero = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vendaNumero);
            try (ResultSet rs = stmt.executeQuery()) {
                LivroDAO livroDAO = new LivroDAO(); 
                while (rs.next()) {
                    int livroId = rs.getInt("livro_id");
                    Livro livro = livroDAO.buscarLivroPorId(livroId);
                    livros.add(livro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }

        return livros;
    }

    public List<Venda> listarVendas() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM vendas";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setNumero(rs.getInt("numero"));
                venda.setCliente(rs.getString("cliente"));
                venda.setValor(rs.getFloat("valor"));
                venda.setLivrosASeremVendidos(buscarLivrosVendidos(venda.getNumero()));
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
           
        }

        return vendas;
    }
}
