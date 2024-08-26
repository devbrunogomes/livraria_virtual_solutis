package solutis.livrariavirtual_solutis;

public class LivroImpresso extends Livro {

    //Atributos
    protected float frete;
    protected int estoque;

    //Construtores
    public LivroImpresso(long id,float frete, int estoque, String titulo, String autores, String editora, float preco) {
        super(titulo, autores, editora, preco);
        this.frete = frete;
        this.estoque = estoque;
        this.id = id;
    }

    public LivroImpresso() {
    }

    //Getter e Setters
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
    public void atualizarEstoque(int qtdLivros) {
        this.estoque -= qtdLivros;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Id: %-5s Titulo: %-35s Autores: %-25s Editora: %-30s Preço: %-10.2f Estoque: %-5s", getId(),
            getTitulo(), getAutores(), getEditora(), getPreco(), getEstoque()));
        return sb.toString();
    }


}
