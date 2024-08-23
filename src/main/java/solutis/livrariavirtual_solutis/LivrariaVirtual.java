package solutis.livrariavirtual_solutis;

import java.util.ArrayList;
import java.util.Scanner;

public class LivrariaVirtual {
    private final Scanner scan = new Scanner(System.in);
    ArrayList<Livro> livrosCadastrados = new ArrayList<>();
    ArrayList<Venda> vendasRealizadas = new ArrayList<>();

    //Atributos
    private final int MAX_IMPRESSOS = 10;
    private final int MAX_ELETRONICOS = 20;
    private final int MAX_VENDAS = 50;
    private int numImpressos = 0;
    private int numEletronicos = 0;
    private int numVendas = 0;
    private int index = 0;

    //Construtores
    public LivrariaVirtual(int numImpressos, int numEletronicos, int numVendas) {
        this.numImpressos = numImpressos;
        this.numEletronicos = numEletronicos;
        this.numVendas = numVendas;
    }

    public LivrariaVirtual() {
    }

    //Getters e Setters
    public int getNumImpressos() {
        return numImpressos;
    }

    public void setNumImpressos(int numImpressos) {
        this.numImpressos = numImpressos;
    }

    public int getNumEletronicos() {
        return numEletronicos;
    }

    public void setNumEletronicos(int numEletronicos) {
        this.numEletronicos = numEletronicos;
    }

    public int getNumVendas() {
        return numVendas;
    }

    public void setNumVendas(int numVendas) {
        this.numVendas = numVendas;
    }

    //Métodos
    public void cadastrarLivro() {

        System.out.println("""
                           Processo de Cadasstro
                           1 - Livro Impresso
                           2 - Livro Eletronico
                           3 - Ambos
                           """);
        int resposta = scan.nextInt();
        switch (resposta) {
            case 1:
                cadastrarLivroImpresso();
                break;
            case 2:
                cadastrarLivroEletronico();
                break;
            case 3:
                cadastroAmbos();
                break;
            default:
                throw new AssertionError();
        }
        for (Livro livros : livrosCadastrados) {
            System.out.println(livros);
        }
    }

    public void cadastroAmbos() {
        System.out.println("Conteúdo comum a ambos os tipos: ");
        System.out.println("-> Título: ");
        scan.nextLine();
        String titulo = scan.nextLine();
        System.out.println("-> Autores: ");
        String autores = scan.nextLine();
        System.out.println("-> Editora: ");
        String editora = scan.nextLine();
        System.out.println("---- Para livro Impresso ----");
        System.out.println("-> Frete:");
        float frete = scan.nextFloat();
        System.out.println("-> Estoque:");
        int estoque = scan.nextInt();
        System.out.println("-> Preço livro impresso: ");
        float precoImpresso = scan.nextFloat();
        System.out.println("---- Para livro eletrônico ----");
        System.out.println("-> Preço livro eletrônico: ");
        float precoEletronico = scan.nextFloat();
        System.out.println("-> Tamanho:");
        int tamanho = scan.nextInt();

        livrosCadastrados.add(index, new LivroImpresso(frete, estoque, titulo, autores, editora, precoImpresso));
        index++;
        numImpressos++;
        livrosCadastrados.add(index, new LivroEletronico(tamanho, titulo, autores, editora, precoEletronico));
        index++;
        numEletronicos++;

    }

    public void cadastrarLivroImpresso() {
        if (numImpressos == MAX_IMPRESSOS) {
            System.out.println("Quantidade de livros impressos já está no máximo!");
            return;
        }
        System.out.println("Digite os dados do livro impresso: ");
        System.out.println("-> Título:");
        scan.nextLine();
        String titulo = scan.nextLine();
        System.out.println("-> Autores:");
        String autores = scan.nextLine();
        System.out.println("-> Editora:");
        String editora = scan.nextLine();
        System.out.println("-> Frete:");
        float frete = scan.nextFloat();
        System.out.println("-> Estoque:");
        int estoque = scan.nextInt();
        System.out.println("-> Preço:");
        float preco = scan.nextFloat();

        livrosCadastrados.add(index, new LivroImpresso(frete, estoque, titulo, autores, editora, preco));
        numImpressos++;
        index++;
    }

