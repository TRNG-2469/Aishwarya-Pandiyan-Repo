public class PrimitivePractice {
    public static void main(String[] args) {

        byte myByte = 12;
        short myShort = 1000;
        int myInt = 100000;
        long myLong = 15000000000L;
        float myFloat = 3.14f;
        double myDouble = 3.14159265358979;
        char myChar = 'J';
        boolean myBoolean = true;

        System.out.println("byte: " + myByte);
        System.out.println("short: " + myShort);
        System.out.println("int: " + myInt);
        System.out.println("long " + myLong);
        System.out.println("float: " + myFloat);
        System.out.println("double: " + myDouble);
        System.out.println("char: " + myChar);
        System.out.println("boolean: " + myBoolean);

        byte smallVal = 50;
        int largeVal = smallVal;
        System.out.println("smallVal (byte): " + smallVal);
        System.out.println("largeVal (int, widened): " + largeVal);

        double decimalVal = 99.99;
        int truncatedVal = (int) decimalVal;
        System.out.println("decimalVal (double): " + decimalVal);
        System.out.println("truncatedVal (int, cast): " + truncatedVal);

        int a = 10;
        int b = a;
        b = 20;
        System.out.println("a (primitive): " + a);
        System.out.println("b (primitive): " + b);

        int[] firstArray = new int[]{10, 20, 30};
        int[] secondArray= firstArray;

        secondArray[0] = 999;

        System.out.println("firstArray[0]: " + firstArray[0]);
        System.out.println("secondArray[0]: " + secondArray[0]);

    }
}
