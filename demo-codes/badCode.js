// Security Vulnerabilities

// SQL Injection (using mysql library example)
function sqlInjectionVulnerable(userInput) {
    const mysql = require('mysql');
    const connection = mysql.createConnection({
        host: 'localhost',
        user: 'root',
        password: 'password123', // Hardcoded password
        database: 'mydb'
    });
    
    // SQL Injection - string concatenation
    const query = "SELECT * FROM users WHERE username = '" + userInput + "'";
    connection.query(query, (error, results) => {
        console.log(results);
    });
}

// Hardcoded credentials
const API_KEY = "sk-1234567890abcdef"; // Hardcoded API key
const SECRET_TOKEN = "my_secret_token_12345"; // Hardcoded secret
const PASSWORD = "admin123"; // Hardcoded password

// Using eval - code injection risk
function dangerousEval(userInput) {
    return eval(userInput); // Never use eval with user input
}

// Command injection
function commandInjection(userInput) {
    const exec = require('child_process').exec;
    exec('ls ' + userInput); // Command injection vulnerability
}

// Path traversal
function pathTraversal(fileName) {
    const fs = require('fs');
    const path = './data/' + fileName; // No validation
    return fs.readFileSync(path, 'utf8');
}

// Insecure random
function insecureRandom() {
    return Math.random(); // Not cryptographically secure
}

// XSS vulnerability
function xssVulnerable(userInput) {
    document.getElementById('output').innerHTML = userInput; // XSS risk
}

// Weak cryptography
function weakCrypto(data) {
    const crypto = require('crypto');
    const hash = crypto.createHash('md5'); // MD5 is weak
    hash.update(data);
    return hash.digest('hex');
}

// Code Smells

// Too many parameters
function tooManyParameters(a, b, c, d, e, f, g, h, i) {
    return a + b + c + d + e + f + g + h + i;
}

