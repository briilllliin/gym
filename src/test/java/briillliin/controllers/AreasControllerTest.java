package briillliin.controllers;

import briillliin.entity.Areas;
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
public class AreasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllAreas() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        .get("/areas")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.areas").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.areas[*].areaId").isNotEmpty());
    }

    @Test
    public void getAreaById() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .get("/areas/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.areaId").value(20));
    }

    @Test
    public void createArea() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/areas")
                .content(asJsonString(new Areas("Гимнастический зал")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.areaId").exists());
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
                .put("/areas/{id}", 20)
                .content(asJsonString(new Areas("Гимнастический зал №2")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Гимнастический зал"));
    }

    @Test
    public void deleteTrainer() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.delete("/areas/{id}", 20))
                .andExpect(status().isAccepted());
    }

}
