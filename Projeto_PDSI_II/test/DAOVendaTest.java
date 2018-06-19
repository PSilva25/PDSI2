/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Model.Produto;
import Model.Venda;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rafael PSilva
 */
public class DAOVendaTest extends TestCase{
     Venda v = null;
     DAOVenda instance = new DAOVenda();
     DAOProduto instanceprod = new DAOProduto();
     ArrayList<Produto> list;
     
    public DAOVendaTest() {
      
    }

    
    @Before
    public void setUp() {
        Produto p = new Produto(888888,20, "Tetse 1", "Marca Teste 1", "Modelo Teste 1", "Descicao Teste 1", 47);
        Produto p1 = new Produto(99999,10, "Tetse 1", "Marca Teste 1", "Modelo Teste 1", "Descicao Teste 1", 44);
        instanceprod.save(p);
        instanceprod.save(p1);
        
        Produto p3 = instanceprod.listar_por_cod(888888).get(0);
        Produto p2 = instanceprod.listar_por_cod(99999).get(0);
        list = new ArrayList();
        list.add(p2);
        list.add(p3);
        v = new Venda("055.335.047-97", "2018-07-07", 100, list);
    }
    
    @After
    public void tearDown() {
    
    
    }

    /**
     * Test of save method, of class DAOVenda.
     */
    @Test
    public void testSaveSemData() {
        //System.out.println("save");
        v.setCpf_cli("");
        v.setData("");
        boolean result = instance.save(v);
        assertFalse("Toda venda deve ter datas!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testSaveTotalNegativo() {
        //System.out.println("save");
         v.setCpf_cli("");
        v.setTotal(-1);
        boolean result = instance.save(v);
        assertFalse("Toda venda deve Saldo total positivo!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testSaveTotalZerado() {
        //System.out.println("save");
        v.setCpf_cli("");
        v.setTotal(0);
        
        boolean result = instance.save(v);
        assertFalse("Toda venda deve ter Saldo total maior que 0!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testSaveShoppingNull() {
        //System.out.println("save");
         v.setCpf_cli("");
        ArrayList<Produto> l  = null;
        v.setShopping(l);
        boolean result = instance.save(v);
        assertFalse("Toda venda deve ter Produtos vinculados!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testSaveShoppingVazio() {
        //System.out.println("save");
        v.setCpf_cli("");
        ArrayList<Produto> l  = new ArrayList();
        v.setShopping(l);
        boolean result = instance.save(v);
        assertFalse("Toda venda deve ter Produtos vinculados!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testSave() {
        //System.out.println("save");
        v.setCpf_cli("");
        boolean result = instance.save(v);
        assertTrue("Esse cadastro deve resultar em uma venda!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    /**
     * Test of saveWhithCpf method, of class DAOVenda.
     */
    
    @Test
    public void testSaveWhithCpfSemData() {
        //System.out.println("save");
        v.setCpf_cli("");
        v.setData("");
        boolean result = instance.saveWhithCpf(v);
        assertFalse("Toda venda deve ter datas!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void ttestSaveWhithCpfTotalNegativo() {
        //System.out.println("save");
         v.setCpf_cli("");
        v.setTotal(-1);
        boolean result = instance.saveWhithCpf(v);
        assertFalse("Toda venda deve Saldo total positivo!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testSaveWhithCpfTotalZerado() {
        //System.out.println("save");
        v.setCpf_cli("");
        v.setTotal(0);
        
        boolean result = instance.saveWhithCpf(v);
        assertFalse("Toda venda deve ter Saldo total maior que 0!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testSaveWhithCpfShoppingNull() {
        //System.out.println("save");
         v.setCpf_cli("");
        ArrayList<Produto> l  = null;
        v.setShopping(l);
        boolean result = instance.saveWhithCpf(v);
        assertFalse("Toda venda deve ter Produtos vinculados!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testSaveWhithCpfShoppingVazio() {
        //System.out.println("save");
        v.setCpf_cli("");
        ArrayList<Produto> l  = new ArrayList();
        v.setShopping(l);
        boolean result = instance.saveWhithCpf(v);
        assertFalse("Toda venda deve ter Produtos vinculados!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testSaveWhithCpf() {
        //System.out.println("save");
        boolean result = instance.saveWhithCpf(v);
        assertTrue("Esse cadastro deve resultar em uma venda!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    

    /**
     * Test of listSave method, of class DAOVenda.
     */
    @Test
    public void testListSaveValorMaiorMaior() {
        ArrayList<Produto> shoping = list; 
        shoping.get(0).setQuant(shoping.get(0).getQuant()+1);
        boolean result = instance.listSave(shoping);
        assertFalse("O valor de quantidade de produto Não deve exceder ao Salvo em Banco!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testListSave() {
        ArrayList<Produto> shoping = list; 
        shoping.get(0).setQuant(shoping.get(0).getQuant()-5);
        boolean result = instance.listSave(shoping);
        assertFalse("O valor de quantidade de produto Não deve exceder ao Salvo em Banco!",result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of verifica method, of class DAOVenda.
     */
    @Test
    public void testVerificaCpfSemData() {
        //System.out.println("save");
        v.setData("");
        boolean result = instance.verifica(v);
        assertFalse("Toda venda deve ter datas!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testVerificaTotalNegativo() {
        //System.out.println("save");
        v.setTotal(-1);
        boolean result = instance.verifica(v);
        assertFalse("Toda venda deve Saldo total positivo!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    @Test
    public void testVerificaTotalZerado() {
        //System.out.println("save");
        v.setTotal(0);        
        boolean result = instance.verifica(v);
        assertFalse("Toda venda deve ter Saldo total maior que 0!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testVerificaShoppingNull() {
        //System.out.println("save");
        ArrayList<Produto> l  = null;
        v.setShopping(l);
        boolean result = instance.verifica(v);
        assertFalse("Toda venda deve ter Produtos vinculados!",result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testVerificaShoppingVazio() {
        //System.out.println("save");
        ArrayList<Produto> l  = new ArrayList();
        v.setShopping(l);
        boolean result = instance.verifica(v);
        assertFalse("Toda venda deve ter Produtos vinculados!",result);
        // TODO review the generated test code and remove the default call to fail.
    } 
    
    @Test
    public void testVerifica() {
        boolean result = instance.verifica(v);
        assertTrue("Esse teste deve retornar true pois os valores estão corretos!", result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of verivicaListaProd method, of class DAOVenda.
     */
    @Test
    public void testVerivicaListaProdMaior() {
        
        ArrayList<Produto> shoping = list; 
        shoping.get(0).setQuant(shoping.get(0).getQuant()+1);
        boolean result = instance.verivicaListaProd(shoping);
        assertFalse("O valor de quantidade de produto Não deve exceder ao Salvo em Banco!", result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testVerivicaListaProd() {
        
        ArrayList<Produto> shoping = list; 
        shoping.get(0).setQuant(shoping.get(0).getQuant()-5);
        boolean result = instance.verivicaListaProd(shoping);
        assertTrue("O valor de quantidade de produto Não deve exceder ao Salvo em Banco!", result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of delete method, of class DAOVenda.
     */
    @Test
    public void testDeleteSemcpf() {
        ArrayList<Venda> aux = instance.listar_por_data("2018-07-07");
        v = aux.get(0);
        boolean result = instance.delete(v);
        assertTrue("Esse valor deve resultar em exclusão!", result);
    }
    @Test
    public void testDeleteComcpf() {
        ArrayList<Venda> aux = instance.listar_por_cli("055.335.047-97");
        v = aux.get(0);
        boolean result = instance.delete(v);
        assertTrue("Esse valor deve resultar em exclusão!", result);
    }

}
