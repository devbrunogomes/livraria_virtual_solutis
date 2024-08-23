
package solutis.livrariavirtual_solutis;

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
        System.out.println("Processo de Venda");
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
