import java.util.ArrayList;
import java.util.Collections;

public class AllGamesRecord {
    private ArrayList<GameRecord> listGameRecord;

    public AllGamesRecord(){
        this.listGameRecord = new ArrayList<>();
    }
    /*
    - add(GameRecord)-- adds a GameRecord to the AllGamesRecord
    - average()-- returns the average score for all games added to the record
    - average(playerId) -- returns the average score for all games of a particular player
    - highGameList(n)-- returns a sorted list of the top n scores including player and score. This method should use the Collections class to sort the game instances.
    - highGameList(playerId, n)-- returns a sorted list of the top n scores for the specified player.. This method should use the Collections class to sort the game instances.
     */
    public void add(GameRecord record){
        this.listGameRecord.add(record);
    }

    public double average(){
        double sum = 0;
        for(GameRecord record : this.listGameRecord){
            sum += record.getScore();
        }
        return sum/this.listGameRecord.size();
    }

    public double average(String playerId){
        double sum = 0;
        int count = 0;
        for(GameRecord record : this.listGameRecord){
            if(record.getPlayerId() == playerId) {
                sum += record.getScore();
                count++;
            }
        }
        return (count!=0) ? sum/count++ : 0;
    }

    public ArrayList<GameRecord> highGameList(int topN){
        ArrayList<GameRecord> list = new ArrayList<>();
        Collections.sort(this.listGameRecord);
        // Check if topN is not out of range
        if(this.listGameRecord.size()<topN){
            topN = this.listGameRecord.size();
        }
        for(int i=0;i<topN;i++){
            list.add(this.listGameRecord.get(i));
        }
        return list;
    }

    public ArrayList<GameRecord> highGameList(String playerId, int topN){
        ArrayList<GameRecord> list = new ArrayList<>();
        Collections.sort(this.listGameRecord);
        int count = 0;
        int i = 0;
        while(count<topN){
            if(this.listGameRecord.get(i).getPlayerId()==playerId) {
                list.add(this.listGameRecord.get(i));
                count++;
            }
            i++;
        }
        return list;
    }

    @Override
    public String toString() {
        return "Highest score: " + highGameList(2) + "\nAverage score: " + average();
    }
}
