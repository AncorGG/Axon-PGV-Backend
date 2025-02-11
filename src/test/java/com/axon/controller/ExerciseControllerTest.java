package com.axon.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.axon.model.Exercise;
import com.axon.repository.ExerciseRepository;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ExerciseControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
    	exerciseRepository.deleteAll();
    	exerciseRepository.save(new Exercise(1L, "Facil", 1, 5F, 30));
    	exerciseRepository.save(new Exercise(2L, "Normal", 2, 10F, 20));
    }
    
    @Test
    void getAllExercises_ShouldReturnAList() throws Exception {
        mockMvc.perform(get("/api/exercises")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Facil"))
                .andExpect(jsonPath("$[1].name").value("Normal"));
    }
    
    @Test
    void getExerciseById_ShouldReturnSingleExercise() throws Exception {
        Long exerciseId = 2L;
        
        mockMvc.perform(get("/api/exercises/" + exerciseId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(exerciseId))
                .andExpect(jsonPath("$.name").value("Normal"));
    }
    
}
