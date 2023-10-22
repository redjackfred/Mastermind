import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

abstract public class WheelOfFortune extends GuessingGame{
    protected int limitGuess = 8;
    // Answer phrase
    String phrase;
    // Hidden phrase to be guessed, and we need to use this for sophisticated bot, so I set it as a public static
    public static StringBuilder hiddenPhrase;
    private char guess;
    List<String> phraseList;
    protected Set<Integer> usedPhraseIndex;

    public WheelOfFortune(){
        try {
            this.phraseList = Files.readAllLines(Paths.get("testThreePhrases.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // phrases list shouldn't be empty
        assert this.phraseList != null;
        usedPhraseIndex = new HashSet<>();
    }

    // Get a phrase randomly from 'phrases.txt'
    public void randomPhrase() {
        // Get a random phrase from the list
        Random rand = new Random();
        int r;
        while(true){
            r = rand.nextInt(phraseList.size());
            if(!usedPhraseIndex.contains(r)){
                usedPhraseIndex.add(r);
                break;
            }
        }
        this.phrase = this.phraseList.get(r);
        System.out.println(this.phrase);  // For testing only
    }

    // Replace all letters to be guessed with '*'
    public void generateHiddenPhrase() {
        // Replace all letters from the phrase with *
        this.hiddenPhrase = new StringBuilder(this.phrase);
        for (int i = 0; i < this.hiddenPhrase.length(); i++) {
            if (Character.isLetter(this.hiddenPhrase.charAt(i))) {
                this.hiddenPhrase.setCharAt(i, '*');
            }
        }
    }

    //Playing progress and it returns a score of a game
    public int playingProgress(int limitGuess){
        StringBuilder previousGuessSB = new StringBuilder();
        this.randomPhrase();
        this.generateHiddenPhrase();
        int score;

        while (limitGuess != 0 && !this.phrase.contentEquals(this.hiddenPhrase)) {
            System.out.println("\nHidden Phrase: " + this.hiddenPhrase);
            System.out.println("Previous Guesses: " + previousGuessSB);
            this.guess = this.getGuess(previousGuessSB.toString());
            previousGuessSB.append(this.guess);
            if (!this.processGuess()) {
                limitGuess--;
                System.out.println("You miss. You have " + limitGuess + " chance" + ((limitGuess != 1) ? "s" : "") + " left.");
            } else {
                System.out.println("Bingo!");
            }
        }

        System.out.println("\nAnswer: " + this.phrase);
        if (limitGuess != 0) {
            score = limitGuess*limitGuess;
            System.out.println("You Win!");
        } else {
            score = 0;
            System.out.println("You Loss...");
        }

        return score;
    }

    // Main logic for guessing the right phrase
    public boolean processGuess() {
        boolean isMatched = false;
        for (int i = 0; i < this.phrase.length(); i++) {
            if (Character.toLowerCase(this.phrase.charAt(i)) == Character.toLowerCase(this.guess)) {
                this.hiddenPhrase.setCharAt(i, this.phrase.charAt(i));
                isMatched = true;
            }
        }
        return isMatched;
    }

    public abstract char getGuess(String previousGuesses);
}
