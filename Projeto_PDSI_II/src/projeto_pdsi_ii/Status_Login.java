
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
                      
        if(Situacao.equals("conectado")){
            
            if(ID == 1) new Cadastro_de_Bebidas();
            if(ID == 2) new Cadastro_Alimentos_Frios();
            if(ID == 3) new Cadastro_Alimentos_Massas();
            if(ID == 4) new Cadastro_Alimentos_Vegetais();
            
            if(ID == 5) new Listagem_Bebidas();
            if(ID == 6) new Listagem_Frios();
            if(ID == 7) new Listagem_Massas();
            if(ID == 8) new Listagem_Vegetais();
            
            if(ID == 9) new abrir_caixa();
            if(ID == 10) new fechar_caixa();
            if(ID == 11) new Listagem_Caixa();
            
        }else{
            
            new Login(ID);
            
        }
                         
    }
       
}
