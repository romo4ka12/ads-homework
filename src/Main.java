public class Main {

    public static void main(String[] args) {
//        Task 1
//         printDigitsOfNumber(5123);
//
//        Task 2
//        int n = 5;
//        double average = sum(n, new int[] {3,5,2,8,4}) / (double) n;
//        System.out.println(average);
//
//        Task 3
//        System.out.println(isPrime(15));
//
//        Task 4
//        System.out.println(fact(5));
//
//        Task 5
//        System.out.println(fibonacci(7));
//
//        Task 6
//        System.out.println(power(2,4));
//
//        Task 7
//        reverse(4, new int[] {1,2,3,4});
//
//        Task 8
//        System.out.println(isDigits("1234a5"));
//
//        Task 9
//        System.out.println(charNum("123abc"));
//
//        Task 10
//        System.out.println(GCD(142,48));
    }

    static void printDigitsOfNumber(int n){
        if (n == 0){
            return;
        }
        else {
            printDigitsOfNumber(n / 10);
            System.out.println(n % 10);
        }
    }

    static int sum(int n, int[] numbers){
        if(n == 0){
            return 0;
        }
        else {
            return numbers[n-1] + sum(n - 1, numbers);
        }

    }


    static boolean isPrime(int n){
        return check(n,n);
    }

    static boolean check(int number, int n){
        if((n - 1) == 1){
            return true;
        }
        else{
            if(number % (n-1) == 0) {
                return false;
            }
            else{
                return check(number, n - 1);
            }
        }
    }

    static int fact(int n){
        if(n <= 1){
            return 1;
        }
        else{
            return fact(n - 1) * n;
        }
    }

    static int fibonacci(int n){
        if(n == 0) {
            return 0;
        }
        if(n == 1){
            return 1;
        }
        else{
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    static int power(int a, int n) {
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return a;
        }
        else{
            return a * power(a, n-1);
        }

    }

    static void reverse(int n, int[] numbers){
        if(n == 1){
            System.out.println(numbers[0]);
            return;
        }
        else{
            System.out.println(numbers[n-1]);
            reverse(n-1, numbers);
        }
    }

    static String isDigits(String text){
        if (text == "") {
            return "Yes";
        }
        if(!Character.isDigit(text.charAt(0))){
            return "No";
        }
        else{
            return isDigits(text.substring(1));
        }

    }

    static int charNum(String text){
        if(text == ""){
            return 0;
        }
        else{
            return charNum(text.substring(1)) + 1;
        }
    }

    static int GCD(int a, int b){
        if(a % b == 0){
            return b;
        }
        else{
            return GCD(b,a % b);
        }
        }

    }
