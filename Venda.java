package solutis.livrariavirtual_solutis;

import java.util.ArrayList;
import java.util.List;

import solutis.livrariavirtual_solutis.model.Livro;

public class Venda {

    private List<Livro> livrosASeremVendidos = new ArrayList<>();

    //Atributos
    private static int numVendas;
    private int numero;
    private String cliente;
    private float valor;

    //Construtores
    public Venda(String cliente) {
        this.cliente = cliente;        
    }

    public Venda() {

    }

    //Getters e Setters
    public int getNumVendas() {
        return numVendas;
    }

    public void setNumVendas(int numVendas) {
        Venda.numVendas = numVendas;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valorTotal) {
        this.valor = valorTotal;
    }

    //Metodos
    public void addLivro(Livro livro, int index) {
        System.out.println("Adicionando livro ao carrinho");
        getLivrosASeremVendidos().add(index,livro);
    }

    public void listarLivros() {
        float valorTotal = 0;

        System.out.println("\n-- Livros no Carrinho --");

        //Loop For
        for (Livro livroASerVendido : getLivrosASeremVendidos()) {
            System.out.println("Titulo - " + livroASerVendido.getTitulo());
            System.out.println("Preco - " + livroASerVendido.getPreco());

            //Pra cada iteracao, aumentar o valorTotal
            valorTotal += (float)livroASerVendido.getPreco();
        }

        //Ao final do loop, alterar o valor da venda
        setValor(valorTotal);
    }

	public List<Livro> getLivrosASeremVendidos() {
		return livrosASeremVendidos;
	}

	public void setLivrosASeremVendidos(List<Livro> livrosASeremVendidos) {
		this.livrosASeremVendidos = livrosASeremVendidos;
	}
}
