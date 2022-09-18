package lb2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Lab2Hard {

    private final Comparator<Pair> comparator = (o1, o2) -> {
        if (o1.getFrequency() > o2.getFrequency()) {
            return 1;
        } else if (o1.getFrequency() < o2.getFrequency()) {
            return -1;
        }
        return 0;
    };

    public void parse() {
        File file = new File("src/main/java/lb2/adv.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String st = "";
            int i = 1;
            StringBuilder stringBuilder = new StringBuilder();
            while (st != null) {
                st = reader.readLine();
                if (st != null && !st.isEmpty()) {
                    stringBuilder.append(st).append("$");
                    if (i % 3 == 0) {
                        doDecode(stringBuilder.substring(0, stringBuilder.lastIndexOf("$")), i / 3);
                        stringBuilder = new StringBuilder();
                    }
                    i++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doDecode(String st, int item) {
        String[] strings = st.split("\\$");
        String[] chars = strings[0].split(" ");
        String[] frequencies = strings[1].split(" ");
        List<Pair> list = analyzeText(strings[2], chars);
        List<Pair> oldList = getPairList(chars, frequencies);
        switch (item) {
            case 1 -> {
                swipe("ю", "ш", oldList);
            }
            case 2 -> {
                swipe("я", "б", oldList);
                swipe("ч", "й", oldList);
                swipe("х", "ц", oldList);
                swipe("ю", "ж", oldList);
            }
            case 3  -> swipe("щ", "ш", oldList);
            case 4 -> {
                swipe("а", "р", oldList);
                swipe("ф", "ц", oldList);
                swipe("п", "м", oldList);
                swipe("ф", "б", oldList);
            }
            case 5 -> {
                swipe("ч", "ц", oldList);
                swipe("щ", "ж", oldList);
            }
        }
        String text = getText(list, oldList, strings[2]);
        System.out.println(oldList);
        System.out.println(list);
        System.out.println(text);
    }

    private void swipe(String letter, String letter2, List<Pair> oldList) {
        Optional<Pair> pair = getPairByLetter(letter, oldList);
        Optional<Pair> pair1 = getPairByLetter(letter2, oldList);
        if (pair.isPresent() && pair1.isPresent()) {
            oldList.set(oldList.indexOf(pair.get()), pair1.get());
            oldList.set(oldList.indexOf(pair1.get()), pair.get());
        }
    }

    private String getText(List<Pair> list, List<Pair> oldList, String string) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = string.toCharArray();
        for (char aChar : chars) {
            String letter = findRealLetter(list, oldList, String.valueOf(aChar));
            stringBuilder.append(letter);
        }
        return stringBuilder.toString();
    }

    private String findRealLetter(List<Pair> list, List<Pair> oldList, String letter) {
        for (int i = 0; i < list.size(); i++) {
            if (letter.equals(list.get(i).getLetter())) {
                return oldList.get(i).getLetter();
            }
        }
        return letter;
    }

    private List<Pair> getPairList(String[] chars, String[] frequencies) {
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i <= chars.length - 1; i++) {
            list.add(new Pair(chars[i], Double.valueOf(frequencies[i])));
        }
        list.sort(comparator);
        return list;
    }

    private List<Pair> analyzeText(String string, String[] alp) {
        List<Pair> list = new ArrayList<>();
        char[] chars = string.toCharArray();
        int letterCount = 0;
        for (int i = 0; i <= alp.length - 1; i++) {
            list.add(new Pair(String.valueOf(alp[i]), 0.));
        }
        for (int i = 0; i <= chars.length - 1; i++) {
            Optional<Pair> pair = getPairByLetter(String.valueOf(chars[i]), list);
            if (pair.isPresent()) {
                Pair pair1 = pair.get();
                if (pair1.getFrequency() != null) {
                    letterCount++;
                    pair1.setFrequency(pair1.getFrequency() + 1);
                    list.set(list.indexOf(pair1), pair1);
                }
            }
        }
        for (Pair pair : list) {
            pair.setFrequency(Math.round(pair.getFrequency() / letterCount * 10000.0) / 10000.0);
        }
        list.sort(comparator);
        return list;
    }

    private Optional<Pair> getPairByLetter(String letter, List<Pair> list) {
        return list.stream().filter(it -> it.getLetter().equals(letter)).findFirst();
    }

}
