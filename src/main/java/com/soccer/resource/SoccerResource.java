package com.soccer.resource;

import com.soccer.model.*;
import com.soccer.repository.ClubRepository;
import com.soccer.repository.CountryRepository;
import com.soccer.repository.PlayerRepository;
import com.soccer.service.api.SoccerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/soccer")
public class SoccerResource {

    @Autowired
    private SoccerService soccerServiceImpl;
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ClubRepository clubRepository;


    @Autowired
    private CountryRepository countryRepository;

    @GetMapping(path="/~/managers")
    public @ResponseBody List<Manager> findAllUsers() {
        return soccerServiceImpl.getAllManagers();
    }

    @PostMapping(path="/create-manager")
    @ResponseBody
    public ManagerResponse createManager(@RequestBody ManagerRequest managerRequest) {
        return soccerServiceImpl.createManager(managerRequest);
    }

    @GetMapping(path="/get-manager/{managerId}")
    @ResponseBody
    public Manager findManagerById(@PathVariable String managerId) {
        return soccerServiceImpl.getManagerById(managerId);
    }
    @GetMapping(path = "/~/players")
    public @ResponseBody List<Player> findAllPlayers() {
        return soccerServiceImpl.getAllPlayers();
    }
    @GetMapping(path = "/get-player/{playerId}")
    public @ResponseBody Player findPlayerById(@PathVariable String playerId) {
        return soccerServiceImpl.getPlayerById(playerId);
    }

    @PostMapping(path = "/create-player")
    @ResponseBody
    public PlayerResponse createPlayer (@RequestBody PlayerRequest playerRequest) {
        return soccerServiceImpl.createPlayer(playerRequest);
    }

    @PatchMapping(path = "/edit-player/{playerId}")
    @ResponseBody
    public ResponseEntity<PlayerResponse> editPlayer(@RequestBody PlayerRequest playerRequest, @PathVariable String playerId) {
        PlayerResponse playerResponse = new PlayerResponse();
        try {
             playerResponse = soccerServiceImpl.editPlayer(playerRequest, playerId);
        }
        catch (Exception ex) {

        }
        return  new ResponseEntity<>(playerResponse, HttpStatus.NO_CONTENT);
    }
    @PatchMapping(path = "/edit-manager/{managerId}")
    @ResponseBody
    public ResponseEntity<ManagerResponse> editManager(@RequestBody ManagerRequest managerRequest, @PathVariable String managerId) {
        ManagerResponse managerResponse = new ManagerResponse();
        try {
            managerResponse = soccerServiceImpl.editManager(managerRequest, managerId);
        }
        catch (Exception ex) {

        }
        return  new ResponseEntity<>(managerResponse, HttpStatus.NO_CONTENT);
    }
    @GetMapping(path = "/~/clubs")
    @ResponseBody
    public List<Club> findAllClubs()
    {
        return soccerServiceImpl.getAllClubs();
    }

    @GetMapping(path = "/club/{clubId}")
    @ResponseBody
    public Club getClubById(@PathVariable String clubId) {
        Club club = new Club();
        try {
            club = soccerServiceImpl.getClubById(clubId);
        } catch (Exception e) {

        }
        return  club;
    }

    @PostMapping(path = "/create-club")
    @ResponseBody
    public ClubResponse createClub(@RequestBody ClubRequest clubRequest) {
        return soccerServiceImpl.createClub(clubRequest);
    }
}
