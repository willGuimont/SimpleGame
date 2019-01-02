package io.github.williamguimont.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

public class Serializator {

    public static class SerializationException extends Exception {

        private static final long serialVersionUID = 7317936501184462520L;

        public SerializationException(String message) {
            super(message);
        }
    }

    public static String serializeToString(Serializable object) throws SerializationException {
        try {
            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteOutput);
            objectStream.writeObject(object);
            objectStream.close();
            return Base64.getEncoder().encodeToString(byteOutput.toByteArray());
        } catch (IOException e) {
            throw new SerializationException(e.getMessage());
        }
    }

    public static Object loadFromString(String object) throws SerializationException {
        try {
            byte[] objectByte = Base64.getDecoder().decode(object);
            ObjectInputStream objectStream = new ObjectInputStream(new ByteArrayInputStream(objectByte));
            Object ob = objectStream.readObject();
            objectStream.close();
            return ob;
        } catch (IOException | ClassNotFoundException e) {
            throw new SerializationException(e.getMessage());
        }
    }
}
