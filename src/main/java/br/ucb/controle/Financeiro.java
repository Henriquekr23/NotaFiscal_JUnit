package main.java.br.ucb.controle;

import main.java.br.ucb.entidade.Pedido;

public class Financeiro {

    public static void processarPagamento(Pedido pedido, boolean pagamentoRecebido) {
        if (pedido.getStatus() != Pedido.Status.AGUARDANDO_PAGAMENTO) {
            System.out.println("Pedido não está aguardando pagamento. Status atual: " + pedido.getStatus());
            return;
        }

        System.out.println("Financeiro: Gerando cobrança para o cliente " + pedido.getCliente().getNome());

        if (pagamentoRecebido) {
            pedido.atualizarStatus(Pedido.Status.PAGO);
            pedido.atualizarStatus(Pedido.Status.FINALIZADO);
            System.out.println("Pagamento recebido. Pedido finalizado!");
        } else {
            pedido.atualizarStatus(Pedido.Status.INADIMPLENTE);
            pedido.atualizarStatus(Pedido.Status.CANCELADO);
            System.out.println("Cliente inadimplente. Pedido cancelado.");
        }
    }
}