package persistencia;

import entidade.Biblioteca;
import entidade.Cliente;
import util.Arquivo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BibliotecaDAO implements PersistenciaDAO<Biblioteca> {
    final String CAMINHO_ARQUIVO = "C:\\Users\\Ryan\\Desktop\\Biblioteca\\biblioteca.txt";
    @Override
    public void cadastrar(Biblioteca objeto) throws IOException {
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
    public List<Biblioteca> listar() throws IOException {
        Arquivo arquivo = new Arquivo();

        List<String> linhas = arquivo.lerArquivo(CAMINHO_ARQUIVO);

        List<Biblioteca> bibliotecas = new ArrayList<>();

        for (String linha: linhas) {
            String[] k = linha.split("[|]");
            Biblioteca c = new Biblioteca();
            c.setId(Long.valueOf(k[0]));
            c.setRazaoSocial(k[1]);
            c.setEndereco(k[2]);
            bibliotecas.add(c);
        }

        return bibliotecas;
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
            throw new IOException("BIBLIOTECA NAO ENCONTRADA");
        }
    }

    @Override
    public void alterar(Biblioteca objeto) throws IOException {
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
            throw new IOException("BIBLIOTECA NAO ENCONTRADA");
        }
    }

    @Override
    public Biblioteca consultar(Long id) throws IOException {
        List<Biblioteca> bibliotecas = listar();
        Biblioteca encontrado = null;
        for(Biblioteca biblioteca: bibliotecas) {
            if (id.equals(biblioteca.getId())) {
                encontrado = biblioteca;
                break;
            }
        }
        if (encontrado == null) {
            throw new IOException("Biblioteca nao encontrada");
        }
        return encontrado;
    }
}
