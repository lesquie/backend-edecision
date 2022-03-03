package com.ipi.backendedecision.config;

import com.ipi.backendedecision.models.*;
import com.ipi.backendedecision.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
public class DatabaseLoader {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseLoader.class);

    @Bean
    CommandLineRunner initDatabase(ProjectRepository projectRepository,
                                   TeamRepository teamRepository,
                                   UserRepository userRepository,
                                   ProposalRepository proposalRepository,
                                   VoteRepository voteRepository) {
        return args -> {
            User u1 = new User("user1", "user1");
            User u2 = new User("user2", "user2");

            Team t1 = new Team("team1", TeamType.DEV, List.of(u1,u2));
            Project p1 = new Project("project1", List.of(t1));

            Proposal prop1 = new Proposal("prop1", "prop1prop1", PublicationLevel.TEAM, List.of(u1));
            Proposal prop2 = new Proposal("prop2", "prop2prop2", PublicationLevel.PROJECT, List.of(u1, u2));
            Proposal prop3 = new Proposal("prop3", "prop3prop3", PublicationLevel.TEAM, List.of(u2));
            Proposal prop4 = new Proposal("prop4", "prop4prop4", PublicationLevel.COMMUNITY, List.of(u1, u2));

            Vote v1 = new Vote(u1, prop2, VoteType.YES);
            Vote v2 = new Vote(u1, prop3, VoteType.NO);
            Vote v3 = new Vote(u1, prop4, VoteType.UNDETERMINED);
            Vote v4 = new Vote(u2, prop1, VoteType.YES);
            Vote v5 = new Vote(u2, prop2, VoteType.NO);
            Vote v6 = new Vote(u2, prop4, VoteType.YES);

            userRepository.save(u1);
            userRepository.save(u2);

            teamRepository.save(t1);
            projectRepository.save(p1);
            proposalRepository.saveAll(List.of(prop1, prop2, prop3, prop4));

            voteRepository.saveAll(List.of(v1, v2, v3, v4, v5, v6));

            logger.debug("DB loaded");
        };
    }
}
