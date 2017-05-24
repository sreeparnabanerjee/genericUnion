package com.cleo.union.resources;

import com.cleo.union.model.Request;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sreeparna on 23/05/17.
 */
@Path("/getGenericUnion")
public class GenericUnionResource {

  @POST
  @Path("/{sets}")
  public Response getGenericUnion(@PathParam("sets") Integer sets, Request request) {
    return Response.ok().entity(getResult(sets, (HashMap<String, Integer>) request.getInput())).build();
  }

  @GET
  @Path("/inputVariables/{sets}")
  public Response getInputVariables(@PathParam("sets") Integer sets) throws JsonProcessingException {
    ArrayList<String> requiredInput = findRequiredInput(sets);
    HashMap<String,Integer> input = new HashMap<>();
    for (String element : requiredInput) {
      element = element.trim();
      element = element.replace(" ", " intersection ");
      System.out.println("Give value for cardinality of element : " + element + " : ");
      input.put(element, 0);
    }
    Request request = new Request(input);
    return Response.ok().entity(new ObjectMapper().writeValueAsBytes(request)).build();
  }

  public int getResult(int sets, HashMap<String, Integer> inputs) {

    // combination of inputs required
    ArrayList<String> requiredInput = findRequiredInput(sets);
    int union = 0;

    union = getUnion(inputs, requiredInput);
    System.out.println("Result is : " + union);
    return union;
  }


  public ArrayList<String> findRequiredInput(int numberOfSets) {
    ArrayList<String> requiredInput = new ArrayList<>();
    char sets[] = new char[numberOfSets];
    sets[0] = 'A';

    for (int count = 1; count < numberOfSets; count++) {
      int ascii = (int) sets[count - 1] + 1;
      sets[count] = (char) ascii;
    }

    for (int count = 1; count <= numberOfSets; count++) {
      getCombination(sets, numberOfSets, count, requiredInput);
    }
    return requiredInput;
  }

  public void combinationUtil(char[] sets, char[] data, int start,
                              int end, int index, int combinationSize, String combination, ArrayList<String> requiredInput) {
    // Current combination is ready
    if (index == combinationSize) {
      for (int j = 0; j < combinationSize; j++) {
        combination += data[j] + " ";
      }
      requiredInput.add(combination);
      combination = "";
      return;
    }

    // replace index with all possible elements. The condition
    // "end-i+1 >= r-index" makes sure that including one element
    // at index will make a combination with remaining elements
    // at remaining positions
    for (int i = start; i <= end && end - i + 1 >= combinationSize - index; i++) {
      data[index] = sets[i];
      combinationUtil(sets, data, i + 1, end, index + 1, combinationSize, combination, requiredInput);
    }
  }

  // The main function that prints all combinations of size r
  // in arr[] of size n. This function mainly uses combinationUtil()
  void getCombination(char[] sets, int numberOfSets, int combinationSize, ArrayList<String> requiredinputs) {
    // A temporary array to store all combination one by one
    char data[] = new char[combinationSize];

    // all combination using temprary array 'data[]'
    combinationUtil(sets, data, 0, numberOfSets - 1, 0, combinationSize, "", requiredinputs);
  }


  // Formula for inclusion-exclusion principle
  public int getUnion(HashMap<String, Integer> input, ArrayList<String> requiredInput) {
    int union = 0;
    for (String element : requiredInput) {
      element = element.trim();
      element = element.replace(" ", " intersection ");
      int length = element.split(" intersection ").length;
      Integer inputValue = input.get(element);
      if (length % 2 == 0) {
        union += inputValue * -1;
      } else {
        union += inputValue;
      }
    }
    return union;
  }


}
