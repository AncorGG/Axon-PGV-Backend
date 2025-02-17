package com.axon.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.axon.model.Routine;
import com.axon.model.User;
import com.axon.repository.RoutineExerciseRepository;
import com.axon.repository.RoutineRepository;

@WebMvcTest(RoutineController.class)
@Import(TestSecurityConfig.class)
class RoutineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoutineRepository routineRepository;

    @MockBean
    private RoutineExerciseRepository routineExerciseRepository;
	
	@Test
	void GetAllRoutines() throws Exception {
		Routine routine1 = new Routine(1L, "Piensa rapido", "Rutina para pensar muy rapido", LocalDateTime.of(2025, 2, 11, 17, 0, 0, 0),null);
		Routine routine2 = new Routine(2L, "Piensa lento", "Rutina para pensar lentamente", LocalDateTime.now(),null);

        List<Routine> routines = Arrays.asList(routine1, routine2);
        
        System.out.println(routines);
        
        when(routineRepository.findAll()).thenReturn(routines);
        

        String response = mockMvc.perform(get("/api/routines")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andReturn()
                .getResponse()
                .getContentAsString();
        
        System.out.println("Respuesta de JSON de controlador: " + response);
        	
        verify(routineRepository, times(1)).findAll();
	}
	
	@Test
	void GetRoutineById() throws Exception {
	    Long id = 1L;
	    Routine routine1 = new Routine(id, "Piensa rapido", "Descripción de la rutina", LocalDateTime.now(),null);
	    Routine routine2 = new Routine(id, "Piensa rapido", "Descripción de la rutina", LocalDateTime.now(),null);

	    when(routineRepository.findById(id)).thenReturn(java.util.Optional.of(routine1));

	    mockMvc.perform(get("/api/routines/"+ id)
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.id_routine").value(id));
	}

	@Test
	void GetRoutinesByUserId() throws Exception {
	    int userId = 1;
	    User user1 = new User();
	    User user2 = new User();
	    user1.setId((long) userId);
	    user2.setId((long) 2);

	    Routine routine1 = new Routine(1L, "Rutina A", "Descripción A", LocalDateTime.now(), user1);
	    Routine routine2 = new Routine(2L, "Rutina B", "Descripción B", LocalDateTime.now(), user1);
	    Routine routine3 = new Routine(3L, "Rutina C", "Descripción C", LocalDateTime.now(), user2);

	    List<Routine> routines = Arrays.asList(routine1, routine2, routine3);

	    when(routineRepository.findByUserId(userId)).thenAnswer(invocation ->
	        routines.stream().filter(r -> r.getUser().getId().equals((long) userId)).toList()
	    );

	    mockMvc.perform(get("/api/routines/user/" + userId)
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()").value(2))
	            .andExpect(jsonPath("$[0].id_routine").value(1))
	            .andExpect(jsonPath("$[1].id_routine").value(2));
	}
	
	@Test
    void InsertRoutine() throws Exception {
        Routine routine = new Routine(1L, "Nueva Rutina", "Descripción", LocalDateTime.now(),null);
        when(routineRepository.save(any(Routine.class))).thenReturn(routine);

        mockMvc.perform(post("/api/routines")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"routine_name\":\"Nueva Rutina\",\"description\":\"Descripción\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id_routine").value(1));
    }
	
	@Test
    void UpdateRoutine() throws Exception {
        Long id = 1L;
        Routine existingRoutine = new Routine(id, "Antigua", "Descripción", LocalDateTime.of(2025, 2, 11, 17, 0, 0, 0),null);
        Routine updatedRoutine = new Routine(id, "Nueva", "Nueva Descripción", LocalDateTime.now(),null);

        when(routineRepository.findById(id)).thenReturn(java.util.Optional.of(existingRoutine));
        when(routineRepository.save(any(Routine.class))).thenReturn(updatedRoutine);

        mockMvc.perform(put("/api/routines/"+ id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"routine_name\":\"Nueva\",\"description\":\"Nueva Descripción\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routine_name").value(updatedRoutine.getRoutine_name()));
    }
	
	@Test
    void DeleteRoutine() throws Exception {
        Long id = 1L;
        Routine routine = new Routine(id, "Eliminar", "Descripción", LocalDateTime.now(),null);

        when(routineRepository.findById(id)).thenReturn(java.util.Optional.of(routine));
        doAnswer(invocation -> {
            when(routineRepository.findById(id)).thenReturn(java.util.Optional.empty());
            return null;
        }).when(routineRepository).deleteById(id);

        mockMvc.perform(delete("/api/routines/"+ id))
                .andExpect(status().isOk());
    }

}
