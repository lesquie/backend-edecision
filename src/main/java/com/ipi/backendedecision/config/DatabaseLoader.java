package com.ipi.backendedecision.config;

import com.ipi.backendedecision.models.*;
import com.ipi.backendedecision.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
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
                                   VoteRepository voteRepository,
                                   CommentRepository commentRepository) {
        return args -> {
            User u1 = new User("user1", "user1");
            User u2 = new User("user2", "user2");

            Team t1 = new Team("team1", TeamType.DEV, List.of(u1,u2));
            Project p1 = new Project("project1", List.of(t1));

            Comment c1 = new Comment("comment 1", u1);
            Comment c2 = new Comment("comment 2", u1);
            Comment c3 = new Comment("comment 3", u1);
            Comment c4 = new Comment("comment 4", u2);
            Comment c5 = new Comment("comment 5", u2);
            Comment c6 = new Comment("comment 6", u2);

            Proposal prop1 = new Proposal("prop1", "prop1prop1", PublicationLevel.TEAM,
                    LocalDate.of(2022, 3, 4), List.of(u1), List.of(c1, c3));
            Proposal prop2 = new Proposal("prop2", "prop2prop2",
                    PublicationLevel.PROJECT, LocalDate.of(2022, 3, 6), List.of(u1, u2), List.of());
            Proposal prop3 = new Proposal("prop3", "prop3prop3", PublicationLevel.TEAM,
                    LocalDate.of(2022, 2, 28), List.of(u2), List.of(c4));
            Proposal prop4 = new Proposal("prop4", "prop4prop4", PublicationLevel.COMMUNITY,
                    LocalDate.of(2022, 3, 28), List.of(u1, u2), List.of(c5, c2, c6));

            Vote v1 = new Vote(u1, prop2, VoteType.YES);
            Vote v2 = new Vote(u1, prop3, VoteType.NO);
            Vote v3 = new Vote(u1, prop4, VoteType.UNDETERMINED);
            Vote v4 = new Vote(u2, prop1, VoteType.YES);
            Vote v5 = new Vote(u2, prop2, VoteType.NO);
            Vote v6 = new Vote(u2, prop4, VoteType.YES);

            userRepository.save(u1);
            userRepository.save(u2);

            commentRepository.save(c1);
            commentRepository.save(c2);
            commentRepository.save(c3);
            commentRepository.save(c4);
            commentRepository.save(c5);
            commentRepository.save(c6);

            teamRepository.save(t1);
            projectRepository.save(p1);
            proposalRepository.saveAll(List.of(prop1, prop2, prop3, prop4));

            voteRepository.saveAll(List.of(v1, v2, v3, v4, v5, v6));

            logger.debug("DB loaded");
        };
    }
}
