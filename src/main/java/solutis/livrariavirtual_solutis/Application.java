package solutis.livrariavirtual_solutis;

import java.sql.Connection;
import javax.swing.JOptionPane;
import solutis.livrariavirtual_solutis.model.DAO;
import solutis.livrariavirtual_solutis.model.LivroDAO;
import solutis.livrariavirtual_solutis.model.VendaDAO;

/**
 * Estrutura das classes
 *
 * Atributos Construtor Getters e Setters Métodos Overrides
 *
 */

/** TO DO
* Em cadastro de Livro, atualizar o estoque
* Sobreescrever metodos toString das classes
*/
public class Application {

    public static void main(String[] args) {
        DAO dao = new DAO();
        Connection connection = dao.conectar();
        
        if (connection == null) {
            System.out.println("Não foi possível conectar ao banco de dados.");
            return;
        }
        
        VendaDAO vendaDAO = new VendaDAO(connection);
        LivroDAO livroDAO = new LivroDAO(connection);

        LivrariaVirtual livrariaTeste = new LivrariaVirtual(vendaDAO, livroDAO);

        String[] opcoes = {"Cadastrar livro", "Realizar uma venda", "Listar livros", "Listar vendas", "Sair"};
        int escolhaUsuario;

        do {
           escolhaUsuario = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Menu Livraria Virtual", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
         
            switch (escolhaUsuario) {
                case 0:
                    livrariaTeste.cadastrarLivro();
                    break;
                case 1:
                    livrariaTeste.realizarVenda();
                    break;
                case 2:
                    livrariaTeste.listarLivros();
                    break;
                case 3:
                    livrariaTeste.listarVendas();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Saindo do programa...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        } while (escolhaUsuario != 4);
    }
}
