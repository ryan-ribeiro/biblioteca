package persistencia;

import entidade.Biblioteca;
import entidade.Cliente;
import entidade.EmprestimoDevolucao;
import entidade.Livro;
import util.Arquivo;
import util.UtilData;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmprestimoDevolucaoDAO implements PersistenciaDAO<EmprestimoDevolucao> {
    final String CAMINHO_ARQUIVO = "C:\\Users\\Ryan\\Desktop\\Biblioteca\\emprestimo.txt";

    public void emprestar(EmprestimoDevolucao emprestimo) throws Exception{

        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();

        LivroDAO livroDAO = new LivroDAO();

        ClienteDAO clienteDAO = new ClienteDAO();

        if (clienteDAO.consultar(emprestimo.getCliente().getId()) == null) {
            throw new Exception("Cliente nao existe");
        }

        if (bibliotecaDAO.consultar(emprestimo.getBiblioteca().getId()) == null) {
            throw new Exception("Biblioteca não existe");
        }

        if (livroDAO.consultar(emprestimo.getLivro().getId()) == null) {
            throw new Exception("Livro não existe");
        }

        Arquivo arquivo = new Arquivo();

        List<String> linhas = arquivo.lerArquivo(CAMINHO_ARQUIVO);

        if (linhas == null) {
            linhas = new ArrayList<>();
        }
                emprestimo.setId(new Date().getTime());
                emprestimo.setDtEmprestimo(new Date());
                linhas.add(emprestimo.toString());


                arquivo.escreverArquivo(CAMINHO_ARQUIVO, linhas);
    }

    public void devolucao(EmprestimoDevolucao emprestimo) throws Exception {

        Arquivo arquivo = new Arquivo();

        List<String> linhas = arquivo.lerArquivo(CAMINHO_ARQUIVO);

        if (linhas == null) {
            linhas = new ArrayList<>();
        }
        emprestimo = consultar(emprestimo.getId());
        emprestimo.setDtDevolucao(new Date());
        alterar(emprestimo);
    }

//    public EmprestimoDevolucao consultar(Long id) {
//        Arquivo arquivo = new Arquivo();
//        List<Livro> livroes = listar();
//        Livro encontrado = null;
//        for(Livro livro: livroes) {
//            if (id.equals(livro.getId())) {
//                encontrado = livro;
//                break;
//            }
//        }
//        if (encontrado == null) {
//            throw new IOException("Livro nao encontrado");
//        }
//        return encontrado;
//    }



    public List<EmprestimoDevolucao> listar() throws Exception, ParseException {

        Arquivo arquivo = new Arquivo();

        List<String> linhas = arquivo.lerArquivo(CAMINHO_ARQUIVO);

        List<EmprestimoDevolucao> emprestimos = new ArrayList<>();

        BibliotecaDAO bibliotecaDAO = new BibliotecaDAO();

        LivroDAO livroDAO = new LivroDAO();

        ClienteDAO clienteDAO = new ClienteDAO();

        for (String linha: linhas) {
            String[] k = linha.split("[|]");

            EmprestimoDevolucao e = new EmprestimoDevolucao();

            e.setId(Long.valueOf(k[0]));

            Biblioteca biblioteca = bibliotecaDAO.consultar(Long.valueOf(k[1]));
            e.setBiblioteca(biblioteca);

            Livro livro = livroDAO.consultar(Long.valueOf(k[2]));
            e.setLivro(livro);

            Cliente cliente = clienteDAO.consultar(Long.valueOf(k[3]));
            e.setCliente(cliente);

            e.setDtEmprestimo(new UtilData().fromStringToDateWithHours(k[4]));

            e.setDtDevolucao(new UtilData().fromStringToDateWithHours(k[5]));

            emprestimos.add(e);
        }

        return emprestimos;
    }
    @Override
    public void cadastrar(EmprestimoDevolucao objeto) throws Exception {

        if(consultar(objeto.getId()) != null){
            throw new Exception("Livro já cadastrado");
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
            throw new IOException("LIVRO NAO ENCONTRADO");
        }
    }

    @Override
    public void alterar(EmprestimoDevolucao objeto) throws Exception {
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
            throw new Exception("LIVRO NAO ENCONTRADO");
        }
    }

    @Override
    public EmprestimoDevolucao consultar(Long id) throws Exception {
        if(id == null) {
            throw new Exception("ID NAO PODE SER NULO");
        }

        List<EmprestimoDevolucao> emprestimos = listar();
        EmprestimoDevolucao encontrado = null;
        for(EmprestimoDevolucao emprestimo: emprestimos) {
            if (id.equals(emprestimo.getId())) {
                encontrado = emprestimo;
                break;
            }
        }
        if (encontrado == null) {
            throw new Exception("Emprestimo nao encontrado");
        }
        return encontrado;
    }
}
