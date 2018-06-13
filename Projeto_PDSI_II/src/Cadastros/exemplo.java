/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cadastros;

import Backgrounds.BG_Adicionais;
import Backgrounds.BG_Login;
import Backgrounds.BG_Pagamento;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import Getters_e_Setters.Registro;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class exemplo extends JFrame implements ActionListener{
    
    
    ArrayList<Registro> RegistroList = new ArrayList<Registro>();
    ArrayList<Registro> RegistroListAtt = new ArrayList<Registro>();
    
    ArrayList<String> LFrios = new ArrayList<>();
    ArrayList<String> LMassas = new ArrayList<>();
    ArrayList<String> LVegetais = new ArrayList<>();
   
    JRadioButton Frios = new JRadioButton("Frios");
    JRadioButton Massas = new JRadioButton("Massas");
    JRadioButton Vegetais = new JRadioButton("Vegetais"); 
    ButtonGroup group = new ButtonGroup();
    
    JComboBox<String> Representa = new JComboBox<>();
    JComboBox<String> Pega_Fios = new JComboBox<>();
    JComboBox<String> Pega_Massas = new JComboBox<>();
    JComboBox<String> Pega_Vegetais = new JComboBox<>();

    JTextField Pega_Quantidade = new JTextField();
       
    JButton Cancelar = new JButton("Cancelar");
    JButton Finalizar = new JButton("Finalizar");
    
    String Forma_Pagamento;
   
    int ID, tipo = 0;
    
    DAO c = new DAO();
  
    public exemplo() {
        
        this.RegistroList = RegistroList;      
        
        Font fonte = new Font("SansSerif", Font.BOLD, 17);
        Font fonte_botoes = new Font("SansSerif", Font.BOLD, 17);          
        
        
        Frios.setBackground(Color.decode("#009fe3"));
        Frios.setBounds(100, 40, 100, 30);
        Frios.addActionListener(this);
        Frios.setFont(fonte);
        add(Frios);
        
       
        Massas.setBackground(Color.decode("#009fe3"));
        Massas.setBounds(200, 40, 100, 30);
        Massas.addActionListener(this);
        Massas.setFont(fonte);
        add(Massas);
        
        Vegetais.setBackground(Color.decode("#009fe3"));       
        Vegetais.setBounds(320, 40, 110, 30);
        Vegetais.addActionListener(this);
        Vegetais.setFont(fonte);
        add(Vegetais);
        
        
        group.add(Frios);
        group.add(Massas);
        group.add(Vegetais);
        
        
        
        Representa.setBounds(100, 100, 150, 30);
        Representa.setFont(fonte);
        
        add(Representa);
        add(Pega_Fios);
        add(Pega_Massas);
        add(Pega_Vegetais);
        
  
        JLabel Quantidade = new JLabel("Quantidade:");
        Pega_Quantidade.setBounds(200, 155, 70, 30);
        Quantidade.setBounds(100, 150, 130, 40);
        Pega_Quantidade.setFont(fonte);
        Quantidade.setFont(fonte);
        add(Pega_Quantidade);
        add(Quantidade);
        
        Cancelar.setBorder(new Borda_Redonda(7));
        Cancelar.setBounds(25, 250, 160, 40);
        Cancelar.addActionListener(this);
        Cancelar.setFont(fonte_botoes);
        add(Cancelar);
        
        
        Finalizar.setBorder(new Borda_Redonda(7));
        Finalizar.setBounds(345, 250, 160, 40);
        Finalizar.addActionListener(this);
        Finalizar.setFont(fonte_botoes);
        add(Finalizar);

  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new BG_Adicionais());
        setBackground(Color.black);
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(530, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    
    
    public void Posiciona_Frios() {

        Pega_Fios.setBounds(100, 90, 150, 30);
        Pega_Fios.setEditable(false);
        Pega_Fios.setSelectedIndex(-1);

    }
    

    public void Posiciona_Massas() {

        Pega_Massas.setBounds(100, 90, 150, 30);
        Pega_Massas.setEditable(false);
        Pega_Massas.setSelectedIndex(-1);

    }
    

    public void Posiciona_Vegetais() {

        Pega_Vegetais.setBounds(100, 90, 150, 30);
        Pega_Vegetais.setEditable(false);
        Pega_Vegetais.setSelectedIndex(-1);

    }
    
 
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == Frios){
            
            tipo = 1;
            
            Posiciona_Frios();
            
            Representa.setVisible(false);
            Pega_Fios.setVisible(true);
            Pega_Massas.setVisible(false);
            Pega_Vegetais.setVisible(false);

            if (LFrios.isEmpty() == true) {
                
                Posiciona_Frios();
                
                Preenche_list(1);
 
                Pega_Fios.addItem("");

                for (int i = 0; i < LFrios.size(); i++) {

                    Pega_Fios.addItem(LFrios.get(i));

                }            

            }
        
        }else if (e.getSource() == Massas) {
            
            tipo = 2;    
            
            Posiciona_Massas();
            
            Representa.setVisible(false);
            Pega_Fios.setVisible(false);
            Pega_Massas.setVisible(true);
            Pega_Vegetais.setVisible(false);

            if (LMassas.isEmpty() == true) {
                
                Posiciona_Massas();
                
                Preenche_list(2);

                Pega_Massas.addItem("");

                for (int i = 0; i < LMassas.size(); i++) {

                    Pega_Massas.addItem(LMassas.get(i));

                }
                
            }
            
        }else if(e.getSource()==Vegetais) {
            
            tipo = 3;
            
            Posiciona_Vegetais();           
            
            Representa.setVisible(false);
            Pega_Fios.setVisible(false);
            Pega_Massas.setVisible(false);
            Pega_Vegetais.setVisible(true);
           
            if (LVegetais.isEmpty() == true) {
                
                Posiciona_Vegetais();
                
                Preenche_list(3);
                
                Pega_Vegetais.addItem("");
                
                for (int i = 0; i < LVegetais.size(); i++) {

                    Pega_Vegetais.addItem(LVegetais.get(i));

                }
            
            }
            
        }
        
    }
        
     
    public void Preenche_list(int bebida) {

        c.conexao();

        if(bebida == 1) c.executaSQL("select * from Cad_alimentos");
        if(bebida == 2) c.executaSQL("select * from Cad_massas");
        if(bebida == 3) c.executaSQL("select * from Cad_vegetais");

        try {

            c.rs.first();
            
            do{

                String nome = c.rs.getString("Tipo");

                if(bebida == 1) LFrios.add(nome);
                if(bebida == 2) LMassas.add(nome);
                if(bebida == 3) LVegetais.add(nome);       
            
            }while(c.rs.next());

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto!");

        }
        
    }
        
        
    public static void main(String[]args){
        
        new exemplo();
        
    }

}
