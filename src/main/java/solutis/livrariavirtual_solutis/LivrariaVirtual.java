package solutis.livrariavirtual_solutis;

import java.awt.Dimension;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import solutis.livrariavirtual_solutis.LivroImpresso;
import solutis.livrariavirtual_solutis.model.LivroDAO;
import solutis.livrariavirtual_solutis.model.VendaDAO;

public class LivrariaVirtual {
    //Instancias para banco de dados
    private LivroDAO livroDAO;
    private VendaDAO vendaDAO;

    ArrayList<Livro> livrosCadastrados = new ArrayList<>();
    ArrayList<Venda> vendasRealizadas = new ArrayList<>();
    

    // Atributos
    private final int MAX_IMPRESSOS = 10;
    private final int MAX_ELETRONICOS = 20;
    private final int MAX_VENDAS = 50;
    private int numImpressos = 0;
    private int numEletronicos = 0;
    private long numVendas = 1;
    private long index = 0;

    // Construtor

    public LivrariaVirtual(VendaDAO vendaDAO, LivroDAO livroDAO) {
        this.livroDAO = livroDAO;
        this.vendaDAO = vendaDAO;
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
        numImpressos++; //contador local
        livroDAO.cadastrarLivroImpresso(livroImpresso);

        LivroEletronico livroEletronico = new LivroEletronico(index, tamanho, titulo, autores, editora, precoEletronico);
        livrosCadastrados.add((int) index++, livroEletronico);
        numEletronicos++; //contador local
        
        //Inserir no banco de dados
        livroDAO.cadastrarLivroEletronico(livroEletronico);

        return livroImpresso;  // Retorno para exibicao de Caixa de texto
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
        livrosCadastrados.add((int) index++, novoLivroImpresso);
        numImpressos++; //contador local
        
        //Inserir no banco de dados
        livroDAO.cadastrarLivroImpresso(novoLivroImpresso);
        
        return novoLivroImpresso; // Retorno para exibicao de Caixa de texto
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
        numEletronicos++; //contador local
        
        //Inserir no banco de dados
        livroDAO.cadastrarLivroEletronico(novoLivroEletronico);
        
        return novoLivroEletronico; // Retorno para exibicao de Caixa de texto
    }

