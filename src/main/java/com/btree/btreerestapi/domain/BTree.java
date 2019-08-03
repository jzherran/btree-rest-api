package com.btree.btreerestapi.domain;

import com.google.gson.annotations.Expose;
import java.util.Objects;
import java.util.Optional;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString.Exclude;

@Data
@RequiredArgsConstructor
public class BTree {

  @Exclude private boolean wsx;

  @Exclude private boolean wsy;

  @Expose @NonNull private Node root;

  /**
   * Find the lowest common ancestor related to node n1 and node n2.
   *
   * @param n1 a node
   * @param n2 a node
   * @return the identifier of the node who is the LCA for the nodes received
   */
  public Optional<Integer> findLca(final int n1, final int n2) {
    wsx = false;
    wsy = false;

    Node lca = findLcaUtil(root, n1, n2);

    if (wsx && wsy && Objects.nonNull(lca)) {
      return Optional.of(lca.getValue());
    }

    return Optional.empty();
  }

  private Node findLcaUtil(final Node node, final int n1, final int n2) {
    if (node == null) {
      return null;
    }

    Node temp = null;

    if (node.getValue() == n1) {
      wsx = true;
      temp = node;
    }
    if (node.getValue() == n2) {
      wsy = true;
      temp = node;
    }

    final Node leftLca = findLcaUtil(node.getLeft(), n1, n2);
    final Node rightLca = findLcaUtil(node.getRight(), n1, n2);

    if (temp != null) {
      return temp;
    }

    if (leftLca != null && rightLca != null) {
      return node;
    }

    return (leftLca != null) ? leftLca : rightLca;
  }
}
