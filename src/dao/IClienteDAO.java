package dao;

import domain.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface IClienteDAO {
    public Integer cadastrar(Cliente cliente) throws Exception;

    public Cliente consultar(String codigo) throws Exception;

    public Integer excluir(Cliente clienteDB) throws Exception;

    public Integer atualizar (Cliente cliente) throws Exception;

    public List<Cliente> buscarTodos() throws SQLException;
}
