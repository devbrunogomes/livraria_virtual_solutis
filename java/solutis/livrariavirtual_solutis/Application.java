package solutis.livrariavirtual_solutis;

import solutis.livrariavirtual_solutis.model.DAO;
import solutis.livrariavirtual_solutis.model.VendaDAO;
import solutis.livrariavirtual_solutis.model.LivroDAO;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);

        DAO dao = new DAO();
        Connection connection = dao.conectar();

        if (connection == null) {
            System.out.println("Não foi possível conectar ao banco de dados.");
            return;
        }

       
        VendaDAO vendaDAO = new VendaDAO(connection);
        LivroDAO livroDAO = new LivroDAO(connection);

        LivrariaVirtual livrariaTeste = new LivrariaVirtual(vendaDAO, livroDAO);

        int escolhaUsuario = 0;

        do {
            System.out.println("""
                    ---- Livraria SQUAD 1 ----
                        == MENU PRINCIPAL ==
                    1 - Cadastro de Livro
                    2 - Realizar Venda
                    3 - Listar Livros
                    4 - Listar Vendas
                    5 - Encerrar Programa
                    """);
            escolhaUsuario = scan.nextInt();

            switch (escolhaUsuario) {
                case 1:
                    livrariaTeste.cadastrarLivro();
                    break;
                case 2:
                    livrariaTeste.realizarVenda();
                    break;
                case 3:
                    livrariaTeste.listarLivros();
                    break;
                case 4:
                    livrariaTeste.listarVendas();
                    break;
                case 5:
                    System.out.println("Encerrando Programa!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (escolhaUsuario != 5);

        scan.close();
    }
}
