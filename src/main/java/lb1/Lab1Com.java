package lb1;

public class Lab1Com {

    Lab1Com(int offset) {
        this.offset = offset;
    }

    private int offset;

    private String alp = "abcdefghijklmnopqrstuvwxyz";

    public String crypt(String text) {
        StringBuilder stringBuilder = new StringBuilder();

        for (char ch : text.toCharArray()) {
            if (ch == ' ') {
                stringBuilder.append(ch);
                continue;
            }

            stringBuilder.append(
                    alp.charAt((alp.indexOf(ch) + alp.length() +
                            (offset % alp.length())) % alp.length())
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
                            (alp.indexOf(ch) + alp.length() -
                                    (offset % alp.length())) % alp.length()
                    )
            );
        }

        return stringBuilder.toString();
    }
}