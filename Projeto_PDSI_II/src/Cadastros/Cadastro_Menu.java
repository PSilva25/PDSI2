package Cadastros;

import Getters_e_Setters.Menu;
import Backgrounds.BG_CadAlimentos_Frios;
import Backgrounds.BG_CadPedidos;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import Getters_e_Setters.Registro;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import projeto_pdsi_ii.*;


public class Cadastro_Menu extends JFrame implements ActionListener {
    
    JButton Voltar = new JButton("Voltar");
    JButton Adicionar = new JButton("Cadastrar Pedido");
 
    Menu dados_Menu = new Menu();
    
    JTextField Pega_Nome_Pedido = new JTextField();
    JTextField Pega_Preco = new JTextField();
    
    int ID = 0;
    
    DAO c = new DAO();
  
    public Cadastro_Menu() {
              
        Font fonte = new Font("SansSerif", Font.BOLD, 15);
       
        JLabel Pedido = new JLabel("Nome do Pedido:");
        Pega_Nome_Pedido.setBounds(285, 225, 210, 30);
        Pedido.setBounds(160, 220, 130, 40);
        Pega_Nome_Pedido.setFont(fonte);
        Pedido.setFont(fonte);
        add(Pega_Nome_Pedido);
        add(Pedido);
                       
        JLabel Preco = new JLabel("Preço por Porção:");
        Pega_Preco.setBounds(780, 225, 100, 30);
        Preco.setBounds(650, 220, 130, 40);
        Pega_Preco.setFont(fonte);
        Preco.setFont(fonte);
        add(Pega_Preco);
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
       
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new BG_CadPedidos());
        setBackground(Color.black);
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(1100, 550);
        setLocationRelativeTo(null);
        setVisible(true);
   
    }
    
    
    public void preenche(){
        
        dados_Menu.setLanche((String) Pega_Nome_Pedido.getText());
        dados_Menu.setPreco(Float.parseFloat(Pega_Preco.getText()));
         
    }
 
    
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Adicionar) {
            
            try {
                
                preenche();               
                ArmazenaDados();                
                PegaID();               
                new Ingredientes(ID);
                             
            } catch (SQLException ex) {
 
            }
        
        }else if (e.getSource() == Voltar) {
            
            dispose();
           
        }
        
    }
 
    
    public void ArmazenaDados() throws SQLException {
  
        c.conexao();
        
        String sql = "insert into cardapio (Nome, Preco) values (?,?);";

        PreparedStatement stmt = c.conn.prepareStatement(sql);
                
        try {
                
            stmt.setString(1,dados_Menu.getLanche());
            stmt.setFloat(2,dados_Menu.getPreco());
           
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar este produto!");                        
            Logger.getLogger(Cadastro_de_Bebidas.class.getName()).log(Level.SEVERE, null, ex);
                       
        } 
          
        stmt.execute();

        stmt.close();
        
    }

    
    public void PegaID() {
        
        c.conexao();

        c.executaSQL("select * from cardapio where Nome = '" + Pega_Nome_Pedido.getText()+ "'");
        
        try {

            c.rs.first();

            ID = c.rs.getInt("ID_Lanche");

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }       
        
        Pega_Nome_Pedido.setText("");
        Pega_Preco.setText("");
                   
    }
    
    
    public static void main(String [] args){
        
       // new Cadastro_Menu();
        
    }
    
    
    
}