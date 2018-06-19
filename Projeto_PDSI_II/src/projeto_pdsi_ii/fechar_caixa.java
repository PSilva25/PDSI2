/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_pdsi_ii;

import Getters_e_Setters.Fechamento_Caixa;
import Backgrounds.BG_CadAlimentos_Massas;
import Backgrounds.BG_Fechar_Caixa;
import Banco_de_Dados.DAO;
import Botoes.Borda_Redonda;
import Cadastros.Cadastro_Alimentos_Massas;
import Cadastros.Cadastro_de_Bebidas;
import Getters_e_Setters.Componentes_Pedido;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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


public class fechar_caixa extends JFrame implements ActionListener{
    
           
    Fechamento_Caixa dado_F = new Fechamento_Caixa();
   
    JButton Calcular = new JButton("Calcular");
    JButton Cancelar = new JButton("Cancelar");
    JButton Fechar = new JButton("Fechar Caixa");
 
    JTextField Pega_Sangria = new JTextField();
    JTextField Pega_Vendas = new JTextField();
    JTextField Pega_Valor = new JTextField();
  
    DAO c = new DAO();
    
    float venda_dia;

    
    public fechar_caixa() {
        
        Volor_vendas_no_dia();
               
        Font fonte = new Font("SansSerif", Font.BOLD, 17);
           
        JLabel valor = new JLabel("Vendas Dia: ");
        Pega_Vendas.setBounds(170, 70, 130, 30);
        valor.setBounds(70, 63, 130, 40);
        Pega_Vendas.setFont(fonte);
        valor.setFont(fonte);
        add(Pega_Vendas);
        add(valor);       
   
        JLabel Sangria = new JLabel("Sangria:");
        Pega_Sangria.setBounds(140, 135, 150, 30);
        Sangria.setBounds(70, 130, 130, 40);
        Pega_Sangria.setFont(fonte);
        Sangria.setFont(fonte);
        add(Pega_Sangria);
        add(Sangria);
            
        JLabel Vendas = new JLabel("Total:");
        Pega_Valor.setBounds(120, 295, 100, 30);
        Vendas.setBounds(70, 290, 70, 40);
        Pega_Valor.setEditable(false);
        Pega_Valor.setFont(fonte);
        Vendas.setFont(fonte);
        add(Pega_Valor);
        add(Vendas);
        
        Calcular.setBorder(new Borda_Redonda(7));
        Calcular.setBounds(350, 180, 160, 40);
        Calcular.addActionListener(this);
        Calcular.setFont(fonte);
        add(Calcular);
                  
        Cancelar.setBorder(new Borda_Redonda(7));
        Cancelar.setBounds(35, 430, 160, 40);
        Cancelar.addActionListener(this);
        Cancelar.setFont(fonte);
        add(Cancelar);
              
        Fechar.setBorder(new Borda_Redonda(7));
        Fechar.setBounds(350, 430, 160, 40);
        Fechar.addActionListener(this);
        Fechar.setFont(fonte);
        add(Fechar);
  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        add(new BG_Fechar_Caixa());
        setBackground(Color.black);
        setResizable(false);
        setTitle("..:FastZooom:..");
        setSize(550, 550);
        setLocationRelativeTo(null);
        setVisible(true);

    
    }
    
     
    public void preenche(){
    
        dado_F.setValor(Float.parseFloat(Pega_Vendas.getText()));
        dado_F.setSangria(Float.parseFloat(Pega_Sangria.getText()));
        dado_F.setValor_total(Float.parseFloat(Pega_Valor.getText()));
 
    }
    
       
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == Fechar){
            
            if(Pega_Sangria.getText().equals("") == false || Pega_Valor.getText().equals("") == false){
                               
                try {
            
                    preenche();
                    ArmazenaDados();
                    dispose();
            
                } catch (SQLException ex) {

                    Logger.getLogger(abrir_caixa.class.getName()).log(Level.SEVERE, null, ex);

                }              
                
            } else{
                
                JOptionPane.showMessageDialog(null, "Freencha os campos antes de finalizar!");
                
            } 
            
            sai_login();
            
        }else if(e.getSource() == Calcular){
            
            if(Pega_Sangria.getText().equals("") == false){

                float calculo = venda_dia - Float.parseFloat(Pega_Sangria.getText());

                Pega_Valor.setText(String.valueOf(calculo));
              
            }else{
                
                JOptionPane.showMessageDialog(null, "Incira a sangria para efetuar o cálculo.");
                
            }
            
        }else if(e.getSource() == Cancelar){
            
            dispose();
            
        }
        
    }
       
    
    public void ArmazenaDados() throws SQLException {
        
        c.conexao();
         
        
        Date agora = new Date();
        DateFormat mostradata;
        mostradata = DateFormat.getDateInstance(DateFormat.SHORT);
        String x = mostradata.format(agora);
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
        String dataFormatada = sdf.format(hora);
        
     
        
        String sql = "insert into caixa_fechamento (Valor, Valor_sangria, valor_total, data_fechamento, hora_fechamento) values (?,?,?,?,?);";           
            
        PreparedStatement stmt = c.conn.prepareStatement(sql);

        try {

            stmt.setFloat(1,dado_F.getValor());
            stmt.setFloat(2,dado_F.getSangria());
            stmt.setFloat(3,dado_F.getValor_total());
            stmt.setString(4,x);
            stmt.setString(5, dataFormatada);
            
            

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel registrar este processo!");

            Logger.getLogger(Cadastro_de_Bebidas.class.getName()).log(Level.SEVERE, null, ex);

        } 


        stmt.execute();

        stmt.close();

        
    }
    
    
    public void Volor_vendas_no_dia(){
        
               
        float vendas = 0;
                        
        Date agora = new Date();
        DateFormat mostradata;
        mostradata = DateFormat.getDateInstance(DateFormat.SHORT);
        String data_atual = mostradata.format(agora);

        
        c.conexao();

        c.executaSQL("select Preco from registro where Data_Registrada = '" + data_atual + "'");

        try {
               
            c.rs.first();
            
            do{
                                 
                float venda = c.rs.getFloat("Preco");
                
                vendas = vendas+venda;            

            } while(c.rs.next());
                 
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto7!");

        }
       
        venda_dia = vendas;
            
        Pega_Vendas.setText(String.valueOf(venda_dia));
        Pega_Vendas.setEditable(false);
        
    }
    
    
    public void sai_login(){
        
        String situacao = "desconectado";
        
        String sql = "UPDATE login SET Situcao=?  WHERE ID_Usuario = 1";
                  
            try {

                PreparedStatement stmt = c.conn.prepareStatement(sql);

                stmt.setString(1,situacao);

                stmt.executeUpdate();
             
            }catch (SQLException e1) {

                JOptionPane.showMessageDialog(null, e1);

            }
        
    }
    
    
    public static void main(String [] args){
        
        new fechar_caixa();
        
    }

   
    
    
}
