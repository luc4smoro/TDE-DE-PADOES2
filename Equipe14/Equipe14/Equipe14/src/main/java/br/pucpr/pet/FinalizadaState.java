package br.pucpr.pet;

import javafx.stage.Stage;
import java.util.Optional;

public class FinalizadaState implements ConsultaState {

    @Override
    public void iniciar(Consulta consulta, ConsultaController controller, Stage fluxoStage) {
        controller.mostrarAviso("Esta consulta já foi finalizada. Para continuar, você pode reabri-la.");
    }

    @Override
    public void finalizar(Consulta consulta, ConsultaController controller, Stage fluxoStage) {
        controller.mostrarAviso("A consulta já está finalizada.");
    }

    @Override
    public void cancelar(Consulta consulta, ConsultaController controller, Stage fluxoStage) {
        controller.mostrarAviso("Não é possível cancelar uma consulta que já foi finalizada.");
    }

    @Override
    public void reagendar(Consulta consulta, ConsultaController controller, Stage fluxoStage) {
        controller.mostrarAviso("Não é possível reagendar uma consulta que já foi finalizada.");
    }

    @Override
    public void reabrir(Consulta consulta, ConsultaController controller, Stage fluxoStage) {
        consulta.setStatus(StatusConsulta.EM_ANDAMENTO);
        controller.atualizarConsultaNoDataManager(consulta, fluxoStage);
        controller.mostrarSucesso("Consulta reaberta com sucesso!");
        if (fluxoStage != null) {
            fluxoStage.close();
        }
    }

    @Override
    public void abrirFichaAtendimento(Consulta consulta, ConsultaController controller, Stage fluxoStage) {
        controller.mostrarAviso("A ficha de atendimento não pode ser editada para uma consulta finalizada. Visualize o diagnóstico.");
    }

    @Override
    public void gerarRelatorio(Consulta consulta, ConsultaController controller, Stage fluxoStage) {
        // A lógica de gerar relatório está no controller, aqui apenas delegamos.
        // O controller precisa de um método público para isso.
        // Por simplicidade, vamos assumir que o controller tem um método para isso.
        // controller.gerarRelatorioDaConsulta(consulta);
        controller.mostrarAlerta("Gerando Relatório", "A funcionalidade de gerar relatório foi invocada.");
    }

    @Override
    public void visualizarDiagnostico(Consulta consulta, ConsultaController controller, Stage fluxoStage) {
        // A lógica de visualização está no controller, aqui apenas delegamos.
        // controller.visualizarDiagnosticoDaConsulta(consulta);
        controller.mostrarAlerta("Visualizando Diagnóstico", "A funcionalidade de visualizar diagnóstico foi invocada.");
    }

    @Override
    public StatusConsulta getStatusEnum() {
        return StatusConsulta.FINALIZADA;
    }
}