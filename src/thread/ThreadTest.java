package thread;

public class ThreadTest extends  Thread{


    Integer cont = 0;

    public ThreadTest(Integer cont){
        this.cont = cont;
    }


    @Override
    public void run() {

        System.out.println(cont + " - Inicialziada");

    }
}
