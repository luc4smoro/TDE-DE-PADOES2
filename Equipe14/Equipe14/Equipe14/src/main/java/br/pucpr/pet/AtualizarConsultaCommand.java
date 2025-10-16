package br.pucpr.pet;

/**
 * Comando concreto para a ação de atualizar uma consulta existente.
 */
public class AtualizarConsultaCommand implements Command {
    private final ConsultaController controller;

    public AtualizarConsultaCommand(ConsultaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.atualizarConsulta();
    }
}
