package Monday;

public class VerifyApp {
    public static void main(String[] args) {
        System.out.println("=== Environment Verification successful! ====");
        System.out.println("JDK Version: " + System.getProperty("java.version"));
        System.out.println("OS Name:  " + System.getProperty("os.name"));
    }
}
