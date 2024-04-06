package briillliin.controllers;

import briillliin.entity.Trainers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class TrainersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllTrainers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        .get("/trainers")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.trainers").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.trainers[*].trainerId").isNotEmpty());
    }

    @Test
    public void getTrainerById() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .get("/trainers/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.trainerId").value(18));
    }

    @Test
    public void createTrainer() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/trainers")
                .content(asJsonString(new Trainers("Бредихина Алина Андреевна", "1234567890", "1234567890", "blah-blah")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.trainerId").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateTrainer() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .put("/trainers/{id}", 20)
                .content(asJsonString(new Trainers("Бредихина Алина Андреевна", "1234567890", "1234567890", "blah-blah")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Бредихина Алина Андреевна"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.passport").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("blah-blah"));
    }

    @Test
    public void deleteTrainer() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.delete("/trainers/{id}", 20))
                .andExpect(status().isAccepted());
    }

}
