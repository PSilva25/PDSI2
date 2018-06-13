
package Alterações;

import Cadastros.*;
import Backgrounds.BG_Alteracao;
import Banco_de_Dados.DAO;
import Listagem.Listagem_Bebidas;
import Botoes.Borda_Redonda;
import Cadastros.Cadastro_de_Bebidas;
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
import Getters_e_Setters.Lanche;
import Getters_e_Setters.Registro;
import Getters_e_Setters.Bebida;
import Listagem.Listagem_Massas;

public class Alteracao_Estoque_Bebidas extends JFrame implements ActionListener {
    
    String[]botoes = {"SIM", "NAO"};
     
    JButton Cancelar = new JButton("Cancelar");
    JButton Alterar = new JButton("Alterar");
    
    int tamanho = 0, y = 0;
    
    Bebida dados_bebida = new Bebida();
    
    ArrayList<Registro> RegistroList = new ArrayList<Registro>();
    ArrayList<Lanche> LancheList = new ArrayList<Lanche>();
    ArrayList<Bebida> BebidaList = new ArrayList<Bebida>();
    
    
    ArrayList<String> Refrigerante = new ArrayList<>();
    ArrayList<String> Suco = new ArrayList<>();
    ArrayList<String> Milkshake = new ArrayList<>();


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

    float total = 0;
    
    int indexLanhe = 0, indexBebida = 0, tipo = 0;

    
    
