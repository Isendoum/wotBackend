package com.wot.wotbackend.endpoints;


import com.wot.wotbackend.Security.jwt.JwtUtils;
import com.wot.wotbackend.Security.services.PlayerDetailsImpl;
import com.wot.wotbackend.characterModel.characterSkill.CharacterSkill;
import com.wot.wotbackend.documents.ERole;
import com.wot.wotbackend.documents.Player;

import com.wot.wotbackend.documents.Role;
import com.wot.wotbackend.helperClasses.JwtResponse;
import com.wot.wotbackend.helperClasses.LoginRequest;
import com.wot.wotbackend.helperClasses.MessageResponse;
import com.wot.wotbackend.helperClasses.SignupRequest;
import com.wot.wotbackend.itemModel.Item;
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

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        PlayerDetailsImpl userDetails = (PlayerDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getCharacter(),
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

    @PostMapping("/{pathVariable}/updateLastLocation")
    public void updateLastPlayerCoords(@PathVariable("pathVariable") String pathVariable,@RequestParam String latitude,@RequestParam String longitude) {
        System.out.println(latitude+" "+longitude);
        if(playerRepository.findById(pathVariable).isPresent()){
            Player player= playerRepository.findById(pathVariable).get();
           player.setLatitude(Double.parseDouble(latitude));
           player.setLongitude(Double.parseDouble(longitude));
           playerRepository.save(player);
            System.out.println("player location saved");
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



}
