import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String[] colors = {"blue", "red", "orange"};

        System.out.println("Choose the robot's color!");
        for (int i = 0; i<colors.length; i++){
           System.out.println("Color " + (i+1) + ": " + colors[i]); 
        }

        int chosenColor = 0;
        while(true){
            if (scanner.hasNextInt()){
                chosenColor = scanner.nextInt();

                if (chosenColor>=1 && chosenColor <= colors.length){
                    chosenColor -= 1;
                    System.out.println("color " + (chosenColor+1) + ": " + colors[chosenColor]);
                    break;
                } else { 
                    System.out.println("Please, choose a number between 1 and 3.");
                }
            } else { 
                System.out.println("Invalid input. Please, choose a number between 1 and 3.");
                scanner.next();
            }
        }

        String color = colors[chosenColor];
        Robot robot = new Robot(0, 0, color, false, false);
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

        SleepUtil.sleepMs(1200);
        System.out.println(robot.getColor() + " Robot at" + "(" + robot.getX() + ", " + robot.getY() + ")");
        System.out.println("Food at (" + food.getX() + ", " + food.getY() + ")");
        scanner.nextLine();
        SleepUtil.sleepMs(1200);

        while (!robot.foundFood(food)){
            System.out.println("Choose the next move! Remember: you must write up, down, right or left.");
            String movement = scanner.nextLine();

            try{
                robot.move(movement);
            } catch (InvalidMovementException e){
                System.out.println(e.getMessage());
            }

            if (robot.foundFood(food)){
                System.err.println("The " + robot.getColor() + " Robot" + "(" + robot.getX() + ", " + robot.getY() + ")" 
                + " found food at (" + food.getX() + ", " + food.getY() + ")!");
            }
        }
        scanner.close();
    }
}