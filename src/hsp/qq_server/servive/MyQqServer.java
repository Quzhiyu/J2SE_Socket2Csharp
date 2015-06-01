
package hsp.qq_server.servive;

import hsp.qq_server.model.Message;
import hsp.qq_server.model.MessageType;
import hsp.qq_server.model.User;

import java.net.*;
import java.io.*;

import socket_2_csharp.util.StringConverter2ByteArrayUtil;

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
			int acceptNum = 0;
			
			while (true) {
				// ����,�ȴ�����
				Socket s = ss.accept();	
				acceptNum++;
				System.out.printf("��%d������\n", acceptNum);
				
				BufferedReader br = new  BufferedReader(new InputStreamReader(s.getInputStream(), encode));			
				String responseLine = null;
				String rec = responseLine;
				// ����C#�������ַ�������				
				// ��readLine���������ǣ����Զ��������з�
				responseLine = br.readLine();
				rec = responseLine;
//				while ((responseLine = br.readLine()) != null ) {
//					//��readLine���������ǣ����Զ��������з������Ϊ�˱������ݵĸ�ʽ����Ҫ���������һ�����б�ʶ��
//					rec += responseLine + "\n";					
//				}    
				// ���շ��������������
				System.out.printf("���������յ����ַ���=%s\n", rec);
				
				// ��Э�飬�� | �ָ����Ч����
				// split�����Ĳ���Ϊ������ʽ��������һ���򵥵��ַ��������Զ�һЩ�����ַ�Ҫ��ת���ַ�
				// �������� | �͵���ǰ���\\���������ܵõ���ȷ�Ľ����
				String[] data = rec.split("\\|");	// split("|"); // û��������Ч������ 			
				User u = new User();
				u.setUserId(data[0].trim());
				u.setPasswd(data[1].trim());				
				System.out.println("���������յ��û�id:" + u.getUserId() + "  , ����:"
						+ u.getPasswd());				
				
				
				if ("123456".equals(u.getPasswd())) {		
					Message m = new Message();
					// ����һ���ɹ���½����Ϣ��
					m.setMesType(MessageType.message_succeed);
					// ��������ͳɹ���½�ַ���					
					OutputStream outputStream = s.getOutputStream();
					
					BufferedOutputStream bos = new BufferedOutputStream(outputStream);
//					 ���ַ���תΪ�ֽ�����
					byte[] buf = StringConverter2ByteArrayUtil.getBytes(m.getMesType(), encode);	
					bos.write(buf, 0, buf.length);
					bos.flush();	
					System.out.println("�ɹ���½");
					
//					PrintWriter printWriter = new PrintWriter(outputStream);					
//					printWriter.write(m.getMesType());//C#�ͻ����ղ���
//					System.out.println("�ɹ���½");					
				

					// ����͵���һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ.
					SerConClientThread scct = new SerConClientThread(s);
					ManageClientThread.addClientThread(u.getUserId(), scct);
					// ������ÿͻ���ͨ�ŵ��߳�.
					scct.start();

					// ��֪ͨ���������û�.
//					scct.notifyOther(u.getUserId());
				} else {
					System.out.println("��¼ʧ��");
					// ��¼ʧ��
//					m.setMesType(MessageType.message_login_fail);
//					oos.writeObject(m);
//					 �ر�Socket
					s.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();		
		} finally {

		}

	}

}
