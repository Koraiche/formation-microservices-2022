package ma.nxp.s2m.controller;


import ma.nxp.s2m.domain.Client;
import ma.nxp.s2m.feign.SalesClient;
import ma.nxp.s2m.services.IClientService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private IClientService clientService;

    private SalesClient salesClient;

    @Autowired
    public ClientController(IClientService clientService, SalesClient salesClient) {
        this.clientService = clientService;
        this.salesClient = salesClient;
    }


    @GetMapping @PreAuthorize("hasRole('user')")
    public ResponseEntity<List<Client>> getAll(){
        return new ResponseEntity<>(this.clientService.getAll(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Client getOne(@PathVariable Long id){
        return this.clientService.getOne(id);
    }
    @PostMapping
    public ResponseEntity<Client> add(@RequestBody Client client){
        return new ResponseEntity<>(this.clientService.add(client), HttpStatus.CREATED);
    }
    @PutMapping
    public Client update(@RequestBody Client client){
        return this.clientService.update(client);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.clientService.delete(id);
    }

}
