package main;

import entidade.*;
import persistencia.EmprestimoDevolucaoDAO;
import thread.ThreadTest;
import thread.ThreadVerificarAtraso;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
//        Cliente cliente = new Cliente();
//        cliente.setNome("Matheus");
//        cliente.setCpfCnpj("12354678965");
////
//        ClienteDAO clienteDao = new ClienteDAO();
//        clienteDao.cadastrar(cliente);
////        List<Cliente> clientes = new ArrayList<>();
////        clientes = clienteDao.listar();
////        System.out.println(clientes.toString());
//
////        Long id = 1701029267201L;
////        clienteDao.excluir(cliente.getId());
//        cliente.setNome("Matheus apodas");
//        clienteDao.alterar(cliente);



//        BibliotecaDAO bibliotecaDao = new BibliotecaDAO();
////        bibliotecaDao.cadastrar(biblioteca);
//        Biblioteca k = bibliotecaDao.consultar(1701042775533L);
//        k.setRazaoSocial("asd");
//        k.setEndereco("5649jk");
//        bibliotecaDao.alterar(k);
////        bibliotecaDao.cadastrar(k);
//
//        Livro livro = new Livro();
//        Autor autor = new Autor();
//        autor.setNome("askd");
//        livro.setAutor(autor);

//        Autor autor = new Autor();
//        autor.setNome("C.S Lewis");
//
//        AutorDAO autorDao = new AutorDAO();
////        autorDao.cadastrar(autor);
//
        Autor k = new Autor();
//        k = autorDao.consultar(1701298082267L);
//        k.setNome("J.K. Rowling");
//        autorDao.alterar(k);
//
////        System.out.println(autorDao.listar());
////        autorDao.excluir(1701298082267L);
////
//        k.setId(1701297995121L);
//
//        Livro livro = new Livro();
////        livro.setAutor(k);
//        livro.setTitulo("asdfads");
//        livro.setDtPublicacao(new Date());
//
//        LivroDAO livroDao = new LivroDAO();
////        livroDao.cadastrar(livro);
//        Livro livro1 = livroDao.consultar(1701304668137L);
//        Date dataAgora = new Date();
////        Autor hora = dataAgora.getTime();
//        livro1.setTitulo("O Mentecapto");
//        livroDao.alterar(livro1);
//
//        livroDao.listar().stream().forEach(System.out::println);
//        Cliente cliente = new Cliente();
//        cliente.setId(1701038789458L);
//
//        Livro livro = new Livro();
//        livro.setId(1701305675563L);
//
//        Autor autor = new Autor();
//        autor.setId(1701297995121L);
//
//        Biblioteca biblioteca = new Biblioteca();
//        biblioteca.setId(1701042709557L);
//
//        EmprestimoDevolucao emprestimo = new EmprestimoDevolucao();
//        emprestimo.setCliente(cliente);
//        emprestimo.setBiblioteca(biblioteca);
//        emprestimo.setLivro(livro);
//
//        EmprestimoDevolucaoDAO emprestimoDao = new EmprestimoDevolucaoDAO();
//        EmprestimoDevolucaoDAO emprestimo = (EmprestimoDevolucaoDAO) emprestimoDao.listar();
//
//        for (EmprestimoDevolucaoDAO emprestimos: emprestimo) {
//            System.out.println(emprestimo);
//        }

        ThreadVerificarAtraso verificarAtraso = new ThreadVerificarAtraso();
        verificarAtraso.run();
////        emprestimoDao.emprestar(emprestimo);
//        emprestimo.setId(1701551525944L);
//        emprestimoDao.devolucao(emprestimo);
//
        }
//        for(EmprestimoDevolucao e : emprestimos){
//
//            System.out.println("--------------------");
//            System.out.println("Nome: "+ e.getCliente().getNome());
//            System.out.println("CPF: "+ e.getCliente().getCpfCnpj());
//
//            System.out.println("Biblioteca: "+e.getBiblioteca().getRazaoSocial());
//            System.out.println("Endereco: "+ e.getBiblioteca().getEndereco());
//
//            System.out.println("Titulo: "+ e.getLivro().getTitulo());
//            System.out.println("Data Publicacao: "+ e.getLivro().getDtPublicacao());
//            System.out.println("Autor: "+ e.getLivro().getAutor().getNome());
//
//            System.out.println("ID: "+ e.getId());
//            System.out.println("Data Emprestimo: "+e.getDtEmprestimo());
//            System.out.println("Data Devolucao: "+e.getDtDevolucao());
//
//        }

//        ThreadVerificarAtraso atraso = new ThreadVerificarAtraso();
//        atraso.start();




}
