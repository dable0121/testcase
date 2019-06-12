package valuetransfer;

public class ValueTransfer {
    static class A {
        private String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }

    public static void func(A a,int b){
        a.setA("func20");
        b = 20;
        System.out.println("传入后a="+a.getA());
        System.out.println("传入后b="+b);
    }

    public static void main(String[] args) {
        A a = new A();
        a.setA("aaaa");
        int b = 10;

        func(a,b);
        System.out.println("原来的a="+a.getA()+"原来的b="+b);
        A aa = a;
        aa.setA("a2a2a2a2");
        int c = b;
        c=30;
        System.out.println("新的a==="+a.getA());
        System.out.println("新的c==="+c);
        System.out.println("新的b==="+b);
    }
}
