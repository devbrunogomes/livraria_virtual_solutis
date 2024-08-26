package solutis.livrariavirtual_solutis;

import java.util.ArrayList;

public class Venda {

    private ArrayList<Livro> livrosASeremVendidos = new ArrayList<>();

    //Atributos
    private static int numVendas;
    private long numero;
    private String cliente;
    private float valor;

    //Construtores
    public Venda(String cliente) {
        this.cliente = cliente;        
    }

    public Venda() {

    }

    //Getters e Setters

    public ArrayList<Livro> getLivrosASeremVendidos() {
        return livrosASeremVendidos;
    }

    public void setLivrosASeremVendidos(ArrayList<Livro> livrosASeremVendidos) {
        this.livrosASeremVendidos = livrosASeremVendidos;
    }
    
    
    public int getNumVendas() {
        return numVendas;
    }

    public void setNumVendas(int numVendas) {
        this.numVendas = numVendas;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
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
    public void addLivro(Livro livro, long index) {
        System.out.println("Adicionando livro ao carrinho");
        livrosASeremVendidos.add((int)index,livro);
    }

    public void listarLivros() {
        float valorTotal = 0;

        System.out.println("\n-- Livros no Carrinho --");

        //Loop For
        for (Livro livroASerVendido : livrosASeremVendidos) {
            System.out.println("Titulo - " + livroASerVendido.titulo);
            System.out.println("Preco - " + livroASerVendido.getPreco());

            
            valorTotal += livroASerVendido.getPreco();
            
            //Pegar o valor do frete se for um livro impresso
            if (livroASerVendido instanceof LivroImpresso) {
                valorTotal += ((LivroImpresso) livroASerVendido).getFrete();
            }
        }

        setValor(valorTotal);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Número: %-20s Cliente: %-20s Preço: %-10.2f ", 
            getNumero(), getCliente(), getValor()));
        return sb.toString();
    }
}
