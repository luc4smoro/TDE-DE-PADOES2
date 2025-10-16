package br.pucpr.pet;

/**
 * Comando concreto para a ação de inserir uma nova consulta.
 */
public class InserirConsultaCommand implements Command {
    private final ConsultaController controller;

    public InserirConsultaCommand(ConsultaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.inserirConsulta();
    }
}
