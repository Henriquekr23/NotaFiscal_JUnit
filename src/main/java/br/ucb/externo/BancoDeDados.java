package main.java.br.ucb.externo;

import main.java.br.ucb.entidade.Cliente;
import main.java.br.ucb.entidade.Produto;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Produto> produtos = new ArrayList<>();

    public static void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }
    public static void addProduto(Produto produto) {
        produtos.add(produto);
    }

    public static Cliente getCliente(String cpf) {
        for(Cliente c: clientes) {
            if (c.getCPF().equals(cpf)) {
                return c;
            }
        }
        throw new NullPointerException("Cliente não existe na banco de dados");
    }

    public static Produto getProduto(String nome) {
        for(Produto p: produtos) {
            if (p.getNome().equals(nome)) {
                return p;
            }
        }
        throw new NullPointerException("Produto não existe ou está sem estoque");
    }
}
