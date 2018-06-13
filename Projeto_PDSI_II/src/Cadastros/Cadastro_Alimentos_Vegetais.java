
package Cadastros;

import Backgrounds.BG_CadAlimentos_Vegetais;
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
import projeto_pdsi_ii.Login;
import projeto_pdsi_ii.Principal;
import Getters_e_Setters.Vegetais;


public class Cadastro_Alimentos_Vegetais extends JFrame implements ActionListener {
    
    Vegetais dados_V = new Vegetais();
    JButton Voltar = new JButton("Voltar");
    JButton Adicionar = new JButton("Cadastrar");
 

    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_QuantsPocKG = new JTextField();
    JTextField Pega_QuantidadeKG = new JTextField();
    JTextField Pega_Preco = new JTextField();

    
    JComboBox<String> Pega_Vegetais = new JComboBox<>();
    
    
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
        
        Pega_Vegetais.addItem("");
        Pega_Vegetais.addItem("Tomate");
        Pega_Vegetais.addItem("Alface");
        
            
        
        
        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 265, 210, 30);
        Fornecedor.setBounds(180, 260, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);
        
        
                
                
        JLabel Quantidade = new JLabel("Quantidade(KG):");
        Pega_QuantidadeKG.setBounds(775, 165, 100, 30);
        Quantidade.setBounds(650, 160, 130, 40);
        Pega_QuantidadeKG.setFont(fonte);
        Quantidade.setFont(fonte);
        add(Pega_QuantidadeKG);
        add(Quantidade);
        
       
    
        JLabel UKG = new JLabel("Quant. por Porção:");
        Pega_QuantsPocKG.setBounds(790, 225, 100, 30);
        UKG.setBounds(650, 220, 160, 40);
        Pega_QuantsPocKG.setFont(fonte);
        UKG.setFont(fonte);
        add(Pega_QuantsPocKG);
        add(UKG);
                
                
                
        JLabel UP = new JLabel("Preço por Kg:");
        Pega_Preco.setBounds(755, 285, 100, 30);
        UP.setBounds(650, 280, 160, 40);
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
            
        dados_V.setTipo((String) Pega_Vegetais.getSelectedItem());
        dados_V.setFornecedor(Pega_Nome_Fornecedor.getText());
        dados_V.setQuantTKG(Float.parseFloat(Pega_QuantidadeKG.getText()));
        dados_V.setPrecokg(Float.parseFloat(Pega_Preco.getText()));
        dados_V.setQunTPorC(Float.parseFloat(Pega_QuantsPocKG.getText()));
         
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
        String sql = "insert into cad_vegetais (Tipo, Fornecedor,Quantidade, preco_kg, Quantidade_porcao) values (?,?,?,?,?);";

        PreparedStatement stmt = c.conn.prepareStatement(sql);
        
        
        try {
                
            stmt.setString(1,dados_V.getTipo());
            stmt.setString(2,dados_V.getFornecedor());
            stmt.setFloat(3,dados_V.getQuantTKG());
            stmt.setFloat(4,dados_V.getPrecokg());
            stmt.setFloat(5,dados_V.getQunTPorC());
         
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar este produto!");
                        
            Logger.getLogger(Cadastro_de_Bebidas.class.getName()).log(Level.SEVERE, null, ex);
                       
       } 
   
        
        stmt.execute();

        stmt.close();
        
        Pega_Nome_Fornecedor.setText("");
        Pega_QuantidadeKG.setText("");
        Pega_Preco.setText("");
        Pega_QuantsPocKG.setText("");
           
        
    }
 
    public static void main(String [] args){
        
        new Cadastro_Alimentos_Vegetais();
        
    }

    
    
    
    
}
