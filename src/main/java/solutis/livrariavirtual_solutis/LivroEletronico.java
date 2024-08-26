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
        sb.append(String.format("Id: %-5s Titulo: %-35s Autores: %-25s Editora: %-30s Preço: %-8.2f Tamanho(KB):%-10d", getId(),
            getTitulo(), getAutores(), getEditora(), getPreco(), getTamanho()));
        return sb.toString();
    }

}
