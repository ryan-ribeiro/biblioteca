package persistencia;

import entidade.Livro;
import exception.BibliotecaException;
import util.Arquivo;
import util.UtilData;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LivroDAO implements PersistenciaDAO<Livro> {
    final String CAMINHO_ARQUIVO = "C:\\Users\\Ryan\\Desktop\\Biblioteca\\livro.txt";

    @Override
    public void cadastrar(Livro objeto) throws Exception{

        if(consultar(objeto.getId()) != null){
            throw new BibliotecaException("Livro já cadastrado");
        }


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
    public List<Livro> listar() throws Exception {
        Arquivo arquivo = new Arquivo();

        List<String> linhas = arquivo.lerArquivo(CAMINHO_ARQUIVO);

        List<Livro> livros = new ArrayList<>();

        for (String linha: linhas) {
            String[] k = linha.split("[|]");
            Livro a = new Livro();
            a.setId(Long.valueOf(k[0]));
            Long autorId = Long.valueOf(k[1]);
            AutorDAO autorDao = new AutorDAO();
            a.setAutor(autorDao.consultar(autorId));

            a.setTitulo(k[2]);
            Date dataPublicacao = null;
            try {
                dataPublicacao = new UtilData().fromStringToDateWitoutHours(k[3]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            a.setDtPublicacao(dataPublicacao);

            livros.add(a);
        }

        return livros;
    }

    @Override
    public void excluir(Long id) throws Exception {
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
            throw new BibliotecaException("LIVRO NAO ENCONTRADO");
        }
    }

    @Override
    public void alterar(Livro objeto) throws Exception {
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
            throw new BibliotecaException("LIVRO NAO ENCONTRADO");
        }
    }

    @Override
    public Livro consultar(Long id) throws Exception {
        if(id == null) {
            throw new BibliotecaException("ID NAO PODE SER NULO");
        }

        List<Livro> livros = listar();
        Livro encontrado = null;
        for(Livro livro: livros) {
            if (id.equals(livro.getId())) {
                encontrado = livro;
                break;
            }
        }
        if (encontrado == null) {
            throw new BibliotecaException("Livro nao encontrado");
        }
        return encontrado;
    }
}
