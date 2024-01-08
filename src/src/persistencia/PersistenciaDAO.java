package persistencia;

import java.io.IOException;
import java.util.List;

public interface PersistenciaDAO <T> {
    public void cadastrar(T objeto) throws Exception;

    public List<T> listar() throws Exception;

    public void excluir(Long id) throws Exception;

    public void alterar(T objeto) throws Exception;
    public T consultar(Long id) throws Exception;
}