
package solutis.livrariavirtual_solutis;

public class LivrariaVirtual {
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
        System.out.println("Processo de cadastro");
    }
    
    public void realizarVenda(){
        
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
