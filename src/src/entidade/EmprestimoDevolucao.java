package entidade;

import util.UtilData;

import java.util.Date;

public class EmprestimoDevolucao {
    private Long id;
    private Cliente cliente;
    private Livro livro;
    private Biblioteca biblioteca;
    private Date dtEmprestimo;
    private Date dtDevolucao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public Date getDtEmprestimo() {
        return dtEmprestimo;
    }

    public void setDtEmprestimo(Date dtEmprestimo) {
        this.dtEmprestimo = dtEmprestimo;
    }

    public Date getDtDevolucao() {
        return dtDevolucao;
    }

    public void setDtDevolucao(Date dtDevolucao) {
        this.dtDevolucao = dtDevolucao;
    }

    @Override
    public String toString() {
        String dataPublicacao = new UtilData().fromDateToStringWithHours(dtEmprestimo);
        String dataDevolucao = new UtilData().fromDateToStringWithHours(dtDevolucao);

        if(dataPublicacao == null) {
            dataPublicacao = " ";
        }

        if(dataDevolucao == null){
            dataDevolucao = " ";
        }

        return id+"|"+biblioteca.getId()+"|"+livro.getId()+"|"+cliente.getId()+"|"+dataPublicacao+"|"+dataDevolucao;
    }
}
