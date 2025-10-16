package br.pucpr.pet;

public class PagamentoPix implements PagamentoStrategy {
    @Override
    public String pagar(double valor) {
        // Lógica específica para pagamento com Pix
        return "Pagamento de R$" + valor + " realizado com Pix.";
    }
}
