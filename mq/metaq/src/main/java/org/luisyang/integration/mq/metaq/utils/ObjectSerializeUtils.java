package org.luisyang.integration.mq.metaq.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @author ly
 *
 */
public class ObjectSerializeUtils {

	/**
	 * 对象序列化方法
	 * @param obj
	 * @return
	 */
	public static byte[] ObjectToByte(java.lang.Object obj) {
		byte[] bytes = new byte[1024];
		try {
			// object to bytearray
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);

			bytes = bo.toByteArray();

			bo.close();
			oo.close();
		} catch (Exception e) {
			System.out.println("translation" + e.getMessage());
			e.printStackTrace();
		}
		return (bytes);
	}

	/**
	 * 对象反序列化方法
	 * @param bytes
	 * @return
	 */
	public static java.lang.Object ByteToObject(byte[] bytes) {
		java.lang.Object obj = new java.lang.Object();
		try {
			// bytearray to object
			ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
			ObjectInputStream oi = new ObjectInputStream(bi);

			obj = oi.readObject();

			bi.close();
			oi.close();
		} catch (Exception e) {
			System.out.println("translation" + e.getMessage());
			e.printStackTrace();
		}
		return obj;
	}

}
