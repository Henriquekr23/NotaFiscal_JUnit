package main.java.br.ucb.entidade;

public class Produto {
    private String nome;
    private float preco;

    public Produto(String nome, float preco) {
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio!");
        }
        if (preco < 0.01f) {
            throw new IllegalArgumentException("Produto não pode custar menos que 1 centavo!");
        }

        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return this.nome;
    }

    public float getPreco() {
        return this.preco;
    }

    public void setPreco(float preco) {
        if (preco < 0.01f) {
            throw new IllegalArgumentException("Produto não pode custar menos que 1 centavo!");
        }

        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto: " + nome +
                "\nPreço: R$ " + String.format("%.2f", preco);
    }
}