    public void cadastrarLivroEletronico() {
        if (numEletronicos == MAX_ELETRONICOS) {
            System.out.println("Quantidade de livros eletrônicos já está no máximo!");
            return;
        }
        System.out.println("Digite os dados do livro eletrônico: ");
        System.out.println("-> Título:");
        scan.nextLine();
        String titulo = scan.nextLine();
        System.out.println("-> Autores:");
        String autores = scan.nextLine();
        System.out.println("-> Editora:");
        String editora = scan.nextLine();
        System.out.println("-> Tamanho:");
        int tamanho = scan.nextInt();
        System.out.println("-> Preço:");
        float preco = scan.nextFloat();

        livrosCadastrados.add(index, new LivroEletronico(tamanho, titulo, autores, editora, preco));
        numEletronicos++;
        index++;
    }

    public void realizarVenda() {
        //Simulando array de livros impressos na biblioteca SÓ PRA TESTES
        ArrayList<Livro> livrosImpressosDisponiveis = new ArrayList<>();
        livrosImpressosDisponiveis.add(new LivroImpresso());
        livrosImpressosDisponiveis.add(new LivroImpresso());
        livrosImpressosDisponiveis.add(new LivroImpresso());
        livrosImpressosDisponiveis.add(new LivroImpresso());

        System.out.println("Processo de Venda");

        //inputs cliente
        System.out.print("Insira o nome do Cliente: ");
        String nomeCliente = scan.nextLine();
        System.out.print("Quantos livros serão comprados? ");
        int qntdLivrosVenda = scan.nextInt();

        //Nova instancia de venda
        Venda vendaAtual = new Venda(numVendas, numVendas, nomeCliente); //Nova instancia da venda atual

        //Incialização do Array de livros
        ArrayList<Livro> livrosASeremVendidos = new ArrayList<>();

        //loop até a qntd especificada pelo cliente
        for (int i = 0; i < qntdLivrosVenda; i++) {
            System.out.println("Qual o tipo do livro " + (i + 1) + "?");
            System.out.println("""
                               1 - Livro Impresso
                               2 - Livro Eletronico                               
                               """);
            int resposta = scan.nextInt();
            switch (resposta) {
                case 1:
                    //Simulando a funcao de listar livros Impressos:
                    for (int j = 0; j < livrosImpressosDisponiveis.size(); j++) {
                        System.out.println("Livro [" + (j + 1) + "]\n" + livrosImpressosDisponiveis.get(j));
                        System.out.println("");
                    }

                    //Usuario escolhe qual livro da lista vai ser vendido
                    System.out.print("Qual dos livros sera vendido? ");
                    int posicaoLivroASerVendido = scan.nextInt();

                    //adiciona o livro no array de venda
                    livrosASeremVendidos.add(livrosImpressosDisponiveis.get(posicaoLivroASerVendido - 1));

                    break;
                case 2:
                    listarLivrosEletronicos();
                    break;
                default:
                    throw new AssertionError();
            }

        }

        vendaAtual.listarLivros();

        System.out.println("VENDA REALIZADA ");

        float totalVenda = vendaAtual.getValor();
        System.out.println("Total da venda: R$" + totalVenda);

        //Incrementar numero de venda
        this.numVendas++;

    }

    public void listarLivrosImpressos() {
        //TO DO - Ajustar para correta exibicao

        for (Livro livro : livrosCadastrados) {
            if (livro instanceof LivroImpresso) {
                System.out.println(livro);
            }
        }
    }

    public void listarLivrosEletronicos() {
        //TO DO - Ajustar para correta exibicao

        for (Livro livro : livrosCadastrados) {
            if (livro instanceof LivroEletronico) {
                System.out.println(livro);
            }

        }
    }

    public void listarLivros() {
        System.out.println("3 - listar Livros");
        System.out.println("-------------------");
        System.out.println("""
                           1 - Listar Livros Impressos
                           2 - Listar Livros Eletronicos
                           3 - Listar Ambos
                           4 - Retornar pro Menu Principal
                           """);
        int respostaListarLivros = scan.nextInt();
        switch (respostaListarLivros) {
            case 1:
                listarLivrosImpressos();
                break;
            case 2:
                listarLivrosEletronicos();
                break;
            case 3:
                for (Livro livro : livrosCadastrados) {
                    System.out.println(livro);
                }
                break;
            case 4:
                break;
            default:
                throw new AssertionError();
        }

    }

    public void listarVendas() {
        for (Venda vendaRealizada : vendasRealizadas) {
            System.out.println(vendaRealizada);
        }
    }

}
