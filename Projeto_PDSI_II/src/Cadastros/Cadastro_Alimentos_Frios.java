
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


public class Cadastro_Alimentos_Frios extends JFrame implements ActionListener {
    
    JButton Voltar = new JButton("Voltar");
    JButton Adicionar = new JButton("Cadastrar Frios");
 
    Cadastro_Alimento dados_AL = new Cadastro_Alimento();
    
    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_Preco = new JTextField();
    JTextField Pega_Quantidade = new JTextField();
    
    JTextField Pega_Frios = new JTextField();
       
    DAO c = new DAO();
   
    
    public Cadastro_Alimentos_Frios() {
              
        Font fonte = new Font("SansSerif", Font.BOLD, 15);
          
        JLabel Frios = new JLabel("Frios: ");
        Pega_Frios.setBounds(230, 185, 210, 30);
        Frios.setBounds(180, 180, 130, 40);
        Pega_Frios.setFont(fonte);
        Pega_Frios.setEnabled(true);
        Frios.setFont(fonte);
        add(Pega_Frios);
        add(Frios); 
                      
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
           
        JLabel UKG = new JLabel("Preço pde Compra:");
        Pega_Preco.setBounds(720, 275, 100, 30);
        UKG.setBounds(720, 240, 140, 40);
        Pega_Preco.setFont(fonte);
        UKG.setFont(fonte);
        add(Pega_Preco);
        add(UKG);
                       
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
        add(new BG_CadAlimentos_Frios());
        setBackground(Color.black);
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(1100, 550);
        setLocationRelativeTo(null);
        setVisible(true);
   
    }
    
    
    public void preenche(){
        
        dados_AL.setTipo((String) Pega_Frios.getText());
        dados_AL.setFornecedor(Pega_Nome_Fornecedor.getText());
        dados_AL.setQuantT(Float.parseFloat(Pega_Quantidade.getText()));
        dados_AL.setPreco(Float.parseFloat(Pega_Preco.getText()));
       
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
        
        String sql = "insert into estoque_frios (Tipo, Fornecedor, Quantidade, Preco_de_compra) values (?,?,?,?);";

        PreparedStatement stmt = c.conn.prepareStatement(sql);
              
        try {
                
            stmt.setString(1,dados_AL.getTipo());
            stmt.setString(2,dados_AL.getFornecedor());
            stmt.setFloat(3,dados_AL.getQuantT());
            stmt.setFloat(4,dados_AL.getPreco());
           
        
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar este produto!");
                        
            Logger.getLogger(Cadastro_de_Bebidas.class.getName()).log(Level.SEVERE, null, ex);
                       
       } 
           
       stmt.execute();

       stmt.close();
       
       
       Pega_Frios.setText("");
       Pega_Nome_Fornecedor.setText("");
       Pega_Quantidade.setText("");
       Pega_Preco.setText("");
        
    }

    
    public static void main(String [] args){
        
        new Cadastro_Alimentos_Frios();
        
    }
    
    
    
}
