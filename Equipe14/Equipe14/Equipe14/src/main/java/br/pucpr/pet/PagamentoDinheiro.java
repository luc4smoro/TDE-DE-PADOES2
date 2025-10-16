package br.pucpr.pet;

public class PagamentoDinheiro implements PagamentoStrategy {
    @Override
    public String pagar(double valor) {
        // Lógica específica para pagamento com dinheiro
        return "Pagamento de R$" + valor + " realizado com Dinheiro.";
    }
}
