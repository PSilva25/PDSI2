
package projeto_pdsi_ii;


import Backgrounds.*;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import Cadastros.*;
import Getters_e_Setters.Registro;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Jonycássio
 */
public class Pagamento extends JFrame implements ActionListener{
       
    ArrayList<Registro> RegistroList = new ArrayList<Registro>();
    ArrayList<Registro> RegistroListAtt = new ArrayList<Registro>();
    
    JRadioButton PG1 = new JRadioButton("Dinheiro");
    JRadioButton PG2 = new JRadioButton("Cartão");
    
    ButtonGroup pagamento = new ButtonGroup();
    
    JButton Cancelar = new JButton("Cancelar");
    JButton Finalizar = new JButton("Finalizar");
    
    String Forma_Pagamento;
   
    int ID;
    
    DAO c = new DAO();

    
    public Pagamento(ArrayList<Registro> RegistroList) {
        
        this.RegistroList = RegistroList;
               
        Preenche(this.RegistroList);
        
               
        Font fonte = new Font("SansSerif", Font.BOLD, 20);
        Font fonte_botoes = new Font("SansSerif", Font.BOLD, 17);          
               
        PG1.setBackground(Color.decode("#009fe3"));
        PG1.setBounds(130, 100, 120, 30);
        PG1.addActionListener(this);
        PG1.setFont(fonte);
        add(PG1);
        
        
        PG2.setBackground(Color.decode("#009fe3"));       
        PG2.setBounds(310, 100, 110, 30);
        PG2.addActionListener(this);
        PG2.setFont(fonte);
        add(PG2);
        
        
        pagamento.add(PG1);
        pagamento.add(PG2);
             
        
        Cancelar.setBorder(new Borda_Redonda(7));
        Cancelar.setBounds(25, 200, 160, 40);
        Cancelar.addActionListener(this);
        Cancelar.setFont(fonte_botoes);
        add(Cancelar);
        
        
        Finalizar.setBorder(new Borda_Redonda(7));
        Finalizar.setBounds(345, 200, 160, 40);
        Finalizar.addActionListener(this);
        Finalizar.setFont(fonte_botoes);
        add(Finalizar);

  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new BG_Pagamento());
        setBackground(Color.black);
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(530, 300);
        setLocationRelativeTo(null);
        setVisible(true);

    
    }
    
  
    public void Preenche(ArrayList<Registro> a){
             
        for(Registro r : a){
            
            Registro reg = new Registro();
           
            String n = r.getNOME();
            reg.setNOME(n);

            float p = r.getPRECO();
            reg.setPRECO(p);
            
            int q = r.getQUANTIDADE();
            reg.setQUANTIDADE(q);
          
            RegistroListAtt.add(reg);
                       
        }
                      
    }
  
      
    public void actionPerformed(ActionEvent e){
        
        
        if (e.getSource() == PG1){
            
            Forma_Pagamento = "Dinheiro";
            
        }else if(e.getSource() == PG2){
            
            Forma_Pagamento = "Cartão";
            
        }
        
        
        if(e.getSource() == Finalizar){
            
            
            try {
                
                mostra();
                Registra();
                dispose();
            
            } catch (SQLException ex) {
            
                Logger.getLogger(Pagamento.class.getName()).log(Level.SEVERE, null, ex);
            
            }
                       
        }else if(e.getSource()== Cancelar){
            
           dispose();
            
        }
        
    }
    
    
    
    public void mostra(){
        
        for(Registro r : RegistroListAtt){
            
            System.out.println("Nome: "+r.getNOME());
            System.out.println("Preço: "+r.getPRECO());
            System.out.println("Quantidade: "+r.getQUANTIDADE());
            
            System.out.println("\n");
        }
        
    }
    
    
    public void Registra() throws SQLException{
        
        c.conexao();

            if(RegistroListAtt.isEmpty() ){

                JOptionPane.showMessageDialog(null, "Existe um ou mais campos vazios!");

           }else{
                            
                for(Registro r : RegistroListAtt){
                        
                    System.out.println(RegistroListAtt.size());
                    
                    Date agora = new Date();
                    DateFormat mostradata;
                    mostradata = DateFormat.getDateInstance(DateFormat.SHORT);
                    String data = mostradata.format(agora);
                    
                    String sql = "insert into registro (Pedidos, Preco, Quantidade, Total, Pagamento, Data_Registrada) values (?, ?, ?, ?, ?, ?);";
                
                    PreparedStatement stmt = c.conn.prepareStatement(sql);
                    
                    try {

                            stmt.setString(1, r.getNOME());
                            stmt.setFloat(2, r.getPRECO());
                            stmt.setInt(3, r.getQUANTIDADE());
                            stmt.setFloat(4, r.getPRECO() * r.getQUANTIDADE());
                            stmt.setString(5, Forma_Pagamento);
                            stmt.setString(6, data);
                                         

                    } catch (SQLException ex) {

                        JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar este produto!");
                        
                        Logger.getLogger(Compra.class.getName()).log(Level.SEVERE, null, ex);
                       
                    }  
                    
                    stmt.execute();
                
                    stmt.close();
               
                }
                
                
                
            }  
            
            
            
            JOptionPane.showMessageDialog(null, "Compra finalizada!");
            
            dispose();
     
        
    }
    
    
  
    public static void main(String [] args){
        
        //new Pagamento();
        
    }

   
    
    
}
