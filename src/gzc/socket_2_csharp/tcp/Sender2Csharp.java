package gzc.socket_2_csharp.tcp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

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
		//ByteBuffer buf = null;		
		Socket socket = null;
//		DataOutputStream dos = null;
		BufferedOutputStream bos = null;
		
		int j = 0;
//		float num = 123.456F;
		
		for (int i = 0; i < 10; i++) {
			try {
				// TCP��������
				socket = new Socket(InetAddress.getByName(ip), port);
				if (socket.isConnected()) {
					j++;
					System.out.println("�ѽ�����" + j + "������!");
					Thread.sleep(50);
					
					/*
					// ��Floa��ת���ֽ�����
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					dos = new DataOutputStream(baos);							
					dos.writeFloat(num + j);
					dos.flush();
					byte[] buf = baos.toByteArray();
					System.out.printf("�ֽ�����ĳ�����%d\n", buf.length);
					*/					
					
					String str = "��ľ��46" + j;	
					byte[] buf = getBytes(str.toCharArray(), "UTF-8");
					// 
//					dos.write(buf);	
					System.out.printf("�ֽ�����ĳ�����%d\n", buf.length);
					
					// ��������������
					bos	= new BufferedOutputStream(socket.getOutputStream());
					bos.write(buf);
					bos.flush();

//					dos.flush();
//					dos.close();
					bos.close();
					socket.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// ���ַ�תΪ�ֽ�(����)
	private byte[] getBytes(char[] chars, final String charsetName) {
		Charset cs = Charset.forName(charsetName);
		CharBuffer cb = CharBuffer.allocate(chars.length);
		cb.put(chars);
		cb.flip();
		ByteBuffer bb = cs.encode(cb);
		return bb.array();
	}

	// ���ֽ�תΪ�ַ�(����)
	private char[] getChars(byte[] bytes) {
		Charset cs = Charset.forName("GB2312");
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb = cs.decode(bb);

		return cb.array();
	}

}
