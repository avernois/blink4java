package fr.craftinglabs.blink1;

public class ReadCurrentColorInterpreter implements RawResponseInterpreter<RGBColor> {

    public RGBColor interpret(byte[] response) {
        int red = convertToPositiveInt(response[2]);
        int green = convertToPositiveInt(response[3]);
        int blue = convertToPositiveInt(response[4]);
        
        return new RGBColor(red, green, blue);
    }

    private int convertToPositiveInt(byte byt) {
        return byt >= 0 ? byt : byt + 256;
    }
}

