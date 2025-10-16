package br.pucpr.pet;

import javafx.scene.control.Alert;

/**
 * Proxy de Proteção para a ConsultaFacade.
 *
 * Controla o acesso aos métodos da fachada com base no papel (role) do usuário,
 * adicionando uma camada de segurança sem modificar a lógica de negócio principal.
 */
public class ConsultaFacadeProxy implements IConsultaFacade {

    private IConsultaFacade facadeReal;
    private UsuarioRole usuarioRole;

    public ConsultaFacadeProxy(UsuarioRole usuarioRole) {
        this.facadeReal = new ConsultaFacade(); // O Proxy cria e gerencia o objeto real.
        this.usuarioRole = usuarioRole;
    }

    private void mostrarAcessoNegado() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Acesso Negado");
        alert.setHeaderText("Você não tem permissão para executar esta ação.");
        alert.setContentText("Seu papel de usuário (" + usuarioRole + ") não permite esta operação.");
        alert.showAndWait();
    }

    @Override
    public void iniciar(Consulta consulta) {
        if (usuarioRole == UsuarioRole.VETERINARIO || usuarioRole == UsuarioRole.GERENTE) {
            facadeReal.iniciar(consulta);
        } else {
            mostrarAcessoNegado();
        }
    }

    @Override
    public void finalizar(Consulta consulta) {
        if (usuarioRole == UsuarioRole.VETERINARIO || usuarioRole == UsuarioRole.GERENTE) {
            facadeReal.finalizar(consulta);
        } else {
            mostrarAcessoNegado();
        }
    }

    @Override
    public void cancelar(Consulta consulta) {
        // Todos podem cancelar
        facadeReal.cancelar(consulta);
    }

    @Override
    public void reagendar(Consulta consulta) {
        // Todos podem reagendar
        facadeReal.reagendar(consulta);
    }

    @Override
    public void reabrir(Consulta consulta) {
        if (usuarioRole == UsuarioRole.VETERINARIO || usuarioRole == UsuarioRole.GERENTE) {
            facadeReal.reabrir(consulta);
        } else {
            mostrarAcessoNegado();
        }
    }

    @Override
    public void abrirFichaAtendimento(Consulta consulta) {
        if (usuarioRole == UsuarioRole.VETERINARIO || usuarioRole == UsuarioRole.GERENTE) {
            facadeReal.abrirFichaAtendimento(consulta);
        } else {
            mostrarAcessoNegado();
        }
    }

    @Override
    public void gerarRelatorio(Consulta consulta) {
        if (usuarioRole == UsuarioRole.GERENTE) {
            facadeReal.gerarRelatorio(consulta);
        } else {
            mostrarAcessoNegado();
        }
    }

    @Override
    public void visualizarDiagnostico(Consulta consulta) {
        // Todos podem visualizar o diagnóstico de uma consulta finalizada
        facadeReal.visualizarDiagnostico(consulta);
    }
}