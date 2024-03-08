import javax.swing.*;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BIntNo {
    double valor;
    BIntNo esq;
    BIntNo dir;

    public BIntNo(double valor) {
        this.valor = valor;
        this.esq = null;
        this.dir = null;
    }
}

public class ArvoreBinariaBusca {
    private JFrame frame;
    private JTextField textField;
    private BIntNo raiz;
    private int valoresInseridos;

    public ArvoreBinariaBusca() {
        frame = new JFrame("Árvore Binária de Busca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        textField = new JTextField(10);
        JButton inserirButton = new JButton("Inserir");
        JButton exibirEsqButton = new JButton("Exibir à Esquerda");
        JButton exibirDirButton = new JButton("Exibir à Direita");
        JButton exibirRaizButton = new JButton("Exibir Raiz");
        JButton buscarButton = new JButton("Buscar"); // Novo botão de busca
        JButton removerButton = new JButton("Remover"); // Novo botão de remoção
        JButton exibirTodosButton = new JButton("Exibir Todos"); // Novo botão para exibir todos os valores
        JButton encerrarButton = new JButton("Encerrar");

        inserirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double valor = Double.parseDouble(textField.getText());
                    raiz = inserir(raiz, valor);
                    textField.setText("");
                    valoresInseridos++;
                    if (valoresInseridos >= 5) {
                        JOptionPane.showMessageDialog(frame, "Você inseriu 5 valores. A inserção está completa.");
                        // Aqui você pode adicionar outras ações ou encerrar o programa, se desejar.
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Digite um número válido.");
                }
            }
        });

        exibirEsqButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Valores à esquerda:");
                exibirEsq(raiz);
            }
        });

        exibirDirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Valores à direita:");
                exibirDir(raiz);
            }
        });

        exibirRaizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Valor da raiz: " + raiz.valor);
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double valorBuscado = Double.parseDouble(textField.getText());
                    BIntNo resultado = buscar(raiz, valorBuscado);
                    if (resultado != null) {
                        JOptionPane.showMessageDialog(frame, "Valor encontrado: " + resultado.valor);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Item não localizado!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Digite um número válido.");
                }
            }
        });

        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double valorRemover = Double.parseDouble(textField.getText());
                    raiz = remover(raiz, valorRemover);
                    textField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Digite um número válido.");
                }
            }
        });

        exibirTodosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Valores da árvore:");
                exibirTodos(raiz);
                System.out.println("Tamanho da árvore: " + tamanho(raiz));
            }
        });

        encerrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Encerra o programa
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Valor:"));
        panel.add(textField);
        panel.add(inserirButton);
        panel.add(exibirEsqButton);
        panel.add(exibirDirButton);
        panel.add(exibirRaizButton);
        panel.add(buscarButton); // Adicionando o novo botão de busca
        panel.add(removerButton);
        panel.add(exibirTodosButton); // Adicionando o novo botão para exibir todos os valores
        panel.add(encerrarButton); // Adicionando o novo botão para encerrar o programa


        frame.add(panel);
        frame.setVisible(true);
    }


    public static BIntNo inserir(BIntNo arvore, double novoNo){
            if (arvore == null) {
                return new BIntNo(novoNo);
            } else {
                if (novoNo < arvore.valor) {
                    if (arvore.esq != null) {
                        arvore.esq = inserir(arvore.esq, novoNo);
                    } else {
                        System.out.println("Inserindo " + novoNo + " à esquerda de " + arvore.valor);
                        arvore.esq = new BIntNo(novoNo);
                    }
                } else if (novoNo > arvore.valor) {
                    if (arvore.dir != null) {
                        arvore.dir = inserir(arvore.dir, novoNo);
                    } else {
                        System.out.println("Inserindo " + novoNo + " à direita de " + arvore.valor);
                        arvore.dir = new BIntNo(novoNo);
                    }
                }
            return arvore;
            }
        }
        
        public static void exibirEsq(BIntNo arvore) {
            if (arvore != null) {
                exibirEsq(arvore.esq);
                System.out.print(arvore.valor + " ");
            }
        }

        public static void exibirDir(BIntNo arvore) {
            if (arvore != null) {
                exibirDir(arvore.dir);
                System.out.print(arvore.valor + " ");
            }
        }

        public static void exibirRaiz(BIntNo arvore) {
            if (arvore != null) {
                System.out.println("Valor da raiz: " + arvore.valor);
            } else {
                System.out.println("Árvore vazia. Não há raiz.");
            }
        }

        // Função de busca
        public static BIntNo buscar(BIntNo arvore, double valorBuscado) {
            if (arvore == null || arvore.valor == valorBuscado) {
                return arvore;
            }

            if (valorBuscado < arvore.valor) {
                return buscar(arvore.esq, valorBuscado);
            } else {
                return buscar(arvore.dir, valorBuscado);
            }
        }

        public static BIntNo remover(BIntNo arvore, double valorRemover) {
                if (arvore == null) {
                    return arvore;
                }
            
                if (valorRemover < arvore.valor) {
                    arvore.esq = remover(arvore.esq, valorRemover);
                } else if (valorRemover > arvore.valor) {
                    arvore.dir = remover(arvore.dir, valorRemover);
                } else {
                    // Caso 1: Nó sem filhos ou com apenas um filho
                    if (arvore.esq == null) {
                        return arvore.dir;
                    } else if (arvore.dir == null) {
                        return arvore.esq;
                    }
            
                    // Caso 2: Nó com dois filhos
                    arvore.valor = encontrarMenorValor(arvore.dir);
                    arvore.dir = remover(arvore.dir, arvore.valor);
                }
            
                return arvore;
            }
            
            private static double encontrarMenorValor(BIntNo arvore) {
                double menorValor = arvore.valor;
                while (arvore.esq != null) {
                    menorValor = arvore.esq.valor;
                    arvore = arvore.esq;
                }
                return menorValor;
            }

                // Função para exibir todos os valores da árvore
        public static void exibirTodos(BIntNo arvore) {
            if (arvore != null) {
                exibirTodos(arvore.esq);
                System.out.print(arvore.valor + " ");
                exibirTodos(arvore.dir);
            }
        }

        // Função para calcular o tamanho da árvore
        public static int tamanho(BIntNo arvore) {
            if (arvore == null) {
                return 0;
            }
            return 1 + tamanho(arvore.esq) + tamanho(arvore.dir);
        }
        

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ArvoreBinariaBusca());
    }
}