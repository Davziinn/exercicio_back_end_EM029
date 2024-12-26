package dao;

import domain.Produto;

import java.util.List;

public interface IProdutoDAO {
    public Integer cadastrarProduto(Produto produto) throws Exception;

    public Produto consultar(String codigo) throws Exception;

    public Integer excluir(Produto produtoDB) throws Exception;

    public Integer atualizar(Produto produto) throws Exception;

    public List<Produto> buscarTodos() throws Exception;
}
