import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

import java.util.ArrayList;

public class Main extends PApplet {

    private ArrayList<Wrapper> wrappers;
    public static PApplet app;
    private int bottom;
    private int top;

    private int mid;
    private int target;
    private String userInput;

    final private int NUMWRAPPERS = 10;


    private int status;
    //0 means found
    //1 means not found
    //-1 means doesn't exist

    public Main() {
        app = this;
    }

    public static void main(String[] args) {
        PApplet.main("Main");
    }


    private int binarySearchRecursive(int[] arr, int bottom, int top, int target) {
        bottom = 0;
        top = arr[arr.length - 1];
        int mid = (bottom + top) / 2;
        if (mid == target) {
        } else {
            while (target != mid) {
                if (target < mid) {
                    binarySearchRecursive(arr, bottom, mid - 1, target);
                } else if (target > mid) {
                    binarySearchRecursive(arr, mid + 1, top, target);
                } else {
                    return 1;
                }
            }
        }
        return mid; //don't use recursion for visual interface
    }

    private int binarySearchIterative() {
        mid = (bottom + top) / 2;
        if (bottom < top) {
            wrappers.get(mid).wasCompared();
        if (target < wrappers.get(mid).getRank()){
                top = mid - 1;
                return 1;
            } else if (target > wrappers.get(mid).getRank()) {
                bottom = mid + 1;
                return 1;
            } else{
                return 0;
            }
        }
        return -1;
    }

    public void reset() {
        bottom = 0;
        top = wrappers.size() - 1;
        mid = -1;
        status = 1;

        userInput = "";
    }
    public void setup() {
        wrappers = new ArrayList<Wrapper>();
        Table table = loadTable("data/happiness.csv", "header");
        int w = width / table.getRowCount();
        int h = height / 10;
        int y = height / 2;
        int x = 0;

        for (TableRow row : table.rows()) {
            int rank = row.getInt("rank"); // obtain rank data
            String country = row.getString("country"); // obtain quantity
            wrappers.add(new Wrapper(x, y, w, h, rank, country));
            x = x+w;
        }

        reset();


    }


    public void draw() {
        background(255);
        for (Wrapper w : wrappers) {
            w.display();
        }
    }

        public void settings(){
        size(1500, 1000);
        }

        private void selectionSort() {
            int n = wrappers.size();
            for (int i = 0; i < n - 1; i++) {
                int minIndex = findMin(wrappers, i);
                swap(wrappers, i, minIndex);
            }
        }
        private int findMin(ArrayList<Wrapper> wrappers, int startingIndex) {
            int n = wrappers.size();
            int minIndex = startingIndex;
            for (int i = minIndex + 1; i < n; i++) {
                if (wrappers.get(i).getRank() < wrappers.get(minIndex).getRank())
                    minIndex = i;
            }
            return minIndex;
        }
            private void swap(ArrayList<Wrapper> wrappers, int x, int y) {
                int rank = wrappers.get(x).getRank();
                String country = wrappers.get(x).getCountry();
                wrappers.get(x).setRank(wrappers.get(y).getRank());
                wrappers.get(x).setCountry(wrappers.get(y).getCountry());
                wrappers.get(y).setRank(rank);
                wrappers.get(y).setCountry(country);
            }

        public void keyPressed () {
            if (key == 's'){
                int status = binarySearchIterative();
                if(status == -1){
                    System.out.println("Doesn't exist: " + target);
                } else if(status == 0){
                    System.out.println("Found " + target + " at " + wrappers.get(mid).getCountry());
                } else{
                    System.out.println("Not found: " + target);
                }

            } else if(key == 'k') {
                selectionSort();
            } else if(key == '\n'){
                target = Integer.parseInt(userInput);
            } else{
                userInput = userInput + key;
            }
        }

    }




