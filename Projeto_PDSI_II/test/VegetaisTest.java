
import Getters_e_Setters.Vegetais;
import Banco_de_Dados.DAOVegetais;
import java.sql.SQLException;
import java.util.ArrayList;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Rafael PSilva
 */
public class VegetaisTest extends TestCase {

    Vegetais p, edit;
    DAOVegetais dao = new DAOVegetais();

    public VegetaisTest() {
    }

    @Before
    public void setUp() {
        p = new Vegetais("Tomate", "Caro", 5.0, 15.50);
    }

    @After
    public void tearDown() {
    }

// TESTES DE SALVAR ------------------------------------------------------------
    @Test
    public void testSavesemTipo() throws SQLException {
        p.setTipo("");
        boolean r = dao.salvar(p);
        assertFalse("Informe o tipo de bebida!", r);
    }

    @Test
    public void testSavesemFornecedor() throws SQLException {
        p.setFornecedor("");
        boolean r = dao.salvar(p);
        assertFalse("Informe o nome do Fornecedor!", r);
    }

    @Test
    public void testSavecomTipo() throws SQLException {
        boolean r = dao.salvar(p);
        assertTrue("Tipo cadastrado!", r);
    }

    @Test
    public void testSavecomFornecedor() throws SQLException {
        boolean r = dao.salvar(p);
        assertTrue("Fornecedor cadastrado!", r);
    }

    @Test
    public void testSavecomVolume() throws SQLException {
        boolean r = dao.salvar(p);
        assertTrue("Volume cadastrado!", r);
    }

    @Test
    public void testSalvarQtdNeg() throws SQLException {
        p.setQuantidade(-1);
        boolean r = dao.salvar(p);
        assertFalse("Cadastro deve ser maior que 0!", r);
    }

    @Test
    public void testSalvarQtdPos() throws SQLException {
        p.setQuantidade(3);
        boolean r = dao.salvar(p);
        assertTrue("Quantidade cadastrada!", r);
    }

    @Test
    public void testSalvarPreco0() throws SQLException {
        p.setPreco(0);
        boolean r = dao.salvar(p);
        assertFalse("Preço deve ser maior que 0!", r);
    }

    @Test
    public void testSalvarPrecoNeg() throws SQLException {
        p.setPreco(-1);
        boolean r = dao.salvar(p);
        assertFalse("Preço deve ser positivo!", r);
    }

    @Test
    public void testSalvarPrecoPos() throws SQLException {
        p.setPreco((float) 2.50);
        boolean r = dao.salvar(p);
        assertTrue("Preço cadastrado!", r);
    }

    @Test
    public void testSave() throws SQLException {
        boolean r = dao.salvar(p);
        assertTrue("Cadastro completo!", r);
    }

// TESTES ATUALIZAR ------------------------------------------------------------
    @Test
    public void testAtualizarSemTipo() {
        ArrayList<Vegetais> aux = dao.listarcada(3);
        edit = aux.get(0);
        edit.setTipo("");
        boolean r = dao.atualizar(edit, 3);
        assertFalse("Irformar tipo para alteração!", r);
    }

    @Test
    public void testAtualizarSemFornecedor() {
        ArrayList<Vegetais> aux = dao.listarcada(3);
        edit = aux.get(0);
        edit.setFornecedor("");
        boolean r = dao.atualizar(edit, 3);
        assertFalse("Irformar fornecedor para alteração!", r);
    }

    @Test
    public void testAtualizarTipo() {
        ArrayList<Vegetais> aux = dao.listarcada(3);
        edit = aux.get(0);
        edit.setTipo("REFRIGERANTE");
        boolean r = dao.atualizar(edit, 3);
        assertTrue("Alteração concluída!", r);
    }

    @Test
    public void testAtualizarFornecedor() {
        ArrayList<Vegetais> aux = dao.listarcada(3);
        edit = aux.get(0);
        edit.setFornecedor("Guaribas");
        boolean r = dao.atualizar(edit, 3);
        assertTrue("Alteração concluída!", r);
    }

    @Test
    public void testAtualizarQtdNeg() {
        ArrayList<Vegetais> aux = dao.listarcada(3);
        edit = aux.get(0);
        edit.setQuantidade(-1);
        boolean r = dao.atualizar(edit, 3);
        assertFalse("Quantidade deve ser positiva!", r);
    }

    @Test
    public void testAtualizarPrecoNeg() {
        ArrayList<Vegetais> aux = dao.listarcada(3);
        edit = aux.get(0);
        edit.setPreco(-1);
        boolean r = dao.atualizar(edit, 3);
        assertFalse("Preco deve ser positivo!", r);
    }

    @Test
    public void testAtualizarPreco0() {
        ArrayList<Vegetais> aux = dao.listarcada(3);
        edit = aux.get(0);
        edit.setPreco(0);
        boolean r = dao.atualizar(edit, 3);
        assertFalse("Preco deve ser maior que 0!", r);
    }

    @Test
    public void testAtualizarQuantPos() {
        ArrayList<Vegetais> aux = dao.listarcada(3);
        edit = aux.get(0);
        edit.setQuantidade(3);
        boolean r = dao.atualizar(edit, 3);
        assertTrue("Sucesso na alteração!", r);
    }

// TESTES BLINDAGENS -----------------------------------------------------------
    @Test
    public void testBlindagemSemTipo() {
        p.setTipo("");
        boolean r = dao.blindagem(p);
        assertFalse("Verificação falhou!", r);
    }

    @Test
    public void testBlindagemSemFornecedor() {
        p.setFornecedor("");
        boolean r = dao.blindagem(p);
        assertFalse("Verificação falhou!", r);
    }

    @Test
    public void testBlindagemQtdNeg() {
        p.setQuantidade(-1);
        boolean r = dao.blindagem(p);
        assertFalse("Verificação falhou!", r);
    }

    @Test
    public void testBlindagemPrecoNeg() {
        p.setPreco(-1);
        boolean r = dao.blindagem(p);
        assertFalse("Verificação falhou!", r);
    }

    @Test
    public void testBlindagemPreco0() {
        p.setPreco(0);
        boolean r = dao.blindagem(p);
        assertFalse("Verificação falhou!", r);
    }

    @Test
    public void testBlindagemQtdPos() {
        p.setQuantidade(3);
        boolean r = dao.blindagem(p);
        assertTrue("Verificação aceita!", r);
    }

    @Test
    public void testBlindagemPrecoPos() {
        p.setPreco((float) 2.50);
        boolean r = dao.blindagem(p);
        assertTrue("Verificação aceita!", r);
    }

// TESTE DELETAR ---------------------------------------------------------------
    @Test
    public void testDelete() {
        ArrayList<Vegetais> aux = dao.listarcada(3);
        edit = aux.get(0);
        boolean r = dao.deletar(3);
        assertTrue("Dado deletado!", r);
    }

}
