package lb2;

import lb1.Lab1Com;

import java.io.*;

public class Lab2Com {

    public void parse() {
        File file = new File("src/main/java/lb2/com.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String st = "";
            while (st != null) {
                st = reader.readLine();
                doDecode(st);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doDecode(String st) {
        System.out.println("Start decode");
        Lab1Com lab1Com = new Lab1Com(0);
        String alp = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        lab1Com.setAlp(alp);
        for (int i = 0; i <= alp.length(); i++) {
            lab1Com.setOffset(i);
            if (st != null) {
                System.out.println(i + " " + lab1Com.decrypt(st));
            }
        }
        System.out.println("Finish decode");
    }
}
