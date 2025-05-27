package com.maximedyma.tennis.web;

import com.maximedyma.tennis.data.TournamentList;
import com.maximedyma.tennis.service.TournamentNotFoundException;
import com.maximedyma.tennis.service.TournamentService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TournamentController.class)
public class TournamentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TournamentService tournamentService;

    @Test
    public void shouldListAllTournaments() throws Exception {
        // Given
        Mockito.when(tournamentService.getAllTournaments()).thenReturn(TournamentList.ALL);

        // When / Then
        mockMvc.perform(get("/tournaments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].name", CoreMatchers.is("Australian Open")))
                .andExpect(jsonPath("$[1].name", CoreMatchers.is("French Open")))
                .andExpect(jsonPath("$[2].name", CoreMatchers.is("Wimbledon")))
                .andExpect(jsonPath("$[3].name", CoreMatchers.is("US Open")));
    }

    @Test
    public void shouldRetrieveTournament() throws Exception {
        // Given
        UUID tournamentToRetrieve = UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12");
        Mockito.when(tournamentService.getByIdentifier(tournamentToRetrieve)).thenReturn(TournamentList.FRENCH_OPEN);

        // When / Then
        mockMvc.perform(get("/tournaments/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is("French Open")));
    }

    @Test
    public void shouldReturn404NotFound_WhenTournamentDoesNotExist() throws Exception {
        // Given
        UUID tournamentToRetrieve = UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12");
        Mockito.when(tournamentService.getByIdentifier(tournamentToRetrieve)).thenThrow(new TournamentNotFoundException(tournamentToRetrieve));

        // When / Then
        mockMvc.perform(get("/tournaments/a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12"))
                .andExpect(status().isNotFound());
    }
}
