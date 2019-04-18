package string;

public class PidGen {
    public static void main(String[] args) {
        System.out.println(String.format("P%05d", Long.parseLong("P00101".substring(1)) + 1));
    }
}
