package com.btree.btreerestapi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.btree.btreerestapi.domain.BTree;
import com.btree.btreerestapi.persistence.BTreeEntity;
import com.btree.btreerestapi.persistence.BTreeRepository;
import com.google.gson.Gson;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BTreeServiceTest {

  private static String JSON_BTREE =
      "{\n"
          + "    \"root\": {\n"
          + "        \"value\": 67,\n"
          + "        \"left\": {\n"
          + "            \"value\": 39,\n"
          + "            \"left\": {\n"
          + "                \"value\": 28,\n"
          + "                \"right\": {\n"
          + "                \t\"value\": 29\n"
          + "                }\n"
          + "            },\n"
          + "            \"right\": {\n"
          + "                \"value\": 44\n"
          + "            }\n"
          + "        },\n"
          + "        \"right\": {\n"
          + "        \t\"value\": 76,\n"
          + "        \t\"right\": {\n"
          + "        \t\t\"value\": 74\n"
          + "        \t},\n"
          + "        \t\"left\": {\n"
          + "        \t\t\"value\": 85,\n"
          + "        \t\t\"left\": {\n"
          + "        \t\t\t\"value\": 83\n"
          + "        \t\t},\n"
          + "        \t\t\"right\": {\n"
          + "        \t\t\t\"value\": 87\n"
          + "        \t\t}\n"
          + "        \t}\n"
          + "        }\n"
          + "    }\n"
          + "}";

  private static String JSON_BTREE_WITH_ID =
      "{\n"
          + "    \"id\": 1,\n"
          + "    \"root\": {\n"
          + "        \"value\": 67,\n"
          + "        \"left\": {\n"
          + "            \"value\": 39,\n"
          + "            \"left\": {\n"
          + "                \"value\": 28,\n"
          + "                \"right\": {\n"
          + "                \t\"value\": 29\n"
          + "                }\n"
          + "            },\n"
          + "            \"right\": {\n"
          + "                \"value\": 44\n"
          + "            }\n"
          + "        },\n"
          + "        \"right\": {\n"
          + "        \t\"value\": 76,\n"
          + "        \t\"right\": {\n"
          + "        \t\t\"value\": 74\n"
          + "        \t},\n"
          + "        \t\"left\": {\n"
          + "        \t\t\"value\": 85,\n"
          + "        \t\t\"left\": {\n"
          + "        \t\t\t\"value\": 83\n"
          + "        \t\t},\n"
          + "        \t\t\"right\": {\n"
          + "        \t\t\t\"value\": 87\n"
          + "        \t\t}\n"
          + "        \t}\n"
          + "        }\n"
          + "    }\n"
          + "}";

  @Mock private BTreeRepository repository;
  @InjectMocks private BTreeService service;

  @Test
  public void testGetAllBTrees() {
    when(repository.findAll())
        .thenReturn(
            Collections.singletonList(BTreeEntity.builder().id(1L).json(JSON_BTREE).build()));

    List<BTree> allBTrees = service.getAllBTrees();

    verify(repository, times(1)).findAll();

    assertNotNull(allBTrees);
    assertEquals(1, allBTrees.size());
  }

  @Test
  public void testLowestCommonAncestorWithId() {
    when(repository.findById(anyLong()))
        .thenReturn(Optional.of(BTreeEntity.builder().id(1L).json(JSON_BTREE).build()));

    Optional<Integer> ancestor = service.getLowestCommonAncestor(1L, 83, 87);

    verify(repository, times(1)).findById(anyLong());

    assertNotNull(ancestor);
    assertTrue(ancestor.isPresent());
    assertEquals(Integer.valueOf(85), ancestor.get());
  }

  @Test
  public void testLowestCommonAncestorWithBTree() {
    Optional<Integer> ancestor =
        service.getLowestCommonAncestor(
            new Gson().fromJson(JSON_BTREE_WITH_ID, BTree.class), 83, 87);

    assertNotNull(ancestor);
    assertTrue(ancestor.isPresent());
    assertEquals(Integer.valueOf(85), ancestor.get());
  }

  @Test
  public void testLowestCommonAncestorBTreeNotFound() {
    when(repository.findById(anyLong())).thenReturn(Optional.empty());

    Optional<Integer> ancestor = service.getLowestCommonAncestor(1L, 83, 87);

    verify(repository, times(1)).findById(anyLong());

    assertNotNull(ancestor);
    assertFalse(ancestor.isPresent());
  }

  @Test
  public void testLowestCommonAncestorNotFoundNode() {
    Optional<Integer> ancestor =
        service.getLowestCommonAncestor(
            new Gson().fromJson(JSON_BTREE_WITH_ID, BTree.class), 83, 102);

    assertNotNull(ancestor);
    assertFalse(ancestor.isPresent());
  }

  @Test
  public void testSaveBTree() {
    when(repository.save(any(BTreeEntity.class))).thenReturn(BTreeEntity.builder().build());

    service.saveBTree(JSON_BTREE);

    verify(repository, times(1)).save(any(BTreeEntity.class));
  }
}
