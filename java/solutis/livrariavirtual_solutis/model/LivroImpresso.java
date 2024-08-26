package solutis.livrariavirtual_solutis.model;


public class LivroImpresso extends Livro {

    //Atributos
    protected float frete;
    protected int estoque;

    //Construtores
    public LivroImpresso(long id, float frete, int estoque, String titulo, String autores, String editora, float preco) {
        super(id, titulo, autores, editora, preco);
        this.frete = frete;
        this.estoque = estoque;
    }

    public LivroImpresso() {
    }

    //Getter e Setters
    
    public long getId() {
    	return id;
    }
    
    public void setId(long id) {
    	this.id =id;
    }
    public float getFrete() {
        return frete;
    }

    public void setFrete(float frete) {
        this.frete = frete;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    //Métodos
    public void atualizarEstoque() {

    }

    @Override
    public String toString() {
        return super.toString();
   
    }

}
