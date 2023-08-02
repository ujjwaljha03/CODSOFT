import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Task1 {
    public static String compare(int Number,int Guess){
        if(Guess == Number){
            return(" Correct! ");
        }
        else if (Guess>Number) {
            return(" Too High! ");
        }
        else{
            return(" Too Low! ");
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int Rounds = 1;
        int Attempts = 5;
        int Rou_Score = 0;
        System.out.println("Total Rounds are Three and which have Five Attempts Each.");
        while(Rounds<3){
            int Number = ThreadLocalRandom.current().nextInt(1,101);
            System.out.println(" Round " + Rounds);
             for(int i=1;i<=Attempts;i++){
                System.out.println(" Attempt " + i);
                System.out.print("   Enter the Number to Guess : ");
                int Guess = sc.nextInt();
                String value = compare(Number,Guess);
                System.out.println("   Your Guess is" + value);
                if (value.equals(" Correct! ")){
                   int Att_Score = 1 ;
                    Rou_Score += Att_Score;
                    System.out.println(" Your Round " + Rounds + " score is " + Att_Score + " out of " + i + " Attempts");
                    break;
                }
            }
            System.out.print(" Do You Want to play another Round?(Yes/No): ");
            String ask = sc.next();
            if(ask.equals("No") || ask.equals("no") || ask.equals("n")){
                break;
            }
            Rounds++;
        }
        System.out.println("Your Total Score is " + Rou_Score + " out of " + Rounds + " Rounds played.");
    }
}
