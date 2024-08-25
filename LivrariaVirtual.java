package solutis.livrariavirtual_solutis;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import solutis.livrariavirtual_solutis.database.LivroDAO;
import solutis.livrariavirtual_solutis.database.VendaDAO;
import solutis.livrariavirtual_solutis.model.*;


public class LivrariaVirtual {
	
	private LivroDAO livroDAO;
	private VendaDAO vendaDAO;
	
	public LivrariaVirtual() throws SQLException {
		this.livroDAO = new LivroDAO(); 
		this.vendaDAO = new VendaDAO();
		}
    
    
    private final Scanner scan = new Scanner(System.in);
    ArrayList<Livro> livrosCadastrados = new ArrayList<>();
    ArrayList<Venda> vendasRealizadas = new ArrayList<>();

    //Atributos
    private final int MAX_IMPRESSOS = 10;
    private final int MAX_ELETRONICOS = 20;
    private final int MAX_VENDAS = 50;
    private int numImpressos = 0;
    private int numEletronicos = 0;
    private int numVendas = 0;
    private int index = 0;

    //Construtores
    public LivrariaVirtual(int numImpressos, int numEletronicos, int numVendas) {
        this.numImpressos = numImpressos;
        this.numEletronicos = numEletronicos;
        this.numVendas = numVendas;
    }
    
    //Getters e Setters
    public int getNumImpressos() {
        return numImpressos;
    }
    
    public void setNumImpressos(int numImpressos) {
        this.numImpressos = numImpressos;
    }
    
    public int getNumEletronicos() {
        return numEletronicos;
    }
    
    public void setNumEletronicos(int numEletronicos) {
        this.numEletronicos = numEletronicos;
    }
    
    public int getNumVendas() {
        return numVendas;
    }
    
    public void setNumVendas(int numVendas) {
        this.numVendas = numVendas;
    }

    //Métodos
    public void cadastrarLivro() throws SQLException{
        
        System.out.println("""
                           Processo de Cadasstro
                           1 - Livro Impresso
                           2 - Livro Eletronico
                           3 - Ambos
                           """);
        int resposta = scan.nextInt();
        switch (resposta) {
            case 1:
                cadastrarLivroImpresso();
                break;
            case 2:
                cadastrarLivroEletronico();
                break;
            case 3:
                cadastroAmbos();
                break;
            default:
                throw new AssertionError();
        }
        for (Livro livros : livrosCadastrados) {
            System.out.println(livros);
        }
    }
    
    public void cadastroAmbos() throws SQLException {
        System.out.println("Conteúdo comum a ambos os tipos: ");
        System.out.println("-> Código do Livro");
        long id = scan.nextLong();
        System.out.println("-> Título: ");
        scan.nextLine();
        String titulo = scan.nextLine();
        System.out.println("-> Autores: ");
        String autores = scan.nextLine();
        System.out.println("-> Editora: ");
        String editora = scan.nextLine();
        System.out.println("---- Para livro Impresso ----");
        System.out.println("-> Frete:");
        float frete = scan.nextFloat();
        System.out.println("-> Estoque:");
        int estoque = scan.nextInt();
        System.out.println("-> Preço livro impresso: ");
        float precoImpresso = scan.nextFloat();
        System.out.println("---- Para livro eletrônico ----");
        System.out.println("-> Preço livro eletrônico: ");
        float precoEletronico = scan.nextFloat();
        System.out.println("-> Tamanho:");
        int tamanho = scan.nextInt();
        
        livrosCadastrados.add(index, new LivroImpresso(id,frete, estoque, titulo, autores, editora, precoImpresso));
        index++;
        numImpressos++;
        livrosCadastrados.add(index, new LivroEletronico(id,tamanho, titulo, autores, editora, precoEletronico));
        index++;
        numEletronicos++;
        
    }
    
    public void cadastrarLivroImpresso() {
        if (numImpressos == MAX_IMPRESSOS) {
            System.out.println("Quantidade de livros impressos já está no máximo!");
            return;
        }
        System.out.println("Digite os dados do livro impresso: ");
        System.out.println("-> Código do Livro");
        Long id = scan.nextLong();
        System.out.println("-> Título:");
        scan.nextLine();
        String titulo = scan.nextLine();
        System.out.println("-> Autores:");
        String autores = scan.nextLine();
        System.out.println("-> Editora:");
        String editora = scan.nextLine();
        System.out.println("-> Frete:");
        Float frete = scan.nextFloat();
        System.out.println("-> Estoque:");
        int estoque = scan.nextInt();
        System.out.println("-> Preço:");
        Float preco = scan.nextFloat();
        
    
		livrosCadastrados.add(index, new LivroImpresso(id,frete, estoque, titulo, autores, editora, preco));
        numImpressos++;
        index++;
    }
    
    public void cadastrarLivroEletronico() {
        if (numEletronicos == MAX_ELETRONICOS) {
            System.out.println("Quantidade de livros eletrônicos já está no máximo!");
            return;
        }
        System.out.println("Digite os dados do livro eletrônico: ");
        System.out.println("-> Código do Livro");
        Long id = scan.nextLong();
        System.out.println("-> Título:");
        scan.nextLine();
        String titulo = scan.nextLine();
        System.out.println("-> Autores:");
        String autores = scan.nextLine();
        System.out.println("-> Editora:");
        String editora = scan.nextLine();
        System.out.println("-> Tamanho:");
        int tamanho = scan.nextInt();
        System.out.println("-> Preço:");
        Float preco = scan.nextFloat();
        
        livrosCadastrados.add(index, new LivroEletronico(id,tamanho, titulo, autores, editora, preco));
        numEletronicos++;
        index++;
    }
    
