package com.maximedyma.tennis.service;

import com.maximedyma.tennis.data.PlayerEntity;
import com.maximedyma.tennis.model.Player;
import com.maximedyma.tennis.model.PlayerDescription;
import com.maximedyma.tennis.model.Rank;
import com.maximedyma.tennis.model.TournamentDescription;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PlayerMapper {

    public Player playerEntityToPlayer(PlayerEntity playerEntity) {
        PlayerDescription description = new PlayerDescription(
                playerEntity.getIdentifier(),
                playerEntity.getFirstName(),
                playerEntity.getLastName(),
                playerEntity.getBirthDate(),
                new Rank(
                        playerEntity.getRank(),
                        playerEntity.getPoints())
        );
        Set<TournamentDescription> tournaments = playerEntity.getTournaments().stream()
                .map(
                        tournamentEntity ->
                            new TournamentDescription(
                                    tournamentEntity.getIdentifier(),
                                    tournamentEntity.getName(),
                                    tournamentEntity.getStartDate(),
                                    tournamentEntity.getEndDate(),
                                    tournamentEntity.getPrizeMoney(),
                                    tournamentEntity.getCapacity()
                            )
                )
                .collect(Collectors.toSet());

        return new Player(description, tournaments);
    }
}