    public void realizarVenda() {
        //Sair se variavel local chegou ao maximo
        if (numVendas >= MAX_VENDAS) {
            JOptionPane.showMessageDialog(null, "Limite de Vendas atingido!");
            return;
        }
        
        //Sair se quantidade de vendas no banco de dados chegou ao maximo
        if (vendaDAO.listarVendas().size() >= MAX_VENDAS) {
            JOptionPane.showMessageDialog(null, "Limite de Vendas atingido!");
            return;
        }

        int qtdLivrosImpressos = 0;

        String nomeCliente = JOptionPane.showInputDialog("Insira o nome do Cliente:");
        int qntdLivrosVenda = Integer.parseInt(JOptionPane.showInputDialog("Quantos livros serão comprados?"));
        
        // Nova instancia da venda atual
        Venda vendaAtual = new Venda(nomeCliente); 

        //Loop que vai até a quantidade maxima que o usuario inseriu
        for (int i = 0; i < qntdLivrosVenda; i++) {
            String[] tipos = {"Livro Impresso", "Livro Eletrônico"};
            String tipoLivro = (String) JOptionPane.showInputDialog(null, "Qual o tipo do livro " + (i + 1) + "?",
                    "Tipo de Livro", JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);

            if (tipoLivro == null) {
                JOptionPane.showMessageDialog(null, "Operação cancelada.");
                return;
            }   

            Livro livroSelecionado = null; //Para armazenar o livro escolhido

            switch (tipoLivro) {
                case "Livro Impresso":
                    // Cria uma lista de títulos de livros impressos disponíveis
                    List<Livro> titulosImpressos = livroDAO.listarLivros("impresso");

                    // Mostra a lista de títulos para o usuário escolher
                    LivroImpresso livroImpressoEscolhido = (LivroImpresso) JOptionPane.showInputDialog(null, "Selecione o livro impresso:",
                            "Escolher Livro", JOptionPane.QUESTION_MESSAGE, null, titulosImpressos.toArray(), titulosImpressos.get(0));

                    // Encontra o livro escolhido
                    for (Livro livro : titulosImpressos) {
                        if (livro.equals(livroImpressoEscolhido)) {
                            livroSelecionado = livro;
                            break;
                        }
                    }
                    qtdLivrosImpressos++;
                    break;
                case "Livro Eletrônico":
                    // Cria uma lista de títulos de livros eletrônicos disponíveis
                    List<Livro> titulosEletronicos = livroDAO.listarLivros("eletronico");

                    // Mostra a lista de títulos para o usuário escolher
                    LivroEletronico livroEletronicoEscolhido = (LivroEletronico) JOptionPane.showInputDialog(null, "Selecione o livro eletrônico:",
                            "Escolher Livro", JOptionPane.QUESTION_MESSAGE, null, titulosEletronicos.toArray(), titulosEletronicos.get(0));

                    // Encontra o livro escolhido
                    for (Livro livro : titulosEletronicos) {
                        if (livro.equals(livroEletronicoEscolhido)) {
                            livroSelecionado = livro;
                            break;
                        }
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                    return;
            }

            // Adiciona o livro selecionado ao Array da venda atual
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
            vendasRealizadas.add(vendaAtual);
            
            //Pegar o ultimo ID usado na tabela Venda e somar 1
            long idVenda = vendaDAO.listarVendas().size() + 1;
            vendaAtual.setNumero(idVenda);

            //Adicionar venda ao Banco de dados
            vendaDAO.adicionarVenda(vendaAtual);
            
            //Atualizar o estoque do banco de dados
            for (Livro livroNoCarrinho : vendaAtual.getLivrosASeremVendidos()) {
                //se for impresso
                if (livroNoCarrinho instanceof LivroImpresso) {                    
                    int novoEstoque = ((LivroImpresso) livroNoCarrinho).getEstoque() - 1;
                    livroDAO.atualizarEstoqueLivro(livroNoCarrinho.getId(), novoEstoque);
                }
            }
            
            this.numVendas++;
            
        } else {
            vendaAtual.setValor(0);
            JOptionPane.showMessageDialog(null, "VENDA CANCELADA");
        }
    }

    public void listarLivrosImpressos() {
        // Lista dos livros impressos no Banco de dados
        List<Livro> livrosImpressosDisponiveis = livroDAO.listarLivros("impresso");

        StringBuilder livrosImpressoList = new StringBuilder();
        for (int i = 0; i < livrosImpressosDisponiveis.size(); i++) {
            if (livrosImpressosDisponiveis.get(i) instanceof LivroImpresso) {
                livrosImpressoList.append(livrosImpressosDisponiveis.get(i)).append("\n");
            }
        }

        // Criando uma JTextArea para exibir os livros impressos
        JTextArea textArea = new JTextArea(livrosImpressoList.toString());
        textArea.setEditable(false);
        textArea.setLineWrap(true); 
        textArea.setWrapStyleWord(true);

        // Colocando a JTextArea dentro de um JScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(1000, 300));

        // Exibindo o JScrollPane em um JOptionPane
        JOptionPane.showMessageDialog(null, scrollPane, "Livros Impressos Disponíveis", JOptionPane.INFORMATION_MESSAGE);
    }

    public void listarLivrosEletronicos() {
        // Lista dos livros eletrônicos no Banco de dados
        List<Livro> livrosEletronicosDisponiveis = livroDAO.listarLivros("eletronico");

        StringBuilder livrosEletronicoList = new StringBuilder();
        for (int i = 0; i < livrosEletronicosDisponiveis.size(); i++) {
            if (livrosEletronicosDisponiveis.get(i) instanceof LivroEletronico) {
                livrosEletronicoList.append(livrosEletronicosDisponiveis.get(i)).append("\n");
            }
        }

        // Criando uma JTextArea para exibir os livros eletrônicos
        JTextArea textArea = new JTextArea(livrosEletronicoList.toString());
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Colocando a JTextArea dentro de um JScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(1000, 300));
        // Exibindo o JScrollPane em um JOptionPane
        JOptionPane.showMessageDialog(null, scrollPane, "Livros Eletrônicos Disponíveis", JOptionPane.INFORMATION_MESSAGE);
    }

    public void listarAmbos() {
        // Lista dos livros no Banco de dados
        List<Livro> todosLivrosDisponiveis = livroDAO.listarTodosLivros();

        StringBuilder livrosEletronicoList = new StringBuilder();
        for (int i = 0; i < todosLivrosDisponiveis.size(); i++) {
            livrosEletronicoList.append(todosLivrosDisponiveis.get(i)).append("\n");
        }

        // Criando uma JTextArea para exibir os livros
        JTextArea textArea = new JTextArea(livrosEletronicoList.toString());
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Colocando a JTextArea dentro de um JScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(1000, 300));

        // Exibindo o JScrollPane em um JOptionPane
        JOptionPane.showMessageDialog(null, scrollPane, "Livros Disponíveis", JOptionPane.INFORMATION_MESSAGE);
    }

    public void listarLivros() {
        String[] opcoes = {"Listar Livros Impressos", "Listar Livros Eletrônicos", "Listar Ambos", "Retornar ao Menu Principal"};
        int respostaListarLivros = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Listar Livros",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        switch (respostaListarLivros) {
            case 0: // Listar Livros Impressos
                this.listarLivrosImpressos();
                break;
            case 1: // Listar Livros Eletrônicos
                this.listarLivrosEletronicos();
                break;
            case 2: // Listar Ambos
                this.listarAmbos();
                break;
            case 3: // Retornar ao Menu Principal
                break;
            default:
                throw new AssertionError();
        }
    }

    public void listarVendas() {
        //Lista de vendas do Banco de Dados
        List<Venda> listaDeVendasBancoDeDados = vendaDAO.listarVendas();

        StringBuilder mensagem = new StringBuilder();
        for (int i = 0; i < listaDeVendasBancoDeDados.size(); i++) {            
            mensagem.append(listaDeVendasBancoDeDados.get(i)).append("\n");
        }
        
        JOptionPane.showMessageDialog(null, mensagem.length() > 0 ? mensagem.toString() : "Nenhuma venda realizada.");
    }
}
