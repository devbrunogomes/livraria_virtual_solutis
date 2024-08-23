
package solutis.livrariavirtual_solutis;

import java.util.ArrayList;
import java.util.Scanner;

public class LivrariaVirtual {
    Scanner scan = new Scanner(System.in);
    
    //Atributos
    private final int MAX_IMPRESSOS = 10;
    private final int MAX_ELETRONICOS = 20;
    private final int MAX_VENDAS = 50;
    private Livro[] livros;
    private int numImpressos;
    private int numEletronicos;
    private int numVendas;
    
    //Construtores
    public LivrariaVirtual(int numImpressos, int numEletronicos, int numVendas) {
        this.numImpressos = numImpressos;
        this.numEletronicos = numEletronicos;
        this.numVendas = numVendas;
    }

    public LivrariaVirtual() {
    }
    
    //Getters e Setters

    public Livro[] getLivros() {
        return livros;
    }

    public void setLivros(Livro[] livros) {
        this.livros = livros;
    }

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
    public void cadastrarLivro(){
        System.out.println("""
                           Processo de Cadasstro
                           1 - Livro Impresso
                           2 - Livro Eletronico
                           3 - Ambos
                           """);
        int resposta = scan.nextInt();
        switch (resposta) {
            case 1:
                //To do: Chamar funcão externa para cadastrar livro impresso
                break;
            default:
                throw new AssertionError();
        }
        
    }
    
    public void cadastrarLivroImpresso() {
        //to do: Inserir logica
    }
    
    public void cadastrarLivroEletronico(){
        
    }
    
    public void realizarVenda(){
        //Simulando array de livros impressos na biblioteca SÓ PRA TESTES
        ArrayList<Livro> livrosImpressosDisponiveis = new ArrayList<>();
        livrosImpressosDisponiveis.add(new LivroImpresso());
        livrosImpressosDisponiveis.add(new LivroImpresso());
        livrosImpressosDisponiveis.add(new LivroImpresso());
        livrosImpressosDisponiveis.add(new LivroImpresso());
        
        System.out.println("Processo de Venda");
        
        //inputs cliente
        System.out.print("Insira o nome do Cliente: ");
        String nomeCliente = scan.nextLine();
        System.out.print("Quantos livros serão comprados? ");       
        int qntdLivrosVenda = scan.nextInt();
        
        //Incialização do Array de livros
        ArrayList<Livro> livrosASeremVendidos = new ArrayList<>();
        
        //loop até a qntd especificada pelo cliente
        for (int i = 0; i < qntdLivrosVenda; i++) {
            System.out.println("Qual o tipo do livro " + (i + 1) + "?");
            System.out.println("""
                               1 - Livro Impresso
                               2 - Livro Eletronico                               
                               """);
            int resposta = scan.nextInt();
            switch (resposta) {
                case 1:
                    //Simulando a funcao de listar livros Impressos:
                    for (int j = 0; j < livrosImpressosDisponiveis.size(); j++) {
                        System.out.println("Livro [" + (j+1) + "]\n" +livrosImpressosDisponiveis.get(j));
                        System.out.println("");
                    }
                    
                    //Usuario escolhe qual livro da lista vai ser vendido
                    System.out.print("Qual dos livros sera vendido? ");
                    int posicaoLivroASerVendido = scan.nextInt();
                    
                    //adiciona o livro no array de venda
                    livrosASeremVendidos.add(livrosImpressosDisponiveis.get(posicaoLivroASerVendido - 1));
                    
                    break;
                case 2: 
                    listarLivrosEletronicos();
                    break;
                default:
                    throw new AssertionError();
            }
            
        }
        
        System.out.println("VENDA REALIZADA ");
        float totalVenda = 0;
        for (Livro livroASerVendido : livrosASeremVendidos) {
            System.out.println("Titulo - " + livroASerVendido.titulo);
            System.out.println("Preco - " + livroASerVendido.getPreco());
            totalVenda += (float)livroASerVendido.getPreco();
        }
        
        System.out.println("Total da venda: R$" + totalVenda);
        
        //Simulando um novo array de vendas
        ArrayList<Venda> vendasRealizadas = new ArrayList<>();
        vendasRealizadas.add(new Venda(numVendas, numVendas, nomeCliente, totalVenda));
        this.numVendas++;

    }
    
    public void listarLivrosImpressos(){
        
    }
    
    public void listarLivrosEletronicos() {
        
    }
    
    public void listarLivros() {
        
    }
    
    public void listarVendas() {
        
    }
    
    
    
}
