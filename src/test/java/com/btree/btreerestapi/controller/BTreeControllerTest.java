package com.btree.btreerestapi.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.btree.btreerestapi.domain.BTree;
import com.btree.btreerestapi.service.BTreeService;
import com.google.gson.Gson;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class BTreeControllerTest {

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

  @Mock private BTreeService service;
  @InjectMocks private BTreeController controller;

  @Test
  public void testGetAllBTrees() {
    when(service.getAllBTrees())
        .thenReturn(Collections.singletonList(new Gson().fromJson(JSON_BTREE, BTree.class)));

    ResponseEntity<List<BTree>> allBTrees = controller.getAllBTrees();

    assertNotNull(allBTrees);
    assertEquals(HttpStatus.OK, allBTrees.getStatusCode());
    assertNotNull(allBTrees.getBody());
    assertEquals(1, allBTrees.getBody().size());
  }

  @Test
  public void testCreateBTree() {
    doNothing().when(service).saveBTree(anyString());

    controller.createBTree(new Gson().fromJson(JSON_BTREE, BTree.class));

    verify(service, times(1)).saveBTree(anyString());
  }

  @Test
  public void testGetLowestCommonAncestorWithId() {
    when(service.getLowestCommonAncestor(anyLong(), anyInt(), anyInt())).thenReturn(Optional.of(1));

    ResponseEntity<Integer> ancestor = controller.getLowestCommonAncestor(1L, 2, 3);

    assertNotNull(ancestor);
    assertEquals(HttpStatus.OK, ancestor.getStatusCode());
    assertNotNull(ancestor.getBody());
    assertEquals(Integer.valueOf(1), ancestor.getBody());
  }

  @Test
  public void testGetLowestCommonAncestorWithBTree() {
    when(service.getLowestCommonAncestor(any(BTree.class), anyInt(), anyInt()))
        .thenReturn(Optional.of(1));

    ResponseEntity<Integer> ancestor =
        controller.getLowestCommonAncestor(new BTree(), 2, 3);

    assertNotNull(ancestor);
    assertEquals(HttpStatus.OK, ancestor.getStatusCode());
    assertNotNull(ancestor.getBody());
    assertEquals(Integer.valueOf(1), ancestor.getBody());
  }
}
