
package Cadastros;

import Cadastros.*;
import Backgrounds.*;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import Getters_e_Setters.*;
import Listagem.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cadastro_Alimentos_Vegetais extends JFrame implements ActionListener {
    
    Vegetais dados_V = new Vegetais();
    JButton Voltar = new JButton("Voltar");
    JButton Adicionar = new JButton("Cadastrar");
 
    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_Quantidade = new JTextField();
    JTextField Pega_Preco = new JTextField();
    JTextField Pega_Vegetais = new JTextField();  
    
    DAO c = new DAO();
    
   
    public Cadastro_Alimentos_Vegetais() {
               
        Font fonte = new Font("SansSerif", Font.BOLD, 15);
          
        JLabel Vegetais = new JLabel("Vegetais: ");
        Pega_Vegetais.setBounds(255, 185, 130, 30);
        Vegetais.setBounds(180, 180, 130, 40);
        Pega_Vegetais.setFont(fonte);
        Vegetais.setFont(fonte);
        add(Pega_Vegetais);
        add(Vegetais);
       
        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 265, 210, 30);
        Fornecedor.setBounds(180, 260, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);
                
        JLabel Quantidade = new JLabel("Quantidade:");
        Pega_Quantidade.setBounds(720, 195, 100, 30);
        Quantidade.setBounds(720, 160, 130, 40);
        Pega_Quantidade.setFont(fonte);
        Quantidade.setFont(fonte);
        add(Pega_Quantidade);
        add(Quantidade);
   
               
        JLabel UP = new JLabel("Preço de Compra:");
        Pega_Preco.setBounds(720, 275, 100, 30);
        UP.setBounds(720, 240, 140, 40);
        Pega_Preco.setFont(fonte);
        UP.setFont(fonte);
        add(Pega_Preco);
        add(UP);
        
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
       
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new BG_CadAlimentos_Vegetais());
        setBackground(Color.black);
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(1100, 550);
        setLocationRelativeTo(null);
        setVisible(true);
   
    }
         
    
    public void preenche(){
            
        dados_V.setTipo((String) Pega_Vegetais.getText());
        dados_V.setFornecedor(Pega_Nome_Fornecedor.getText());
        dados_V.setQuantidade(Integer.parseInt(Pega_Quantidade.getText()));
        dados_V.setPreco(Float.parseFloat(Pega_Preco.getText()));
         
    }
        
    
    public void actionPerformed(ActionEvent e) {    
       
        if (e.getSource() == Adicionar) {
            
            try {
                 
                preenche();
                ArmazenaDados();
                              
            } catch (SQLException ex) {

            }
            
        }else if (e.getSource() == Voltar) {
            
            dispose();
        
        }
 
    }
       
    
    public void ArmazenaDados() throws SQLException { 
        
        c.conexao();
        
        String sql = "insert into estoque_vegetais (Tipo, Fornecedor, Quantidade, Preco_de_Compra) values (?,?,?,?);";

        PreparedStatement stmt = c.conn.prepareStatement(sql);
               
        try {
                
            stmt.setString(1,dados_V.getTipo());
            stmt.setString(2,dados_V.getFornecedor());
            stmt.setFloat(3,dados_V.getQuantidade());
            stmt.setFloat(4,dados_V.getPreco());
         
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar este produto!");
                        
            Logger.getLogger(Cadastro_de_Bebidas.class.getName()).log(Level.SEVERE, null, ex);
                       
       } 
          
       stmt.execute();

       stmt.close();
       
       Pega_Vegetais.setText("");
       Pega_Nome_Fornecedor.setText("");
       Pega_Quantidade.setText("");
       Pega_Preco.setText("");
                  
    }
 
    
    public static void main(String [] args){
        
        new Cadastro_Alimentos_Vegetais();
        
    }

    
    
    
    
}
