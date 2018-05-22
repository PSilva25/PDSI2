
package Remoção;


import Backgrounds.BG_Remocao;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import Listagem.Listagem_Vegetais;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import projeto_pdsi_ii.Vegetais;

public class Remocao_Alimentos_Vegetais extends JFrame implements ActionListener {
    
    
    int Id=0;
    
    Vegetais dados_V = new Vegetais();
    
    JButton Cancelar = new JButton("Cancelar");
    JButton Remover = new JButton("Remover");
 

    JTextField Pega_Nome_Fornecedor = new JTextField();
    JTextField Pega_QuantsPocKG = new JTextField();
    JTextField Pega_QuantidadeKG = new JTextField();
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
        
        
                
                
        JLabel Quantidade = new JLabel("Quantidade(KG):");
        Pega_QuantidadeKG.setBounds(775, 165, 100, 30);
        Quantidade.setBounds(650, 160, 130, 40);
        Pega_QuantidadeKG.setFont(fonte);
        Pega_QuantidadeKG.setEditable(false);
        Pega_QuantidadeKG.setText(String.valueOf(dados_V.getQuantTKG()));
        Quantidade.setFont(fonte);
        add(Pega_QuantidadeKG);
        add(Quantidade);
        
       
    
        JLabel UKG = new JLabel("Quant. por Porção:");
        Pega_QuantsPocKG.setBounds(790, 225, 100, 30);
        UKG.setBounds(650, 220, 160, 40);
        Pega_QuantsPocKG.setFont(fonte);
        Pega_QuantsPocKG.setEditable(false);
        Pega_QuantsPocKG.setText(String.valueOf(dados_V.getQunTPorC()));
        UKG.setFont(fonte);
        add(Pega_QuantsPocKG);
        add(UKG);
                
                
                
        JLabel UP = new JLabel("Preço por Kg:");
        Pega_Preco.setBounds(755, 285, 100, 30);
        UP.setBounds(650, 280, 160, 40);
        Pega_Preco.setFont(fonte);
        Pega_Preco.setEditable(false);
        Pega_Preco.setText(String.valueOf(dados_V.getPrecokg()));
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
          
            Remove_dados(Id);
            
            new Listagem_Vegetais();
            
        } if (e.getSource() == Cancelar) {

            dispose();
            new Listagem_Vegetais();
     } 
    }
    
    
    public void Pega_Dados(int ID) throws SQLException{

          c.conexao();
               
        c.executaSQL("select * from cad_vegetais where ID_vegetal = " + ID);
   
        try {

            c.rs.first();                   
                    
            dados_V.setTipo(c.rs.getString("Tipo"));
            dados_V.setFornecedor((c.rs.getString("Fornecedor")));
            dados_V.setQuantTKG(c.rs.getFloat("Quantidade"));
            dados_V.setPrecokg(c.rs.getFloat("preco_kg"));
            dados_V.setQunTPorC(c.rs.getFloat("Quantidade_porcao"));
            
                                        
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }               
      
    
    }
    
    public void Remove_dados(int Id){
        
        String sql = "delete from cad_vegetais where ID_vegetal='" + Id + "'";

            try {

                PreparedStatement stmt = c.conn.prepareStatement(sql);


                stmt.executeUpdate();

                //mensagem de alerta informando que os dados foram deletados
                JOptionPane.showMessageDialog(null, "DADO DELETADO!");

            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, e1);
            }

        
    }
   
    
    
    
    public static void main(String [] args){
        
        //new Remocao_Alimentos_Vegetais();
        
    }
    
    
    
}
