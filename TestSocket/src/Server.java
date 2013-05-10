import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public void run() {

		try {
			ServerSocket ss = new ServerSocket(2345);
			Socket s = ss.accept();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);

			while (true) {
				String msg = in.readLine();
				if (msg != null && msg.equals("byebye")) {
					break;
				}
				System.out.println("From Client: " + msg);
				out.println("Message Recived: " + msg);
				out.flush();

			}
			out.close();
			in.close();
			s.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {

		Server s = new Server();
		s.run();

	}

}
