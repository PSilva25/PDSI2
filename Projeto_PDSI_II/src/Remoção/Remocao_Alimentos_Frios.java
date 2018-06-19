package Remoção;

import Banco_de_Dados.DAOFrios;
import Backgrounds.*;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import Getters_e_Setters.*;
import Listagem.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Remocao_Alimentos_Frios extends JFrame implements ActionListener {

    int Id = 0;

    String[] botoes = {"SIM", "NAO"};

    Cadastro_Alimento dados_F = new Cadastro_Alimento();

    JButton Cancelar = new JButton("Cancelar");
    JButton Remover = new JButton("Remover");

    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_Preco = new JTextField();
    JTextField Pega_Total_Compra = new JTextField();
    JTextField Pega_Quantidade = new JTextField();
    JTextField Pega_Unidade_porcao = new JTextField();
    JTextField Pega_PT = new JTextField();
    JTextField Pega_Frios = new JTextField();

    DAO con = new DAO();
    DAOFrios f = new DAOFrios();

    public Remocao_Alimentos_Frios(int ID) throws SQLException {

        this.Id = ID;

        Pega_Dados(Id);

        Font fonte = new Font("SansSerif", Font.BOLD, 15);

        JLabel Frios = new JLabel("Frios: ");
        Pega_Frios.setBounds(230, 185, 210, 30);
        Frios.setBounds(180, 180, 130, 40);
        Pega_Frios.setFont(fonte);
        Pega_Frios.setEnabled(true);
        Pega_Frios.setEditable(false);
        Pega_Frios.setText(dados_F.getTipo());
        Frios.setFont(fonte);
        add(Pega_Frios);
        add(Frios);

        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 265, 210, 30);
        Fornecedor.setBounds(180, 260, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Pega_Nome_Fornecedor.setEditable(false);
        Pega_Nome_Fornecedor.setText(dados_F.getFornecedor());
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);

        JLabel Quantidade = new JLabel("Quantidade:");
        Pega_Quantidade.setBounds(720, 195, 100, 30);
        Quantidade.setBounds(720, 160, 130, 40);
        Pega_Quantidade.setFont(fonte);
        Pega_Quantidade.setEditable(false);
        Pega_Quantidade.setText(String.valueOf(dados_F.getQuantT()));
        Quantidade.setFont(fonte);
        add(Pega_Quantidade);
        add(Quantidade);

        JLabel UKG = new JLabel("Preço pde Compra:");
        Pega_Preco.setBounds(720, 275, 100, 30);
        UKG.setBounds(720, 240, 140, 40);
        Pega_Preco.setFont(fonte);
        Pega_Preco.setEditable(false);
        Pega_Preco.setText(String.valueOf(dados_F.getPreco()));
        UKG.setFont(fonte);
        add(Pega_Preco);
        add(UKG);

        Cancelar.setBorder(new Borda_Redonda(7));
        Cancelar.setBounds(130, 430, 160, 40);
        Cancelar.addActionListener(this);
        Cancelar.setFont(fonte);
        add(Cancelar);

        Remover.setBorder(new Borda_Redonda(7));
        Remover.setBounds(808, 430, 160, 40);
        Remover.addActionListener(this);
        Remover.setFont(fonte);
        add(Remover);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new BG_Remocao());
        setBackground(Color.black);
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(1100, 550);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Remover) {

            int opcao = JOptionPane.showOptionDialog(null, "Realmente deseja excluir este produto", "Sem saída", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);

            if (botoes[opcao].equals("SIM")) {

                f.deletar(Id);
                dispose();
                new Listagem_Frios();

            } else {

                JOptionPane.showMessageDialog(null, "OPERACAO CANCELADA");
                dispose();
                new Listagem_Frios();

            }

        } else if (e.getSource() == Cancelar) {

            dispose();
            new Listagem_Frios();

        }

    }

    public void Pega_Dados(int ID) throws SQLException {

        con.conexao();

        con.executaSQL("select * from estoque_frios where ID_alimento = " + ID);

        try {

            con.rs.first();

            dados_F.setTipo(con.rs.getString("Tipo"));
            dados_F.setFornecedor((con.rs.getString("Fornecedor")));
            dados_F.setQuantT(con.rs.getFloat("Quantidade"));
            dados_F.setPreco(con.rs.getFloat("Preco_de_compra"));

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }

    }

    public static void main(String[] args) {

        //new Remocao_Alimentos_Frios();
    }

}
