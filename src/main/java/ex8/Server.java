package ex8;

public class Server {

    private boolean isConnected;

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public void handleRequest(int clientId) {
        try {
            setConnected(true);
            System.out.println("Client " + clientId + " connected to the server");
            Thread.sleep(2000);
            System.out.println("Client " + clientId + " finished job");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            setConnected(false);
        }
    }

}
