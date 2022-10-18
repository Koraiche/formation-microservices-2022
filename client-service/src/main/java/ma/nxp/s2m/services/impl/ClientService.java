package ma.nxp.s2m.services.impl;

import ma.nxp.s2m.dao.ClientRepository;
import ma.nxp.s2m.domain.Client;
import ma.nxp.s2m.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Client getOne(Long id) {
        Optional<Client> client = this.clientRepository.findById(id);
        return client.isPresent()?client.get():null;
    }

    @Override
    public void delete(Long id) {
        this.clientRepository.deleteById(id);
    }

    @Override
    public Client update(Client client) {
        if(client.getId()!=null){
            return this.clientRepository.save(client);
        }
        return null;
    }

    @Override
    public Client add(Client client) {
        client.setId(null);
        return this.clientRepository.save(client);
    }
}
