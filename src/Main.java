import com.mrheadshot62.server.ServerController;

public class Main {
    private static Object o = new Object();
    private int i=0;

    public static void main(String[] args) {
        ServerController sc = new ServerController();
        sc.startServer();
    }
}
