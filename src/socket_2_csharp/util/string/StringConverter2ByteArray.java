package socket_2_csharp.util.string;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * �ַ������ֽ�����ת��
 * @author gzc
 *
 */
public final class StringConverter2ByteArray {
	
	/**
	 * ���ַ�(��ָ���ı���)תΪ�ֽ�(����)
	 * @param chars
	 * @param charsetName ָ�����ַ������룬��"UTF-8"
	 * @return
	 */
	public static byte[] getBytes(final char[] chars, final String charsetName) {
		Charset cs = Charset.forName(charsetName);	//"UTF-8"
		CharBuffer cb = CharBuffer.allocate(chars.length);
		cb.put(chars);
		cb.flip();
		ByteBuffer bb = cs.encode(cb);
		return bb.array();
	}

	/**
	 * ���ֽ�����תΪ�ַ�(��ָ�����ַ����������)
	 * @param bytes
	 * @param charsetName ָ�����ַ������룬��"UTF-8"
	 * @return
	 */
	public char[] getChars(byte[] bytes, final String charsetName) {
		Charset cs = Charset.forName(charsetName);	//"GB2312"
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb = cs.decode(bb);

		return cb.array();
	}
		
}
