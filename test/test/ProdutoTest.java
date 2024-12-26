package test;

import dao.ClienteDAO;
import dao.IClienteDAO;
import dao.IProdutoDAO;
import dao.ProdutoDAO;
import domain.Cliente;
import domain.Produto;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProdutoTest {
    @Test
    public void cadastrarTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setCodigoProduto("01");
        produto.setNome("Davi");

        Integer qtd = dao.cadastrarProduto(produto);
        assertTrue(qtd == 1);

        Produto produtoDB = dao.consultar(produto.getCodigoProduto());
        assertNotNull(produtoDB);
        assertNotNull(produtoDB.getId_produto());
        assertEquals(produto.getCodigoProduto(), produtoDB.getCodigoProduto());
        assertEquals(produto.getNome(), produtoDB.getNome());

        Integer qtdDel = dao.excluir(produtoDB);
        assertNotNull(qtdDel == 1);
    }

    @Test
    public void atualizarTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setCodigoProduto("02");
        produto.setNome("João");

        Integer qtd = dao.cadastrarProduto(produto);
        assertTrue(qtd == 1);

        produto.setNome("João Atualizado");
        Integer qtdAtualizada = dao.atualizar(produto);
        assertTrue(qtdAtualizada == 1);

        Produto produtoAtualizado = dao.consultar(produto.getCodigoProduto());
        assertNotNull(produtoAtualizado);
        assertEquals("João Atualizado", produtoAtualizado.getNome());

        Integer qtdDel = dao.excluir(produtoAtualizado);
        assertNotNull(qtdDel == 1);
    }

    @Test
    public void buscarTodosTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();

        Produto produto1 = new Produto();
        produto1.setCodigoProduto("03");
        produto1.setNome("Carlos");
        dao.cadastrarProduto(produto1);

        Produto produto2 = new Produto();
        produto2.setCodigoProduto("04");
        produto2.setNome("Maria");
        dao.cadastrarProduto(produto2);

        List<Produto> produtos = dao.buscarTodos();
        assertNotNull(produtos);
        assertTrue(produtos.size() >= 2);

        boolean encontrouCliente1 = produtos.stream().anyMatch(c -> c.getCodigoProduto().equals("03") && c.getNome().equals("Arroz"));
        boolean encontrouCliente2 = produtos.stream().anyMatch(c -> c.getCodigoProduto().equals("04") && c.getNome().equals("Feijão"));
        assertTrue(encontrouCliente1);
        assertTrue(encontrouCliente2);

        dao.excluir(produto1);
        dao.excluir(produto2);
    }
}
