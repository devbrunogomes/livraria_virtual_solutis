package solutis.livrariavirtual_solutis;

import java.util.Scanner;

/**
 * Estrutura das classes
 *
 * Atributos Construtor Getters e Setters Métodos Overrides
 *
 */

/** TO DO
* Listar Livros Impressos
* Listar Livros Eletronicos
* Listar Vendas
* Em realizar Vendas, iterar sobre o array criado de livros
* Em realizar Vendas, incluir a venda de livros eletronicos
* Em cadastro de Livro, atualizar o estoque
* Rever o atributo estático de contagem de vendas
* Sobreescrever metodos toString das classes
*/
public class Application {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        LivrariaVirtual livrariaTeste = new LivrariaVirtual();

        int escolhaUsuario = 0;

        do {
            //Programa irá se repetir, até o usuario escolher o 5
            
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
                    throw new AssertionError();
            }
        } while (escolhaUsuario != 5);
    }
}
