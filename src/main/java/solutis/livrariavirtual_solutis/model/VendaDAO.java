package solutis.livrariavirtual_solutis.model;

import solutis.livrariavirtual_solutis.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import solutis.livrariavirtual_solutis.Livro;

public class VendaDAO {

    private Connection conn;

    public VendaDAO(Connection conn) {
        this.conn = conn; 
    }

    public void adicionarVenda(Venda venda) {
        String sqlVenda = "INSERT INTO Venda (id, cliente, valor) VALUES (?, ?, ?)";
       

        try (PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS)) {
            stmtVenda.setLong(1, venda.getNumero());
            stmtVenda.setString(2, venda.getCliente());
            stmtVenda.setFloat(3, venda.getValor()); 

            int rowsAffected = stmtVenda.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmtVenda.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long vendaId = generatedKeys.getLong(1);
                        for (Livro livro : venda.getLivrosASeremVendidos()) {
                            adicionarLivroVendido(vendaId, livro);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionarLivroVendido(long vendaId, Livro livro) {
        String sql = "INSERT INTO LivrosVendidos (venda_id, livro_id) VALUES (?, ?)"; 
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, vendaId);
            stmt.setLong(2, livro.getId());


            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Venda> listarVendas() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM Venda";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setNumero(rs.getLong("id"));
                venda.setCliente(rs.getString("cliente"));
                venda.setValor(rs.getFloat("valor"));
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        for (Venda venda : vendas) {
            System.out.println("ID: " + venda.getNumero());
            System.out.println("Cliente: " + venda.getCliente());
            System.out.println("Valor: " + venda.getValor());
            System.out.println();
            
        }

        return vendas;
    }
}
