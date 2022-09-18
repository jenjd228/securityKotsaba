package lb2;

public class Pair{

    private String letter;
    private Double frequency;

    public Pair(String letter, Double frequency){
        this.letter = letter;
        this.frequency = frequency;
    }

    public void setFrequency(Double frequency){
        this.frequency = frequency;
    }

    public Double getFrequency(){
        return frequency;
    }

    public String getLetter() {
        return letter;
    }

    @Override
    public String toString() {
        return letter.concat(" : ").concat(frequency.toString());
    }
}
