package com.btree.btreerestapi.service;

import com.btree.btreerestapi.domain.BTree;
import com.btree.btreerestapi.persistence.BTreeEntity;
import com.btree.btreerestapi.persistence.BTreeRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import java.util.Optional;
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
   * Calculates the lowest common ancestor for a BTree stored.
   *
   * @param idBTree identifier of the BTree stored
   * @param n1 fist node
   * @param n2 second node
   * @return an optional with the value of the node who is the lowest common ancestor or empty if
   *     not exist the BTree or not is possible calculate the lowest common ancestor for those nodes
   */
  public Optional<Integer> getLowestCommonAncestor(final long idBTree, final int n1, final int n2) {
    log.debug("Service :: calculations of LCA for B-Tree {}", idBTree);
    return getBTree(idBTree).flatMap(bt -> getLowestCommonAncestor(bt, n1, n2));
  }

  /**
   * Calculates the lowest common ancestor for a BTree received by parameter.
   *
   * @param btree the BTree who will be calculate the lowest common ancestor
   * @param n1 fist node
   * @param n2 second node
   * @return an optional with the value of the node who is the lowest common ancestor or empty if
   *     not is possible calculate the lowest common ancestor for those nodes
   */
  public Optional<Integer> getLowestCommonAncestor(final BTree btree, final int n1, final int n2) {
    log.debug(
        "Service :: calculations of LCA for B-Tree {}",
        new GsonBuilder().setPrettyPrinting().create().toJson(btree));
    return btree.findLca(n1, n2);
  }

  /**
   * Saves a BTree contained in the {@link String} received by parameter.
   *
   * @param btree string with the information of {@link BTreeEntity}
   */
  public void saveBTree(final String btree) {
    repository.save(BTreeEntity.builder().json(btree).build());
  }

  private Optional<BTree> getBTree(final long idBTree) {
    log.debug("Service :: get B-Tree with id {}", idBTree);
    return repository.findById(idBTree).map(bt -> new Gson().fromJson(bt.getJson(), BTree.class));
  }
}
