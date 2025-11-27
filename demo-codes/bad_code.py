import os
import pickle
import random
import hashlib
import sqlite3
from flask import Flask, request
import subprocess

# Security Vulnerabilities

def sql_injection_vulnerable(user_input):
    """SQL Injection vulnerability"""
    conn = sqlite3.connect('database.db')
    cursor = conn.cursor()
    # SQL Injection - never do this
    query = f"SELECT * FROM users WHERE username = '{user_input}'"
    cursor.execute(query)
    return cursor.fetchall()

def command_injection(user_input):
    """Command injection vulnerability"""
    # Command injection risk
    os.system("ls " + user_input)
    subprocess.call("ping " + user_input, shell=True)

def hardcoded_credentials():
    """Hardcoded credentials - security issue"""
    password = "admin123"  # Hardcoded password
    api_key = "sk-1234567890abcdef"  # Hardcoded API key
    secret_token = "my_secret_token_12345"
    return password, api_key

def weak_cryptography(data):
    """Weak cryptographic algorithm"""
    # MD5 is weak and should not be used
    return hashlib.md5(data.encode()).hexdigest()

def insecure_random():
    """Using insecure random number generator"""
    # random module is not cryptographically secure
    return random.randint(1, 1000000)

def pickle_deserialization(data):
    """Insecure deserialization"""
    # Pickle is unsafe with untrusted data
    return pickle.loads(data)

def path_traversal(filename):
    """Path traversal vulnerability"""
    # No validation - can access any file
    with open("/data/" + filename, 'r') as f:
        return f.read()

app = Flask(__name__)

@app.route('/eval')
def dangerous_eval():
    """Code injection via eval"""
    user_code = request.args.get('code')
    return str(eval(user_code))  # Never use eval with user input

@app.route('/exec')
def dangerous_exec():
    """Code injection via exec"""
    user_code = request.args.get('code')
    exec(user_code)  # Never use exec with user input
    return "Executed"

# Code Smells

def too_many_parameters(a, b, c, d, e, f, g, h, i, j):
    """Too many parameters"""
    return a + b + c + d + e + f + g + h + i + j

def cognitive_complexity(x, y, z):
    """High cognitive complexity"""
    if x > 0:
        if y > 0:
            if z > 0:
                if x > y:
                    if y > z:
                        if x > z:
                            if x + y > z:
                                if x * y > z:
                                    return True
    return False

def duplicated_code_1():
    """Duplicated code block 1"""
    name = "John"
    age = 30
    email = "john@example.com"
    print(f"Name: {name}")
    print(f"Age: {age}")
    print(f"Email: {email}")
    print("Processing complete")

def duplicated_code_2():
    """Duplicated code block 2"""
    name = "Jane"
    age = 25
    email = "jane@example.com"
    print(f"Name: {name}")
    print(f"Age: {age}")
    print(f"Email: {email}")
    print("Processing complete")

def magic_numbers(price):
    """Magic numbers - should use constants"""
    tax = price * 0.18  # Magic number
    discount = price * 0.15  # Magic number
    shipping = price * 0.05  # Magic number
    return price + tax - discount + shipping

def unused_variables():
    """Unused variables"""
    unused_var = 42
    another_unused = "hello"
    result = "world"
    return result

def empty_except():
    """Empty except block"""
    try:
        result = 10 / 0
    except:
        pass  # Swallowing exception silently

def broad_except():
    """Catching too broad exception"""
    try:
        risky_operation()
    except Exception:  # Too broad
        pass

def very_long_function():
    """Function too long"""
    print("Line 1")
    print("Line 2")
    print("Line 3")
    print("Line 4")
    print("Line 5")
    print("Line 6")
    print("Line 7")
    print("Line 8")
    print("Line 9")
    print("Line 10")
    print("Line 11")
    print("Line 12")
    print("Line 13")
    print("Line 14")
    print("Line 15")
    print("Line 16")
    print("Line 17")
    print("Line 18")
    print("Line 19")
    print("Line 20")
    print("Line 21")
    print("Line 22")
    print("Line 23")
    print("Line 24")
    print("Line 25")
    print("Line 26")
    print("Line 27")
    print("Line 28")
    print("Line 29")
    print("Line 30")
    print("Line 31")
    print("Line 32")
    print("Line 33")
    print("Line 34")
    print("Line 35")
    print("Line 36")
    print("Line 37")
    print("Line 38")
    print("Line 39")
    print("Line 40")

# Bugs

def null_pointer_bug():
    """Null pointer (None) access"""
    my_var = None
    return my_var.upper()  # AttributeError

def division_by_zero():
    """Division by zero"""
    return 10 / 0

def resource_leak():
    """Resource not closed"""
    f = open('test.txt', 'r')
    data = f.read()
    # File never closed

def infinite_loop():
    """Infinite loop"""
    while True:
        print("Loop")
        # No break condition

def unreachable_code():
    """Dead/unreachable code"""
    return 42
    print("This will never execute")
    x = 100

def mutable_default_argument(item, my_list=[]):
    """Mutable default argument bug"""
    my_list.append(item)
    return my_list

def comparison_with_none(value):
    """Wrong None comparison"""
    if value == None:  # Should use 'is'
        return True
    return False

def catching_and_raising():
    """Incorrect exception handling"""
    try:
        risky_operation()
    except Exception as e:
        raise e  # Loses traceback, should use 'raise'

# Bad Practices

def commented_out_code():
    """Commented out code"""
    print("Active code")
    # print("Old code 1")
    # print("Old code 2")
    # print("Old code 3")
    # print("Old code 4")

def todo_comments():
    """TODO comments"""
    # TODO: Fix this later
    # FIXME: This is broken
    # HACK: Temporary solution
    # XXX: Check this
    print("Code")

def print_statements_for_debugging():
    """Debug print statements left in code"""
    x = 42
    print("DEBUG: x =", x)
    print("DEBUG: Processing...")
    return x * 2

# Global variables - bad practice
GLOBAL_VAR = 0
global_counter = 0

def modifying_global():
    """Modifying global variables"""
    global global_counter
    global_counter += 1

# Class with too many methods (God class)
class GodClass:
    def method1(self): pass
    def method2(self): pass
    def method3(self): pass
    def method4(self): pass
    def method5(self): pass
    def method6(self): pass
    def method7(self): pass
    def method8(self): pass
    def method9(self): pass
    def method10(self): pass
    def method11(self): pass
    def method12(self): pass
    def method13(self): pass
    def method14(self): pass
    def method15(self): pass
    def method16(self): pass
    def method17(self): pass
    def method18(self): pass
    def method19(self): pass
    def method20(self): pass

# Empty class
class EmptyClass:
    pass

def risky_operation():
    """Placeholder for risky operation"""
    pass

# Multiple statements on one line
x = 1; y = 2; z = 3

# Wildcard import
from os import *

# Unused import
import json
import sys
import re

# Wrong naming convention
def MyFunction():  # Should be snake_case
    pass

class my_class:  # Should be PascalCase
    pass

CONSTANT_value = 42  # Should be all caps
