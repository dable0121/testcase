package leetcode;

public class FactorialAdd {
    //1!+2!+3!+...+N!=
    public static long factorialAdd(int n) {
        long rs = 0;//初始结果
        long curr = 1;//起始数值1
        for (int i = 1; i <= n; i++) {
            curr *= i;
            rs += curr;
        }
        return rs;
    }

    public static void main(String[] args) {
        System.out.println(factorialAdd(5));
    }
}
