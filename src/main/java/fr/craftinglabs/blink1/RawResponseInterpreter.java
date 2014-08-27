package fr.craftinglabs.blink1;

public interface RawResponseInterpreter<T> {
    public T interpret(byte[] response);
}