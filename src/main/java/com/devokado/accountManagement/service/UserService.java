package com.devokado.accountManagement.service;

import com.devokado.accountManagement.model.User;
import com.devokado.accountManagement.model.request.LoginRequest;
import com.devokado.accountManagement.model.request.RegisterRequest;
import com.devokado.accountManagement.repository.UserRepository;
import com.kavenegar.sdk.KavenegarApi;
import com.kavenegar.sdk.models.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Value("${otp.code.size}")
    private int otpCodeSize;

    @Value("${otp.kavenegar.apikey}")
    private String apiKey;

    @Value("${otp.kavenegar.template}")
    private String template;

    @Autowired
    UserRepository userRepository;

    public User create(User model) {
        model.setPassword(new BCryptPasswordEncoder().encode(model.getPassword()));
        model.setUuid(java.util.UUID.randomUUID());
        return userRepository.save(model);
    }

//    public String login(LoginRequest loginRequest) {
//        return "";
//    }
//
//    public String refreshToken(String refreshToken) {
//        return "";
//    }
//
//    public void logout(String userId) {
//
//    }
//
//    public void resetPassword(String userId, String newPassword) {
//    }
//
//    public String createSMSCode() {
//        if (otpCodeSize < 1) {
//            throw new RuntimeException("Number of digits must be bigger than 0");
//        }
//        double maxValue = Math.pow(10.0, otpCodeSize); // 10 ^ nrOfDigits;
//        Random r = new Random();
//        long code = (long) (r.nextFloat() * maxValue);
//        return Long.toString(code);
//    }
//
//    public SendResult sendSMS(String code, String mobile) {
//        KavenegarApi api = new KavenegarApi(apiKey);
//        return api.verifyLookup(mobile, code, template);
//    }
//
//    public boolean isUserExist(String mobile) {
//        return true;
//    }
//
//    public void getUserId(String username) {
//    }
//
//    public void updateUserInKeycloak(String userId, RegisterRequest model) {
//    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public Optional<User> findUserByMobile(String mobile) {
        return userRepository.findUserByMobile(mobile);
    }

}
