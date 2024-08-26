package solutis.livrariavirtual_solutis;

import javax.swing.*;
import java.util.ArrayList;
import solutis.livrariavirtual_solutis.LivroImpresso;
public class LivrariaVirtual {

    ArrayList<Livro> livrosCadastrados = new ArrayList<>();
    ArrayList<Venda> vendasRealizadas = new ArrayList<>();
    LivroImpresso livroImpresso;

    // Atributos
    private final int MAX_IMPRESSOS = 10;
    private final int MAX_ELETRONICOS = 20;
    private final int MAX_VENDAS = 50;
    private int numImpressos = 0;
    private int numEletronicos = 0;
    private long numVendas = 0;
    private long index = 0;

    // Construtores
    public LivrariaVirtual(int numImpressos, int numEletronicos, int numVendas) {
        this.numImpressos = numImpressos;
        this.numEletronicos = numEletronicos;
        this.numVendas = numVendas;
    }

    public LivrariaVirtual() {
    }

    // Getters e Setters
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

    public long getNumVendas() {
        return numVendas;
    }

    public void setNumVendas(long numVendas) {
        this.numVendas = numVendas;
    }

    // Métodos
    public void cadastrarLivro() {
    String[] opcoes = {"Impresso", "Eletrônico", "Ambos"};
    String tipo = (String) JOptionPane.showInputDialog(null, "Qual tipo de livro deseja cadastrar?",
            "Cadastro de Livro", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

    Livro livroCriado = null;  // Variável para armazenar o livro recém-criado

    switch (tipo) {
        case "Impresso":
            livroCriado = cadastrarLivroImpresso();
            break;
        case "Eletrônico":
            livroCriado = cadastrarLivroEletronico();
            break;
        case "Ambos":
            livroCriado = cadastroAmbos();
            break;
        default:
            JOptionPane.showMessageDialog(null, "Opção inválida.");
            break;
    }

    if (livroCriado != null) {
        JOptionPane.showMessageDialog(null, "Livro cadastrado:\n" + livroCriado);
    }
}


   public Livro cadastroAmbos() {
    String titulo = JOptionPane.showInputDialog("Título:");
    String autores = JOptionPane.showInputDialog("Autores:");
    String editora = JOptionPane.showInputDialog("Editora:");
    float frete = Float.parseFloat(JOptionPane.showInputDialog("Frete:"));
    int estoque = Integer.parseInt(JOptionPane.showInputDialog("Estoque:"));
    float precoImpresso = Float.parseFloat(JOptionPane.showInputDialog("Preço do livro impresso:"));
    float precoEletronico = Float.parseFloat(JOptionPane.showInputDialog("Preço do livro eletrônico:"));
    int tamanho = Integer.parseInt(JOptionPane.showInputDialog("Tamanho do arquivo (KB):"));

    LivroImpresso livroImpresso = new LivroImpresso(index, frete, estoque, titulo, autores, editora, precoImpresso);
    livrosCadastrados.add((int) index++, livroImpresso);
    numImpressos++;

    LivroEletronico livroEletronico = new LivroEletronico(index,tamanho, titulo, autores, editora, precoEletronico);
    livrosCadastrados.add((int) index++, livroEletronico);
    numEletronicos++;

    return livroImpresso;  // Ou retorne livroEletronico se preferir, ou altere a lógica para escolher qual livro retornar
}


    public Livro cadastrarLivroImpresso() {
    if (numImpressos == MAX_IMPRESSOS) {
        JOptionPane.showMessageDialog(null, "Quantidade de livros impressos já está no máximo!");
        return null;
    }

    String titulo = JOptionPane.showInputDialog("Digite os dados do livro impresso:\nTítulo:");
    String autores = JOptionPane.showInputDialog("Autores:");
    String editora = JOptionPane.showInputDialog("Editora:");
    float frete = Float.parseFloat(JOptionPane.showInputDialog("Frete:"));
    int estoque = Integer.parseInt(JOptionPane.showInputDialog("Estoque:"));
    float preco = Float.parseFloat(JOptionPane.showInputDialog("Preço:"));

    LivroImpresso novoLivroImpresso = new LivroImpresso(index, frete, estoque, titulo, autores, editora, preco);
    livrosCadastrados.add((int)index++, novoLivroImpresso);
    numImpressos++;
    return novoLivroImpresso;
}


    public Livro cadastrarLivroEletronico() {
    if (numEletronicos == MAX_ELETRONICOS) {
        JOptionPane.showMessageDialog(null, "Quantidade de livros eletrônicos já está no máximo!");
        return null;
    }

    String titulo = JOptionPane.showInputDialog("Digite os dados do livro eletrônico:\nTítulo:");
    String autores = JOptionPane.showInputDialog("Autores:");
    String editora = JOptionPane.showInputDialog("Editora:");
    int tamanho = Integer.parseInt(JOptionPane.showInputDialog("Tamanho do arquivo (KB):"));
    float preco = Float.parseFloat(JOptionPane.showInputDialog("Preço:"));

    LivroEletronico novoLivroEletronico = new LivroEletronico(index, tamanho, titulo, autores, editora, preco);
    livrosCadastrados.add((int) index++, novoLivroEletronico);
    numEletronicos++;
    return novoLivroEletronico;
}


    public void realizarVenda() {
        if (numVendas >= MAX_VENDAS) {
            JOptionPane.showMessageDialog(null, "Limite de Vendas atingido!");
            return;
        }
        int qtdLivrosImpressos = 0;

        String nomeCliente = JOptionPane.showInputDialog("Insira o nome do Cliente:");
        int qntdLivrosVenda = Integer.parseInt(JOptionPane.showInputDialog("Quantos livros serão comprados?"));

        Venda vendaAtual = new Venda(nomeCliente); // Nova instancia da venda atual

        for (int i = 0; i < qntdLivrosVenda; i++) {
        String[] tipos = {"Livro Impresso", "Livro Eletrônico"};
        String tipoLivro = (String) JOptionPane.showInputDialog(null, "Qual o tipo do livro " + (i + 1) + "?",
                "Tipo de Livro", JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);

        if (tipoLivro == null) {
            JOptionPane.showMessageDialog(null, "Operação cancelada.");
            return;
        }

        Livro livroSelecionado = null;

        switch (tipoLivro) {
            case "Livro Impresso":
                // Cria uma lista de títulos de livros impressos disponíveis
                ArrayList<String> titulosImpressos = new ArrayList<>();
                for (Livro livro : livrosCadastrados) {
                    if (livro instanceof LivroImpresso) {
                        titulosImpressos.add(livro.getTitulo());
                    }
                }

                // Mostra a lista de títulos para o usuário escolher
                String livroImpressoEscolhido = (String) JOptionPane.showInputDialog(null, "Selecione o livro impresso:",
                        "Escolher Livro", JOptionPane.QUESTION_MESSAGE, null, titulosImpressos.toArray(), titulosImpressos.get(0));

                // Encontra o livro escolhido
                for (Livro livro : livrosCadastrados) {
                    if (livro instanceof LivroImpresso && livro.getTitulo().equals(livroImpressoEscolhido)) {
                        livroSelecionado = livro;
                        break;
                    }
                }
                qtdLivrosImpressos++;
                break;
            case "Livro Eletrônico":
                // Cria uma lista de títulos de livros eletrônicos disponíveis
                ArrayList<String> titulosEletronicos = new ArrayList<>();
                for (Livro livro : livrosCadastrados) {
                    if (livro instanceof LivroEletronico) {
                        titulosEletronicos.add(livro.getTitulo());
                    }
                }

                // Mostra a lista de títulos para o usuário escolher
                String livroEletronicoEscolhido = (String) JOptionPane.showInputDialog(null, "Selecione o livro eletrônico:",
                        "Escolher Livro", JOptionPane.QUESTION_MESSAGE, null, titulosEletronicos.toArray(), titulosEletronicos.get(0));

                // Encontra o livro escolhido
                for (Livro livro : livrosCadastrados) {
                    if (livro instanceof LivroEletronico && livro.getTitulo().equals(livroEletronicoEscolhido)) {
                        livroSelecionado = livro;
                        break;
                    }
                }
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opção inválida.");
                return;
        }

        // Adiciona o livro selecionado à venda atual
        if (livroSelecionado != null) {
            vendaAtual.addLivro(livroSelecionado, i);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum livro foi selecionado.");
        }
    }

        vendaAtual.listarLivros();

        int respostaConfirmarVenda = JOptionPane.showConfirmDialog(null,
                "Total da venda: R$" + vendaAtual.getValor() + "\nConfirmar a Venda?", "Confirmar Venda",
                JOptionPane.YES_NO_OPTION);

        if (respostaConfirmarVenda == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "VENDA REALIZADA");
            vendasRealizadas.add((int) numVendas, vendaAtual);
            this.numVendas++;
            vendaAtual.setNumero(this.getNumVendas());
            livroImpresso.atualizarEstoque(qtdLivrosImpressos);
        } else {
            vendaAtual.setValor(0);
            JOptionPane.showMessageDialog(null, "VENDA CANCELADA");
        }
    }

