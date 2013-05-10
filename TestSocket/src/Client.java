import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public void run() {

		try {
			Socket s = new Socket("localhost", 2345);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));

			while (true) {

				String taskName = reader.readLine();
				out.println(taskName);
				out.flush();
				if (taskName != null && taskName.equals("byebye")) {
					break;
				}
			}

			in.close();
			reader.close();
			out.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {

		Client c = new Client();
		c.run();

	}

}
