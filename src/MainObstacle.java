import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainObstacle {
    public static void main(String[] args){
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String[] colors = {"blue", "red", "orange"};

        System.out.println("Choose the dumb robot's color!");
        for (int i = 0; i<colors.length; i++){
           System.out.println("Color " + (i+1) + ": " + colors[i]); 
        }

        int chosenColor1 = 0;
        while(true){
            if (scanner.hasNextInt()){
                chosenColor1 = scanner.nextInt();

                if (chosenColor1>=1 && chosenColor1 <= colors.length){
                    chosenColor1 -= 1;
                    System.out.println("color " + (chosenColor1+1) + ": " + colors[chosenColor1]);
                    break;
                } else { 
                    System.out.println("Please, choose a number between 1 and 3.");
                }
            } else { 
                System.out.println("Invalid input. Please, choose a number between 1 and 3.");
                scanner.next();
            }
        }

        String colorOne = colors[chosenColor1];
        
        System.out.println("Now, choose the color of the smart robot");

        int chosenColor2 = 0;
        while(true){
            if (scanner.hasNextInt()){
                chosenColor2 = scanner.nextInt();

                if (chosenColor2>=1 && chosenColor2 <= colors.length){
                    chosenColor2 -= 1;
                    System.out.println("color " + (chosenColor2+1) + ": " + colors[chosenColor2]);
                    break;
                } else { 
                    System.out.println("Please, choose a number between 1 and 3.");
                }
            } else { 
                System.out.println("Invalid input. Please, choose a number between 1 and 3.");
                scanner.next();
            }
        }

        String colorTwo = colors[chosenColor2];

        Robot dumbRobot = new Robot(0, 0, colorOne, false, false);
        SmartRobot smartRobot = new SmartRobot(0, 0, colorTwo, false, false);
    
        int foodX = 0;
        int foodY = 0;

        boolean done = false;

        while (!done){
            System.out.println("Choose the food's coordinates. Choose x:");
            if (scanner.hasNextInt()){
                foodX = scanner.nextInt();
                if (foodX>=0 && foodX <= 4){
                    System.out.println("Now, choose y:");
                    if (scanner.hasNextInt()){
                        foodY = scanner.nextInt();
                        if (foodY>= 0 && foodY <= 4){
                            done = true;
                        } else {
                            System.out.println("Choose a number between 0 and 3 for y!");
                        }
                    } else {
                        System.out.println("Invalid input. Choose a number between 0 and 3 for y!");
                        scanner.next();
                    }
                } else {
                    System.out.println("Choose a number between 0 and 3 for x!");
                }
            } else {
                System.out.println("Invalid input. Choose a number between 0 and 3 for x!");
                scanner.next();
            }
        }
        
        Food food = new Food(foodX, foodY);

        boolean choseBomb = false;
        int bombX = 0;
        int bombY = 0;

        while (!choseBomb) {
            System.out.println("Choose the bomb's coordinates. Choose x:");
            if (scanner.hasNextInt()) {
                bombX = scanner.nextInt();
                if (bombX >= 0 && bombX <= 3) {
                    System.out.println("Now, choose y:");
                    if (scanner.hasNextInt()) {
                        bombY = scanner.nextInt();
                        if (bombY >= 0 && bombY <= 3) {
                            if (bombX != food.getX() || bombY != food.getY()) {
                                choseBomb = true;
                            } else {
                                System.out.println("That position is already taken! Choose another.");
                            }
                        } else {
                            System.out.println("Choose a number between 0 and 3 for y!");
                        }
                    } else {
                        System.out.println("Invalid input. Choose a number between 0 and 3 for y!");
                        scanner.next();
                    }
                } else {
                    System.out.println("Choose a number between 0 and 3 for x!");
                }
            } else {
                System.out.println("Invalid input. Choose a number between 0 and 3 for x!");
                scanner.next();
            }
        }
        
        Bomb bomb = new Bomb(1, bombX, bombY);
        List<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(bomb);

        int rocks = 3;
        int placedRocks = 0;

        Grid grid = new Grid(obstacles, food);

        while (rocks > placedRocks){
            System.out.println("Choose the rock coordinates. Choose x: ");
            int rockX; int rockY;

            if (scanner.hasNextInt()) {
                rockX = scanner.nextInt();
                if (rockX >= 0 && rockX <= 3) {
                    System.out.println("Now, choose y:");
                    if (scanner.hasNextInt()) {
                        rockY = scanner.nextInt();
                        if (rockY >= 0 && rockY <= 3) {
                            if (!grid.isOccupied(rockX, rockY)) {
                                Rock newRock = new Rock(2, rockX, rockY);
                                obstacles.add(newRock);
                                placedRocks++;
                            } else {
                                System.out.println("That position is already taken! Choose another.");
                            }
                        } else {
                            System.out.println("Choose a number between 0 and 3 for y!");
                        }
                    } else {
                        System.out.println("Invalid input. Choose a number between 0 and 3 for y!");
                        scanner.next();
                    }
                } else {
                    System.out.println("Choose a number between 0 and 3 for x!");
                }
            } else {
                System.out.println("Invalid input. Choose a number between 0 and 3 for x!");
                scanner.next();
            }            
        }

        System.out.println(dumbRobot.getColor() + " Robot at " + "(" + dumbRobot.getX() + ", " + dumbRobot.getY() + ")");
        System.out.println(smartRobot.getColor() + " Robot at " + "(" + smartRobot.getX() + ", " + smartRobot.getY() + ")");
        System.out.println("Food at (" + food.getX() + ", " + food.getY() + ")");
        System.out.println("Obstacles at: ");
        for (Obstacle obs : obstacles){
            if ( obs instanceof Bomb){
                System.out.println("Bomb at (" + obs.getX() + ", " + obs.getY() + ")");
            } else if (obs instanceof Rock){
                System.out.println("Rock at (" + obs.getX() + ", " + obs.getY() + ")");
            }
        }
        scanner.nextLine();

        int validMoves = 0;
        int validMoves2 = 0;
        int invalidMoves = 0;
        int invalidMoves2 = 0;

        while (!dumbRobot.foundFood(food) && !dumbRobot.wasExploded(bomb)){
            int oldX = dumbRobot.getX();
            int oldY = dumbRobot.getY();
            try {
                int randomMove = random.nextInt(1, 5);
                System.out.println("Dumb robot: ");
                dumbRobot.move(randomMove);
                validMoves++;
                SleepUtil.sleepMs(2500);

            } catch (InvalidMovementException e) {
                invalidMoves++;
                System.out.println(dumbRobot.getColor() + " Dumb robot " + e.getMessage());
                return;
            }

            for (Obstacle obs : obstacles){
                if(dumbRobot.getX() == obs.getX() && dumbRobot.getY() == obs.getY()){
                    obs.bump(dumbRobot);
                    if (dumbRobot.wasExploded(bomb)){
                        System.out.println("Dumb robot exploded!");
                        return;
                    } else {
                        try{
                            dumbRobot.setX(oldX);
                            dumbRobot.setY(oldY);
                        } catch (InvalidMovementException e){
                            System.out.println("Going back.");
                        }
                        
                    }
                }
            }

            if (dumbRobot.foundFood(food)){
                if (dumbRobot.foundFood(food)){
                    System.out.println(dumbRobot.getColor() + " Dumb robot found food!");
                } else if (dumbRobot.wasExploded(bomb)) {
                    System.out.println(dumbRobot.getColor() + " Dumb robot exploded!");
                }
                break;
            }
        }

        while(!smartRobot.foundFood(food) && !smartRobot.wasExploded(bomb)){
            try {
                System.out.println("Smart robot: ");
                smartRobot.move(0); // ignora o inteiro, serve só pra inicializar
                validMoves2++;
                SleepUtil.sleepMs(2600);
                if (smartRobot.getFailed()){
                    invalidMoves2++;
                }
            } catch (InvalidMovementException e) {
                System.out.println(smartRobot.getColor() + " Smart robot " + e.getMessage());
            }

            if (smartRobot.foundFood(food)|| smartRobot.wasExploded(bomb)){
                if (smartRobot.foundFood(food)){
                    System.out.println(smartRobot.getColor() + " Smart robot found food!");
                } else if (smartRobot.wasExploded(bomb)){
                    System.out.println(smartRobot.getColor() + " Smart robot exploded!");
                }
                
                break;
            }
        }

        System.out.println("(Dumb robot) Valid moves: " + validMoves + ", invalid moves: " + invalidMoves);
        System.out.println("(Smart robot) Valid moves: " + validMoves2 + ", invalid moves: " + invalidMoves2);

        scanner.close();

    }
}