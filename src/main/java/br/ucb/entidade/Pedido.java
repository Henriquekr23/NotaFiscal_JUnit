package main.java.br.ucb.entidade;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedido {
    private Cliente cliente;
    private LocalDateTime dataPedido;
    private ArrayList<Produto> produtos;
    private Status status;

    public enum Status {
        PENDENTE,       //Aguardando análise
        APROVADO,       //Análise aprovada, Nota fiscal emitida
        EM_EXPEDICAO,       //Separando os produtos
        AGUARDANDO_PAGAMENTO,       //Fatura emitida, aguardando recebimento
        PAGO,       //Pagamento aprovado
        FINALIZADO,         //Pedido Finalizado
        INADIMPLENTE,       //Cliente inadimplente, cobrança enviada
        CANCELADO       //Pedido cancelado
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.dataPedido = LocalDateTime.now();
        this.produtos = new ArrayList<>();
        this.status = Status.PENDENTE;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public static boolean verificacaoTransicao(Status atual, Status novo) {
        switch (atual) {
            case PENDENTE:
                return novo == Status.APROVADO || novo == Status.CANCELADO;

            case APROVADO:
                return novo == Status.EM_EXPEDICAO;

            case EM_EXPEDICAO:
                return novo == Status.AGUARDANDO_PAGAMENTO;

            case AGUARDANDO_PAGAMENTO:
                return novo == Status.PAGO || novo == Status.INADIMPLENTE;

            case PAGO:
                return novo == Status.FINALIZADO;

            case INADIMPLENTE:
                return novo == Status.CANCELADO;

            default:
                return false;
        }
    }

    public boolean atualizarStatus(Status novoStatus) {
        if (verificacaoTransicao(this.status, novoStatus)) {
            this.status = novoStatus;
            System.out.println("Status atualizado para: " + novoStatus);
            return true;
        } else {
            System.out.println("Transição inválida: " + this.status + "para " + novoStatus);
            return false;
        }
    }

    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public float calcularValorTotal() {
        float total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco();
        }
        return total;
    }
}
