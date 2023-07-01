package org.example.repository;
import org.example.models.*;
import org.example.repository.PlayerRepo;

import java.util.List;

public class PlayerRepoImpl implements PlayerRepo{
    private static List<BasePlayer> curPlayers;
    private static PlayerRepoImpl instance;

    public static PlayerRepoImpl getInstance(){
        if(instance == null)
            instance = new PlayerRepoImpl();
        return instance;
    }

    public static void loadPlayer(List<BasePlayer> playerList){
        curPlayers = playerList;
    }
    public List<BasePlayer> getCurPlayers(){
        return curPlayers;
    }
    @Override
    public StringBuffer getCurrentPlayerStats(){
        StringBuffer stats = new StringBuffer();
        stats.append("======================== Point Board ========================\n");
        for(BasePlayer player : curPlayers){
            stats.append(player.toString());
            stats.append(" " + (player.getLost() ? "Eliminated" : ""));
            stats.append("\n");
        }
        stats.append("=============================================================\n");
        return stats;
    }

    @Override
    public void endGame(){
        for(BasePlayer player : curPlayers){
            player.setCurPoint(0);
            player.setWinner(false);
        }
        instance = null;
    }
}
