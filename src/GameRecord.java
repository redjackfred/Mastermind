public class GameRecord implements Comparable<GameRecord>{
    private int score;
    private String playerId;

    @Override
    public int compareTo(GameRecord o) {
        if(this.score<o.score){
            return 1;
        }else if(this.score>o.score){
            return -1;
        }else {
            return 0;
        }
    }

    public int getScore(){
        return this.score;
    }
    public String getPlayerId() {
        return playerId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "GameRecord{" +
                "score=" + score +
                ", playerId='" + playerId + '\'' +
                '}';
    }
}
