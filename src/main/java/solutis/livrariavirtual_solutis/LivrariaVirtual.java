
package solutis.livrariavirtual_solutis;

import java.util.ArrayList;
import java.util.Scanner;

public class LivrariaVirtual {
    Scanner scan = new Scanner(System.in);
    ArrayList<Livro> livrosCadastrados = new ArrayList<>();

    //Atributos
    private final int MAX_IMPRESSOS = 10;
    private final int MAX_ELETRONICOS = 20;
    private final int MAX_VENDAS = 50;
    private Livro[] livros;
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

    public LivrariaVirtual(){}
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

    public void cadastroAmbos(){
        System.out.println("Conteúdo comum a ambos os tipos: ");
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

        livrosCadastrados.add(index, new LivroImpresso(frete, estoque, titulo, autores, editora, precoImpresso));
        index++;
        numImpressos++;
        livrosCadastrados.add(index, new LivroEletronico(tamanho, titulo, autores, editora, precoEletronico));
        index++;
        numEletronicos++;

    }

    public void cadastrarLivroImpresso() {
        if(numImpressos == MAX_IMPRESSOS){
            System.out.println("Quantidade de livros impressos já está no máximo!");
            return;
        }
        System.out.println("Digite os dados do livro impresso: ");
        System.out.println("-> Título:");
        scan.nextLine();
        String titulo = scan.nextLine();
        System.out.println("-> Autores:");
        String autores = scan.nextLine();
        System.out.println("-> Editora:");
        String editora = scan.nextLine();
        System.out.println("-> Frete:");
        float frete = scan.nextFloat();
        System.out.println("-> Estoque:");
        int estoque = scan.nextInt();
        System.out.println("-> Preço:");
        float preco = scan.nextFloat();

        livrosCadastrados.add(index, new LivroImpresso(frete ,estoque, titulo, autores, editora, preco));
        numImpressos++;
        index++;
    }
    
    public void cadastrarLivroEletronico(){
        if(numEletronicos == MAX_ELETRONICOS){
            System.out.println("Quantidade de livros eletrônicos já está no máximo!");
            return;
        }
        System.out.println("Digite os dados do livro eletrônico: ");
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
        float preco = scan.nextFloat();

        livrosCadastrados.add(index, new LivroEletronico(tamanho, titulo, autores, editora, preco));
        numEletronicos++;
        index++;
    }
    
    public void realizarVenda(){
        System.out.println("Processo de Venda");
    }
    
    public void listarLivrosImpressos(){
        
    }
    
    public void listarLivrosEletronicos() {
        
    }
    
    public void listarLivros() {
        for(Livro livros : livrosCadastrados){
            System.out.println(livros);
        }

    }
    
    public void listarVendas() {
        
    }
    
    
    
}
