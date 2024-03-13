//package com.provedcode.config;
//
//import com.provedcode.user.mapper.UserInfoMapper;
//import com.provedcode.user.repo.UserInfoRepository;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@AllArgsConstructor
//public class InitConfig implements CommandLineRunner {
//    UserInfoRepository userInfoRepository;
//    PasswordEncoder passwordEncoder;
//    UserInfoMapper userInfoMapper;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // TODO: Method to change passwords for already created users from data1.sql
//        userInfoRepository.saveAll(
//                userInfoRepository.findAll().stream()
//                                  .map(i -> {
//                                      i.setPassword(passwordEncoder.encode(i.getPassword()));
//                                      return i;
//                                  }).toList());
//    }
//}