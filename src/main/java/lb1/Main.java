package lb1;

public class Main {
    public static void main(String[] args) {
        Lab1Com lab1Com = new Lab1Com(-1);
        System.out.println(lab1Com.crypt("abc xyz hello world"));
        System.out.println(lab1Com.decrypt("zab wxy gdkkn vnqkc"));

        Lab1Hard lab1Hard = new Lab1Hard();
        System.out.println(lab1Hard.crypt("abc xyz hello world"));
        System.out.println(lab1Hard.decrypt("zyx cba svool dliow"));
    }


}
