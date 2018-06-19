
package Remoção;


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

public class Remocao_Alimentos_Vegetais extends JFrame implements ActionListener {
       
    int Id = 0;
    
    String[]botoes = {"SIM", "NAO"};
    
    Vegetais dados_V = new Vegetais();
    
    JButton Cancelar = new JButton("Cancelar");
    JButton Remover = new JButton("Remover");
 
    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_Quantidade = new JTextField();
    JTextField Pega_Preco = new JTextField();
    JTextField Pega_Vegetais = new JTextField();
      
    DAO c = new DAO();

    
    public Remocao_Alimentos_Vegetais(int ID) throws SQLException {
        
        Id = ID;
        
        Pega_Dados(Id);
        
        Font fonte = new Font("SansSerif", Font.BOLD, 15);
           
        JLabel Vegetais = new JLabel("Vegetais: ");
        Pega_Vegetais.setBounds(255, 185, 130, 30);
        Vegetais.setBounds(180, 180, 130, 40);
        Pega_Vegetais.setFont(fonte);
        Pega_Vegetais.setEditable(false);
        Pega_Vegetais.setText(dados_V.getTipo());
        Vegetais.setFont(fonte);
        add(Pega_Vegetais);
        add(Vegetais);       
        
        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 265, 210, 30);
        Fornecedor.setBounds(180, 260, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Pega_Nome_Fornecedor.setEditable(false);
        Pega_Nome_Fornecedor.setText(dados_V.getFornecedor());
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);
               
        JLabel Quantidade = new JLabel("Quantidade:");
        Pega_Quantidade.setBounds(720, 195, 100, 30);
        Quantidade.setBounds(720, 160, 130, 40);
        Pega_Quantidade.setFont(fonte);
        Pega_Quantidade.setEditable(false);
        Pega_Quantidade.setText(String.valueOf(dados_V.getQuantidade()));
        Quantidade.setFont(fonte);
        add(Pega_Quantidade);
        add(Quantidade);   
                
        JLabel UP = new JLabel("Preço de Compra:");
        Pega_Preco.setBounds(720, 275, 100, 30);
        UP.setBounds(720, 240, 140, 40);
        Pega_Preco.setFont(fonte);
        Pega_Preco.setEditable(false);
        Pega_Preco.setText(String.valueOf(dados_V.getPreco()));
        UP.setFont(fonte);
        add(Pega_Preco);
        add(UP);
       
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
          
            int opcao = JOptionPane.showOptionDialog(null, "Realmente deseja excluir este produto", "Aviso", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
           
            if(botoes[opcao].equals("SIM")){
            
                Remove_dados(Id);
                
                dispose();
                new Listagem_Vegetais();
            
            }else{
                
                JOptionPane.showMessageDialog(null, "OPERACAO CANCELADA");
                
                dispose();
                new Listagem_Vegetais();
            
            }
                       
        }else if (e.getSource() == Cancelar) {

            dispose();
            new Listagem_Vegetais();
            
        }
        
    }
    
    
    public void Pega_Dados(int ID) throws SQLException{

        c.conexao();
               
        c.executaSQL("select * from estoque_vegetais where ID_alimento = " + ID);
   
        try {

            c.rs.first();                   
                    
            dados_V.setTipo(c.rs.getString("Tipo"));
            dados_V.setFornecedor((c.rs.getString("Fornecedor")));
            dados_V.setQuantidade(c.rs.getFloat("Quantidade"));
            dados_V.setPreco(c.rs.getFloat("Preco_de_Compra"));
                                                    
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }               
         
    }
    
   
    public void Remove_dados(int Id){
        
        String sql = "delete from estoque_vegetais where ID_alimento='" + Id + "'";

        try {

            PreparedStatement stmt = c.conn.prepareStatement(sql);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "DADO DELETADO!");

        } catch (SQLException e1) {
             
            JOptionPane.showMessageDialog(null, e1);
            
        }
       
    }
   
      
    public static void main(String [] args){
        
        //new Remocao_Alimentos_Vegetais();
        
    }
    
    
    
}
