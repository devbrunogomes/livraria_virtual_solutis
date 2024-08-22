
package solutis.livrariavirtual_solutis;


public class Venda {
    //Atributos
    private Livro[] livros;
    private int numVendas;
    private int numero;
    private String cliente;
    private float valor;
    
    //Construtores
    public Venda(int numVendas, int numero, String cliente, float valor) {
        this.numVendas = numVendas;
        this.numero = numero;
        this.cliente = cliente;
        this.valor = valor;
    }
    
    public Venda(){
        
    }
    
    //Getters e Setters

    public Livro[] getLivros() {
        return livros;
    }

    public void setLivros(Livro[] livros) {
        this.livros = livros;
    }

    public int getNumVendas() {
        return numVendas;
    }

    public void setNumVendas(int numVendas) {
        this.numVendas = numVendas;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    

    //Metodos
    public void addLivro(Livro livro, int index) {

    }

    public void listarLivros() {

    }
}
