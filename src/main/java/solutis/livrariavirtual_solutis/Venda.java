package solutis.livrariavirtual_solutis;

import java.util.ArrayList;

public class Venda {

    ArrayList<Livro> livrosASeremVendidos = new ArrayList<>();

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
        this.numVendas = numVendas;
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

    public void setValor(float valor) {
        this.valor = valor;
    }

    //Metodos
    public void addLivro(Livro livro, int index) {
        System.out.println("Adicionando livro ao carrinho");
        livrosASeremVendidos.add(livro);
    }

    public void listarLivros() {
        float valorTotal = 0;

        System.out.println("-- Livros no Carrinho --");

        //Loop For
        for (Livro livroASerVendido : livrosASeremVendidos) {
            System.out.println("Titulo - " + livroASerVendido.titulo);
            System.out.println("Preco - " + livroASerVendido.getPreco());

            //Pra cada iteracao, aumentar o valorTotal
            valorTotal += livroASerVendido.getPreco();
        }

        //Ao final do loop, alterar o valor da venda
        setValor(valorTotal);
    }
}
