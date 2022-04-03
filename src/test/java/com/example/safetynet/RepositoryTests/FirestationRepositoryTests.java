package com.example.safetynet.RepositoryTests;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.model.Firestation;
import com.example.safetynet.repository.FirestationsRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FirestationRepositoryTests {

    @Autowired
    FirestationsRepository firestationsRepository;

    @Test
    public  void saveNormalTest(){
        List<Firestation> fs = new ArrayList<>();

        firestationsRepository.save(fs);
    }
}
