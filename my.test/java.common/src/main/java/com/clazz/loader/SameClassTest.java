package com.clazz.loader;

import java.io.*;
import java.lang.reflect.Method;

/**
 * Created by frinder_liu on 2016/4/26.
 */
public class SameClassTest {

    public static void main(String[] args) {
        String path = "C:\\Users\\frinder_liu\\Desktop\\classes";
        ClassLoader loader1 = new FileSystemClassLoader(path);
        ClassLoader loader2 = new FileSystemClassLoader(path);
        String className = "com.clazz.loader.Sample";
        try {
            Class<?> class1 = loader1.loadClass(className);
            Object obj1 = class1.newInstance();
            Class<?> class2 = loader2.loadClass(className);
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            setSampleMethod.invoke(obj1, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


//class Sample {
//    private Sample instance;
//
//    public void setSample(Object instance) {
//        this.instance = (Sample) instance;
//    }
//}


class FileSystemClassLoader extends ClassLoader {

    private String rootDir;

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getClassData(String className) {
        String path = classNameToPath(className);
        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String className) {
        System.out.println(className);
        return rootDir + File.separatorChar
                + className.replace('.', File.separatorChar) + ".class";
    }
}

