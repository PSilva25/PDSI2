package Cadastros;

import Backgrounds.BG_CadAlimentos_Frios;
import Backgrounds.BG_CadPedidos;
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
import projeto_pdsi_ii.Cadastro_Alimento;


public class Cadastro_Pedidos extends JFrame implements ActionListener {
    
    JButton Voltar = new JButton("Voltar");
    JButton Adicionar = new JButton("Cadastrar Pedido");
 
    Cadastro_Alimento dados_AL = new Cadastro_Alimento();
    
    JTextField Pega_Nome_Pedido = new JTextField();
    JTextField Pega_Preco = new JTextField();
    
    
    DAO c = new DAO();

    
    public Cadastro_Pedidos() {
        
        
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
        
    
         
    }
    
    
    public void actionPerformed(ActionEvent e) {

      
       
        if (e.getSource() == Adicionar) {
            
            try {
                
                preenche();
                ArmazenaDados();
                
                
            } catch (SQLException ex) {
                
        

            }
        
        }

        else if (e.getSource() == Voltar) {
            
            dispose();
           
        }
    }
 
    public void ArmazenaDados() throws SQLException {

                
         
        
        c.conexao();
        String sql = "insert into cad_alimentos (Tipo, Fornecedor,QuantidadeT, Preco_de_compra, preco_total, Quantidade_kg_porcao) values (?,?,?,?,?,?);";

        PreparedStatement stmt = c.conn.prepareStatement(sql);
        
        
        try {
                
            stmt.setString(1,dados_AL.getTipo());
            stmt.setString(2,dados_AL.getFornecedor());
            stmt.setFloat(3,dados_AL.getQuantT());
            stmt.setFloat(4,dados_AL.getPreco());
            stmt.setFloat(5,dados_AL.getPreco_Total());
            stmt.setFloat(6,dados_AL.getUniPorcao());
        
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar este produto!");
                        
            Logger.getLogger(Cadastro_de_Bebidas.class.getName()).log(Level.SEVERE, null, ex);
                       
       } 
   
        
        stmt.execute();

        stmt.close();
        
    }

    
    public static void main(String [] args){
        
        new Cadastro_Pedidos();
        
    }
    
    
    
}