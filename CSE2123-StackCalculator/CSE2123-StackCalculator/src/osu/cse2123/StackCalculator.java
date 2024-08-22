package osu.cse2123;
/**
 * A series of methods to implement a calculator that evaluates postfix expressions
 * (also known as RPN or Reverse Polish Notation) using stacks and queues.
 *
 * @author ERIKA THOMAS
 * @version 10/5/2023
 */
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class StackCalculator {
/**
 * Returns the sequence of digit characters from the front of a String
 * that make up an integer value.
 * @param str String that starts with an integer value
 * @precond str starts with 1 or more digit characters
 * @return the sequence of digits that start the String str
 */
public static String getNextDigitSequence(String str) {
char x;
//initializing char x to correspond to the index at str
String newStr = "";
//intializing the string to return
for (int i = 0; i < str.length(); i++) {
//when i = 0 and it is less than the length of str, add 1 to every loop and do the following
x = str.charAt(i);
// x equals to the char at the index of i in str
if (Character.isDigit(x)) {
//if x is a digit do the following
//System.out.println(x);
//this is to see each char at x
newStr = Character.toString(x);
//add each char at x to newStr
}
}
return newStr;
//returning newStr
}
/**
 * Returns the sequence of whitespace characters from the front of a
 * String.
 * @param str String that starts with whitespace
 * @precond str starts with 1 or more whitespace characters
 * @return the
 */
public static String getNextWhitespaceSequence(String str) {
char v;
//initializing char v to correspond to the index at str
String newStr = "";
//intializing the string to return
int x = 0;
//initalizing int x to equal 0 to count the amount of whitespaces in str
for (int i = 0; i < str.length(); i++) {
//when i = 0 and it is less than the length of str, add 1 to every loop and do the following
v = str.charAt(i);
// v equals to the char at the index of i in str
//System.out.println(v);
//this is to see each char at v
if (Character.isWhitespace(v)) {
//if v has a whitespace do the following
x++;
//adding 1 to x
newStr = Integer.toString(x);
//adding the amount of whitespaces in str to newStr
}
}
return newStr;
//returning newStr
}
/**
 * Converts a string of mathematical operations into a Queue of
 * Strings. See the assignment write-up for details.
 * @param str String to convert
 * @precond str contains only digits, whitespace and operators + - / * % ( )
 * @return a queue with each digit sequence (number) and operator as a
separate entry in the same
 * order they appeared in the string str
 */
public static Queue<String> parseSymbols(String str) {
Queue<String> q = new LinkedList<>();
//making queue list called 'q'
String q1 = getNextDigitSequence(str);
q.add(q1);
//using the results of the getNextDigitSequence method and adding it to q
String q2 = getNextWhitespaceSequence(str);
q.add(q2);
//using the results of the getNextWhitespaceSequence method and adding it to q
return q;
//returing the queue list
}
/**
 * Combines operands num1 and num2 with the operator op and returns the
result
 * of (num1 op num2)
 * @param op - operator + - * % or /
 * @param num1 first operand to combine
 * @param num2 second operand to combine
 * @precond op is restricted to be one of "+" "-" "*" "%" "/"
 * @return num1 op num2
 */
public static int evaluate(String op, int num1, int num2) {
int answer = 0;
//initalizing answer at 0
for (int i = 0; i < op.length(); i++) {
//when int i = 0 and i is less than the length of string op, add i by 1 and do the following
char sign = op.charAt(i);
//initialize char sign to equal the character at i in string op
if (sign == '+') {
//if sign equals the character + do the following:
answer = num1 + num2;
//have answer equal the answer of num1 plus num2
}
else if (sign == '-') {
//if sign equals the character - do the following:
answer = num1 - num2;
//have answer equal the answer of num1 - num2
}
else if (sign == '*') {
//if sign equals the character * do the following:
answer = num1 * num2;
//have answer equal the answer of num1 * num2
}
else if (sign == '/') {
//if sign equals the character / do the following:
answer = num1/num2;
//have answer equal the answer of num1 /
}
else if (sign == '%') {
//if sign equals the character % do the following:
answer = num1%num2;
//have answer equal the answer of num1 % num2
}
}
return answer;
//return the answer
}
/**
 * Evaluates postfix algebraic expressions+
 * @param input queue to be evaluated
 * @precond input is formatted as a correct postfix algebraic expression
 * @return the evaluation of input as an postfix algebraic expression
 */
public static int postfixEvaluate(Queue<String> input) {
Deque<Integer> operands = new LinkedList<>();
//create a stack list called operands
int result = 0;
//initalize int result as 0
while (!input.isEmpty()) {
//while the queue input is not empty do the following
for (int i = 0; i < input.size(); i++) {
//when int i = 0 and i is less than the size of queue input, add 1 to i and do the following
String remove = input.remove();
//initilize string remove and remove the head of queue input
char head = remove.charAt(i);
//convert head to a character at index i
if (Character.isDigit(head)) {
//if head is a digit from 0-9 do the following
result = Character.getNumericValue(head);
//convert head back into the int result
operands.push(result);
//push (add) result to the operands stack
}
else {
//if head is an operator do the following
if (head == '+') {
//if head equals the character + do the following
int pop1 = operands.pop();
//initalize int pop1 and remove the head in the operands stack
int pop2 = operands.pop();
//initalize int pop2 and remove the head in the operands stack
result = evaluate("+", pop1, pop2);
//use the evalute method to find the answer to the equation
operands.push(result);
//push the result to the operands stack
}
else if (head == '-') {
//if head equals the character - do the following
int pop1 = operands.pop();
//initalize int pop1 and remove the head in the operands stack
int pop2 = operands.pop();
//initalize int pop2 and remove the head in the operands stack
result = evaluate("-", pop1, pop2);
//use the evalute method to find the answer to the equation
operands.push(result);
//push the result to the operands stack
}
else if (head == '*') {
//if head equals the character * do the following
int pop1 = operands.pop();
//initalize int pop1 and remove the head in the operands stack
int pop2 = operands.pop();
//initalize int pop2 and remove the head in the operands stack
result = evaluate("*", pop1, pop2);
//use the evalute method to find the answer to the equation
operands.push(result);
//push the result to the operands stack
}
else if (head == '/') {
//if head equals the character / do the following
int pop1 = operands.pop();
//initalize int pop1 and remove the head in the operands stack
int pop2 = operands.pop();
//initalize int pop2 and remove the head in the operands stack
result = evaluate("/", pop1, pop2);
//use the evalute method to find the answer to the equation
operands.push(result);
//push the result to the operands stack
}
else if (head == '%') {
//if head equals the character % do the following
int pop1 = operands.pop();
//initalize int pop1 and remove the head in the operands stack
int pop2 = operands.pop();
//initalize int pop2 and remove the head in the operands stack
result = evaluate("%", pop1, pop2);
//use the evalute method to find the answer to the equation
operands.push(result);
//push the result to the operands stack
}
}
}
}
return result;
//return the result
}
public static void main(String[] args) {
Queue<String> postfixQueue = new LinkedList<>();
Scanner scan = new Scanner(System.in);
System.out.print("Enter a postfix expression: ");
String postfix = scan.nextLine();
if (!postfix.isEmpty()) {
postfixQueue.add(postfix);
int result = postfixEvaluate(postfixQueue);
System.out.println("The answer is: " + result);
}
else {
System.out.print("Goodbye!");
}
}
}