using System;
using System.Data.SqlClient;
using System.IO;
using System.Security.Cryptography;
using System.Text;
using System.Net;

namespace SonarQubeDemo
{
    // Empty class - code smell
    public class EmptyClass { }

    public class SecurityVulnerabilities
    {
        // SQL Injection vulnerability
        public void SqlInjectionVulnerable(string userInput)
        {
            string connectionString = "Server=myServer;Database=myDb;User Id=sa;Password=123456;";
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                connection.Open();
                string query = "SELECT * FROM Users WHERE Username = '" + userInput + "'"; // SQL Injection
                SqlCommand command = new SqlCommand(query, connection);
                command.ExecuteReader();
            }
        }

        // Hardcoded credentials
        private string password = "admin123"; // Security hotspot
        private string apiKey = "sk-1234567890abcdef"; // Hardcoded secret

        // Weak cryptography
        public string WeakEncryption(string plainText)
        {
            MD5 md5 = MD5.Create(); // Weak hashing algorithm
            byte[] inputBytes = Encoding.ASCII.GetBytes(plainText);
            byte[] hashBytes = md5.ComputeHash(inputBytes);
            return Convert.ToBase64String(hashBytes);
        }

        // Path traversal vulnerability
        public string ReadFile(string fileName)
        {
            string path = "C:\\Data\\" + fileName; // Path traversal
            return File.ReadAllText(path);
        }

        // Insecure random
        public int GetRandomNumber()
        {
            Random random = new Random(); // Not cryptographically secure
            return random.Next();
        }
    }

    public class CodeSmells
    {
        // Too many parameters
        public void MethodWithTooManyParameters(string a, string b, string c, string d, string e, string f, string g, string h)
        {
            Console.WriteLine(a + b + c + d + e + f + g + h);
        }

        // Cognitive complexity - nested if statements
        public void ComplexMethod(int x, int y, int z)
        {
            if (x > 0)
            {
                if (y > 0)
                {
                    if (z > 0)
                    {
                        if (x > y)
                        {
                            if (y > z)
                            {
                                if (x > z)
                                {
                                    Console.WriteLine("Complex");
                                }
                            }
                        }
                    }
                }
            }
        }

        // Duplicated code block 1
        public void DuplicatedCode1()
        {
            string name = "John";
            int age = 30;
            string email = "john@example.com";
            Console.WriteLine("Name: " + name);
            Console.WriteLine("Age: " + age);
            Console.WriteLine("Email: " + email);
        }

        // Duplicated code block 2
        public void DuplicatedCode2()
        {
            string name = "Jane";
            int age = 25;
            string email = "jane@example.com";
            Console.WriteLine("Name: " + name);
            Console.WriteLine("Age: " + age);
            Console.WriteLine("Email: " + email);
        }

        // Magic numbers
        public double CalculateTotal(double price)
        {
            double tax = price * 0.18; // Magic number
            double discount = price * 0.15; // Magic number
            return price + tax - discount;
        }

        // Unused variable
        public void UnusedVariable()
        {
            int unusedVar = 42;
            string result = "Hello";
            Console.WriteLine(result);
        }

        // Empty catch block
        public void EmptyCatchBlock()
        {
            try
            {
                int result = 10 / 0;
            }
            catch (Exception ex)
            {
                // Empty catch - swallowing exception
            }
        }

        // Method too long
        public void VeryLongMethod()
        {
            Console.WriteLine("Line 1");
            Console.WriteLine("Line 2");
            Console.WriteLine("Line 3");
            Console.WriteLine("Line 4");
            Console.WriteLine("Line 5");
            Console.WriteLine("Line 6");
            Console.WriteLine("Line 7");
            Console.WriteLine("Line 8");
            Console.WriteLine("Line 9");
            Console.WriteLine("Line 10");
            Console.WriteLine("Line 11");
            Console.WriteLine("Line 12");
            Console.WriteLine("Line 13");
            Console.WriteLine("Line 14");
            Console.WriteLine("Line 15");
            Console.WriteLine("Line 16");
            Console.WriteLine("Line 17");
            Console.WriteLine("Line 18");
            Console.WriteLine("Line 19");
            Console.WriteLine("Line 20");
            Console.WriteLine("Line 21");
            Console.WriteLine("Line 22");
            Console.WriteLine("Line 23");
            Console.WriteLine("Line 24");
            Console.WriteLine("Line 25");
            Console.WriteLine("Line 26");
            Console.WriteLine("Line 27");
            Console.WriteLine("Line 28");
            Console.WriteLine("Line 29");
            Console.WriteLine("Line 30");
            Console.WriteLine("Line 31");
            Console.WriteLine("Line 32");
            Console.WriteLine("Line 33");
            Console.WriteLine("Line 34");
            Console.WriteLine("Line 35");
            Console.WriteLine("Line 36");
            Console.WriteLine("Line 37");
            Console.WriteLine("Line 38");
            Console.WriteLine("Line 39");
            Console.WriteLine("Line 40");
        }
    }

    public class Bugs
    {
        // Null pointer dereference
        public void NullPointerBug()
        {
            string str = null;
            Console.WriteLine(str.Length); // NullReferenceException
        }

        // Division by zero
        public int DivideByZero(int a)
        {
            return a / 0; // DivideByZeroException
        }

        // Resource leak - file not closed
        public void ResourceLeak()
        {
            FileStream fs = new FileStream("test.txt", FileMode.Open);
            // File never closed - resource leak
        }

        // Infinite loop
        public void InfiniteLoop()
        {
            while (true)
            {
                Console.WriteLine("Loop");
                // No break condition
            }
        }

        // Dead code
        public int DeadCode(int x)
        {
            return x * 2;
            Console.WriteLine("This will never execute"); // Dead code
        }

        // Incorrect exception handling
        public void CatchingGenericException()
        {
            try
            {
                throw new InvalidOperationException();
            }
            catch (Exception ex) // Too generic
            {
                throw ex; // Loses stack trace
            }
        }

        // Assignment instead of comparison
        public bool AssignmentInCondition(int x)
        {
            if (x = 5) // Should be ==
            {
                return true;
            }
            return false;
        }

        // String comparison with ==
        public bool StringComparison(string a, string b)
        {
            return a == b; // Should use Equals()
        }
    }

    public class BadPractices
    {
        // Public field instead of property
        public string PublicField;

        // No access modifier
        string noAccessModifier;

        // Commented out code
        public void CommentedCode()
        {
            Console.WriteLine("Active code");
            // Console.WriteLine("Old code 1");
            // Console.WriteLine("Old code 2");
            // Console.WriteLine("Old code 3");
        }

        // TODO comments
        public void TodoComments()
        {
            // TODO: Fix this later
            // FIXME: This is broken
            // HACK: Temporary solution
            Console.WriteLine("Code");
        }

        // Non-constant field names should not be PascalCase
        private int MyBadFieldName = 0;

        // Multiple variables declared on same line
        int a, b, c = 0;

        // Nested class
        public class NestedClass
        {
            public class DeeplyNestedClass
            {
                public void Method() { }
            }
        }
    }

    // Class with too many methods
    public class GodClass
    {
        public void Method1() { }
        public void Method2() { }
        public void Method3() { }
        public void Method4() { }
        public void Method5() { }
        public void Method6() { }
        public void Method7() { }
        public void Method8() { }
        public void Method9() { }
        public void Method10() { }
        public void Method11() { }
        public void Method12() { }
        public void Method13() { }
        public void Method14() { }
        public void Method15() { }
        public void Method16() { }
        public void Method17() { }
        public void Method18() { }
        public void Method19() { }
        public void Method20() { }
    }
}
