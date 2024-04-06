package briillliin.controllers;

import briillliin.entity.Clients;
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
public class ClientsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllClients() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        .get("/clients")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.clients").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.clients[*].clientId").isNotEmpty());
    }

    @Test
    public void getClientById() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .get("/clients/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientId").value(1));
    }

    @Test
    public void createClient() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/clients")
                .content(asJsonString(new Clients("Бредихина Алина Андреевна", "1234567890", "blah-blah")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientId").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateClient() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .put("/clients/{id}", 20)
                .content(asJsonString(new Clients("Бредихина Алина Андреевна", "1234567890", "blah-blah")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Бредихина Алина Андреевна"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("blah-blah"));
    }

    @Test
    public void deleteClient() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.delete("/clients/{id}", 20))
                .andExpect(status().isAccepted());
    }

}
