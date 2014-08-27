package fr.craftinglabs.blink1;

public class PatternLineInterpreter implements RawResponseInterpreter<PatternLine> {

    public PatternLine interpret(byte[] response) {
        int red = convertToPositiveInt(response[2]);
        int green = convertToPositiveInt(response[3]);
        int blue = convertToPositiveInt(response[4]);
        
        int fadeTime = (convertToPositiveInt(response[5])*256 + convertToPositiveInt(response[6])) * 10;
        
        return new PatternLine(new RGBColor(red, green, blue), fadeTime, convertToPositiveInt(response[7]));
    }
    
    private int convertToPositiveInt(byte byt) {
        return byt >= 0 ? byt : byt + 256;
    }
}
