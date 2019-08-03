package com.btree.btreerestapi.domain;

import com.google.gson.annotations.Expose;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Node {

  @Expose @NotNull private int value;

  @Expose private Node left;

  @Expose private Node right;
}
