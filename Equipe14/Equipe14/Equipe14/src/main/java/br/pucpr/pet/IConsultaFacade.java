package br.pucpr.pet;

/**
 * Interface que define o contrato para a fachada de operações de Consulta.
 * Usada tanto pela implementação real (ConsultaFacade) quanto pelo Proxy (ConsultaFacadeProxy).
 */
public interface IConsultaFacade {
    void iniciar(Consulta consulta);
    void finalizar(Consulta consulta);
    void cancelar(Consulta consulta);
    void reagendar(Consulta consulta);
    void reabrir(Consulta consulta);
    void abrirFichaAtendimento(Consulta consulta);
    void gerarRelatorio(Consulta consulta);
    void visualizarDiagnostico(Consulta consulta);
}