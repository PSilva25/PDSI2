
package projeto_pdsi_ii;

import Banco_de_Dados.DAO;
import Getters_e_Setters.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Decrementa_Estoque {
    
    
    DAO c = new DAO();
    

    public Decrementa_Estoque(ArrayList<Lanche> LancheList, ArrayList<Bebida> BebidaList, ArrayList<Registro> RegistroList) {       
        
        int cont = 0;
        
        if(LancheList.isEmpty() == true || BebidaList.isEmpty() == true){
                
            JOptionPane.showMessageDialog(null, "Não há dados para serem registrados!");
                
        }
        
        if(LancheList.isEmpty() == false){
             
            decrementa_frio(LancheList);
            decrementa_massa(LancheList);
            cont++;
                      
        }
        
        if(BebidaList.isEmpty() == false){
                
            decrementa_Bebida(BebidaList);
            cont++;
            
        }
        
        if(cont == 2) new Pagamento(RegistroList);
        
        
              
    }
    
  
    public void decrementa_frio(ArrayList<Lanche> LancheList){
        
        int ID = -1;
        
        ArrayList<Componentes_Pedido> CF = new ArrayList<Componentes_Pedido>();
          
        for(Lanche lanche : LancheList){
            
            c.conexao();

            c.executaSQL("select * from cardapio where Nome = '" + lanche.getPedido() + "'");

            try {

                c.rs.first();

                ID = c.rs.getInt("ID_Lanche");

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto5!");

            }
                     
            c.executaSQL("select * from cardapio_ingredientes_frios where Id = '" + ID + "'");

            try {

                c.rs.first();

                do {

                    Componentes_Pedido cp = new Componentes_Pedido();

                    cp.setNome(c.rs.getString("Frio"));

                    System.out.println("o nome é: "+cp.getNome());

                    cp.setQuantidade(c.rs.getInt("Quantidade"));

                    CF.add(cp);

                } while (c.rs.next());

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto6!");

            } 

            int Quantidade_Frio = -1;
            int nova_quantidade = -1;

            for(Componentes_Pedido cmp : CF){

                c.executaSQL("select * from estoque_frios where Tipo = '" + cmp.getNome() + "'");

                try {

                    c.rs.first();

                    Quantidade_Frio = c.rs.getInt("Quantidade");
                    String nome = c.rs.getString("Tipo");

                } catch (SQLException ex) {

                } 

                nova_quantidade = Quantidade_Frio - (cmp.getQuantidade() * lanche.getQuantidade());

                String update = "UPDATE estoque_frios SET Quantidade = ? where Tipo = '" + cmp.getNome() + "'";

                try {

                    PreparedStatement stmt = c.conn.prepareStatement(update);

                    stmt.setFloat(1, nova_quantidade);

                    stmt.executeUpdate();

                }catch (SQLException e1) {

                    JOptionPane.showMessageDialog(null, e1);

                }

            }   

            CF.clear();                  
          
        }
              
    }
    
    
    public void decrementa_massa(ArrayList<Lanche> LancheList){
        
        int ID = -1;
        
        ArrayList<Componentes_Pedido> CM = new ArrayList<Componentes_Pedido>();
        
        for(Lanche lanche : LancheList){
            
            c.conexao();

            c.executaSQL("select * from cardapio where Nome = '" + lanche.getPedido() + "'");

            try {

                c.rs.first();

                ID = c.rs.getInt("ID_Lanche");

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto7!");

            }
                     
            c.executaSQL("select * from cardapio_ingredientes_massas where Id = '" + ID + "'");

            try {

                c.rs.first();

                do {

                    Componentes_Pedido cp = new Componentes_Pedido();

                    cp.setNome(c.rs.getString("Massa"));

                    cp.setQuantidade(c.rs.getInt("Quantidade"));

                    CM.add(cp);

                } while (c.rs.next());

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto8!");

            } 

            int Quantidade_Massa = -1;
            int nova_quantidade = -1;

            for(Componentes_Pedido cmp : CM){

                c.executaSQL("select * from estoque_massas where Tipo = '" + cmp.getNome() + "'");

                try {

                    c.rs.first();

                    Quantidade_Massa = c.rs.getInt("Quantidade");
                    String nome = c.rs.getString("Tipo");

                } catch (SQLException ex) {

                
                } 

                nova_quantidade = Quantidade_Massa - (cmp.getQuantidade() * lanche.getQuantidade());

                String update = "UPDATE estoque_massas SET Quantidade = ? where Tipo = '" + cmp.getNome() + "'";
                
                try {

                    PreparedStatement stmt = c.conn.prepareStatement(update);

                    stmt.setFloat(1, nova_quantidade);

                    stmt.executeUpdate();

                } catch (SQLException e1) {

                    JOptionPane.showMessageDialog(null, e1);

                }

            }   

            CM.clear();

        }
        
    }
    
    
    public void decrementa_Bebida(ArrayList<Bebida> BebidaList){
        
        int quantidade_bebidas = 0 ;
             
        for(Bebida bebida : BebidaList){

            c.conexao();

            c.executaSQL("select * from estoque_bebidas where Nome = '"+bebida.getBebida()+"' and Volume = '"+bebida.getVolume()+"'");
            

            try {

                c.rs.first();

                quantidade_bebidas  = c.rs.getInt("Quantidade");

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Não foi possivel encontar este produto10!");

            }
                  
            int nova_quantidade = -1;
                  
            nova_quantidade = quantidade_bebidas - bebida.getQuantidade();
                                  
            String update = "UPDATE estoque_bebidas SET Quantidade = ? where Nome = '" + bebida.getBebida()+ "'and Volume = '"+bebida.getVolume()+"'";
 
            try {

                PreparedStatement stmt = c.conn.prepareStatement(update);
                
                stmt.setFloat(1, nova_quantidade);

                stmt.executeUpdate();

            } catch (SQLException e1) {

                JOptionPane.showMessageDialog(null, e1);

            }
                 
        }   
                       
    }
    
}
