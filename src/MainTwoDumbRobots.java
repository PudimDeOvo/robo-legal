import java.util.Random;
import java.util.Scanner;

public class MainTwoDumbRobots {
    public static void main(String[] args){
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String[] colors = {"blue", "red", "orange"};

        System.out.println("Choose the first robot's color!");
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
        
        System.out.println("Now, choose the color of the second robot");

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

        Robot robotOne = new Robot(0, 0, colorOne, false, false);
        Robot robotTwo = new Robot(0, 0, colorTwo, false, false);

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
                            System.out.println("Choose a number between 1 and 4 for y!");
                        }
                    } else {
                        System.out.println("Invalid input. Choose a number between 1 and 4 for y!");
                        scanner.next();
                    }
                } else {
                    System.out.println("Choose a number between 1 and 4 for x!");
                }
            } else {
                System.out.println("Invalid input. Choose a number between 1 and 4 for x!");
                scanner.next();
            }
        }
        
        Food food = new Food(foodX, foodY);

        System.out.println(robotOne.getColor() + " Robot at " + "(" + robotOne.getX() + ", " + robotOne.getY() + ")");
        System.out.println(robotTwo.getColor() + " Robot at " + "(" + robotTwo.getX() + ", " + robotTwo.getY() + ")");
        System.out.println("Food at (" + food.getX() + ", " + food.getY() + ")");
        scanner.nextLine();

        int validMoves = 0;
        int validMoves2 = 0;
        int invalidMoves = 0;
        int invalidMoves2 = 0;

        while (!robotOne.foundFood(food) && !robotTwo.foundFood(food)){
            try {
                int randomMove = random.nextInt(1, 5);
                System.out.println("Robot one: ");
                robotOne.move(randomMove);
                validMoves++;
                SleepUtil.sleepMs(2500);
            } catch (InvalidMovementException e) {
                invalidMoves++;
                System.out.println(robotOne.getColor() + " Robot 1 " + e.getMessage());
            }

            if (robotOne.foundFood(food)){
                System.out.println(robotOne.getColor() + " Robot 1 found food!");
            }

            try {
                int randomMove = random.nextInt(1, 5);
                System.out.println("Robot two: ");
                validMoves2++;
                robotTwo.move(randomMove);
                SleepUtil.sleepMs(2600);
            } catch (InvalidMovementException e) {
                invalidMoves2++;
                System.out.println(robotTwo.getColor() + " Robot 2 " + e.getMessage());
            }

            if (robotTwo.foundFood(food)){
                System.out.println(robotTwo.getColor() + " Robot 2 found food!");
            }
            
        }

        System.out.println("(Robot one) Valid moves: " + validMoves + ", invalid moves: " + invalidMoves);
        System.out.println("(Robot two) Valid moves: " + validMoves2 + ", invalid moves: " + invalidMoves2);

        scanner.close();

    }
}
