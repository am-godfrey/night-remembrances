package com.mammoth.journal.repositories;

import com.mammoth.journal.model.Entry;
import com.mammoth.journal.model.JournalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<JournalUser, Long> {
    Optional<JournalUser> findByUsername(String username);
}
