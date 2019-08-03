package com.btree.btreerestapi.service;

import com.btree.btreerestapi.persistence.BTreeEntity;
import com.btree.btreerestapi.persistence.BTreeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BTreeService {

  private BTreeRepository repository;

  public void saveBTree(final String btree) {
    repository.save(BTreeEntity.builder().json(btree).build());
  }
}
