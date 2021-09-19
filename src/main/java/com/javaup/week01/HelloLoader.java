package com.javaup.week01;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class HelloLoader extends ClassLoader {

	public static void main (String args[]) {
		HelloLoader helloLoader = new HelloLoader();
		try {
			Class<?> helloClass = helloLoader.findClass("Hello");
			Object hello = helloClass.getDeclaredConstructor().newInstance();
			Stream.of(helloClass.getDeclaredMethods()).forEach(item -> {
				try {
					item.invoke(hello);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			});
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		
		byte[] bytes = getClassBytes(name, ".xlass");
		return this.defineClass(name, bytes, 0, bytes.length);
	}

	private byte[] getClassBytes(String name, String suffix) {
		byte[] classBytes = null;
		try (InputStream ins = this.getClass().getClassLoader().getResourceAsStream(name + suffix)){
			classBytes = new byte[ins.available()];
			ins.read(classBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return decode(classBytes);
	}
	
	private byte[] decode (byte[] classBytes) {
		byte result[] = new byte[classBytes.length];
		for (int i = 0; i < classBytes.length; i++) {
			result[i] = (byte)(255 - classBytes[i]);
		}
		return result;
	}
	
}
