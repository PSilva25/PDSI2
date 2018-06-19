
package projeto_pdsi_ii;

import Getters_e_Setters.Abertura_Caixa;
import Backgrounds.BG_Abrir_Caixa;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import Cadastros.Cadastro_Alimentos_Massas;
import Cadastros.Cadastro_de_Bebidas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import projeto_pdsi_ii.Principal;
/**
 *
 * @author Jonycássio
 */
public class abrir_caixa extends JFrame implements ActionListener{
    
    
    
    
   
    JButton Cancelar = new JButton("Cancelar");
    JButton Abrir = new JButton("Abrir Caixa");
 

    JTextField Pega_Descricao = new JTextField();
    
    JTextField Pega_Valor = new JTextField();

    Abertura_Caixa dado_C = new Abertura_Caixa();
  
    
    DAO c = new DAO();

    
    public abrir_caixa() {
        
        
        
        Font fonte = new Font("SansSerif", Font.BOLD, 17);

           
        JLabel valor = new JLabel("Valor: ");
        Pega_Valor.setBounds(185, 185, 130, 30);
        valor.setBounds(135, 180, 130, 40);
        Pega_Valor.setFont(fonte);
        valor.setFont(fonte);
        add(Pega_Valor);
        add(valor);       
        
        
      
            
        
        
        JLabel Descricao = new JLabel("Descrição:");
        Pega_Descricao.setBounds(220, 265, 210, 30);
        Descricao.setBounds(135, 260, 130, 40);
        Pega_Descricao.setFont(fonte);
        Descricao.setFont(fonte);
        add(Pega_Descricao);
        add(Descricao);
                
        
       
        
        
        
        Cancelar.setBorder(new Borda_Redonda(7));
        Cancelar.setBounds(35, 430, 160, 40);
        Cancelar.addActionListener(this);
        Cancelar.setFont(fonte);
        add(Cancelar);
        
        
        Abrir.setBorder(new Borda_Redonda(7));
        Abrir.setBounds(350, 430, 160, 40);
        Abrir.addActionListener(this);
        Abrir.setFont(fonte);
        add(Abrir);

  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new BG_Abrir_Caixa());
        setBackground(Color.black);
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(550, 550);
        setLocationRelativeTo(null);
        setVisible(true);

    
    }
    
     public void preenche(){
          
        dado_C.setDescricao(Pega_Descricao.getText());
        dado_C.setValor(Float.parseFloat(Pega_Valor.getText()));
           
    }
    
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == Abrir){
            
            try {
            
                preenche();
                ArmazenaDados();
                dispose();
            
            } catch (SQLException ex) {
            
                Logger.getLogger(abrir_caixa.class.getName()).log(Level.SEVERE, null, ex);
            
            }
            
        }else if(e.getSource()== Cancelar){
            
            dispose();
            
        }        
    
    }
    
    
    
    public void ArmazenaDados() throws SQLException {
        
        c.conexao();
        
        Date agora = new Date();
        DateFormat mostradata;
        mostradata = DateFormat.getDateInstance(DateFormat.SHORT);
       String x = mostradata.format(agora);
        System.out.println(x);
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
        String dataFormatada = sdf.format(hora);
        System.out.println(dataFormatada);
        
        
        String sql = "insert into caixa_abertura (Descricao, Valor_abertura, Data_abertura, Hora_Abertura) values (?,?,?,?);";           
            
        PreparedStatement stmt = c.conn.prepareStatement(sql);

        try {

            stmt.setString(1,dado_C.getDescricao());
            stmt.setFloat(2,dado_C.getValor());
            stmt.setString(3,x);
            stmt.setString(4, dataFormatada);


        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel registrar este processo!");

            Logger.getLogger(Cadastro_de_Bebidas.class.getName()).log(Level.SEVERE, null, ex);

        } 


        stmt.execute();

        stmt.close();

        
    }
    
    
    
    public static void main(String [] args){
        
        new abrir_caixa();
        
    }

   
    
    
}
