package socket_2_csharp.tcp.client;

import java.io.BufferedOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import socket_2_csharp.util.string.StringConverter2ByteArray;

public class Sender2Csharp {

	public static void main(String[] args) {
		// JAVA TCP�ͻ��ˣ���C#������
		Sender sender = new Sender("127.0.0.1", 10000);
		sender.send();
	}

}

class Sender {

	private String ip;
	private int port;

	public Sender(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void send() {
		Socket socket = null;
		BufferedOutputStream bos = null;		
		int j = 0;
		
		for (int i = 0; i < 10; i++) {
			try {
				// TCP��������
				socket = new Socket(InetAddress.getByName(ip), port);
				if (socket.isConnected()) {
					j++;
					System.out.println("�ѽ�����" + j + "������!");
					Thread.sleep(50);
					
					String str = "��ľ��46" + j;	
					// ���ַ���תΪ�ֽ�����
					byte[] buf = StringConverter2ByteArray.getBytes(str.toCharArray(), "UTF-8");			
					System.out.printf("�ֽ�����ĳ�����%d\n", buf.length);
					
					// ��C#������������
					bos	= new BufferedOutputStream(socket.getOutputStream());
					bos.write(buf);
					bos.flush();

					bos.close();
					socket.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
