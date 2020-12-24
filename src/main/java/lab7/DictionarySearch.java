package lab7;

import lombok.Getter;

import java.sql.SQLException;
import java.util.*;

@Getter
public class DictionarySearch {

    private DictionaryExtractor extractor;
    private Map<String, Double> customers_rating;
    private TreeMap<String, Double> customers_rating_sorted;
    private List<Map.Entry<String, Double>> listofSortedCustomers;
    private List<SegmentDTO> segments;

    public DictionarySearch() throws SQLException {
        extractor = new DictionaryExtractor();
        customers_rating = extractor.getMapOfCliets();
        customers_rating_sorted = new TreeMap<>();
        customers_rating_sorted.putAll(customers_rating);
        listofSortedCustomers =  new ArrayList<>(customers_rating_sorted.entrySet());
        segments = new ArrayList<>();
        for (int i = (int) 'a'; i <= (int) 'z'; i++) {
            SegmentDTO segmentDTO = new SegmentDTO();
            segmentDTO.setCharacter((char)i);
            segmentDTO.setLeft_border(-1);
            segmentDTO.setRight_border(-1);
            segments.add(segmentDTO);
        }
        segmentsSplitter();
    }

    private void segmentsSplitter() {
        int i = 0;
        for (int j = 0; j < segments.size() && i < listofSortedCustomers.size(); j++) {
            char c = segments.get(j).getCharacter();
            segments.get(j).setLeft_border(i);
            int right_border = i;
            while (i < listofSortedCustomers.size()) {
                char letterEmail = listofSortedCustomers.get(i).getKey().charAt(0);
                if (letterEmail != c) {
                    segments.get(j).setRight_border(i-1);
                    break;
                }
                if (i+1 >= listofSortedCustomers.size()) {
                    segments.get(j).setRight_border(i);
                }
               i++;
            }
        }
    }

    public Double linealSearch(String email) {
        for (Map.Entry<String, Double> entry: customers_rating.entrySet()) {
            if(entry.getKey() ==  email) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static Double binarySearch(String email, List<Map.Entry<String, Double>> list, int l, int r) {
        while (l <= r) {
            int m = l + (r-l)/2;
            Map.Entry<String, Double> entry = list.get(m);
            if (entry.getKey().equals(email)) {
                return entry.getValue();
            }
            if (entry.getKey().compareTo(email) < 0) {
                l = m+1;
            }
            else {
                r = m-1;
            }
        }
        return null;
    }
    
    public Double binary_search(String email) {
        int l = 0, r = listofSortedCustomers.size()-1;
        return binarySearch(email, listofSortedCustomers, l, r);
    }
    
    public Double segment_search(String email) {
        int l = -1,r = -1;
        for (SegmentDTO segment: segments) {
            if (segment.getCharacter() == email.charAt(0)) {
                l = segment.getLeft_border();
                r= segment.getRight_border();
                break;
            }
        }
        return binarySearch(email, listofSortedCustomers, l, r);
    }

    private static void BubbleSort(List<SegmentDTO> sortedSegments) {
        for (int i = 0; i < sortedSegments.size()-1; i++) {
            for (int j = 0; j < sortedSegments.size() - 1 - i; j++) {
                SegmentDTO segment_1 = sortedSegments.get(j);
                SegmentDTO segment_2 = sortedSegments.get(j+1);
                int frequency_1 = (segment_1.getRight_border() - segment_1.getLeft_border());
                int frequency_2 = (segment_2.getRight_border() - segment_2.getLeft_border());
                if (frequency_1 < frequency_2){
                    sortedSegments.set(j, segment_2);
                    sortedSegments.set(j+1, segment_1);
                }
            }
        }
    }

    public static void quickSort(List<SegmentDTO> arr, int start, int end) {
        if (start < end) {
            int pivot = partition(arr, start, end);
            quickSort(arr, start, pivot-1);
            quickSort(arr, pivot+1, end);
        }
    }

    private static int partition(List<SegmentDTO> arr, int begin, int end) {
        SegmentDTO pivotSegm = arr.get((begin + end) / 2);
        int pivot = (pivotSegm.right_border - pivotSegm.getLeft_border());
        int i = begin - 1, j = end + 1;

        while (true)
        {


            do
            {
                i++;
            }
            while (arr.get(i).getRight_border() - arr.get(i).getLeft_border() < pivot);

            do
            {
                j--;
            }
            while (arr.get(j).getRight_border() - arr.get(j).getLeft_border() > pivot);

            if (i >= j)
                return j;
            SegmentDTO tmp = arr.get(i);
            arr.set(i, arr.get(j));
            arr.set(j , tmp);
            //Swap(ref arr[i], ref arr[j]);
        }
    }

    public Double searchBySegmentSortedQuick(String email) {
        List<SegmentDTO> sortedSegments = new ArrayList<>(segments);

        quickSort(sortedSegments, 0 , sortedSegments.size()-1);

        int l = -1, r= -1;
        for (SegmentDTO segment: sortedSegments) {
            if (segment.getCharacter() == email.charAt(0)) {
                l = segment.getLeft_border();
                r = segment.getRight_border();
                break;
            }
        }
        return binarySearch(email, listofSortedCustomers, l, r);
    }



    public Double searchBySegmentSortedBubble(String email) {
        List<SegmentDTO> sortedSegments = new ArrayList<>(segments);

        BubbleSort(sortedSegments);
        int l = -1, r= -1;
        for (SegmentDTO segment: sortedSegments) {
            if (segment.getCharacter() == email.charAt(0)) {
                l = segment.getLeft_border();
                r = segment.getRight_border();
                break;
            }
        }
        return binarySearch(email, listofSortedCustomers, l, r);
    }



    public static void main(String[] args) throws SQLException {
        DictionarySearch search = new DictionarySearch();
        char c = 'a';

        for (int i = 0; i < search.listofSortedCustomers.size(); i++) {
            if (search.searchBySegmentSortedQuick(search.listofSortedCustomers.get(i).getKey()) == null) {
                System.out.println("Not found");
            }
        }
    }
}
