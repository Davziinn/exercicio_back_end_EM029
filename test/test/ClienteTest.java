package test;

import dao.ClienteDAO;
import dao.IClienteDAO;
import domain.Cliente;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
public class ClienteTest {
    @Test
    public void cadastrarTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();
        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Davi");

        Integer qtd = dao.cadastrar(cliente);
        assertTrue(qtd == 1);

        Cliente clienteDB = dao.consultar(cliente.getCodigo());
        assertNotNull(clienteDB);
        assertNotNull(clienteDB.getId_cliente());
        assertEquals(cliente.getCodigo(), clienteDB.getCodigo());
        assertEquals(cliente.getNome(), clienteDB.getNome());

        Integer qtdDel = dao.excluir(clienteDB);
        assertNotNull(qtdDel == 1);
    }

    @Test
    public void atualizarTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();
        Cliente cliente = new Cliente();
        cliente.setCodigo("02");
        cliente.setNome("João");

        Integer qtd = dao.cadastrar(cliente);
        assertTrue(qtd == 1);

        cliente.setNome("João Atualizado");
        Integer qtdAtualizada = dao.atualizar(cliente);
        assertTrue(qtdAtualizada == 1);

        Cliente clienteAtualizado = dao.consultar(cliente.getCodigo());
        assertNotNull(clienteAtualizado);
        assertEquals("João Atualizado", clienteAtualizado.getNome());

        Integer qtdDel = dao.excluir(clienteAtualizado);
        assertNotNull(qtdDel == 1);
    }

    @Test
    public void buscarTodosTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();

        Cliente cliente1 = new Cliente();
        cliente1.setCodigo("03");
        cliente1.setNome("Carlos");
        dao.cadastrar(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setCodigo("04");
        cliente2.setNome("Maria");
        dao.cadastrar(cliente2);

        List<Cliente> clientes = dao.buscarTodos();
        assertNotNull(clientes);
        assertTrue(clientes.size() >= 2);

        boolean encontrouCliente1 = clientes.stream().anyMatch(c -> c.getCodigo().equals("03") && c.getNome().equals("Carlos"));
        boolean encontrouCliente2 = clientes.stream().anyMatch(c -> c.getCodigo().equals("04") && c.getNome().equals("Maria"));
        assertTrue(encontrouCliente1);
        assertTrue(encontrouCliente2);

        dao.excluir(cliente1);
        dao.excluir(cliente2);
    }


}