package solutis.livrariavirtual_solutis;

public class LivroEletronico extends Livro {

    //Atributos
    private int tamanho;

    //Construtores
    public LivroEletronico(long id, int tamanho, String titulo, String autores, String editora, float preco) {
        super(titulo, autores, editora, preco);
        this.tamanho = tamanho;
        this.id = id;
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
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Id: %-8s Titulo: %-20s Autores: %-20s Editora: %-20s Preço: %-10.2f Tamanho(KB):%-10d%n", getId(),
            getTitulo(), getAutores(), getEditora(), getPreco(), getTamanho()));
        return sb.toString();
    }

}
