import java.util.ArrayList;

public class StartupBust {

//    declare and initialize the variables for the game
    private GameHelper helper = new GameHelper();
    private ArrayList<Startup> startups = new ArrayList<Startup>();
    private int numOfGuesses = 0;

//    making the three ships and putting them in an ArrayList
    private void setUpGame() {
        Startup one = new Startup();
        one.setName("The Osiel");
        Startup two = new Startup();
        two.setName("The Justin");
        Startup three = new Startup();
        three.setName("The Ivan");
        startups.add(one);
        startups.add(two);
        startups.add(three);

//        printing instructions for the user
//        ask the helper class for a startup location you just got from the helper

        System.out.println("Your goal is to sink three ships manned by your instructors!");
        System.out.println(" The Osiel,  The Justin, and The Ivan");
        System.out.println("Try to sink them all in the fewest number of guesses");

// repeat with each ship in the list

        for (Startup startup : startups) { // repeat with all startups in the list
            ArrayList<String> newLocation = helper.placeStartup(3); // ask the startups to check the user guess, looking for a hit
            startup.setLocationCells(newLocation);
        }
    }
// as long as the startup list is not empty
//    get user input and call the checkUserGuess method
//    call the finishGame method
    private void startPlaying() {
        while (!startups.isEmpty()) {
            String userGuess = helper.getUserInput("Enter a guess");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++; // increment the number of guesses the user has made
        String result = "miss"; // assume it's a miss unless told otherwise

        for (int i = 0; i < startups.size(); i++) {
            Startup startupToTest = startups.get(i);
            result = startupToTest.checkYourself(userGuess);

            if (result.equals("hit")) {
                break;
            }
            if (result.equals("kill")) {
                startups.remove(startupToTest);
                break;
            }
        }

        System.out.println(result); // print result to user
    }


    // print a message telling the user the result
    private void finishGame() {
        System.out.println("All ships are dead! That's kinda embarrassing...");
        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses.");
            System.out.println("You got out before your options sank.");
        } else {
            System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
            System.out.println("Fish are dancing with your options");
        }
    }

    public static void main(String[] args) {
        StartupBust game = new StartupBust();
        game.setUpGame();
        game.startPlaying();
    }
}
