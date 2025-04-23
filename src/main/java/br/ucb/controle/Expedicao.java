package main.java.br.ucb.controle;

import main.java.br.ucb.entidade.Pedido;

public class Expedicao {

    public static void processarExpedicao(Pedido pedido) {
        if (pedido.getStatus() != Pedido.Status.APROVADO) {
            System.out.println("Expedição: Pedido não está aprovado. Status atual: " + pedido.getStatus());
            return;
        }

        pedido.atualizarStatus(Pedido.Status.EM_EXPEDICAO);
        System.out.println("Expedição: Separando os produtos do pedido...");

        // Simulação da separação
        for (var produto : pedido.getProdutos()) {
            System.out.println("-> Separado: " + produto.getNome());
        }

        pedido.atualizarStatus(Pedido.Status.AGUARDANDO_PAGAMENTO);
        System.out.println("Expedição: Produtos prontos. Aguardando pagamento.");
    }
}