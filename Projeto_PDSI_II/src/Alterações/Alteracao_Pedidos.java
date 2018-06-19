
package Alterações;

import Cadastros.*;
import Backgrounds.*;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
import Getters_e_Setters.*;
import Listagem.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Alteracao_Pedidos extends JFrame implements ActionListener {
    
    int Id = 0;
    
    String[]botoes = {"SIM", "NAO"};
    
    JButton Cancelar = new JButton("Cancelar");
    JButton Alterar = new JButton("Alterar");

    Menu dados_pedidos = new Menu();
   
    JTextField Pega_Nome_Pedido = new JTextField();
    JTextField Pega_Preco = new JTextField();
   
    DAO c = new DAO();
   
    
    public Alteracao_Pedidos(int ID) throws SQLException  {
        
        this.Id = ID;
        
        ArmazenaDados(Id);
               
        Font fonte = new Font("SansSerif", Font.BOLD, 15);
       
        JLabel Pedido = new JLabel("Nome do Pedido:");
        Pega_Nome_Pedido.setBounds(285, 225, 210, 30);
        Pedido.setBounds(160, 220, 130, 40);
        Pega_Nome_Pedido.setFont(fonte);
        Pedido.setFont(fonte);
        Pega_Nome_Pedido.setText(dados_pedidos.getLanche());
        add(Pega_Nome_Pedido);
        add(Pedido);
                
        JLabel Preco = new JLabel("Preço por Porção:");
        Pega_Preco.setBounds(780, 225, 100, 30);
        Preco.setBounds(650, 220, 130, 40);
        Pega_Preco.setFont(fonte);
        Pega_Preco.setText(String.valueOf(dados_pedidos.getPreco()));
        Preco.setFont(fonte);
        add(Pega_Preco);
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
        
        if (e.getSource() == Alterar) {
          
            try {
                
                int opcao = JOptionPane.showOptionDialog(null, "Realmente deseja alterar este produto", "Aviso!!!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);

                if(botoes[opcao].equals("SIM")){

                    Atualiza(Id);
                    dispose();
                    new Listagem_Pedidos();

                }else{

                    JOptionPane.showMessageDialog(null, "OPERACAO CANCELADA");
                    dispose();
                    new Listagem_Pedidos();

                }
             
            } catch (SQLException ex) {
            
                Logger.getLogger(Alteracao_Estoque_Bebidas.class.getName()).log(Level.SEVERE, null, ex);
            
            }
            
        }else if (e.getSource() == Cancelar) {

            dispose();
            new Listagem_Pedidos();
     
        } 
    
    }
    
    
    public void ArmazenaDados(int ID) throws SQLException{

        c.conexao();
               
        c.executaSQL("select * from cardapio where ID_Lanche = " + ID);
   
        try {

            c.rs.first();                   
                    
            dados_pedidos.setLanche(c.rs.getString("Nome"));
            dados_pedidos.setPreco((c.rs.getFloat("Preco")));       
                                        
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }             
           
    }
    
   
    public void Atualiza(int x) throws SQLException{

        c.conexao();
          
        dados_pedidos.setLanche(Pega_Nome_Pedido.getText());       
        dados_pedidos.setPreco(Float.parseFloat(Pega_Preco.getText()));
              
        String sql = "UPDATE cardapio SET Nome = ?,Preco = ? where ID_Lanche = '" + x + "'";

        try {

            PreparedStatement stmt = c.conn.prepareStatement(sql);

            stmt.setString(1, dados_pedidos.getLanche());
            stmt.setFloat(2, dados_pedidos.getPreco());
                      
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "DADOS ALTERADOS!");

        } catch (SQLException e1) {
        
            JOptionPane.showMessageDialog(null, e1);

        }

        dispose();
    
    }
         
    
    public static void main(String [] args){
        
        //new Alteracao_Pedidos();
        
    }
    
    
    
}