    public void realizarVenda() throws SQLException{
        if (numVendas >= MAX_VENDAS) {
            System.out.println("Limite de Vendas atingido!");
            return;
        }
        
        System.out.println("Processo de Venda");

        //inputs cliente
        System.out.print("Insira o nome do Cliente: ");
        String nomeCliente = scan.next();
        System.out.print("Quantos livros serão comprados? ");
        int qntdLivrosVenda = scan.nextInt();

        //Nova instancia de venda
        Venda vendaAtual = new Venda(nomeCliente); //Nova instancia da venda atual        

        //loop até a qntd especificada pelo cliente
        for (int i = 0; i < qntdLivrosVenda; i++) {
            System.out.println("Qual o tipo do livro " + (i + 1) + "?");
            System.out.println("""
                               1 - Livro Impresso
                               2 - Livro Eletronico                               
                               """);
            int resposta = scan.nextInt();
            
            int posicaoLivroASerVendido; //Guarda o valor do index no array
            switch (resposta) {
                case 1:
                    //Chamada do metodo para listar livros impressos
                    listarLivrosImpressos();

                    //Usuario escolhe qual livro da lista vai ser vendido
                    System.out.print("Qual dos livros sera vendido? ");
                    posicaoLivroASerVendido = scan.nextInt();

                    //adiciona o livro no array de venda
                    //System.out.println(livrosCadastrados.get(posicaoLivroASerVendido-1));
                    Livro livroPreMetodo = livrosCadastrados.get(posicaoLivroASerVendido-1);
                    vendaAtual.addLivro(livroPreMetodo, i);
                    break;
                case 2:
                    //Chamada do metodo para listar livros eletronicos
                    listarLivrosEletronicos();

                    //Usuario escolhe qual livro da lista vai ser vendido
                    System.out.print("Qual dos livros sera vendido? ");
                    posicaoLivroASerVendido = scan.nextInt();

                    //adiciona o livro no array de venda
                    vendaAtual.addLivro(livrosCadastrados.get(posicaoLivroASerVendido - 1), i);
                    break;
                default:
                    throw new AssertionError();
            }
            
        }
        //Exibir os livros no carrinho
        vendaAtual.listarLivros();
        
        //Processo de confirmacao de Venda
        System.out.println("\nTotal da venda: R$" + vendaAtual.getValor());        
        System.out.println("\nConfirmar a Venda? ");
        System.out.println("""
                               1 - Confirmar                               
                               2 - Cancelar
                               """);
        
        int respostaConfimarVenda = scan.nextInt();
        
        switch (respostaConfimarVenda) {
            case 1:
                System.out.println("VENDA REALIZADA ");
                //Incluir venda no Array
                vendasRealizadas.add(numVendas,vendaAtual);
                
                //Incrementar numero de venda
                this.numVendas++;                
                vendaAtual.setNumero(this.getNumVendas());
                
                break;
            
            case 2:
                //Retornar ao Menu Principal se a venda for cancelada
                vendaAtual.setValor(0);
                System.out.println("VENDA CANCELADA ");
                break;
            default:
                throw new AssertionError();
        }
        
    }
    
    public void listarLivrosImpressos() throws SQLException {
        //TO DO - Ajustar para correta exibicao
        for (int i = 0; i < livrosCadastrados.size(); i++) {
            if (livrosCadastrados.get(i) instanceof LivroImpresso) {
                System.out.println("Livro [" + (i + 1) + "]\n");
                System.out.println(livrosCadastrados.get(i) + "\n");
            }
        }
        
    }
    
    public void listarLivrosEletronicos()  throws SQLException{
        //TO DO - Ajustar para correta exibicao

        for (int i = 0; i < livrosCadastrados.size(); i++) {
            if (livrosCadastrados.get(i) instanceof LivroEletronico) {
                System.out.println("Livro [" + (i + 1) + "]\n");
                System.out.println(livrosCadastrados.get(i) + "\n");
            }
        }
    }
    
    public void listarLivros() throws SQLException {
        System.out.println("""
                           "3 - listar Livros"
                           "-------------------"
                           1 - Listar Livros Impressos
                           2 - Listar Livros Eletronicos
                           3 - Listar Ambos
                           4 - Retornar pro Menu Principal                           
                           """);
        int respostaListarLivros = scan.nextInt();
        
        //Usuario escolhe qual o tipo de lista
        switch (respostaListarLivros) {
            case 1:
                listarLivrosImpressos();
                break;
            case 2:
                listarLivrosEletronicos();
                break;
            case 3:
                for (Livro livro : livrosCadastrados) {
                    System.out.println(livro);
                }
                break;
            case 4:
                break;
            default:
                throw new AssertionError();
        }
        
    }
    
    public void listarVendas() throws SQLException{
        for (int i = 0; i < vendasRealizadas.size(); i++) {
            System.out.println("Venda " + vendasRealizadas.get(i).getNumero());
            System.out.println(vendasRealizadas.get(i));
            System.out.println("");
        }        
    }
    
}
