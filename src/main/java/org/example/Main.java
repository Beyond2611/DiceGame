package org.example;
import org.example.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.example.repository.PlayerRepoImpl;

public class Main {
    public static void clearScreen(){
        for(int i = 1; i <= 60; ++i){
            System.out.println();
        }
    }
    public static void main(String[] args) {

        List<CPU> cpuList = new ArrayList<>();
        CPU cpu1 = new CPU("Yasuo Bot", "Your skills are formidable, i accept my lost!");
        cpuList.add(cpu1);
        CPU cpu2 = new CPU("Yone Bot", "Your skills are formidable, i accept my lost!");
        cpuList.add(cpu2);
        CPU cpu3 = new CPU("K'Shante Bot", "Your skills are formidable, i accept my lost!");
        cpuList.add(cpu3);
        CPU cpu4 = new CPU("VMD", "Your skills are formidable, i accept my lost!");
        cpuList.add(cpu4);

        List<Dice> dieList = new ArrayList<>();
        Dice die1 = new Dice(1);
        dieList.add(die1);
        Dice die2 = new Dice(2);
        dieList.add(die2);
        Dice die3 = new Dice(3);
        dieList.add(die3);
        Dice die4 = new Dice(4);
        dieList.add(die4);
        Scanner in = new Scanner(System.in);
        System.out.println("Number of players: ");
        int numOfPlayers = in.nextInt();
        in.nextLine();
        List<BasePlayer> curPlayers = new ArrayList<>();
        boolean exit = false;
            for (int i = 1; i <= numOfPlayers; ++i) {
                System.out.print("Name of Player " + Integer.toString(i) + ": ");
                String tempName = in.nextLine();
                Player tempPlayer = new Player(tempName);
                curPlayers.add(tempPlayer);
                System.out.println();
                System.out.println("=====================================================");
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            for (int i = 1; i <= 4 - numOfPlayers; ++i) {
                System.out.println("Adding CPU!");
                curPlayers.add(cpuList.get(i - 1));
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            System.out.println("CPU Added!");
            PlayerRepoImpl curInstance = PlayerRepoImpl.getInstance();
            PlayerRepoImpl.loadPlayer(curPlayers);
            int winner = 0;
            int remains = 4;
            while(!exit) {
                clearScreen();
                if(remains == 1 || winner != 0){
                    for(BasePlayer player : curInstance.getCurPlayers())
                        if(player.getWinner() == true){
                            System.out.println("\t\t\tCONGRATULATION");
                            System.out.println("\t\t" + player.getName() + " is the winner!");
                        }
                    for(BasePlayer player : curInstance.getCurPlayers())
                    {
                        if(player instanceof CPU && player.getWinner() == false)
                            ((CPU)player).annouceDefeat();
                    }
                    //PlayerRepoImpl.endGame();
                    System.out.println("Press enter to exit...");
                    in.nextLine();
                    clearScreen();
                    System.exit(0);
                }
                for (BasePlayer player : curInstance.getCurPlayers()) {
                    if(player.getLost() || winner == 1)
                        continue;
                    clearScreen();
                    System.out.println(curInstance.getCurrentPlayerStats().toString());
                    System.out.println(player.getName() + " 's turn!");
                    System.out.println("Choosing Dice.....");
                    int curDice = (int) (Math.random() * 4 + 1);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Your current Dice: " + Integer.toString(curDice));
                    int point;
                    if(player instanceof Player)
                    {
                        System.out.println("Press the enter key to roll your dice!");
                        in.nextLine();
                        point = dieList.get(curDice - 1).roll();
                        System.out.println("You rolled a " + Integer.toString(point));
                    }
                    else{
                        dieList.get(curDice - 1).roll();
                        point = dieList.get(curDice - 1).roll();
                        System.out.println("You rolled a " + Integer.toString(point));
                    }
                    int tempCurPoint = player.getCurPoint();
                    if(tempCurPoint + point == 21){
                        winner = 1;
                        player.setWinner(true);
                        System.out.println("Your total is 21! YOU WIN!");
                        System.out.println("Press the enter key to continue!");
                        in.nextLine();
                        continue;
                    }
                    if(tempCurPoint + point > 21){
                        player.setLost(true);
                        System.out.println("Points exceeded 21, you are eliminated!");
                        System.out.println("Press the enter key to continue!");
                        in.nextLine();
                        player.setCurPoint(0);
                        continue;
                    }
                    player.addPoints(point);
                    System.out.println("Press the enter key to continue!");
                    in.nextLine();
                }
            }
    }
}