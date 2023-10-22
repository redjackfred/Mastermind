import java.util.Scanner;

public class MastermindUserGame extends Mastermind{
    @Override
    GameRecord play() {
        GameRecord record = new GameRecord();
        record.setPlayerId("User");
        record.setScore(playingProgress());

        return record;
    }

    @Override
    boolean playNext() {
        System.out.println("Play or not? (Y/N): ");
        Scanner scanner = new Scanner(System.in);
        char input = Character.toLowerCase(scanner.nextLine().charAt(0));
        while(input !='y' && input != 'n'){
            System.out.println("Please enter Y or N to continue: ");
            input = Character.toLowerCase(scanner.nextLine().charAt(0));
        }

        return switch (input){
            case 'y'-> true;
            case 'n'-> false;
            default -> false;
        };
    }

    public static void main(String[] args) {
        MastermindUserGame mastermindUserGame = new MastermindUserGame();
        AllGamesRecord record = mastermindUserGame.playAll();
        System.out.println(record);  // or call specific functions of record
    }
}
