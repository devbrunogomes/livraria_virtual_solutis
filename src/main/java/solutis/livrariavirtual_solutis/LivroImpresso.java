
package solutis.livrariavirtual_solutis;


public class LivroImpresso extends Livro {
    //Atributos
    protected float frete;
    protected int estoque;
    
    //Construtores
    public LivroImpresso(float frete, int estoque, String titulo, String autores, String editora, float preco) {
        super(titulo, autores, editora, preco);
        this.frete = frete;
        this.estoque = estoque;
    }

    public LivroImpresso() {
    }

    //Métodos
    public void atualizarEstoque() {

    }

    @Override
    public String toString() {
        return super.toString();
        //To do: acrescentar frete e estoque
    }

}
