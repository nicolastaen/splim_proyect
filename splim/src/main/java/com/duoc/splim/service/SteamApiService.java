package com.duoc.splim.service;

import com.duoc.splim.dto.SteamApiDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SteamApiService {

    @Value("${steam.api.key}")
    private String steamApiKey;

    @Autowired
    @Qualifier("steamWebClient")
    private WebClient steamWebClient;

    /**
     * consulta las cuentas publicas creadas en steam segun el id de la cuenta utilizando SteamWebApi/ISteamUser
     * La API es publica para cualquier persona que tenga una cuenta de steam y que haya gastado mas de $5.00US
     * y hace uso de una key proporcionada en la pagina de Steam
     * 
     * @param steamid Se coloca el Id del jugador (ej: 76561198996847108 )
     * @return SteamApiDto con el perfil del jugador
     */

    public SteamApiDto obtenerDatosUsuario(String steamId){
        return steamWebClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/ISteamUser/GetPlayerSummaries/v0002/")
                    .queryParam("key", steamApiKey)
                    .queryParam("steamids", steamId)
                    .build())
                .retrieve()
                .bodyToMono(SteamApiDto.class)
                .block();
    }
}
