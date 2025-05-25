package com.maximedyma.tennis.service;

import com.maximedyma.tennis.Player;
import com.maximedyma.tennis.PlayerList;
import com.maximedyma.tennis.PlayerToSave;
import com.maximedyma.tennis.Rank;
import com.maximedyma.tennis.data.PlayerEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankingCalculator {

    private final List<PlayerEntity> currentPlayersRanking;

    public RankingCalculator(List<PlayerEntity> currentPlayersRanking) {
        this.currentPlayersRanking = currentPlayersRanking;
    }

    public List<PlayerEntity> getNewPlayersRanking() {


        currentPlayersRanking.sort((player1, player2) -> Integer.compare(
                player2.getPoints(),
                player1.getPoints()
        ));

        List<PlayerEntity> updatedPlayers = new ArrayList<>();

        for (int i = 0; i < currentPlayersRanking.size(); i++) {
            PlayerEntity updatedPlayer = currentPlayersRanking.get(i);
            updatedPlayer.setRank(i + 1);
            updatedPlayers.add(updatedPlayer);
        }
        return updatedPlayers;
    }
}