    public Alteracao_Estoque_Bebidas(int x) throws SQLException {
        
        y = ArmazenaDados(x);
        
        Font fonte = new Font("SansSerif", Font.BOLD, 14);

        JLabel Tipo_Bebida = new JLabel("Tipo: ");
        Pega_Tipo_Bebida.setBounds(240, 165, 130, 30);
        Tipo_Bebida.setBounds(180, 160, 130, 40);
        Pega_Tipo_Bebida.setFont(fonte);
        Tipo_Bebida.setFont(fonte);
        add(Pega_Tipo_Bebida);
        add(Tipo_Bebida);

        Pega_Tipo_Bebida.addItem(dados_bebida.getTipo());
        
        
        JLabel Nome_Bebida = new JLabel("Bebida:");
        Pega_Nome_Bebida.setBounds(240, 225, 210, 30);
        Nome_Bebida.setBounds(180, 220, 130, 40);
        Pega_Nome_Bebida.setFont(fonte);
        Pega_Nome_Bebida.setText(dados_bebida.getBebida());
        Nome_Bebida.setFont(fonte);
        add(Pega_Nome_Bebida);
        add(Nome_Bebida);

        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(270, 285, 210, 30);
        Fornecedor.setBounds(180, 280, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Pega_Nome_Fornecedor.setText(dados_bebida.getFornecedor());
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);

        Pequeno.setBounds(650, 180, 80, 30);
        Pequeno.addActionListener(this);
        Pequeno.setFont(fonte);
        add(Pequeno);

        Medio.setBounds(730, 180, 80, 30);
        Medio.addActionListener(this);
        Medio.setFont(fonte);
        add(Medio);

        Grande1.setBounds(810, 180, 54, 30);
        Grande1.addActionListener(this);
        Grande1.setFont(fonte);
        add(Grande1);

        Grande2.setBounds(860, 180, 80, 30);
        Grande2.addActionListener(this);
        Grande2.setFont(fonte);
        add(Grande2);

        volume.add(Pequeno);
        volume.add(Medio);
        volume.add(Grande1);
        volume.add(Grande2);

	if (dados_bebida.getVolume().equals("300 ML")) Pequeno.setSelected(true);
        if (dados_bebida.getVolume().equals("600 ML")) Medio.setSelected(true);
        if (dados_bebida.getVolume().equals("1 L")) Grande1.setSelected(true);
        if (dados_bebida.getVolume().equals("2 L")) Grande2.setSelected(true);

        JLabel Quantidade = new JLabel("Quantidade:");
        Pega_Quantidade_Bebida.setBounds(745, 235, 100, 30);
        Quantidade.setBounds(650, 230, 130, 40);
        Pega_Quantidade_Bebida.setFont(fonte);
        Pega_Quantidade_Bebida.setText(String.valueOf(dados_bebida.getQuantidade()));
        Quantidade.setFont(fonte);
        add(Pega_Quantidade_Bebida);
        add(Quantidade);

        JLabel Preco = new JLabel("Preço por unidade:");
        Pega_Preco_Bebida.setBounds(790, 285, 100, 30);
        Preco.setBounds(650, 280, 140, 40);
        Pega_Preco_Bebida.setFont(fonte);
        Pega_Preco_Bebida.setText(String.valueOf(dados_bebida.getPreco()));
        Preco.setFont(fonte);
        add(Pega_Preco_Bebida);
        add(Preco);
        
        
        
        Cancelar.setBorder(new Borda_Redonda(7));
        Cancelar.setBounds(130, 430, 160, 40);
        Cancelar.addActionListener(this);
        Cancelar.setFont(fonte);
        add(Cancelar);
        
        
        Alterar.setBorder(new Borda_Redonda(7));
        Alterar.setBounds(808, 430, 160, 40);
        Alterar.addActionListener(this);
        Alterar.setFont(fonte);
        add(Alterar);

        
       
        
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new BG_Alteracao());
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
     
     
      if (e.getSource() == Alterar) {
          
            try {
                      int opcao = JOptionPane.showOptionDialog(null, "Realmente deseja alterar este produto", "Aviso!!!",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
           
            if(botoes[opcao].equals("SIM")){
            
                Atualiza(y);
                   
                new Listagem_Bebidas();
                
            
                
            }else{
                
            JOptionPane.showMessageDialog(null, "OPERACAO CANCELADA");
            
                 new Listagem_Bebidas();
            
            }
             
            } catch (SQLException ex) {
                Logger.getLogger(Alteracao_Estoque_Bebidas.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
      }
      if (e.getSource() == Cancelar) {

            dispose();
            new Listagem_Bebidas();
     }
     }
     
     public int ArmazenaDados(int ID) throws SQLException{

        c.conexao();
        
         
        c.executaSQL("select * from bebida where ID_bebida = " + ID);
   
        try {

            c.rs.first();
                    
                    dados_bebida.setBebida(c.rs.getString("Nome"));
                    dados_bebida.setTipo(c.rs.getString("Tipo"));
                    dados_bebida.setFornecedor((c.rs.getString("Fornecedor")));
                    dados_bebida.setVolume (c.rs.getString("Volume"));
                    dados_bebida.setQuantidade(c.rs.getInt("Quant"));
                    dados_bebida.setPreco(c.rs.getFloat("Preco"));
                    
                    
            } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }
                Pega_Nome_Bebida.setText("");
                Pega_Nome_Fornecedor.setText("");
                 Pega_Preco_Bebida.setText("");
                Pega_Quantidade_Bebida.setText("");
                volume.clearSelection();
         
        
        return ID;
    }

      public void Atualiza(int x) throws SQLException{

        c.conexao();
        
            if (tamanho == 1) dados_bebida.setVolume("300 ML");
            if (tamanho == 2) dados_bebida.setVolume("600 ML");
            if (tamanho == 3) dados_bebida.setVolume("1 L");
            if (tamanho == 4) dados_bebida.setVolume("2 L");     
            
            dados_bebida.setTipo((String) Pega_Tipo_Bebida.getSelectedItem());
            dados_bebida.setBebida(Pega_Nome_Bebida.getText());
            dados_bebida.setFornecedor(Pega_Nome_Fornecedor.getText());
            dados_bebida.setQuantidade(Integer.parseInt(Pega_Quantidade_Bebida.getText()));
            dados_bebida.setPreco(Float.parseFloat(Pega_Preco_Bebida.getText()));
          
            
            String sql = "UPDATE bebida SET Tipo=?,Nome=?,Fornecedor=?,Volume=?,Quant=?,Preco=? where ID_bebida = '" + x + "'";

            try {

                PreparedStatement stmt = c.conn.prepareStatement(sql);

                stmt.setString(1, dados_bebida.getTipo());
                stmt.setString(2, dados_bebida.getBebida());
                stmt.setString(3, dados_bebida.getFornecedor());
                stmt.setString(4, dados_bebida.getVolume());
                stmt.setInt(5,dados_bebida.getQuantidade());
                stmt.setFloat(6, dados_bebida.getPreco());
                   
               
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "DADOS ALTERADOS!");

            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, e1);

            }

            dispose();
        }
         
        
        
    
    
    
    public static void main(String [] args){
        
        //new Alteracao_Estoque_Bebidas();
        
    }
    
    
    
}
