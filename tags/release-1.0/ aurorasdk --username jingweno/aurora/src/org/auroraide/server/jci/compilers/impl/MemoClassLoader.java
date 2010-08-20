package org.auroraide.server.jci.compilers.impl;



import org.apache.commons.jci.stores.ResourceStore;

public class MemoClassLoader extends ClassLoader {

	public Class<?> defineClass(ResourceStore store,String className){
		byte[] b=loadClassData(store,className);
		String name=className.substring(0, className.length()-6).replace("/", ".");
		return defineClass(name, b, 0, b.length);
	}
	
	private byte[] loadClassData(ResourceStore store, String className){
		return store.read(className);
	}
}
