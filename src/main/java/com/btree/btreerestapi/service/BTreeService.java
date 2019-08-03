package com.btree.btreerestapi.service;

import com.btree.btreerestapi.domain.BTree;
import com.btree.btreerestapi.persistence.BTreeEntity;
import com.btree.btreerestapi.persistence.BTreeRepository;
import com.google.gson.Gson;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BTreeService {

  private BTreeRepository repository;

  /**
   * Gets all BTrees stored in H2.
   *
   * @return a list of {@link BTree} objects.
   */
  public List<BTree> getAllBTrees() {
    return StreamSupport.stream(repository.findAll().spliterator(), false)
        .map(
            bt -> {
              BTree btree = new Gson().fromJson(bt.getJson(), BTree.class);
              btree.setId(bt.getId());
              return btree;
            })
        .collect(Collectors.toList());
  }

  /**
   * Saves a BTree contained in the {@link String} received by parameter.
   *
   * @param btree string with the information of {@link BTreeEntity}
   */
  public void saveBTree(final String btree) {
    repository.save(BTreeEntity.builder().json(btree).build());
  }
}
