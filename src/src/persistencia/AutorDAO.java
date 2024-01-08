package persistencia;
import entidade.Autor;
import entidade.Biblioteca;
import util.Arquivo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AutorDAO implements PersistenciaDAO<Autor> {
    final String CAMINHO_ARQUIVO = "C:\\Users\\Ryan\\Desktop\\Biblioteca\\autor.txt";

    @Override
    public void cadastrar(Autor objeto) throws IOException {
        Arquivo arquivo = new Arquivo();

        List<String> linhas = arquivo.lerArquivo(CAMINHO_ARQUIVO);

        if (linhas == null) {
            linhas = new ArrayList<>();
        }
        objeto.setId(new Date().getTime());
        linhas.add(objeto.toString());


        arquivo.escreverArquivo(CAMINHO_ARQUIVO, linhas);
    }

    @Override
    public List<Autor> listar() throws IOException {
        Arquivo arquivo = new Arquivo();

        List<String> linhas = arquivo.lerArquivo(CAMINHO_ARQUIVO);

        List<Autor> autores = new ArrayList<>();

        for (String linha: linhas) {
            String[] k = linha.split("[|]");
            Autor a = new Autor();
            a.setId(Long.valueOf(k[0]));
            a.setNome(k[1]);
            autores.add(a);
        }

        return autores;
    }

    @Override
    public void excluir(Long id) throws IOException {
        Arquivo arquivo = new Arquivo();

        List<String> linhas = arquivo.lerArquivo(CAMINHO_ARQUIVO);
        int index = 0;
        boolean encontrou = false;
        for (String linha: linhas) {
            String[] k = linha.split("[|]");
            Long i = Long.valueOf(k[0]);
            if (i.equals(id)) {
                encontrou = true;
                break;
            }
            index++;
        }

        if (encontrou) {
            linhas.remove(index);
            arquivo.escreverArquivo(CAMINHO_ARQUIVO, linhas);
        } else {
            throw new IOException("AUTOR NAO ENCONTRADO");
        }
    }

    @Override
    public void alterar(Autor objeto) throws IOException {
        Arquivo arquivo = new Arquivo();

        List<String> linhas = arquivo.lerArquivo(CAMINHO_ARQUIVO);
        int index = 0;
        boolean encontrou = false;
        for (String linha: linhas) {
            String[] k = linha.split("[|]");
            Long i = Long.valueOf(k[0]);
            if (i.equals(objeto.getId())) {
                encontrou = true;
                break;
            }
            index++;
        }

        if (encontrou) {
            linhas.remove(index);
            linhas.add(objeto.toString());
            arquivo.escreverArquivo(CAMINHO_ARQUIVO, linhas);
        } else {
            throw new IOException("AUTOR NAO ENCONTRADO");
        }
    }

    @Override
    public Autor consultar(Long id) throws IOException {
        List<Autor> autores = listar();
        Autor encontrado = null;
        for(Autor autor: autores) {
            if (id.equals(autor.getId())) {
                encontrado = autor;
                break;
            }
        }
        if (encontrado == null) {
            throw new IOException("Autor nao encontrado");
        }
        return encontrado;
    }
}
