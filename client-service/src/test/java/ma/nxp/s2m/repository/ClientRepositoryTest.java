package ma.nxp.s2m.repository;


import ma.nxp.s2m.dao.ClientRepository;
import ma.nxp.s2m.domain.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class ClientRepositoryTest {
    @Autowired
    ClientRepository clientRepository;
    private Long randomId;

    @BeforeAll
    void createClient(){
        Client client = new Client(null,"Fahd","KORAICHE","fkoraiche@s2m.ma","sidi maarouf");
        randomId = clientRepository.save(client).getId();
    }
    @AfterAll
    void deleteClients(){
        clientRepository.deleteAll();
    }
    @Test
    void testFindAllClients(){
        Iterable<Client> clients = clientRepository.findAll();
        Assertions.assertThat(clients).extracting(Client::getFirstName).containsOnly("Fahd");
        clientRepository.deleteAll();
        Assertions.assertThat(clientRepository.findAll()).isEmpty();
    }
    @Test
    void testFindOneClient(){
        Client client = clientRepository.findById(randomId).orElse(null);

    }
}
