package Cadastros;

import Backgrounds.BG_CadAlimentos_Bebida;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Getters_e_Setters.Bebida;
import projeto_pdsi_ii.Compra;
import Getters_e_Setters.Lanche;
import projeto_pdsi_ii.Login;
import projeto_pdsi_ii.Principal;
import Getters_e_Setters.Registro;
import projeto_pdsi_ii.Status_Login;

public class Cadastro_de_Bebidas extends JFrame implements ActionListener {
    
    JButton Voltar = new JButton("Voltar");
    JButton Adicionar = new JButton("Cadastrar Bebida");
    
    Bebida dados_bebida = new Bebida();
    
    
    JTextField Pega_Nome_Bebida = new JTextField();
    JTextField Pega_Quantidade_Bebida = new JTextField();
    JTextField Pega_Preco_Bebida = new JTextField();
    JTextField Pega_Volume_Bebida = new JTextField();
    JTextField Pega_Nome_Fornecedor = new JTextField();

    JTextArea Mostra_Quantidade = new JTextArea();
    JTextArea Mostra_Modelo = new JTextArea();
    JTextArea Mostra_Marca = new JTextArea();
    JTextArea Mostra_Preco = new JTextArea();
    JTextArea Mostra_Nome = new JTextArea();

    JComboBox<String> Pega_Tipo_Bebida = new JComboBox<>();

    JRadioButton Pequeno = new JRadioButton("300 ML");
    JRadioButton Medio = new JRadioButton("600 ML");
    JRadioButton Grande1 = new JRadioButton("1 L");
    JRadioButton Grande2 = new JRadioButton("2 L");
    ButtonGroup volume = new ButtonGroup();

    DAO c = new DAO();

    int indexLanhe = 0, indexBebida = 0, tipo = 0, tamanho = 0;

    //Status_Login sl = new Status_Login();
    
    public Cadastro_de_Bebidas() {
        
        Font fonte = new Font("SansSerif", Font.BOLD, 14);

        JLabel Tipo_Bebida = new JLabel("Tipo: ");
        Pega_Tipo_Bebida.setBounds(240, 165, 130, 30);
        Tipo_Bebida.setBounds(180, 160, 130, 40);
        Pega_Tipo_Bebida.setFont(fonte);
        Tipo_Bebida.setFont(fonte);
        add(Pega_Tipo_Bebida);
        add(Tipo_Bebida);

        Pega_Tipo_Bebida.addItem("");
        Pega_Tipo_Bebida.addItem("Refrigerante");
        Pega_Tipo_Bebida.addItem("Suco");
        Pega_Tipo_Bebida.addItem("Milkshack");

        JLabel Nome_Bebida = new JLabel("Bebida:");
        Pega_Nome_Bebida.setBounds(240, 225, 210, 30);
        Nome_Bebida.setBounds(180, 220, 130, 40);
        Pega_Nome_Bebida.setFont(fonte);
        Nome_Bebida.setFont(fonte);
        add(Pega_Nome_Bebida);
        add(Nome_Bebida);

        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(270, 285, 210, 30);
        Fornecedor.setBounds(180, 280, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);

        Pequeno.setBounds(650, 160, 80, 30);
        Pequeno.addActionListener(this);
        Pequeno.setFont(fonte);
        add(Pequeno);

        Medio.setBounds(730, 160, 80, 30);
        Medio.addActionListener(this);
        Medio.setFont(fonte);
        add(Medio);

        Grande1.setBounds(810, 160, 54, 30);
        Grande1.addActionListener(this);
        Grande1.setFont(fonte);
        add(Grande1);

        Grande2.setBounds(860, 160, 80, 30);
        Grande2.addActionListener(this);
        Grande2.setFont(fonte);
        add(Grande2);

        volume.add(Pequeno);
        volume.add(Medio);
        volume.add(Grande1);
        volume.add(Grande2);

        JLabel Quantidade = new JLabel("Quantidade:");
        Pega_Quantidade_Bebida.setBounds(745, 215, 100, 30);
        Quantidade.setBounds(650, 210, 130, 40);
        Pega_Quantidade_Bebida.setFont(fonte);
        Quantidade.setFont(fonte);
        add(Pega_Quantidade_Bebida);
        add(Quantidade);

        JLabel Preco = new JLabel("Preço por unidade:");
        Pega_Preco_Bebida.setBounds(790, 265, 100, 30);
        Preco.setBounds(650, 260, 140, 40);
        Pega_Preco_Bebida.setFont(fonte);
        Preco.setFont(fonte);
        add(Pega_Preco_Bebida);
        add(Preco);

        Voltar.setBorder(new Borda_Redonda(7));
        Voltar.setBounds(130, 430, 160, 40);
        Voltar.addActionListener(this);
        Voltar.setFont(fonte);
        add(Voltar);

        Adicionar.setBorder(new Borda_Redonda(7));
        Adicionar.setBounds(808, 430, 160, 40);
        Adicionar.addActionListener(this);
        Adicionar.setFont(fonte);
        add(Adicionar);

       
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new BG_CadAlimentos_Bebida());
        setBackground(Color.black);
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(1100, 550);
        setLocationRelativeTo(null);
        setVisible(true);
        
        

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Pequeno) tamanho = 1;
        if (e.getSource() == Medio) tamanho = 2;
        if (e.getSource() == Grande1) tamanho = 3;
        if (e.getSource() == Grande2) tamanho = 4;
        

        if (e.getSource() == Adicionar) {
                   
            dados_bebida.setTipo((String) Pega_Tipo_Bebida.getSelectedItem());
            dados_bebida.setBebida(Pega_Nome_Bebida.getText());
            dados_bebida.setFornecedor(Pega_Nome_Fornecedor.getText());
            dados_bebida.setQuantidade(Integer.parseInt(Pega_Quantidade_Bebida.getText()));
            dados_bebida.setPreco(Float.parseFloat(Pega_Preco_Bebida.getText()));
            if (tamanho == 1) dados_bebida.setVolume("300 ML");
            if (tamanho == 2) dados_bebida.setVolume("600 ML");
            if (tamanho == 3) dados_bebida.setVolume("1 L");
            if (tamanho == 4) dados_bebida.setVolume("2 L");               
                
            try {
                    
                ArmazenaDados();
                
                
            } catch (SQLException ex) {
                
        
            }
        
        }else if (e.getSource() == Voltar) {

            dispose();
            
        }
    
    }
 
        
       
    public void ArmazenaDados() throws SQLException{

        c.conexao();

        String sql = "insert into bebida (Tipo, Nome, Fornecedor, Volume, Quant, Preco) values (?,?,?,?,?,?);";

        PreparedStatement stmt = c.conn.prepareStatement(sql);
        
        
        try {
                
            stmt.setString(1,dados_bebida.getTipo());
            stmt.setString(2,dados_bebida.getBebida());
            stmt.setString(3,dados_bebida.getFornecedor());
            stmt.setString(4,dados_bebida.getVolume());
            stmt.setInt(5,dados_bebida.getQuantidade());
            stmt.setFloat(6,dados_bebida.getPreco());
        
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar este produto!");
                        
            Logger.getLogger(Cadastro_de_Bebidas.class.getName()).log(Level.SEVERE, null, ex);
                       
       } 

        Pega_Nome_Bebida.setText("");
        Pega_Nome_Fornecedor.setText("");
        Pega_Preco_Bebida.setText("");
        Pega_Quantidade_Bebida.setText("");
        volume.clearSelection();
         
        
        stmt.execute();

        stmt.close();
        
    }

    public static void main(String[] args) {

        new Cadastro_de_Bebidas();

    }

}
