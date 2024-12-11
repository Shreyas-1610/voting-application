package com.voting.votingapp.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Poll {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String question;

    @ElementCollection //jpa annotation create separate table with id & options
    private List<OptionVote> options = new ArrayList<>();

    // @ElementCollection
    // private List<Long> votes = new ArrayList<>();
}
