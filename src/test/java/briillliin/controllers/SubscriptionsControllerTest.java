package briillliin.controllers;

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
public class SubscriptionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllSubscriotions() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        .get("/activities")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.activities").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.activities[*].subscriptionId").isNotEmpty());
    }

    @Test
    public void getSubscriotionById() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .get("/subscriptions/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.subscriptionId").value(1));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void deleteTrainer() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.delete("/subscriptions/{id}", 1))
                .andExpect(status().isAccepted());
    }

}
