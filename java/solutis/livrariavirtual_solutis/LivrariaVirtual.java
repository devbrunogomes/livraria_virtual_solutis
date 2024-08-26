package solutis.livrariavirtual_solutis;

import solutis.livrariavirtual_solutis.model.*;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LivrariaVirtual {

    private LivroDAO livroDAO;
    private VendaDAO vendaDAO;

    private final Scanner scan = new Scanner(System.in);
    private final ArrayList<Livro> livrosCadastrados = new ArrayList<>();
    private final ArrayList<Venda> vendasRealizadas = new ArrayList<>();

    // Limites para cadastros e vendas
    private static final int MAX_IMPRESSOS = 10;
    private static final int MAX_ELETRONICOS = 20;
    private static final int MAX_VENDAS = 50;
    
    private int numImpressos = 0;
    private int numEletronicos = 0;
    private int numVendas = 0;
    private int index = 0;


    // Construtor
    public LivrariaVirtual(VendaDAO vendaDAO, LivroDAO livroDAO) {
        this.livroDAO = livroDAO;
        this.vendaDAO = vendaDAO;
    }

    // Métodos para cadastro de livros
    public void cadastrarLivro() throws SQLException {
        System.out.println("""
                           Processo de Cadastro
                           1 - Livro Impresso
                           2 - Livro Eletrônico
                           3 - Ambos
                           """);
        int resposta = scan.nextInt();
        switch (resposta) {
            case 1 -> cadastrarLivroImpresso();
            case 2 -> cadastrarLivroEletronico();
            case 3 -> cadastroAmbos();
            default -> throw new AssertionError("Opção inválida");
        }
    }

    private void cadastroAmbos() throws SQLException {
      
        System.out.println("Conteúdo comum a ambos os tipos:");
        scan.nextLine();  // Limpar o buffer
        System.out.print("-> Título: ");
        String titulo = scan.nextLine();
        System.out.print("-> Autores: ");
        String autores = scan.nextLine();
        System.out.print("-> Editora: ");
        String editora = scan.nextLine();

        // Dados específicos para livro impresso
        System.out.println("---- Para livro Impresso ----");
        System.out.print("-> Frete: ");
        float frete = scan.nextFloat();
        System.out.print("-> Estoque: ");
        int estoque = scan.nextInt();
        System.out.print("-> Preço livro impresso: ");
        float precoImpresso = scan.nextFloat();

        // Dados específicos para livro eletrônico
        System.out.println("---- Para livro eletrônico ----");
        System.out.print("-> Preço livro eletrônico: ");
        float precoEletronico = scan.nextFloat();
        System.out.print("-> Tamanho (MB): ");
        int tamanho = scan.nextInt();

       
        LivroImpresso livroImpresso = new LivroImpresso( index, frete, estoque, titulo, autores, editora, precoImpresso);
     
        LivroEletronico livroEletronico = new LivroEletronico(index, tamanho, titulo, autores, editora, precoEletronico);
        
        livrosCadastrados.add(livroImpresso);
        index++;
        numImpressos++;
        livroDAO.cadastrarLivroImpresso(livroImpresso); 
        
        livrosCadastrados.add(livroEletronico);
        index++;
        numEletronicos++;
        livroDAO.cadastrarLivroEletronico(livroEletronico); 
    }

    private void cadastrarLivroImpresso() throws SQLException {
        if (numImpressos >= MAX_IMPRESSOS) {
            System.out.println("Quantidade de livros impressos já está no máximo!");
            return;
        }
        System.out.println("Digite os dados do livro impresso:");
        scan.nextLine();  
        System.out.print("-> Título: ");
        String titulo = scan.nextLine();
        System.out.print("-> Autores: ");
        String autores = scan.nextLine();
        System.out.print("-> Editora: ");
        String editora = scan.nextLine();
        System.out.print("-> Frete: ");
        float frete = scan.nextFloat();
        System.out.print("-> Estoque: ");
        int estoque = scan.nextInt();
        System.out.print("-> Preço: ");
        float preco = scan.nextFloat();

        LivroImpresso livroImpresso = new LivroImpresso(index, frete, estoque, titulo, autores, editora, preco);
        livrosCadastrados.add(livroImpresso);
        index++;
        numImpressos++;
        livroDAO.cadastrarLivroImpresso(livroImpresso); 
    }

    private void cadastrarLivroEletronico() throws SQLException {
        if (numEletronicos >= MAX_ELETRONICOS) {
            System.out.println("Quantidade de livros eletrônicos já está no máximo!");
            return;
        }
        System.out.println("Digite os dados do livro eletrônico:");
        scan.nextLine();  
        System.out.print("-> Título: ");
        String titulo = scan.nextLine();
        System.out.print("-> Autores: ");
        String autores = scan.nextLine();
        System.out.print("-> Editora: ");
        String editora = scan.nextLine();
        System.out.print("-> Tamanho (MB): ");
        int tamanho = scan.nextInt();
        System.out.print("-> Preço: ");
        float preco = scan.nextFloat();

        LivroEletronico livroEletronico = new LivroEletronico(index, tamanho, titulo, autores, editora, preco);
        livrosCadastrados.add(livroEletronico);
        index++;
        numEletronicos++;
        livroDAO.cadastrarLivroEletronico(livroEletronico); 
    }

    // Método para realizar vendas
    public void realizarVenda() throws SQLException {
        if (numVendas >= MAX_VENDAS) {
            System.out.println("Limite de vendas atingido!");
            return;
        }

        System.out.print("Insira o nome do Cliente: ");
        String nomeCliente = scan.next();
        System.out.print("Quantos livros serão comprados? ");
        int qntdLivrosVenda = scan.nextInt();

        Venda vendaAtual = new Venda(nomeCliente);
        
        List<Livro> livrosSelecionados = new ArrayList<>(); 
        
        for (int i = 0; i < qntdLivrosVenda; i++) {
            System.out.println("Qual o tipo do livro " + (i + 1) + "?");
            System.out.println("""
                               1 - Livro Impresso
                               2 - Livro Eletrônico                               
                               """);
            int resposta = scan.nextInt();
            
            List<Livro> livrosDisponiveis;

            switch (resposta) {
                //case 1 -> listarLivrosImpressos();
                //case 2 -> listarLivrosEletronicos();
            case 1:
            	livrosDisponiveis = livroDAO.listarLivros("impresso");
                break;
            case 2:
                livrosDisponiveis = livroDAO.listarLivros("eletronico");
                break;
             default :
                	throw new AssertionError("Opção inválida");
            }
            
            for (int j = 0; j < livrosDisponiveis.size(); j++) {
                System.out.println("Livro: " + (j + 1));
                System.out.println(livrosDisponiveis.get(j));
                System.out.println();
            }

            System.out.print("Escolha o livro pelo número: ");
            int posicaoLivroASerVendido = scan.nextInt();
            
           
            Livro livroSelecionado = livrosDisponiveis.get(posicaoLivroASerVendido - 1);
            vendaAtual.addLivro(livroSelecionado, i);
        }

        vendaAtual.listarLivros();
        System.out.println("\nTotal da venda: R$" + vendaAtual.getValor());
        System.out.println("\nConfirmar a venda?");
        System.out.println("""
                           1 - Confirmar                               
                           2 - Cancelar
                           """);

        int respostaConfirmarVenda = scan.nextInt();

        if (respostaConfirmarVenda == 1) {
            System.out.println("VENDA REALIZADA");
            vendasRealizadas.add(vendaAtual);
            numVendas++;
            vendaAtual.setNumero(numVendas);
            vendaDAO.adicionarVenda(vendaAtual); 
        } else {
            System.out.println("VENDA CANCELADA");
        }
    }

    // Métodos para listar livros
    public void listarLivrosImpressos() {
        System.out.println("Lista de Livros Impressos:");
        //não precisamos  usar o laço aqui pelo  motivo que  estamos utlizando direto do LivroDAO
        /*for (int i = 0; i < livrosCadastrados.size(); i++) {
            if (livrosCadastrados.get(i) instanceof LivroImpresso) {
                System.out.println("Livro [" + (i + 1) + "]");
                System.out.println(livrosCadastrados.get(i) + "\n");
            }
            
        }*/
        livroDAO.listarLivros("impresso"); 
    }

    public void listarLivrosEletronicos() {
    	System.out.println("Lista de Livros Eletrônicos:");
    	System.out.println();
       /* for (int i = 0; i < livrosCadastrados.size(); i++) {
            if (livrosCadastrados.get(i) instanceof LivroEletronico) {
                System.out.println("Livro ["  + (i + 1) + "]");
                System.out.println(livrosCadastrados.get(i) + "\n");
            }     
        }  */  
       livroDAO.listarLivros("eletronico");
    }

    public void listarLivros() {
        System.out.println("""
                           "3 - Listar Livros"
                           "-------------------"
                           1 - Listar Livros Impressos
                           2 - Listar Livros Eletrônicos
                           """);
        int escolha = scan.nextInt();
        switch (escolha) {
            case 1 -> listarLivrosImpressos();
            case 2 -> listarLivrosEletronicos();
            default -> throw new AssertionError("Opção inválida");
        }
    }

    public void listarVendas() throws SQLException {
    	System.out.println("Lista de Vendas Realizadas:");
    	System.out.println();
        vendaDAO.listarVendas();
    }
}
