/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_pdsi_ii;

import java.util.Date;
import java.text.SimpleDateFormat;
import Backgrounds.BG_Principal;
import Banco_de_Dados.DAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import Cadastros.*;
import Listagem.*;
import java.awt.List;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Jonycássio
 */
public class Principal extends JFrame implements ActionListener{
    
    
    Font fonte = new Font("SansSerif", Font.BOLD, 15);  
    
    JMenuBar Barra = new JMenuBar();
    
    JMenu Menu1 = new JMenu("Venda");
    JMenuItem Item1_1 = new JMenuItem("Efetuar Venda");
    
    JMenu Menu2 = new JMenu("Cadastros");
    JMenuItem Item2_1 = new JMenuItem("Cardápio");
    JMenuItem Item2_2 = new JMenuItem("Bebidas");
    JMenu MenuItem2_3 = new JMenu("Alimentos");
    JMenuItem Item2_3_1 = new JMenuItem("Frios");
    JMenuItem Item2_3_2 = new JMenuItem("Massas");
    JMenuItem Item2_3_3 = new JMenuItem("Vegetais");
      
    
    JMenu Menu3 = new JMenu("Listagem");
    JMenuItem Item3_1 = new JMenuItem("Cardápio");
    JMenuItem Item3_2 = new JMenuItem("Bebidas");
    JMenu MenuItem3_3 = new JMenu("Alimentos");
    JMenuItem Item3_3_1 = new JMenuItem("Frios");
    JMenuItem Item3_3_2 = new JMenuItem("Massas");
    JMenuItem Item3_3_3 = new JMenuItem("Vegetais");
    
    
    
    JMenu Menu4 = new JMenu("Caixa");
    JMenuItem Item4_1 = new JMenuItem("Abrir Caixa");
    JMenuItem Item4_2 = new JMenuItem("Fechar Caixa");
    
    JMenu Menu5 = new JMenu("Sobre");
   
    JButton caixa = new JButton();
    
    DAO con = new DAO();
    
    public Principal(){
        
        
        
        Item1_1.setFont(fonte);
        Item1_1.addActionListener(this);
        
        Item2_1.setFont(fonte);
        Item2_1.addActionListener(this);
        
        Item2_2.setFont(fonte);
        Item2_2.addActionListener(this);
        
        Item2_3_1.setFont(fonte);
        Item2_3_1.addActionListener(this);
        
        Item2_3_2.setFont(fonte);
        Item2_3_2.addActionListener(this);
        
        Item2_3_3.setFont(fonte);
        Item2_3_3.addActionListener(this);
        
        Item3_1.setFont(fonte);
        Item3_1.addActionListener(this);
        
        Item3_2.setFont(fonte);
        Item3_2.addActionListener(this);
        
        Item3_3_1.setFont(fonte);
        Item3_3_1.addActionListener(this);
        
        Item3_3_2.setFont(fonte);
        Item3_3_2.addActionListener(this);
        
        Item3_3_3.setFont(fonte);
        Item3_3_3.addActionListener(this);
        
        Item4_1.setFont(fonte);
        Item4_1.addActionListener(this);
        
        Item4_2.setFont(fonte);
        Item4_2.addActionListener(this);
        
        MenuItem2_3.setFont(fonte);
        MenuItem3_3.setFont(fonte);
        
        
        
        Menu1.add(Item1_1);
        Menu1.setFont(fonte);
        
        ///////////////////////////////////
        
        Menu2.add(Item2_1);
        Menu2.add(Item2_2);
        
        MenuItem2_3.add(Item2_3_1);
        MenuItem2_3.add(Item2_3_2);
        MenuItem2_3.add(Item2_3_3);
        
        Menu2.add(MenuItem2_3);
        Menu2.setFont(fonte);
        
        ///////////////////////////////////
        
        Menu3.add(Item3_1);
        Menu3.add(Item3_2);
        
        MenuItem3_3.add(Item3_3_1);
        MenuItem3_3.add(Item3_3_2);
        MenuItem3_3.add(Item3_3_3);
        
        Menu3.add(MenuItem3_3);
        Menu3.setFont(fonte);
        
        ///////////////////////////////////
        
        Menu4.add(Item4_1);
        Menu4.add(Item4_2);
        Menu4.setFont(fonte);
        
    
        Barra.setBounds(0,0,1100,40);
        add(Barra);
        
        Barra.add(Menu1);
        Barra.add(Menu2);
        Barra.add(Menu3);
        Barra.add(Menu4);
       
        Barra.setBackground(Color.GREEN);
        
        
        caixa.setBounds(10, 450, 15, 15);
        
        if(Status() == 1){
            
            caixa.setBackground(Color.GREEN);
        
        }else{
            
            caixa.setBackground(Color.RED);
        
        }
        
        add(caixa);
        
        
        add(new BG_Principal());
        setBackground(Color.yellow);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);      
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(1100, 550);
        setLocationRelativeTo(null);
        setVisible(true);
              
        
    }
    
    
    public int Status(){
        
        int status = 0;
        
        Date agora = new Date();
        DateFormat mostradata;
        mostradata = DateFormat.getDateInstance(DateFormat.SHORT);
        String x = mostradata.format(agora);
        
        ArrayList<String> PegaData = new ArrayList<>();
        
        con.conexao();

        con.executaSQL("select * from abertura_caixa");
   
        try {

            con.rs.first();
                   
            do{     
               
                String data = con.rs.getString("Data_abertura");
                
                PegaData.add(data);
  
            }while (con.rs.next());
                        
        } catch (SQLException ex) {

        }
        
        
        for(int i = 0; i < PegaData.size(); i++){
            
            if(x.equals(PegaData.get(i))){
                
                status = 1;
                break;
                
            }
            
        }
        
        return status;  
           
    }
     
    
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == Item1_1) new Compra();
        
        if(e.getSource() == Item2_1) new Cadastro_Pedidos();
        if(e.getSource() == Item2_2) new Status_Login(1);
        if(e.getSource() == Item2_3_1) new Status_Login(2);
        if(e.getSource() == Item2_3_2) new Status_Login(3);
        if(e.getSource() == Item2_3_3) new Status_Login(4);
        
        if(e.getSource() == Item3_1) new Listagem_Pedidos();
        if(e.getSource() == Item3_2) new Status_Login(5);
        if(e.getSource() == Item3_3_1) new Status_Login(6);
        if(e.getSource() == Item3_3_2) new Status_Login(7);
        if(e.getSource() == Item3_3_3) new Status_Login(8);
        
        if(e.getSource() == Item4_1) new abrir_caixa();
        if(e.getSource() == Item4_2) new fechar_caixa();
        
       
    }
     

    public static void main(String[]args) {
        
               
        new Principal();
        
        
        
    }
    
    
}
