package persistencia;

import entidade.Biblioteca;
import entidade.Cliente;
import util.Arquivo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteDAO implements PersistenciaDAO<Cliente> {
    final String CAMINHO_ARQUIVO = "C:\\Users\\Ryan\\Desktop\\Biblioteca\\cliente.txt";

    @Override
    public void cadastrar(Cliente objeto) throws IOException {
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
    public List<Cliente> listar() throws IOException {
        Arquivo arquivo = new Arquivo();

        List<String> linhas = arquivo.lerArquivo(CAMINHO_ARQUIVO);

        List<Cliente> clientes = new ArrayList<>();

        for (String linha: linhas) {
            String[] k = linha.split("[|]");
            Cliente c = new Cliente();
            c.setId(Long.valueOf(k[0]));
            c.setCpfCnpj(k[1]);
            c.setNome(k[2]);
            clientes.add(c);
        }

        return clientes;
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
            throw new IOException("CLIENTE NAO ENCONTRADO");
        }
    }

    @Override
    public void alterar(Cliente objeto) throws IOException {
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
            throw new IOException("CLIENTE NAO ENCONTRADO");
        }
    }

    @Override
    public Cliente consultar(Long id) throws IOException {
        List<Cliente> clientes = listar();
        Cliente encontrado = null;

        for(Cliente cliente: clientes) {
            if (id.equals(cliente.getId())) {
                encontrado = cliente;
                break;
            }
        }

        if (encontrado == null) {
            throw new IOException("Biblioteca nao encontrada");
        }

        return encontrado;
    }

    public List<Cliente> consultarPorNome(String nome) throws IOException {
        List<Cliente> clientes = listar();
        List<Cliente> encontrados = new ArrayList<>();

        for(Cliente cliente: clientes) {
            if (nome.equals(cliente.getNome())) {
                encontrados.add(cliente);
            }
        }

        if (encontrados.isEmpty()) {
            throw new IOException("Biblioteca nao encontrada");
        }

        return encontrados;
    }
}
