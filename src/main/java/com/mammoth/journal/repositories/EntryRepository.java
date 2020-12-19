package com.mammoth.journal.repositories;

import com.mammoth.journal.model.Entry;
import com.mammoth.journal.model.JournalUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Table
@Repository
public interface EntryRepository extends CrudRepository<Entry, Long> {
    Optional<Entry> findByEntryId(Long entryId);
    Optional<List<Entry>> findAllByJournalUser(JournalUser journalUser);
}
