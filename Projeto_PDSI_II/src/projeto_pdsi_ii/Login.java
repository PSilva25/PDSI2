
package projeto_pdsi_ii;


import Backgrounds.BG_Login;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import Cadastros.Cadastro_Alimentos_Frios;
import Cadastros.Cadastro_Alimentos_Massas;
import Cadastros.Cadastro_Alimentos_Vegetais;
import Cadastros.Cadastro_de_Bebidas;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Jonycássio
 */
public class Login extends JFrame implements ActionListener{
    
    
    
    
   
    JButton Cancelar = new JButton("Cancelar");
    JButton Botao_Login = new JButton("Entrar");
 

    JTextField Pega_Usuario = new JTextField();
    
    JTextField Pega_Senha = new JTextField();

    
    int ID;
    
    DAO c = new DAO();

    
    public Login(int ID) {
        
        this.ID = ID;
        
        Font fonte = new Font("SansSerif", Font.BOLD, 17);

           
        JLabel Usuario = new JLabel("Usuário: ");
        Pega_Usuario.setBounds(140, 249, 275, 30);
        Usuario.setBounds(60, 240, 130, 40);
        Pega_Usuario.setFont(fonte);
        Usuario.setFont(fonte);
        add(Pega_Usuario);
        add(Usuario);
              
    
        JLabel Senha = new JLabel("Senha: ");
        Pega_Senha.setBounds(140, 311, 275, 30);
        Senha.setBounds(70, 305, 130, 40);
        Pega_Senha.setFont(fonte);
        Senha.setFont(fonte);
        add(Pega_Senha);
        add(Senha);
     
        
        Cancelar.setBorder(new Borda_Redonda(7));
        Cancelar.setBounds(35, 400, 160, 40);
        Cancelar.addActionListener(this);
        Cancelar.setFont(fonte);
        add(Cancelar);
        
        
        Botao_Login.setBorder(new Borda_Redonda(7));
        Botao_Login.setBounds(300, 400, 160, 40);
        Botao_Login.addActionListener(this);
        Botao_Login.setFont(fonte);
        add(Botao_Login);

  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new BG_Login());
        setBackground(Color.black);
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);

    
    }
    
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == Botao_Login){
            
            try {
                
                Status();
                dispose();
            
            } catch (SQLException ex) {
            
                Logger.getLogger(abrir_caixa.class.getName()).log(Level.SEVERE, null, ex);
            
            }
            
        }else if(e.getSource()== Cancelar){
            
            dispose();
            
        }
    }
    
    
    
     
    public void Status() throws SQLException{
        
        int status = 0;
              
        c.conexao();

        c.executaSQL ("select * from login ");
        
        c.rs.first(); 
   
        String user = c.rs.getString("Usuario");
        String pass = c.rs.getString("senha");
        String situacao = "conectado";
          
        System.out.println(user+" "+pass+" "+situacao);
                
        if(Pega_Usuario.getText().equals(user) && Pega_Senha.getText().equals(pass)){
                
            String sql = "UPDATE login SET Situcao=?  WHERE ID_Usuario = 1";
                  
            try {

                PreparedStatement stmt = c.conn.prepareStatement(sql);

                stmt.setString(1,situacao);

                stmt.executeUpdate();
                

                if(ID == 1) new Cadastro_de_Bebidas();
                if(ID == 2) new Cadastro_Alimentos_Frios();
                if(ID == 3) new Cadastro_Alimentos_Massas();
                if(ID == 4) new Cadastro_Alimentos_Vegetais();
                

            } catch (SQLException e1) {

                JOptionPane.showMessageDialog(null, e1);

            }
                
        }else{
                
            JOptionPane.showMessageDialog(null, "Usuario e/ou senha incorretos");
             
            Pega_Senha.setText("");
            
            Pega_Usuario.setText("");
            
            dispose();
            
            new Login(ID);
            
        }
         
           
    }
     
    
    
    public static void main(String [] args){
        
        //new Login();
        
    }

   
    
    
}
