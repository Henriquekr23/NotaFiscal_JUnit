package main.java.br.ucb.externo;

import main.java.br.ucb.entidade.Pedido;

public class Banco {

    // Simula verificação no sistema bancário
    public static boolean verificarPagamento(Pedido pedido) {
        System.out.println("Banco: Consultando pagamento do pedido do cliente " + pedido.getCliente().getNome());

        // Simulação de lógica bancária (pode ser aleatória ou fixa)
        // Aqui vamos simular com 80% de chance de ter sido pago
        double chance = Math.random();
        boolean pago = chance < 0.8;

        System.out.println("Banco: Pagamento " + (pago ? "confirmado." : "não recebido."));
        return pago;
    }
}