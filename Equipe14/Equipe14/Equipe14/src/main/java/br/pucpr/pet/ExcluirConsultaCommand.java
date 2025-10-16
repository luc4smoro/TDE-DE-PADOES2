package br.pucpr.pet;

/**
 * Comando concreto para a ação de excluir uma consulta.
 */
public class ExcluirConsultaCommand implements Command {
    private final ConsultaController controller;

    public ExcluirConsultaCommand(ConsultaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.excluirConsulta();
    }
}
