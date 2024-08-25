package solutis.livrariavirtual_solutis;

import javax.swing.JOptionPane;

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

        LivrariaVirtual livrariaTeste = new LivrariaVirtual();

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
