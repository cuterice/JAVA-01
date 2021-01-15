package com.hm.mytest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class WorldClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class<?> clazz = new WorldClassLoader().findClass("Hello");
            Method method = clazz.getDeclaredMethod("hello");
            method.invoke(clazz.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    private byte[] toByteArray() {
        try {
            File f = new File(WorldClassLoader.class.getClassLoader().getResource("Hello.xlass").getFile());
            FileInputStream in = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[(int)f.length()];
            int n = 0;
            while ((n = in.read(buffer)) != -1 ) {
                out.write(buffer, 0, n);
            }
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

        return null;
    }

    @Override
    protected Class<?> findClass(String filename) throws ClassNotFoundException {
        byte[] data = toByteArray();

        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (255 - data[i]);
        }
        return defineClass(filename, data , 0 , data.length);
    }
}



