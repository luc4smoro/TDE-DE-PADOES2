package br.pucpr.pet;

/**
 * Comando concreto para a ação de limpar os campos do formulário.
 */
public class LimparFormularioCommand implements Command {
    private final ConsultaController controller;

    public LimparFormularioCommand(ConsultaController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.limparFormulario();
    }
}
