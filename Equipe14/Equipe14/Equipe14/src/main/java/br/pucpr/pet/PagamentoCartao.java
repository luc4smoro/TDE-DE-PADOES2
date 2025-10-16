package br.pucpr.pet;

public class PagamentoCartao implements PagamentoStrategy {
    @Override
    public String pagar(double valor) {
        // Lógica específica para pagamento com cartão
        return "Pagamento de R$" + valor + " realizado com Cartão.";
    }
}
