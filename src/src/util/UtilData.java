package util;

import entidade.EmprestimoDevolucao;
import persistencia.EmprestimoDevolucaoDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class UtilData {
    public static void calcularDiferencaEImprimir(List<EmprestimoDevolucao> listaEmprestimos, String dataAtualString){
        for (EmprestimoDevolucao emprestimo : listaEmprestimos) {
            Date dataAtual2 = new Date();
            Date dataEmprestimo = emprestimo.getDtEmprestimo();

            boolean isVencido = dataAtual2.before(emprestimo.getDtEmprestimo());

            if (isVencido) {
                LocalDateTime d1 = dataAtual2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime d2 = dataEmprestimo.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

                long qtdDias = ChronoUnit.DAYS.between(d1, d2);

                if (qtdDias > 7) {
                    System.out.println("A DEVOLUCAO DO LIVRO " + emprestimo.getLivro() + " VENCEU");
                } else if (qtdDias <= 7 && qtdDias >= 0){
                    System.out.println("PODE DEVOLVER O LIVRO " + emprestimo.getLivro());
                }
            }
        }
    }

    public String fromDateToStringWithHours(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public Date fromStringToDateWithHours(String date) throws ParseException {
        if (date == null || date.isBlank()) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date);
    }

    public String fromDateToStringWitoutHours(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public Date fromStringToDateWitoutHours(String date) throws ParseException {
        if (date == null || date.isBlank()) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }
}
