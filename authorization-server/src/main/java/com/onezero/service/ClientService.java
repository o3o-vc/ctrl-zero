package com.onezero.service;

import com.mybatisflex.core.query.QueryChain;
import com.onezero.domain.Client;
import com.onezero.domain.table.ClientTableDef;
import com.onezero.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service
@Transactional
@RequiredArgsConstructor
public class ClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    @Override
    public void save(RegisteredClient registeredClient) {
        clientRepository.insert(Client.from(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        var client = clientRepository.selectOneById(id);
        return Client.from(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = new QueryChain<>(clientRepository)
                .where(ClientTableDef.CLIENT.CLIENT_ID.eq(clientId))
                .one();
        return Client.from(client);
    }
}
