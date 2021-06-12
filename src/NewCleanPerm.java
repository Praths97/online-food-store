import java.util.*;

/**
 * @author varedla
 */
public class NewCleanPerm {

    private static final String FIELDS_DELIMITER = ", ";
    private static final String CROPS_DELIMITER = "/";
    private static Map<Integer, List<String>> crops = new HashMap<>();
    private static int n = 4;

    public static void main(String[] args) {
        crops.put(n, Arrays.asList("Barley", "Corn", "Wheat", "Rye"));
        List<String> cList = crops.get(n);
        Collections.sort(cList);
        if (cList != null) {
            List<String> result = new ArrayList<>();
            List<String> permut = permutations("", cList, 0, cList.size() - 1, result);
            if (permut != null)
                combinations("", permut, result);
            Set<String> unique = new HashSet<>(result);
            List<String> order = new ArrayList<String>(unique);
            Collections.sort(order);
            System.out.println(order.size());
            order.forEach(System.out::println);
        } else {
            System.out.println("INVALID INPUT");
        }
    }

    private static List<String> permutations(String prefix, List<String> input, int start, int end, List<String> result) {
        if (start == end) {
            return input;
            //combinations(prefix, input, result);
        }
        for (int i = start; i <= end; i++) {
            List<String> nList = new ArrayList<>(input);
            Collections.swap(nList, start, i);
            List<String> permut = permutations(prefix, nList, start + 1, end, result);
            if (permut != null)
                combinations(prefix, permut, result);
            Collections.swap(nList, start, i);
        }
        return null;
    }

    private static void combinations(String prefix, List<String> subList, List<String> result) {
        for (int i = 0; i < subList.size(); i++) {
            String current = prefix.equals("") ? subList.get(i) : prefix + FIELDS_DELIMITER + subList.get(i);
            System.out.println();
            if (subList.size() > 1) {
                // TODO IMP  slashCombinations(current, subList.subList(1, subList.size()), result);
                slashCombinations(subList.get(0), subList.subList(1, subList.size()), result);
            }
            result.add(current);
            combinations(current, subList.subList(i + 1, subList.size()), result);
        }
    }

