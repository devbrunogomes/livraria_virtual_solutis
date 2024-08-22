
package solutis.livrariavirtual_solutis;

import java.util.Scanner;

/**
    Estrutura das classes
 * 
 * Atributos
 * Construtor
 * Getters e Setters
 * Métodos
 * Overrides
 * 
 */

public class LivrariaVirtual_Solutis {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        LivrariaVirtual livrariaTeste = new LivrariaVirtual();
        LivroEletronico livroTeste = new LivroEletronico();
        
        System.out.println("""
                           1 - Cadastro de Livro
                           2 - Realizar Venda
                           3 - Listar Livros
                           4 - Listar Vendas
                           5 - Encerrar Programa                        
                           """);
        int resposta = scan.nextInt();
        
        switch (resposta) {
            case 1:
                livrariaTeste.cadastrarLivro();
                break;
            default:
                throw new AssertionError();
        }
    }
}
