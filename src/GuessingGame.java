public abstract class GuessingGame {
    /*
      AllGamesRecord playAll()— a method that plays a set of games and records and returns an AllGamesRecord object for the set.
      Class Game should also define these abstract methods:
      GameRecord play()-- plays a game and returns a GameRecord
      boolean playNext() -- asks if the next game should be played (this will be called even to check if the first game should be played).
      The function should return a boolean.
     */
    AllGamesRecord playAll(){
        AllGamesRecord aGR = new AllGamesRecord();
        while(playNext()){
            aGR.add(play());
        }
        return aGR;
    }
    abstract GameRecord play();
    abstract boolean playNext();
}
