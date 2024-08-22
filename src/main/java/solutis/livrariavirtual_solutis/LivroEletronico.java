
package solutis.livrariavirtual_solutis;


public class LivroEletronico extends Livro {
    //Atributos
    private int tamanho;
    
    //Construtores
    public LivroEletronico(int tamanho, String titulo, String autores, String editora, float preco) {
        super(titulo, autores, editora, preco);
        this.tamanho = tamanho;
    }

    public LivroEletronico() {
    }    
    
    //Metodo toString
    @Override
    public String toString() {
        return super.toString(); 
        //To do: adicionar o Tamanho
    }
    
    
}
