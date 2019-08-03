package com.btree.btreerestapi.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BTreeRepository extends CrudRepository<BTreeEntity, Long> {}
