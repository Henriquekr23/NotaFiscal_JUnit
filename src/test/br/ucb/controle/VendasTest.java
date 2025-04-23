package test.br.ucb.controle;

import main.java.br.ucb.controle.Expedicao;
import main.java.br.ucb.controle.Financeiro;
import main.java.br.ucb.entidade.Cliente;
import main.java.br.ucb.entidade.Pedido;
import main.java.br.ucb.entidade.Produto;
import main.java.br.ucb.externo.BancoDeDados;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendasTest {

    @BeforeAll
    static void antes() {
        Cliente c = new Cliente("12345678901", "Alleph");
        BancoDeDados.addCliente(c);
        Produto p1 = new Produto("iPhone 16 Pro Max", 8000);
        Produto p2 = new Produto("Kinder Ovo", 16);
        BancoDeDados.addProduto(p1);
        BancoDeDados.addProduto(p2);
    }

    // Teste de venda
    @Test
    void venda() {
        Cliente cliente = BancoDeDados.getCliente("12345678901");
        Pedido pedido = new Pedido(cliente);
        Produto iphone = BancoDeDados.getProduto("iPhone 16 Pro Max");
        pedido.adicionarProduto(iphone);
        float valorTotaldoPedido = iphone.getPreco();
        for (int i = 0; i < 7; i++) {
            Produto kinder = BancoDeDados.getProduto("Kinder Ovo");
            pedido.adicionarProduto(kinder);
            valorTotaldoPedido += kinder.getPreco();
        }

        assertEquals(valorTotaldoPedido, pedido.calcularValorTotal());
    }

    //Teste de produto
    @Test
    public void deveCriarProdutoValido() {
        Produto produto = new Produto("Cadeira", 150.0f);
        assertEquals("Cadeira", produto.getNome());
        assertEquals(150.0f, produto.getPreco(), 0.01f);
    }

    //Teste de cliente
    @Test
    public void deveCriarClienteValido() {
        Cliente cliente = new Cliente("Henrique", "123.456.789-00");
        assertEquals("Henrique", cliente.getNome());
        assertEquals("123.456.789-00", cliente.getCPF());
    }

    //Teste de pedido
    @Test
    public void deveAdicionarProdutosAoPedido() {
        Pedido pedido = new Pedido(new Cliente("Carlos", "111.222.333-44"));
        pedido.adicionarProduto(new Produto("Mesa", 300f));
        pedido.adicionarProduto(new Produto("Cadeira", 150f));

        assertEquals(2, pedido.getProdutos().size());
        assertEquals(450f, pedido.calcularValorTotal(), 0.01f);
    }

    //Teste de status do pedido
    @Test
    public void transicaoStatusValida() {
        assertTrue(Pedido.verificacaoTransicao(Pedido.Status.EM_EXPEDICAO, Pedido.Status.APROVADO));
        assertTrue(Pedido.verificacaoTransicao(Pedido.Status.APROVADO, Pedido.Status.AGUARDANDO_PAGAMENTO));
    }

    @Test
    public void transicaoStatusInvalida() {
        assertFalse(Pedido.verificacaoTransicao(Pedido.Status.EM_EXPEDICAO, Pedido.Status.FINALIZADO));
        assertFalse(Pedido.verificacaoTransicao(Pedido.Status.AGUARDANDO_PAGAMENTO, Pedido.Status.APROVADO));
    }

    //Teste de expedição
    @Test
    public void deveProcessarExpedicaoAprovada() {
        Pedido pedido = new Pedido(new Cliente("Carlos", "000.000.000-00"));
        pedido.adicionarProduto(new Produto("Monitor", 800f));
        pedido.atualizarStatus(Pedido.Status.APROVADO);

        Expedicao.processarExpedicao(pedido);

        assertEquals(Pedido.Status.AGUARDANDO_PAGAMENTO, pedido.getStatus());
    }

    @Test
    public void naoDeveProcessarExpedicaoSemAprovacao() {
        Pedido pedido = new Pedido(new Cliente("Joana", "999.999.999-99"));
        pedido.adicionarProduto(new Produto("Teclado", 100f));

        Expedicao.processarExpedicao(pedido);

        assertEquals(Pedido.Status.EM_EXPEDICAO, pedido.getStatus());
    }

    //Teste de financeiro
    @Test
    public void deveFinalizarPedidoComPagamento() {
        Pedido pedido = new Pedido(new Cliente("Ana", "111.111.111-11"));
        pedido.adicionarProduto(new Produto("Impressora", 600f));
        pedido.atualizarStatus(Pedido.Status.AGUARDANDO_PAGAMENTO);

        Financeiro.processarPagamento(pedido, true);

        assertEquals(Pedido.Status.FINALIZADO, pedido.getStatus());
    }

    @Test
    public void deveCancelarPedidoSemPagamento() {
        Pedido pedido = new Pedido(new Cliente("Luis", "222.222.222-22"));
        pedido.adicionarProduto(new Produto("Scanner", 400f));
        pedido.atualizarStatus(Pedido.Status.AGUARDANDO_PAGAMENTO);

        Financeiro.processarPagamento(pedido, false);

        assertEquals(Pedido.Status.CANCELADO, pedido.getStatus());
    }

    @AfterAll
    static void depois(){
        // Limpa o banco de dados
    }
}