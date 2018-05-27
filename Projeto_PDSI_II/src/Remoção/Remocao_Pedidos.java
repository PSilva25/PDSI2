
package Remoção;


import Backgrounds.BG_Remocao;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import Listagem.Listagem_Massas;
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
import projeto_pdsi_ii.Massas;


public class Remocao_Pedidos extends JFrame implements ActionListener {
    
    
    int Id=0;
    
    Massas dados_M = new Massas();
    
    JButton Cancelar = new JButton("Cancelar");
    JButton Remover = new JButton("Remover");
 

    JTextField Pega_Nome_Pedido = new JTextField();
    JTextField Pega_Preco = new JTextField();
    
    
    DAO c = new DAO();

    
    public Remocao_Pedidos(int ID) throws SQLException {
        
        Id = ID;
        
        Pega_Dados(Id);
        
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
          
            System.out.println("");
            Remove_dados(Id);
            
            new Listagem_Massas();
            
        } if (e.getSource() == Cancelar) {

            dispose();
            new Listagem_Massas();
     } 
    }
    
    
    public void Pega_Dados(int ID) throws SQLException{

         c.conexao();
               
        c.executaSQL("select * from Pedidos where ID_Pedido = " + ID);
   
        try {

            c.rs.first();                   
                    
            //dados_M.setTipo(c.rs.getString("Tipo"));
            //dados_M.setFornecedor((c.rs.getString("Fornecedor")));
            //dados_M.setQuantidade(c.rs.getInt("Quantidade"));
            //dados_M.setQuant_porcao(c.rs.getInt("Quantidade_porcao"));
            
                                        
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }               
       
    
    }
    
    public void Remove_dados(int Id){
        
        String sql = "delete from Pedidos where ID_Pedido='" + Id + "'";

            try {

                PreparedStatement stmt = c.conn.prepareStatement(sql);


                stmt.executeUpdate();

                //mensagem de alerta informando que os dados foram deletados
                JOptionPane.showMessageDialog(null, "DADO DELETADO!");

            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, e1);
            }

        dispose();
    }
   
    
    
    public static void main(String [] args){
        
        //new Remocao_Pedidos();
        
    }
    
    
    
}
