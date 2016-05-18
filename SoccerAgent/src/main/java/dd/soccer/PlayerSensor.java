package dd.soccer;

import commonmodel.ElementState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sergey on 25.09.2015.
 */
public class PlayerSensor {
    private Random r = new Random();
    private final int SENSE_FRAME_SIZE = 11;
    private final int POSSIBLE_PLAYER_NUMBERS = SENSE_FRAME_SIZE + 1;


    public List<ElementState> sense(){
        int currentSenseFrameSize = r.nextInt(SENSE_FRAME_SIZE);
        return generatePlayers(currentSenseFrameSize);
    }

    private List<ElementState> generatePlayers(int size){
        List<ElementState> players = new ArrayList<>();
        boolean[] numbers = new boolean[POSSIBLE_PLAYER_NUMBERS];
        for(int i = 0; i < size; i++){
//            players.add(generatePlayer(numbers));
        }
        return players;
    }

//    private Player generatePlayer(boolean[] numbers) {
//        int number = generatePlayerNumber(numbers);
//        if(r.nextBoolean()){
//            return new Player(number);
//        }else{
//            return new Player();
//        }
//    }

    private int generatePlayerNumber(boolean[] numbers){
        int number = r.nextInt(POSSIBLE_PLAYER_NUMBERS);
        while(numbers[number]){
            number = ++number%(POSSIBLE_PLAYER_NUMBERS);
        }
        numbers[number] = true;
        return number;
    }
}
