package br.pucpr.pet;

import javafx.stage.Stage;

/**
 * Façade para simplificar as operações no ciclo de vida de uma Consulta.
 *
 * Esta classe fornece uma interface simplificada para o subsistema de gerenciamento
 * de consultas, que inclui os estados (Agendada, EmAndamento, etc.), o controller
 * e a lógica de UI.
 *
 * O cliente interage com esta fachada em vez de interagir diretamente com as
 * classes do subsistema, reduzindo o acoplamento e simplificando o uso.
 */
public class ConsultaFacade {

    private ConsultaController controller;

    public ConsultaFacade() {
        // O controller pode ser injetado ou instanciado aqui.
        // Para este exemplo, vamos instanciá-lo.
        this.controller = new ConsultaController();
    }

    /**
     * Obtém o objeto de estado correto com base no status da consulta.
     * @param consulta A consulta para a qual o estado é necessário.
     * @return Uma instância de ConsultaState correspondente ao status da consulta.
     */
    private ConsultaState getEstado(Consulta consulta) {
        // Esta é uma implementação de fábrica simples para obter o estado.
        // Poderia ser movida para uma classe Factory separada se ficar mais complexa.
        switch (consulta.getStatus()) {
            case CRIADA:
                return new CriadaState();
            case AGENDADA:
                return new AgendadaState();
            case EM_ANDAMENTO:
                return new EmAndamentoState();
            case FINALIZADA:
                return new FinalizadaState();
            case CANCELADA:
                return new CanceladaState();
            case REAGENDADA:
                return new ReagendadaState();
            default:
                throw new IllegalStateException("Status de consulta desconhecido: " + consulta.getStatus());
        }
    }

    /**
     * Cria e retorna um novo Stage para os fluxos de UI.
     * Cada operação cria seu próprio palco para garantir o isolamento.
     * @return um novo Stage.
     */
    private Stage criarFluxoStage() {
        return new Stage();
    }

    // Métodos da fachada que simplificam as chamadas do cliente

    public void iniciar(Consulta consulta) {
        getEstado(consulta).iniciar(consulta, controller, criarFluxoStage());
    }

    public void finalizar(Consulta consulta) {
        getEstado(consulta).finalizar(consulta, controller, criarFluxoStage());
    }

    public void cancelar(Consulta consulta) {
        getEstado(consulta).cancelar(consulta, controller, criarFluxoStage());
    }

    public void reagendar(Consulta consulta) {
        getEstado(consulta).reagendar(consulta, controller, criarFluxoStage());
    }

    public void reabrir(Consulta consulta) {
        getEstado(consulta).reabrir(consulta, controller, criarFluxoStage());
    }

    public void abrirFichaAtendimento(Consulta consulta) {
        // Note que o Stage não é usado aqui, mas mantemos a consistência da chamada.
        getEstado(consulta).abrirFichaAtendimento(consulta, controller, null);
    }

    public void gerarRelatorio(Consulta consulta) {
        getEstado(consulta).gerarRelatorio(consulta, controller, criarFluxoStage());
    }

    public void visualizarDiagnostico(Consulta consulta) {
        getEstado(consulta).visualizarDiagnostico(consulta, controller, criarFluxoStage());
    }
}