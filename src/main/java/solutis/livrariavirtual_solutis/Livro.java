
package solutis.livrariavirtual_solutis;


public abstract class Livro {

    //Atributos
    protected long id;
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
    
    //Getter e Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

     //não seria  mais  interessante a  classBigDecimal q  lida com valores??
    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
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
