package util;

import exception.BibliotecaException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Arquivo {

    public List<String> lerArquivo(String caminho) throws IOException {
        try {
            List<String> linhas = new ArrayList<>();

            File arquivo = new File(caminho);
            FileReader leitor = new FileReader(arquivo);
            BufferedReader buffer = new BufferedReader(leitor);

            String linha;
            while ((linha = buffer.readLine()) != null) {
                linhas.add(linha);
            }

            buffer.close();
            leitor.close();

            return linhas;
        } catch(IOException i) {
            throw new BibliotecaException("O caminho passado está vazio!");
        }
    }

    public void escreverArquivo(String caminho, List<String> linhas) throws IOException {
        try {
            Writer escritor = new FileWriter(caminho);

            for (String linha : linhas) {
                escritor.write(linha);
                escritor.write('\n');
            }

            escritor.flush();
            escritor.close();
        } catch (BibliotecaException e) {
            System.out.println("A lista de linhas passada está vazia!");
        } catch (IllegalArgumentException i) {
            System.out.println("O caminho passado está vazio!");
        }
    }
}