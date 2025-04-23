package main.java.br.ucb;

import main.java.br.ucb.entidade.Cliente;
import main.java.br.ucb.entidade.Pedido;
import main.java.br.ucb.entidade.Produto;

public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("12312312312", "Henrique Kümmel");

        Produto prod1 = new Produto("Feijão", 20.99f);
        Produto prod2 = new Produto("Arroz", 15.99f);

        Pedido pedido1 = new Pedido(cliente1);
        pedido1.adicionarProduto(prod1);
        pedido1.adicionarProduto(prod2);

        System.out.println(pedido1);

    }
}