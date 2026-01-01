package org.example.tennisplayer.controller;

import org.example.tennisplayer.model.Player;
import org.example.tennisplayer.repository.PlayerRepository;
import org.example.tennisplayer.view.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    private MappingJacksonValue applyView(Object value, boolean isAdmin) {
        MappingJacksonValue wrapper = new MappingJacksonValue(value);
        wrapper.setSerializationView(isAdmin ? Views.Internal.class : Views.Public.class);
        return wrapper;
    }

    @GetMapping
    public MappingJacksonValue getAllPlayers(@RequestParam(defaultValue = "false") boolean isAdmin) {
        List<Player> players = playerRepository.findAll();
        return applyView(players, isAdmin);
    }

    @GetMapping("/{id}")
    public MappingJacksonValue getPlayerById(@PathVariable int id, @RequestParam(defaultValue = "false") boolean isAdmin) {
        Player player = playerRepository.findById(id);
        return applyView(player, isAdmin);
    }

    @PostMapping
    public String createPlayer(@RequestBody Player player) {
        playerRepository.save(player);
        return "Player created successfully!";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlayer(@PathVariable int id, @RequestBody Player player) {
        try {
            playerRepository.findById(id);
            playerRepository.update(id, player);
            return ResponseEntity.ok("Player updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Update failed: Player with ID " + id + " not found.");
        }
    }

    @DeleteMapping("/{id}")
    public String deletePlayer(@PathVariable int id) {
        playerRepository.deleteById(id);
        return "Player deleted successfully!";
    }
}
