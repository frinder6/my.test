package com.clazz.loader;

/**
 * Created by frinder_liu on 2016/4/26.
 */
public class ClassloaderTest {

	public static void main(String[] args) {
		// ClassLoader loader = ClassLoader.getSystemClassLoader();
		// try {
		// Class cls = loader.loadClass("com.clazz.loader.Demo");
		// System.out.println(cls.newInstance().toString());
		// } catch (ClassNotFoundException | IllegalAccessException |
		// InstantiationException e) {
		// e.printStackTrace();
		// }

		ClassLoader loader = ClassloaderTest.class.getClassLoader();
		while (loader != null) {
			System.out.println(loader.toString());
			loader = loader.getParent();
		}
		ClassloaderTest test = new ClassloaderTest();
		System.out.println(test.getClass().getName());
		System.out.println(test.getClass().getName().getBytes().length);
	}

}

class Demo {

	@Override
	public String toString() {
		return "this is demo!";
	}
}
