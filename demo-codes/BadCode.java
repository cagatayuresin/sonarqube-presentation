import java.io.*;
import java.sql.*;
import java.util.*;
import java.security.MessageDigest;
import javax.servlet.http.*;
import java.net.*;

// Security Vulnerabilities
public class BadCode {
    
    // SQL Injection vulnerability
    public void sqlInjectionVulnerable(String userInput) throws SQLException {
        String connectionUrl = "jdbc:mysql://localhost:3306/db";
        Connection conn = DriverManager.getConnection(connectionUrl, "root", "password123");
        
        // SQL Injection - concatenating user input
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
    }
    
    // Hardcoded credentials
    private static final String PASSWORD = "admin123"; // Security hotspot
    private static final String API_KEY = "sk-1234567890abcdef"; // Hardcoded secret
    private String dbPassword = "root"; // Hardcoded password
    
    // Weak cryptography
    public String weakEncryption(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5"); // Weak algorithm
        byte[] hash = md.digest(input.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }
    
    // Path traversal vulnerability
    public String readFile(String fileName) throws IOException {
        File file = new File("C:/data/" + fileName); // Path traversal
        BufferedReader br = new BufferedReader(new FileReader(file));
        return br.readLine();
    }
    
    // Insecure random
    public int getRandomNumber() {
        Random random = new Random(); // Not cryptographically secure
        return random.nextInt();
    }
    
    // Command injection
    public void executeCommand(String userInput) throws IOException {
        Runtime.getRuntime().exec("cmd /c dir " + userInput); // Command injection
    }
    
    // Deserialization vulnerability
    public Object deserializeObject(byte[] data) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        return ois.readObject(); // Insecure deserialization
    }
    
    // Cookie security issue
    public void insecureCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("sessionId", "12345");
        // Missing secure and httpOnly flags
        response.addCookie(cookie);
    }
}

// Code Smells
class CodeSmells {
    
    // Too many parameters
    public void methodWithTooManyParameters(String a, String b, String c, String d, 
                                           String e, String f, String g, String h, String i) {
        System.out.println(a + b + c + d + e + f + g + h + i);
    }
    
