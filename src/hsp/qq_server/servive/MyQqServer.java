
package hsp.qq_server.servive;

import hsp.qq_server.model.Message;
import hsp.qq_server.model.User;

import java.net.*;
import java.io.*;

import socket_2_csharp.util.StringConverter2ByteArray;

/**
 * ����qq�����������ڼ������ȴ�ĳ��qq�ͻ��ˣ�������
 */
public class MyQqServer {

	public MyQqServer() {

		try {
			// ��9999����
			System.out.println("JAVA����������9999����");
			ServerSocket ss = new ServerSocket(9999);
			String encode = "UTF-8";
			
			while (true) {
				// ����,�ȴ�����
				Socket s = ss.accept();				
				
				BufferedReader br = new  BufferedReader(new InputStreamReader(s.getInputStream(), encode));
				String rec = null;
				// ����C#�������ַ�������
				String responseLine = ""; 
				while ((responseLine = br.readLine()) != null) {
					//��readLine���������ǣ����Զ��������з������Ϊ�˱������ݵĸ�ʽ����Ҫ���������һ�����б�ʶ��
					rec += responseLine+"\n";
				}    
				// ���շ��������������
				System.out.println("���������յ����ַ�����" + rec);
				
				// ��Э�飬�� | �ָ����Ч����
				String[] data = rec.split("|");				
				User u = new User();
				u.setUserId(data[0]);
				u.setPasswd(data[1]);				
				System.out.println("���������յ��û�id:" + u.getUserId() + "  ����:"
						+ u.getPasswd());
				
				Message m = new Message();
				// ���տͻ��˷�������Ϣ.
//				ObjectOutputStream oos = new ObjectOutputStream(
//						s.getOutputStream());
				
				if ("123456".equals(u.getPasswd())) {
					System.out.println("�ɹ���½");
//					// ����һ���ɹ���½����Ϣ��
//					m.setMesType("1");
//					oos.writeObject(m);
//
//					// ����͵���һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ.
//					SerConClientThread scct = new SerConClientThread(s);
//					ManageClientThread.addClientThread(u.getUserId(), scct);
//					// ������ÿͻ���ͨ�ŵ��߳�.
//					scct.start();
//
//					// ��֪ͨ���������û�.
//					scct.notifyOther(u.getUserId());
				} else {
//					m.setMesType("2");
//					oos.writeObject(m);
					// �ر�Socket
//					s.close();
				}
				s.close();
			}

		} catch (Exception e) {
			e.printStackTrace();		
		} finally {

		}

	}

}
