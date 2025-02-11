package com.axon.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.axon.model.Exercise;
import com.axon.repository.ExerciseRepository;
import com.axon.repository.RoutineExerciseRepository;

@WebMvcTest(ExerciseController.class)
@Import(TestSecurityConfig.class)
class ExerciseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExerciseRepository exerciseRepository;

    @MockBean
    private RoutineExerciseRepository routineExerciseRepository;

    @Test
    void testGetExercises() throws Exception {
        Exercise exercise1 = new Exercise(1L, "Push-up", 3, 1.5f, 10);
        Exercise exercise2 = new Exercise(2L, "Squat", 2, 1.2f, 8);

        List<Exercise> exercises = Arrays.asList(exercise1, exercise2);
        
        System.out.println(exercises);
        
        when(exerciseRepository.findAll()).thenReturn(exercises);
        

        String response = mockMvc.perform(get("/api/exercises")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].exercise_name").value("Push-up"))
                .andExpect(jsonPath("$[0].difficulty").value(3))
                .andExpect(jsonPath("$[0].speed").value(1.5))
                .andExpect(jsonPath("$[0].experience").value(10))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].exercise_name").value("Squat"))
                .andExpect(jsonPath("$[1].difficulty").value(2))
                .andExpect(jsonPath("$[1].speed").value(1.2))
                .andExpect(jsonPath("$[1].experience").value(8))
                .andReturn()
                .getResponse()
                .getContentAsString();
        
        System.out.println("Respuesta de JSON de controlador: " + response);
        	
        verify(exerciseRepository, times(1)).findAll();
    }
}
