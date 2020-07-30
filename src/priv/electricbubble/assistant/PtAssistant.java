package priv.electricbubble.assistant;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * @author electricbubble
 */
public class PtAssistant {
    private Socket socket = null;
    private String host = "127.0.0.1";
    private int port = 61000;

    public PtAssistant() {
    }

    public PtAssistant(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void init() throws IOException {
        this.socket = new Socket(this.host, this.port);
        this.socket.setSoTimeout(5 << 10);
    }

    public void end() {
        if (this.socket != null && !this.socket.isClosed()) {
            try {
                this.socket.shutdownInput();
                this.socket.shutdownOutput();
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public long incr() throws IOException {
        ByteBuffer buffer = null;

        OutputStream outputStream = this.socket.getOutputStream();

        byte[] req = new byte[]{0, 0, 0, 2, 0};
        outputStream.write(req);
        outputStream.flush();

        InputStream inputStream = this.socket.getInputStream();
        byte[] recv = new byte[4];
        inputStream.read(recv);
        buffer = ByteBuffer.wrap(recv);

        recv = new byte[buffer.getInt() - 4];
        inputStream.read(recv);
        buffer = ByteBuffer.wrap(recv);

        return buffer.getLong();
    }
}
