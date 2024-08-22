
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

    //Metodos
    public void addLivro(Livro livro, int index) {

    }

    public void listarLivros() {

    }
}
