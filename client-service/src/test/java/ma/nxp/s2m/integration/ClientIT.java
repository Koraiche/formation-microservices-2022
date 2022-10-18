package ma.nxp.s2m.integration;


import ma.nxp.s2m.controller.ClientController;
import ma.nxp.s2m.domain.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class ClientIT {
    @Autowired
    ClientController clientController;
    @Test
    void testCreateReadDelete(){
        Client client = new Client(null,"Fahd","KORAICHE","fkoraiche@s2m.ma","sidi maarouf");
        ResponseEntity<Client> response = clientController.add(client);

        ResponseEntity<List<Client>> responseList = clientController.getAll();
        Assertions.assertThat(responseList.getBody()).isNotEmpty();
         clientController.delete(response.getBody().getId());
         Assertions.assertThat(clientController.getAll().getBody()).isEmpty();
    }
}