    private static void slashCombinations(String prefix, List<String> mainSubList, List<String> result) {
        List<String> subList = new ArrayList<>(mainSubList);
        boolean sublist = subList.stream().anyMatch(prefix::contains);
        if (!sublist) {
            Collections.sort(subList);
            //System.out.println("prefix = " + prefix + " subList = " + subList);
            /*if (prefix.equalsIgnoreCase("Rey") && subList.indexOf("Berley") == 0 && subList.contains("Corn") && subList.contains("Wheat")) {
                // System.out.println("subList = " + subList);
            }*/
            for (int i = 0; i < subList.size(); i++) {
                /*if (subList.get(i).equals("Rey") && prefix.equals("Berley") && i == 2) {
                    //System.out.println("i is 2");
                }*/
                if (crops.get(n).contains(prefix) && (crops.get(n).indexOf(subList.get(i)) > crops.get(n).indexOf(prefix))) {

                    if (subList.get(i).equals("Rey") && prefix.equals("Berley")) {
                        System.out.println("subList " + subList);
                    }

                    String slashComb = subList.get(i) + CROPS_DELIMITER + prefix;
                    result.add(slashComb);
                    List<String> forward = new ArrayList<>();
                    if (i + 1 < subList.size()) {
                        forward = subList.subList(i + 1, subList.size());
                        List<String> permut = permutations(slashComb, forward, 0, forward.size() - 1, result);
                        if (permut != null)
                            combinations(slashComb, permut, result);
                    }
                   /* int k = i;
                   while (k + 1 < subList.size()) {
                        List<String> sList = subList.subList(k + 1, subList.size());
                        combinations(slashComb, sList, result);
                        List<String> permut = permutations(slashComb, subList, 0, subList.size() - 1, result);
                        if (permut != null)
                            combinations(slashComb, permut, result);
                        k++;
                    }*/
                    List<String> backward = new ArrayList<>();
                    if (i - 1 >= 0) {
                        backward = subList.subList(0, i);
                        List<String> permut = permutations(slashComb, backward, 0, backward.size() - 1, result);
                        if (permut != null)
                            combinations(slashComb, permut, result);
                    }
                   /* int m = i;
                    while (m - 1 >= 0) {
                        List<String> sList =subList.subList(m - 1, i);
                        combinations(slashComb, sList, result);
                        List<String> permut = permutations(slashComb, sList, 0, sList.size() - 1, result);
                        if (permut != null)
                            combinations(slashComb, permut, result);
                        m--;
                    }*/

                    // forward backwatd combs
                    /*
                        Rey/Berley
                        Rey/Berley, Corn
                        Rey/Berley, Wheat
                        but no combs like below:
                        Rey/Berley, Corn , Wheat
                        Rey/Berley, Wheat , Corn
                    * */
                    List<String> newForwardL = new ArrayList<>(forward);
                    List<String> newBackwardL = new ArrayList<>(backward);
                    newForwardL.addAll(newBackwardL);
                    List<String> permut = permutations(slashComb, newForwardL, 0, newForwardL.size() - 1, result);
                    if (permut != null)
                        combinations(slashComb, permut, result);

                }
                if (subList.size() > 1 && i + 1 < subList.size()) {
                    //System.out.println("i = " + i);
                    String current = prefix + FIELDS_DELIMITER + subList.get(i + 1) + CROPS_DELIMITER + subList.get(i);
                    result.add(current);

                    List<String> forwardList = subList.subList(i + 2, subList.size());
                    List<String> permut = permutations(current, forwardList, 0, forwardList.size() - 1, result);
                    if (permut != null)
                        combinations(current, permut, result);

                    /*for (int j = i + 1; j resu< subList.size(); j++) {
                        List<String> sList =subList.subList(j + 1, subList.size());
                        combinations(current,sList , result);
                        List<String> permut = permutations(current, sList, 0, sList.size() - 1, result);
                        if (permut != null)
                            combinations(current, permut, result);
                    }*/

                    List<String> backTrackSList = subList.subList(0, i);
                    List<String> backTrackPermute = permutations(current, backTrackSList, 0, backTrackSList.size() - 1, result);
                    if (backTrackPermute != null)
                        combinations(current, backTrackPermute, result);

                  /*  int m = i;
                    while (m - 1 >= 0) {
                        List<String> sList =subList.subList(m - 1, i);
                        combinations(current, sList, result);
                        List<String> permut = permutations(current, sList, 0, sList.size() - 1, result);
                        if (permut != null)
                            combinations(current, permut, result);
                        m--;
                    }*/

                    // TODO check use of it
                  /*  List<String> newForwardL = new ArrayList<>(forwardList);
                    List<String> newBackwardL = new ArrayList<>(backTrackSList);
                    newForwardL.addAll(newBackwardL);
                    List<String> allPermute = permutations(current, newForwardL, 0, newForwardL.size() - 1, result);
                    if (permut != null)
                        combinations(current, permut, result);*/


                    int k = i;
                    while (k + 1 - 2 >= 0) {
                        String backtrack = prefix + FIELDS_DELIMITER + subList.get(k + 1) + CROPS_DELIMITER + subList.get(k - 1);
                        result.add(backtrack);

                        List<String> sList2 = subList.subList(i, k + 1);
                        List<String> sList2Permute = permutations(backtrack, sList2, 0, sList2.size() - 1, result);
                        if (sList2Permute != null)
                            combinations(backtrack, sList2Permute, result);
                        k--;

                       /* //for (int j = i + 1; j < subList.size(); j++) {
                        List<String> sList = subList.subList(i, k + 1);
                        combinations(backtrack, sList, result);
                        List<String> permut = permutations(current, sList, 0, sList.size() - 1, result);
                        if (permut != null)
                            combinations(backtrack, permut, result);
                        k--;
                        //}*/
                    }
                   /* List<String> nList = new ArrayList<>(subList.subList(i,subList.size()));
                    Collections.swap(nList, i+1, i);
                    combinations(current, nList, result);*/
                    //slashCombinations(prefix,nList, result);
                    //permutations(current, subList, i, subList.size() - 1, result);
                }
                slashCombinations(prefix + FIELDS_DELIMITER + subList.get(0), subList.subList(1, subList.size()), result);
            } // for
        }
    }

}
