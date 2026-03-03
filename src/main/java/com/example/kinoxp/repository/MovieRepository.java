package com.example.kinoxp.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    public boolean testGithubActionCI(int number){
        return number != 0;
    }

}
