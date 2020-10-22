package com.wot.wotbackend.endpoints;


import com.wot.wotbackend.Security.jwt.JwtUtils;
import com.wot.wotbackend.Security.services.PlayerDetailsImpl;
import com.wot.wotbackend.characterModel.characterSkill.CharacterSkill;
import com.wot.wotbackend.documents.ERole;
import com.wot.wotbackend.documents.Player;

import com.wot.wotbackend.documents.Role;
import com.wot.wotbackend.helperClasses.payloads.securityPayloads.JwtResponse;
import com.wot.wotbackend.helperClasses.payloads.securityPayloads.LoginRequest;
import com.wot.wotbackend.helperClasses.payloads.securityPayloads.MessageResponse;
import com.wot.wotbackend.helperClasses.payloads.securityPayloads.SignupRequest;
import com.wot.wotbackend.itemModel.ConsumableModel.Potion;
import com.wot.wotbackend.itemModel.Item;
import com.wot.wotbackend.questModel.Quest;
import com.wot.wotbackend.repositories.PlayerRepository;
import com.wot.wotbackend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/rest/player")

public class PlayerEndpoint {


    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/all")
    public List<Player> getAll(){
        return playerRepository.findAll();
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("someone in");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        PlayerDetailsImpl playerDetails = (PlayerDetailsImpl) authentication.getPrincipal();
        List<String> roles = playerDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        playerRepository.findByUsername(loginRequest.getUsername()).ifPresent(player -> {player.setLatitude(loginRequest.getLatitude());player.setLongitude(loginRequest.getLongitude());
        playerRepository.save(player);
        });

        return ResponseEntity.ok(new JwtResponse(jwt,
                playerDetails.getId(),
                playerDetails.getUsername(),
                playerDetails.getEmail(),
                playerDetails.getLatitude(),
                playerDetails.getLongitude(),
                playerDetails.getPlayerCharacter(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (playerRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (playerRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Player player = new Player(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail()
                );

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        player.setRoles(roles);
        playerRepository.save(player);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @RequestMapping("/{pathVariable}")
    @ResponseBody
    public Player player(@PathVariable("pathVariable") String pathVariable) {
        System.out.println("Player asked!");
        if(playerRepository.findById(pathVariable).isPresent()){
            return playerRepository.findById(pathVariable).get();
        }else {
            return null;
        }

    }

    @GetMapping("/{pathVariable}/askQuest")
    @ResponseBody
    public Player askForQuest(@PathVariable("pathVariable") String pathVariable) {
        System.out.println("Player asked for quest!");
        if(playerRepository.findById(pathVariable).isPresent()){
            Quest quest= new Quest();
            int random = randomWithRange(0,1);
            switch (random){
                case 0:
                    quest.generateTravelQuest();
                    break;
                case 1:
                    quest.generateKillQuest();
            }
            Player player= playerRepository.findById(pathVariable).get();
            player.getPlayerCharacter().getQuestList().add(quest);
            playerRepository.save(player);
            return player;
        }else{
            return null;
        }
    }

    @GetMapping("/{pathVariable}/completeQuest/{questId}")
    @ResponseBody
    public Player completeQuest(@PathVariable("pathVariable") String pathVariable,@PathVariable("questId")String questId) {
        System.out.println("Player asked to complete quest! "+questId);
        if(playerRepository.findById(pathVariable).isPresent()){

            Player player= playerRepository.findById(pathVariable).get();
            player.getPlayerCharacter().completeQuestById(questId);
            player.getPlayerCharacter().checkForLevelUp();
            playerRepository.save(player);
            return player;
        }else{
            return null;
        }
    }

    @GetMapping("/{pathVariable}/abandonQuest/{questId}")
    @ResponseBody
    public Player abandonQuest(@PathVariable("pathVariable") String pathVariable,@PathVariable("questId")String questId) {
        System.out.println("Player asked to abandon quest! "+questId);
        if(playerRepository.findById(pathVariable).isPresent()){

            Player player= playerRepository.findById(pathVariable).get();
            player.getPlayerCharacter().abandonQuestById(questId);
            playerRepository.save(player);
            return player;
        }else{
            return null;
        }
    }

    @PostMapping("/{pathVariable}/updateLastLocation")
    @ResponseBody
    public Player updateLastPlayerCoords(@PathVariable("pathVariable") String pathVariable,@RequestParam String latitude,@RequestParam String longitude) {
        System.out.println(latitude+" "+longitude);
        if(playerRepository.findById(pathVariable).isPresent()){
            Player player= playerRepository.findById(pathVariable).get();
            player.setLatitude(Double.parseDouble(latitude));
            player.setLongitude(Double.parseDouble(longitude));
            player.getCareer().increaseDistanceTraveled(10);
            player.getPlayerCharacter().getQuestList().forEach(objective->{
                objective.checkObjectives(10);
                    }
                    );
            player.getPlayerCharacter().increaseCurrentHp(Math.round(player.getPlayerCharacter().getMaxHp()*(0.01)));
            player.getPlayerCharacter().increaseCurrentInnerPower(Math.round(player.getPlayerCharacter().getMaxInnerPower()*(0.01)));
           playerRepository.save(player);
            System.out.println("player location saved");
            return player;
        }
        else{
            return null;
        }

    }

    @PostMapping("/{pathVariable}/useItem")
    @ResponseBody
    public Player useItemFromInventory(@PathVariable("pathVariable") String pathVariable,@RequestBody Item item) {
        System.out.println("Player asked to use an item with name "+item.getItemName());
        playerRepository.findById(pathVariable).ifPresent(player -> {
            System.out.println("Player "+player.getUsername()+item.getItemName());

            player.getPlayerCharacter().useItemFromInventoryById(item.getId());
            player.getPlayerCharacter().recalculateStats();
            playerRepository.save(player);
        });
        return playerRepository.findById(pathVariable).get();
    }


    @PostMapping("/{pathVariable}/removeItem")
    @ResponseBody
    public Player removeItemFromInventory(@PathVariable("pathVariable") String pathVariable,@RequestBody Item item) {
        System.out.println("Player asked to use an item with name "+item.getItemName());
        playerRepository.findById(pathVariable).ifPresent(player -> {
            System.out.println("Player "+player.getUsername()+item.getItemName());
            player.getPlayerCharacter().removeItemFromInventoryById(item.getId());
            playerRepository.save(player);
        });
        return playerRepository.findById(pathVariable).get();
    }

    @PostMapping("/{pathVariable}/addItem")
    @ResponseBody
    public void  addItemToInventory(@PathVariable("pathVariable") String pathVariable,@RequestBody Item item) {
        //System.out.println("Player asked to use an item with name "+item.getItemName());
        playerRepository.findById(pathVariable).ifPresent(player -> {
            //System.out.println("Player "+player.getName()+item.getItemName());
            player.getPlayerCharacter().addItemToInventory(item);
            playerRepository.save(player);
        });

    }

    @PostMapping("/{pathVariable}/assignSkill")
    @ResponseBody
    public Player  assignSkillToCharacter(@PathVariable("pathVariable") String pathVariable,@RequestParam("slotNumber") int slotNumber, @RequestBody CharacterSkill characterSkill) {
        //System.out.println("Player asked to use an item with name "+item.getItemName());
        playerRepository.findById(pathVariable).ifPresent(player -> {
            //System.out.println("Player "+player.getName()+item.getItemName());
            switch (slotNumber){
                case 1:
                    player.getPlayerCharacter().setSkill1(characterSkill);
                    playerRepository.save(player);
                    break;
                case 2:
                    player.getPlayerCharacter().setSkill2(characterSkill);
                    playerRepository.save(player);
                    break;
                case 3:
                    player.getPlayerCharacter().setSkill3(characterSkill);
                    playerRepository.save(player);
                    break;
                case 4:
                    player.getPlayerCharacter().setSkill4(characterSkill);
                    playerRepository.save(player);
                    break;
            }

        });
        return playerRepository.findById(pathVariable).get();

    }

    private int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

}
