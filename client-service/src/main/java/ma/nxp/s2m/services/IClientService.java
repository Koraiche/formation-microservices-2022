package ma.nxp.s2m.services;

import ma.nxp.s2m.domain.Client;

import java.util.List;

public interface IClientService {
    public List<Client> getAll();
    public Client getOne(Long id);
    public void delete(Long id);
    public Client update(Client client);
    public Client add(Client client);
}
