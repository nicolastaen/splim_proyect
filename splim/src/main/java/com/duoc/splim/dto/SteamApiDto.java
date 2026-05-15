package com.duoc.splim.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * DTO que mappea la respuesta de la steamWebApi
 * Endpoint de referencia: GET https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=<ApiKey>&steamids=<SteamID>
 * 
 * Retorno de la consulta:
 * 
 * {
  "response": {
    "players": [
      {
        "steamid": "76561197960435530",
        "communityvisibilitystate": 3,
        "profilestate": 1,
        "personaname": "Robin",
        "profileurl": "https://steamcommunity.com/id/robinwalker/",
        "avatar": "https://avatars.steamstatic.com/81b5478529dce13bf24b55ac42c1af7058aaf7a9.jpg",
        "avatarmedium": "https://avatars.steamstatic.com/81b5478529dce13bf24b55ac42c1af7058aaf7a9_medium.jpg",
        "avatarfull": "https://avatars.steamstatic.com/81b5478529dce13bf24b55ac42c1af7058aaf7a9_full.jpg",
        "avatarhash": "81b5478529dce13bf24b55ac42c1af7058aaf7a9",
        "personastate": 0,
        "realname": "Robin Walker",
        "primaryclanid": "103582791429521412",
        "timecreated": 1063407589,
        "personastateflags": 0,
        "loccountrycode": "US",
        "locstatecode": "WA",
        "loccityid": 3961
      }
    ]
  }
}
**/

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SteamApiDto {

    @JsonProperty("steam_profile")
    private SteamProfile steamProfile;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
     public static class SteamProfile {
        private String steamid;
        private String profileurl;
        private String avatarfull;
     }
}
