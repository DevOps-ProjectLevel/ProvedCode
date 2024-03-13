package com.provedcode.talent.repo;

import com.provedcode.talent.model.ProofStatus;
import com.provedcode.talent.model.entity.TalentProof;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalentProofRepository extends JpaRepository<TalentProof, Long> {
    Page<TalentProof> findByTalentIdAndStatus(Long talentId, ProofStatus status, Pageable pageable);

    Page<TalentProof> findByTalentId(Long talentId, Pageable pageable);

    Page<TalentProof> findByStatus(ProofStatus status, Pageable pageable);
}