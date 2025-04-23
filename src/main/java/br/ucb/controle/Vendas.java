package main.java.br.ucb.controle;

import main.java.br.ucb.entidade.Pedido;

public class Vendas {
    public static float venda(Pedido pedido) {
        System.out.println("Processando pedido do cliente: " + pedido.getCliente().getNome());

        if (pedido.getStatus() != Pedido.Status.PENDENTE) {
            System.out.println("Pedido não está pendente, status atual: " + pedido.getStatus());
            return 0;
        }

        pedido.atualizarStatus(Pedido.Status.APROVADO);
        pedido.atualizarStatus(Pedido.Status.EM_EXPEDICAO);
        pedido.atualizarStatus(Pedido.Status.AGUARDANDO_PAGAMENTO);

        // Aqui simulamos o pagamento (poderia ser aleatório ou vindo do usuário)
        boolean clientePagou = true; // ou false, para simular inadimplência

        if (clientePagou) {
            pedido.atualizarStatus(Pedido.Status.PAGO);
            pedido.atualizarStatus(Pedido.Status.FINALIZADO);
        } else {
            pedido.atualizarStatus(Pedido.Status.INADIMPLENTE);
            pedido.atualizarStatus(Pedido.Status.CANCELADO);
        }

        System.out.println("\nPedido final:");
        System.out.println(pedido);

        return pedido.calcularValorTotal();
    }
}