    public void listarLivrosImpressos() {
        StringBuilder livrosImpressoList = new StringBuilder();
        for (int i = 0; i < livrosCadastrados.size(); i++) {
            if (livrosCadastrados.get(i) instanceof LivroImpresso) {
                livrosImpressoList.append("Livro [").append(i + 1).append("]\n")
                        .append(livrosCadastrados.get(i)).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, livrosImpressoList.toString());
    }

    public void listarLivrosEletronicos() {
        StringBuilder livrosEletronicoList = new StringBuilder();
        for (int i = 0; i < livrosCadastrados.size(); i++) {
            if (livrosCadastrados.get(i) instanceof LivroEletronico) {
                livrosEletronicoList.append("Livro [").append(i + 1).append("]\n")
                        .append(livrosCadastrados.get(i)).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, livrosEletronicoList.toString());
    }

   public void listarLivros() {
    String[] opcoes = {"Listar Livros Impressos", "Listar Livros Eletrônicos", "Listar Ambos", "Retornar ao Menu Principal"};
    int respostaListarLivros = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Listar Livros",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

    StringBuilder mensagem = new StringBuilder();

    switch (respostaListarLivros) {
        case 0: // Listar Livros Impressos
            for (int i = 0; i < livrosCadastrados.size(); i++) {
                if (livrosCadastrados.get(i) instanceof LivroImpresso) {
                    mensagem.append("Livro [").append(i + 1).append("]\n");
                    mensagem.append(livrosCadastrados.get(i)).append("\n\n");
                }
            }
            JOptionPane.showMessageDialog(null, mensagem.length() > 0 ? mensagem.toString() : "Nenhum livro impresso cadastrado.");
            break;
        case 1: // Listar Livros Eletrônicos
            for (int i = 0; i < livrosCadastrados.size(); i++) {
                if (livrosCadastrados.get(i) instanceof LivroEletronico) {
                    mensagem.append("Livro [").append(i + 1).append("]\n");
                    mensagem.append(livrosCadastrados.get(i)).append("\n\n");
                }
            }
            JOptionPane.showMessageDialog(null, mensagem.length() > 0 ? mensagem.toString() : "Nenhum livro eletrônico cadastrado.");
            break;
        case 2: // Listar Ambos
            for (Livro livro : livrosCadastrados) {
                mensagem.append(livro).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, mensagem.length() > 0 ? mensagem.toString() : "Nenhum livro cadastrado.");
            break;
        case 3: // Retornar ao Menu Principal
            break;
        default:
            throw new AssertionError();
    }
}

public void listarVendas() {
    StringBuilder mensagem = new StringBuilder();
    for (int i = 0; i < vendasRealizadas.size(); i++) {
        mensagem.append("Venda ").append(vendasRealizadas.get(i).getNumero()).append("\n");
        mensagem.append(vendasRealizadas.get(i)).append("\n\n");
    }
    JOptionPane.showMessageDialog(null, mensagem.length() > 0 ? mensagem.toString() : "Nenhuma venda realizada.");
}
}

