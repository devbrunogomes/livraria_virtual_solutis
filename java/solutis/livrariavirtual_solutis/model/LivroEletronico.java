package solutis.livrariavirtual_solutis.model;


public class LivroEletronico extends Livro {

    //Atributos
    private int tamanho;

    //Construtores
    public LivroEletronico(long id, int tamanho, String titulo, String autores, String editora, float preco) {
        super(id, titulo, autores, editora, preco);
        this.tamanho = tamanho;
    }

    public LivroEletronico() {
    }
    
    //Getter e Setters
    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    //Metodo toString
    @Override
    public String toString() {
        return super.toString();
        //To do: adicionar o Tamanho
    }

}
