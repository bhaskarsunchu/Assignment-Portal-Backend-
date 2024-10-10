package com.scaler.backendinternassignment.services;

import com.scaler.backendinternassignment.dtos.LoginUserResponseDto;
import com.scaler.backendinternassignment.exceptions.InvalidTokenException;
import com.scaler.backendinternassignment.exceptions.PasswordNotMatchingException;
import com.scaler.backendinternassignment.exceptions.UserNotFoundException;
import com.scaler.backendinternassignment.models.Role;
import com.scaler.backendinternassignment.models.Token;
import com.scaler.backendinternassignment.models.User;
import com.scaler.backendinternassignment.repositories.TokenRepository;
import com.scaler.backendinternassignment.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static java.lang.String.valueOf;

// Service class for handling authentication-related operations
@Service
public class AuthenticationService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private TokenRepository tokenRepository;

    // Constructor for dependency injection
    public AuthenticationService(BCryptPasswordEncoder bCryptPasswordEncoder,
                                 UserRepository userRepository,
                                 TokenRepository tokenRepository){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public User signUp(String userName, String email, String password, String role){

        User userToBeSaved = new User();

        userToBeSaved.setUserName(userName);
        userToBeSaved.setEmail(email);
        userToBeSaved.setPassword(bCryptPasswordEncoder.encode(password));
        userToBeSaved.setRole(Role.valueOf(role.toUpperCase()));

        return userRepository.save(userToBeSaved);
    }

    public Token login(String email, String password) throws UserNotFoundException, PasswordNotMatchingException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserNotFoundException("User/Admin not found with email : " + email + ". Provid valid email");
        }
        if (!(bCryptPasswordEncoder.matches(password, user.getPassword()))) {
            throw new PasswordNotMatchingException("Incorrect Password, Provide valid password");
        }
        //Generate the token.
        Token token = createToken(user);
        return tokenRepository.save(token);
    }

    public User validateToken(String tokenValue) throws InvalidTokenException {
        Token token = tokenRepository.findByValueAndExpiryAtGreaterThan(
                tokenValue,
                new Date() // currentTime
        );
        if(token == null){
            throw new InvalidTokenException("Please provide valid credentials or Login again");
        }
        Optional<User> user = userRepository.findById(token.getUserId());
        return user.get();
    }

    private Token createToken(User user) {
        Token token = new Token();
        token.setUserId(user.getId());
        token.setValue(RandomStringUtils.randomAlphanumeric(50));
        //Expiry time of the token is let's say 5 days from now.
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysAfterCurrentTime = today.plusDays(5);
        Date expiryAt = Date.from(thirtyDaysAfterCurrentTime.atStartOfDay(ZoneId.systemDefault()).toInstant());
        token.setExpiryAt(expiryAt);
        return token;
    }
}
