
package solutis.livrariavirtual_solutis;


public abstract class Livro {

    //Atributos
    protected String titulo;
    protected String autores;
    protected String editora;
    protected float preco;
    
    //Construtor
    public Livro(String titulo, String autores, String editora, float preco) {
        this.titulo = titulo;
        this.autores = autores;
        this.editora = editora;
        this.preco = preco;
    }

    public Livro() {
    }
    
    //Metodo toString
    @Override
    public String toString() {
        return "titulo: " + this.titulo
                + "\nAutores: " + this.autores
                + "\nEditora: " + this.editora
                + "\nPreco: " + this.preco;

    }

}
