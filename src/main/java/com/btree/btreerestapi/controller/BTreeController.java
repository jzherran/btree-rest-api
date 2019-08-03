package com.btree.btreerestapi.controller;

import com.btree.btreerestapi.domain.BTree;
import com.btree.btreerestapi.service.BTreeService;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/b-tree")
public class BTreeController {

  private BTreeService service;

  /**
   * Create a B-Tree.
   *
   * @param btree the object to save
a   * @return a {@link ResponseEntity} with the status code related to save
  action
   */
  @PostMapping("/")
  public ResponseEntity<Void> createBTree(@RequestBody final BTree btree) {
    log.debug("Controller :: Post request to create B-Tree with this structure {}", btree);
    service.saveBTree(
        new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(btree));
    return ResponseEntity.ok().build();
  }
}