    // Cognitive complexity - nested conditions
    public void complexMethod(int x, int y, int z) {
        if (x > 0) {
            if (y > 0) {
                if (z > 0) {
                    if (x > y) {
                        if (y > z) {
                            if (x > z) {
                                if (x + y > z) {
                                    System.out.println("Complex");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    // Duplicated code block 1
    public void duplicatedCode1() {
        String name = "John";
        int age = 30;
        String email = "john@example.com";
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
        System.out.println("Processing complete");
    }
    
    // Duplicated code block 2
    public void duplicatedCode2() {
        String name = "Jane";
        int age = 25;
        String email = "jane@example.com";
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
        System.out.println("Processing complete");
    }
    
    // Magic numbers
    public double calculateTotal(double price) {
        double tax = price * 0.18; // Magic number
        double discount = price * 0.15; // Magic number
        double shipping = price * 0.05; // Magic number
        return price + tax - discount + shipping;
    }
    
    // Unused variables
    public void unusedVariables() {
        int unusedVar = 42;
        String anotherUnused = "hello";
        String result = "world";
        System.out.println(result);
    }
    
    // Empty catch block
    public void emptyCatchBlock() {
        try {
            int result = 10 / 0;
        } catch (Exception e) {
            // Empty catch - swallowing exception
        }
    }
    
    // Method too long
    public void veryLongMethod() {
        System.out.println("Line 1");
        System.out.println("Line 2");
        System.out.println("Line 3");
        System.out.println("Line 4");
        System.out.println("Line 5");
        System.out.println("Line 6");
        System.out.println("Line 7");
        System.out.println("Line 8");
        System.out.println("Line 9");
        System.out.println("Line 10");
        System.out.println("Line 11");
        System.out.println("Line 12");
        System.out.println("Line 13");
        System.out.println("Line 14");
        System.out.println("Line 15");
        System.out.println("Line 16");
        System.out.println("Line 17");
        System.out.println("Line 18");
        System.out.println("Line 19");
        System.out.println("Line 20");
        System.out.println("Line 21");
        System.out.println("Line 22");
        System.out.println("Line 23");
        System.out.println("Line 24");
        System.out.println("Line 25");
        System.out.println("Line 26");
        System.out.println("Line 27");
        System.out.println("Line 28");
        System.out.println("Line 29");
        System.out.println("Line 30");
        System.out.println("Line 31");
        System.out.println("Line 32");
        System.out.println("Line 33");
        System.out.println("Line 34");
        System.out.println("Line 35");
        System.out.println("Line 36");
        System.out.println("Line 37");
        System.out.println("Line 38");
        System.out.println("Line 39");
        System.out.println("Line 40");
    }
}

// Bugs
class Bugs {
    
    // Null pointer dereference
    public void nullPointerBug() {
        String str = null;
        System.out.println(str.length()); // NullPointerException
    }
    
    // Division by zero
    public int divideByZero(int a) {
        return a / 0; // ArithmeticException
    }
    
    // Resource leak - connection not closed
    public void resourceLeak() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db");
        Statement stmt = conn.createStatement();
        // Connection never closed - resource leak
    }
    
    // Infinite loop
    public void infiniteLoop() {
        while (true) {
            System.out.println("Loop");
            // No break condition
        }
    }
    
    // Dead code
    public int deadCode(int x) {
        return x * 2;
        System.out.println("This will never execute"); // Dead code
    }
    
    // Incorrect exception handling
    public void incorrectExceptionHandling() throws Exception {
        try {
            riskyOperation();
        } catch (Exception e) {
            throw e; // Loses stack trace
        }
    }
    
    // Catching generic exception
    public void catchingGenericException() {
        try {
            riskyOperation();
        } catch (Exception e) { // Too generic
            e.printStackTrace();
        }
    }
    
    // Array index out of bounds
    public void arrayBug() {
        int[] array = new int[5];
        int value = array[10]; // ArrayIndexOutOfBoundsException
    }
    
    // Thread.run() instead of Thread.start()
    public void threadBug() {
        Thread thread = new Thread(() -> System.out.println("Hello"));
        thread.run(); // Should be start()
    }
    
    private void riskyOperation() throws Exception {
        throw new Exception("Error");
    }
}

// Bad Practices
class BadPractices {
    
    // Public field instead of getter/setter
    public String publicField;
    
    // No access modifier
    String noAccessModifier;
    
    // Commented out code
    public void commentedCode() {
        System.out.println("Active code");
        // System.out.println("Old code 1");
        // System.out.println("Old code 2");
        // System.out.println("Old code 3");
    }
    
    // TODO comments
    public void todoComments() {
        // TODO: Fix this later
        // FIXME: This is broken
        // HACK: Temporary solution
        System.out.println("Code");
    }
    
    // Non-final static field
    public static int nonFinalStatic = 0;
    
    // Using System.out for logging
    public void poorLogging() {
        System.out.println("DEBUG: Processing..."); // Should use logger
        System.err.println("ERROR occurred");
    }
    
    // String comparison with ==
    public boolean stringComparison(String a, String b) {
        return a == b; // Should use equals()
    }
    
    // Synchronized on string
    public void synchronizedOnString() {
        String lock = "lock";
        synchronized(lock) { // Bad practice
            System.out.println("Critical section");
        }
    }
    
    // Using deprecated methods
    @Deprecated
    public void deprecatedMethod() {
        // Deprecated method still in use
    }
    
    // Float comparison with ==
    public boolean floatComparison(float a, float b) {
        return a == b; // Should use epsilon comparison
    }
}

// Empty class
class EmptyClass {
}

// God class - too many methods
class GodClass {
    public void method1() {}
    public void method2() {}
    public void method3() {}
    public void method4() {}
    public void method5() {}
    public void method6() {}
    public void method7() {}
    public void method8() {}
    public void method9() {}
    public void method10() {}
    public void method11() {}
    public void method12() {}
    public void method13() {}
    public void method14() {}
    public void method15() {}
    public void method16() {}
    public void method17() {}
    public void method18() {}
    public void method19() {}
    public void method20() {}
    public void method21() {}
    public void method22() {}
    public void method23() {}
    public void method24() {}
    public void method25() {}
}

// Class with too many fields
class ClassWithTooManyFields {
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    private String field7;
    private String field8;
    private String field9;
    private String field10;
    private String field11;
    private String field12;
    private String field13;
    private String field14;
    private String field15;
}
