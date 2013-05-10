package org.eclipse.bpel.ui.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceMonitor extends Thread{
	private IPublisher pb;

	public ServiceMonitor(Publisher p) {
		this.pb = p;
	}

	@Override
	public void run() {
		
		System.out.println("Monitor is running");

		try {

			ServerSocket ss = new ServerSocket(2345);

			while (true) {
				Socket socket = ss.accept();
				invoke(socket);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void invoke(final Socket socket) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Setup connection");
				BufferedReader in = null;
				PrintWriter out = null;
				try {
					in = new BufferedReader(new InputStreamReader(
							socket.getInputStream()));
					out = new PrintWriter(socket.getOutputStream(), true);

					while (true) {

						String task = in.readLine();
						if (task != null && task.equals("byebye")) {
							System.out.println("Recieve byebye");
							break;
						}
						System.out.println("Recieve task: "+task);
						pb.setName(task);
						out.println("Recieved task name: " + task);
						out.flush();
					}

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					out.close();
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}

		}).start();

	}
}
