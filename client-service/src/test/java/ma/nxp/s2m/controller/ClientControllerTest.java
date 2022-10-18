package ma.nxp.s2m.controller;


import com.google.gson.Gson;
import ma.nxp.s2m.configuration.WithMockOAuth2Conext;
import ma.nxp.s2m.domain.Client;
import ma.nxp.s2m.services.IClientService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.engine.TestExecutionResult;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
@ActiveProfiles("test")
@ComponentScan(basePackageClasses = { KeycloakSecurityComponents.class, KeycloakSpringBootConfigResolver.class })
public class ClientControllerTest {

    @MockBean
    private IClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    private final Gson gson = new Gson();

    @Test
    @WithMockOAuth2Conext(authorities = "user")
    void testFindAllClients() throws Exception {
        Client client = new Client(null,"Fahd","KORAICHE","fkoraiche@s2m.ma","sidi maarouf");
        List list = new ArrayList<>();
        list.add(client);
        Mockito.when(clientService.getAll()).thenReturn(list);

        mockMvc.perform(get("/api/v1/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("Fahd")));
    }

    @Test @WithMockOAuth2Conext
    void shouldFindOneClient() throws Exception {
        Client client = new Client(1l,"Fahd","KORAICHE","fkoraiche@s2m.ma","sidi maarouf");
        Mockito.when(clientService.getOne(1l)).thenReturn(client);

        mockMvc.perform(get("/api/v1/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", Matchers.is("Fahd")));
    }

    @Test @WithMockOAuth2Conext
    void shouldAddOneClient() throws Exception {
        Client request = new Client(1l,"Fahd","KORAICHE","fkoraiche@s2m.ma","sidi maarouf");
        Mockito.when(clientService.add(request)).thenReturn(request);

        mockMvc.perform(post("/api/v1/clients").contentType("application/json").content(gson.toJson(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("firstName", Matchers.is("Fahd")));
    }

    @Test @WithMockOAuth2Conext
    void shouldUpdateOneClient() throws Exception {
        Client request = new Client(1l,"Fahd","KORAICHE","fkoraiche@s2m.ma","sidi maarouf");
        Mockito.when(clientService.update(request)).thenReturn(request);

        mockMvc.perform(put("/api/v1/clients").contentType("application/json").content(gson.toJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", Matchers.is("Fahd")));
    }

    @Test @WithMockOAuth2Conext
    void shouldDeleteOneClient() throws Exception {
        Client request = new Client(1l,"Fahd","KORAICHE","fkoraiche@s2m.ma","sidi maarouf");
        doNothing().when(clientService).delete(1l);
        mockMvc.perform(delete("/api/v1/clients/1"))
                .andExpect(status().isOk());
    }
}
