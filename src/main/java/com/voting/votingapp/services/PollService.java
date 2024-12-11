package com.voting.votingapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.voting.votingapp.model.OptionVote;
import com.voting.votingapp.model.Poll;
import com.voting.votingapp.repositories.PollRepository;

@Service
public class PollService {

    private final PollRepository pollRepository;
    
    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Poll createPoll(Poll poll){
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls(){
        return pollRepository.findAll();
    }

    public Optional<Poll> getPollById(Long id){
        return pollRepository.findById(id);
    }

    public void vote(Long pollId, int optionIndex) {
        //get poll from db
        Poll poll = pollRepository.findById(pollId)
                    .orElseThrow(() -> new RuntimeException("Poll not found"));
        //get all options
        List<OptionVote> options = poll.getOptions();
        //validate the index
        if(optionIndex < 0 || optionIndex>=options.size()){
            throw new IllegalArgumentException("Invalid option index");
        }
        //get selected option
        OptionVote selectedOption = options.get(optionIndex);
        //increment count of selected
        selectedOption.setVoteCount(selectedOption.getVoteCount()+1);

        pollRepository.save(poll);
    }
}