// High cognitive complexity
function complexFunction(x, y, z) {
    if (x > 0) {
        if (y > 0) {
            if (z > 0) {
                if (x > y) {
                    if (y > z) {
                        if (x > z) {
                            if (x + y > z) {
                                if (x * y > z) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return false;
}

// Duplicated code block 1
function duplicatedCode1() {
    const name = "John";
    const age = 30;
    const email = "john@example.com";
    console.log("Name: " + name);
    console.log("Age: " + age);
    console.log("Email: " + email);
    console.log("Processing complete");
}

// Duplicated code block 2
function duplicatedCode2() {
    const name = "Jane";
    const age = 25;
    const email = "jane@example.com";
    console.log("Name: " + name);
    console.log("Age: " + age);
    console.log("Email: " + email);
    console.log("Processing complete");
}

// Magic numbers
function calculateTotal(price) {
    const tax = price * 0.18; // Magic number
    const discount = price * 0.15; // Magic number
    const shipping = price * 0.05; // Magic number
    return price + tax - discount + shipping;
}

// Unused variables
function unusedVariables() {
    const unusedVar = 42;
    const anotherUnused = "hello";
    const result = "world";
    return result;
}

// Empty catch block
function emptyCatch() {
    try {
        const result = riskyOperation();
    } catch (error) {
        // Empty catch block
    }
}

// Function too long
function veryLongFunction() {
    console.log("Line 1");
    console.log("Line 2");
    console.log("Line 3");
    console.log("Line 4");
    console.log("Line 5");
    console.log("Line 6");
    console.log("Line 7");
    console.log("Line 8");
    console.log("Line 9");
    console.log("Line 10");
    console.log("Line 11");
    console.log("Line 12");
    console.log("Line 13");
    console.log("Line 14");
    console.log("Line 15");
    console.log("Line 16");
    console.log("Line 17");
    console.log("Line 18");
    console.log("Line 19");
    console.log("Line 20");
    console.log("Line 21");
    console.log("Line 22");
    console.log("Line 23");
    console.log("Line 24");
    console.log("Line 25");
    console.log("Line 26");
    console.log("Line 27");
    console.log("Line 28");
    console.log("Line 29");
    console.log("Line 30");
    console.log("Line 31");
    console.log("Line 32");
    console.log("Line 33");
    console.log("Line 34");
    console.log("Line 35");
    console.log("Line 36");
    console.log("Line 37");
    console.log("Line 38");
    console.log("Line 39");
    console.log("Line 40");
}

// Bugs

// Null pointer bug
function nullPointerBug() {
    let str = null;
    return str.length; // TypeError
}

// Division by zero
function divideByZero(a) {
    return a / 0; // Returns Infinity but still a bug
}

// Unreachable code
function deadCode() {
    return 42;
    console.log("This will never execute"); // Dead code
    const x = 100;
}

// Incorrect comparison
function incorrectComparison(x) {
    if (x = 5) { // Should be === or ==
        return true;
    }
    return false;
}

// Type coercion bugs
function typeCoercionBug() {
    if ("0" == 0) { // Should use ===
        console.log("Equal");
    }
    if (null == undefined) { // Should use ===
        console.log("Also equal");
    }
}

// Missing await
async function missingAwait() {
    const promise = asyncOperation(); // Missing await
    console.log(promise); // Will log Promise object, not result
}

// Infinite loop
function infiniteLoop() {
    while (true) {
        console.log("Loop");
        // No break condition
    }
}

// Variable shadowing
function variableShadowing() {
    const x = 10;
    if (true) {
        const x = 20; // Shadows outer x
        console.log(x);
    }
}

// Not handling promise rejection
function unhandledPromise() {
    fetch('https://api.example.com/data'); // No .catch()
}

// Bad Practices

// Commented out code
function commentedCode() {
    console.log("Active code");
    // console.log("Old code 1");
    // console.log("Old code 2");
    // console.log("Old code 3");
    // console.log("Old code 4");
}

// TODO comments
function todoComments() {
    // TODO: Fix this later
    // FIXME: This is broken
    // HACK: Temporary solution
    console.log("Code");
}

// console.log for debugging
function debugWithConsole() {
    const x = 42;
    console.log("DEBUG: x =", x); // Should use proper logger
    console.log("DEBUG: Processing...");
    return x * 2;
}

// var instead of let/const
function usingVar() {
    var x = 10; // Should use let or const
    var y = 20;
    return x + y;
}

// Multiple var declarations
var a = 1, b = 2, c = 3; // Bad practice

// Callback hell
function callbackHell() {
    asyncOp1(function(result1) {
        asyncOp2(result1, function(result2) {
            asyncOp3(result2, function(result3) {
                asyncOp4(result3, function(result4) {
                    console.log(result4);
                });
            });
        });
    });
}

// Not using strict mode
function noStrictMode() {
    undeclaredVariable = 42; // Should error in strict mode
}

// God object - too many methods
const godObject = {
    method1: function() {},
    method2: function() {},
    method3: function() {},
    method4: function() {},
    method5: function() {},
    method6: function() {},
    method7: function() {},
    method8: function() {},
    method9: function() {},
    method10: function() {},
    method11: function() {},
    method12: function() {},
    method13: function() {},
    method14: function() {},
    method15: function() {},
    method16: function() {},
    method17: function() {},
    method18: function() {},
    method19: function() {},
    method20: function() {}
};

// Using with statement
function usingWith(obj) {
    with (obj) { // 'with' is deprecated
        console.log(property);
    }
}

// Modifying prototype of built-in objects
Array.prototype.myMethod = function() { // Bad practice
    return this.length;
};

// Global variables
var globalVar1 = "global";
globalVar2 = "also global"; // Implicit global

// Function in loop
function functionInLoop() {
    const funcs = [];
    for (var i = 0; i < 10; i++) {
        funcs.push(function() {
            console.log(i); // Will always log 10
        });
    }
    return funcs;
}

// No default case in switch
function noDefaultCase(x) {
    switch(x) {
        case 1:
            return "one";
        case 2:
            return "two";
        // Missing default case
    }
}

// Empty function
function emptyFunction() {
}

// Unused function
function unusedFunction() {
    console.log("Never called");
}

// Placeholder functions
function riskyOperation() {
    throw new Error("Error");
}

function asyncOperation() {
    return Promise.resolve("data");
}

function asyncOp1(callback) {}
function asyncOp2(data, callback) {}
function asyncOp3(data, callback) {}
function asyncOp4(data, callback) {}

// Trailing comma (not always supported)
const objWithTrailingComma = {
    prop1: "value1",
    prop2: "value2",
};

// Multiple statements on one line
var x = 1; var y = 2; var z = 3;
