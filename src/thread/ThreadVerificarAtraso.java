package thread;

import entidade.EmprestimoDevolucao;
import persistencia.EmprestimoDevolucaoDAO;
import util.UtilData;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ThreadVerificarAtraso extends Thread {
    @Override
    public void run() {
        try {
            while (true) {
                EmprestimoDevolucaoDAO emprestimo = new EmprestimoDevolucaoDAO();
                List<EmprestimoDevolucao> emprestimos;
                try {
                    emprestimos = emprestimo.listar();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                Date data = new Date();
                data.setTime(data.getTime());
                UtilData formatar = new UtilData();
                String dataAtual = formatar.fromDateToStringWithHours(data);

                formatar.calcularDiferencaEImprimir(emprestimos, dataAtual);
                Thread.sleep(86400000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
