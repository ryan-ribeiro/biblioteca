package entidade;

import util.UtilData;

import java.util.Date;

public class Livro {
    private Long id;
    private Autor autor;
    private String titulo;
    private Date dtPublicacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDtPublicacao() {
        return dtPublicacao;
    }

    public void setDtPublicacao(Date dtPublicacao) {
        this.dtPublicacao = dtPublicacao;
    }

    @Override
    public String toString() {
        String dataPublicacao = new UtilData().fromDateToStringWitoutHours(dtPublicacao);
        return id+"|"+ autor.getId()+"|"+titulo+"|"+dataPublicacao;
    }
}
