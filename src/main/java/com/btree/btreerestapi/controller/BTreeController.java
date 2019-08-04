package com.btree.btreerestapi.controller;

import com.btree.btreerestapi.domain.BTree;
import com.btree.btreerestapi.service.BTreeService;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/b-tree")
@Api(value = "B-Tree Controller")
public class BTreeController {

  private BTreeService service;

  /**
   * Gets all BTrees stored in memory.
   *
   * @return a list of {@link BTree}
   */
  @GetMapping
  @ApiOperation("Get all B-Tree stored in H2 database")
  public ResponseEntity<List<BTree>> getAllBTrees() {
    log.debug("Controller :: Get request for return all B-Trees");
    return ResponseEntity.ok(service.getAllBTrees());
  }

  /**
   * Create a B-Tree.
   *
   * @param btree the {@link BTree} object to save
   * @return a {@link ResponseEntity} with the status code related to save action
   */
  @PostMapping
  public ResponseEntity<Void> createBTree(@RequestBody final BTree btree) {
    log.debug("Controller :: Post request to create B-Tree with this structure {}", btree);
    service.saveBTree(
        new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(btree));
    return ResponseEntity.ok().build();
  }

  /**
   * Calculate the lowest common ancestor for the stored BTree with the identifier received by
   * parameter, also using the n1 and n2 to calculate that.
   *
   * @param id the identifier of the BTree
   * @param n1 first node
   * @param n2 second node
   * @return The value of the node who is the lowest common ancestor for both nodes received bvy
   *     parameter.
   */
  @GetMapping("/lowestCommonAncestor/{id}")
  public ResponseEntity<Integer> getLowestCommonAncestor(
      @PathVariable final long id, @RequestParam final int n1, @RequestParam final int n2) {
    log.debug(
        "Controller :: Post to calculate LCA for B-Tree with id {} and those nodes [{}, {}]",
        id,
        n1,
        n2);
    return service
        .getLowestCommonAncestor(id, n1, n2)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Calculate the lowest common ancestor for the stored BTree with the identifier received by
   * parameter, also using the n1 and n2 to calculate that. 1111111111
   *
   * @param btree the BTree who will be calculate the lowest common ancestor
   * @param n1 first node
   * @param n2 second node
   * @return The value of the node who is the lowest common ancestor for both nodes received bvy
   *     parameter.
   */
  @GetMapping("/lowestCommonAncestor")
  public ResponseEntity<Integer> getLowestCommonAncestor(
      @RequestBody final BTree btree, @RequestParam final int n1, @RequestParam final int n2) {
    log.debug(
        "Controller :: Post to calculate LCA for B-Tree received in body and those nodes [{}, {}]",
        n1,
        n2);
    return service
        .getLowestCommonAncestor(btree, n1, n2)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
