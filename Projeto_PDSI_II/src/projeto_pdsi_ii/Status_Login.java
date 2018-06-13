/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_pdsi_ii;

import Banco_de_Dados.DAO;
import Cadastros.*;
import Listagem.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Status_Login {
         
    DAO con = new DAO();
   
    public Status_Login(int ID) {
        
        Status(ID);
        
    }
      
    public void Status(int ID){
        
        String Situacao = null;
        
        con.conexao();

        con.executaSQL("select * from login");
   
        try {

            con.rs.first();
 
            Situacao = con.rs.getString("Situcao");    
                        
        } catch (SQLException ex) {
            
            

        }
        
        System.out.println(Situacao);  
               
        if(Situacao.equals("conectado")){
            
            if(ID == 1) new Cadastro_de_Bebidas();
            if(ID == 2) new Cadastro_Alimentos_Frios();
            if(ID == 3) new Cadastro_Alimentos_Massas();
            if(ID == 4) new Cadastro_Alimentos_Vegetais();
            
            if(ID == 5) new Listagem_Bebidas();
            if(ID == 6) new Listagem_Frios();
            if(ID == 7) new Listagem_Massas();
            if(ID == 8) new Listagem_Vegetais();
            
        }else{
            
            new Login(ID);
            
        }
        
        
           
    }
     
    
    
    
    
}
