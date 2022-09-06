package lb1;

public class Lab1Hard {
    private static final String alp = "abcdefghijklmnopqrstuvwxyz";
    private String alp2 = "";


    public Lab1Hard() {
        StringBuilder oldAlphabet = new StringBuilder(alp);
        StringBuilder newAlphabet = new StringBuilder();
        for (int i = alp.length() - 1; i >= 0; i--) {
            newAlphabet.append(oldAlphabet.charAt(i));
            oldAlphabet.deleteCharAt(i);
        }
        alp2 = newAlphabet.toString();
    }

    public String crypt(String text) {
        StringBuilder stringBuilder = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (ch == ' ') {
                stringBuilder.append(ch);
                continue;
            }

            stringBuilder.append(
                    alp2.charAt(
                            alp.indexOf(ch)
                    )
            );
        }

        return stringBuilder.toString();
    }

    public String decrypt(String text) {
        StringBuilder stringBuilder = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (ch == ' ') {
                stringBuilder.append(ch);
                continue;
            }

            stringBuilder.append(
                    alp.charAt(
                            alp2.indexOf(ch)
                    )
            );
        }

        return stringBuilder.toString();
    }
}
