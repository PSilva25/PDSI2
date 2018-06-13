
package Cadastros;

import Backgrounds.BG_CadAlimentos_Massas;
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
import Getters_e_Setters.Massas;
import projeto_pdsi_ii.Principal;


public class Cadastro_Alimentos_Massas extends JFrame implements ActionListener {
    
    Massas dados_M = new Massas();
    JButton Voltar = new JButton("Voltar");
    JButton Adicionar = new JButton("Cadastrar");
 

    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_Quantidade = new JTextField();
    JTextField Pega_Unidade_porcao = new JTextField();

    
    JComboBox<String> Pega_Massas = new JComboBox<>();
    
    
    DAO c = new DAO();

    
    public Cadastro_Alimentos_Massas() {
        
        
        Font fonte = new Font("SansSerif", Font.BOLD, 15);

           
        JLabel Massas = new JLabel("Massas: ");
        Pega_Massas.setBounds(245, 185, 130, 30);
        Massas.setBounds(180, 180, 130, 40);
        Pega_Massas.setFont(fonte);
        Massas.setFont(fonte);
        add(Pega_Massas);
        add(Massas);       
        
        Pega_Massas.addItem("");
        Pega_Massas.addItem("pão hamburguer");
        Pega_Massas.addItem("pão sanduichi");
        Pega_Massas.addItem("pão hamburguer");
      
            
        
        
        JLabel Fornecedor = new JLabel("Fornecedor:");
        Pega_Nome_Fornecedor.setBounds(275, 265, 210, 30);
        Fornecedor.setBounds(180, 260, 130, 40);
        Pega_Nome_Fornecedor.setFont(fonte);
        Fornecedor.setFont(fonte);
        add(Pega_Nome_Fornecedor);
        add(Fornecedor);
                
        
        JLabel UP = new JLabel("Unidades:");
        Pega_Quantidade.setBounds(735, 185, 100, 30);
        UP.setBounds(655, 180, 160, 40);
        Pega_Quantidade.setFont(fonte);
        UP.setFont(fonte);
        add(Pega_Quantidade);
        add(UP);
        
        
        
        
        JLabel UP1 = new JLabel("Uni. por porção:");
        Pega_Unidade_porcao.setBounds(780, 265, 100, 30);
        UP1.setBounds(655, 260, 160, 40);
        Pega_Unidade_porcao.setFont(fonte);
        UP1.setFont(fonte);
        add(Pega_Unidade_porcao);
        add(UP1);
        
        
        
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
        add(new BG_CadAlimentos_Massas());
        setBackground(Color.black);
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(1100, 550);
        setLocationRelativeTo(null);
        setVisible(true);

    
    }
    
     public void preenche(){
    
            
            dados_M.setTipo((String) Pega_Massas.getSelectedItem());
            dados_M.setFornecedor(Pega_Nome_Fornecedor.getText());
            dados_M.setQuantidade(Integer.parseInt(Pega_Quantidade.getText()));
            dados_M.setQuant_porcao(Integer.parseInt(Pega_Unidade_porcao.getText()));
         
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
        String sql = "insert into cad_massas (Tipo, Fornecedor,Quantidade, Quantidade_porcao) values (?,?,?,?);";

        PreparedStatement stmt = c.conn.prepareStatement(sql);
        
        
        try {
                
            stmt.setString(1,dados_M.getTipo());
            stmt.setString(2,dados_M.getFornecedor());
            stmt.setFloat(3,dados_M.getQuantidade());
            stmt.setFloat(4,dados_M.getQuant_porcao());
         
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar este produto!");
                        
            Logger.getLogger(Cadastro_de_Bebidas.class.getName()).log(Level.SEVERE, null, ex);
                       
       } 
   
        
        stmt.execute();

        stmt.close();
        
    }

    
    
    
    public static void main(String [] args){
        
        new Cadastro_Alimentos_Massas();
        
    }

 
    
    
}
