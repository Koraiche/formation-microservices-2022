package ma.nxp.s2m.service;


import ma.nxp.s2m.controller.ClientController;
import ma.nxp.s2m.dao.ClientRepository;
import ma.nxp.s2m.domain.Client;
import ma.nxp.s2m.services.impl.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void testFindAllClients(){
        Client client = new Client(null,"Fahd","KORAICHE","fkoraiche@s2m.ma","sidi maarouf");
        List<Client> list = new ArrayList<>();
        list.add(client);
        when(clientRepository.findAll()).thenReturn(list);

        List<Client> response = this.clientService.getAll();
        assertEquals(1,response.size());
        verify(this.clientRepository, times(1)).findAll();
    }
    @Test
    public void testFindOneClient(){
        Client client = new Client(1l,"Fahd","KORAICHE","fkoraiche@s2m.ma","sidi maarouf");

        when(clientRepository.findById(1l)).thenReturn(Optional.of(client));

        Client response = this.clientService.getOne(1l);
        assertTrue(response.getId()==1l);
        verify(this.clientRepository, times(1)).findById(1l);
    }
}
