import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet {

    private ArrayList<Wrapper> wrappers;
    public static PApplet app;
    private int bottom;
    private int top;

    private int mid;
    private int target;

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
            if (target < wrappers.get(mid).getID()){
                top = mid - 1;
                return 1;
            } else if (target > wrappers.get(mid).getID()) {
                bottom = mid + 1;
                return 1;
            } else{
                return 0;
            }
        }
        return -1;
    }

    public void reset() {
        wrappers = new ArrayList<Wrapper>();
        int w = width / NUMWRAPPERS;
        int h = height / NUMWRAPPERS;
        int y = height / 2;

        for (int i = 0; i < NUMWRAPPERS; i++) {
            int x = i * w;
            Wrapper wrapper = new Wrapper(x, y, w, h, i);
            wrappers.add(wrapper);
        }

        bottom = 0;
        top = NUMWRAPPERS - 1;
        mid = -1;
        status = 1;
        selectionSort();
    }
    public void setup() {
        reset();
    }


    public void draw() {
        for (Wrapper w : wrappers) {
            w.display();
        }
    }

        public void settings(){
        size(100, 100);
        }

        private void selectionSort(){
        int n = wrappers.size();
           // int n = NUMWRAPPERS;
        for(int i = 0; i < n-1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (wrappers.get(j).getValue() < wrappers.get(min).getValue()) {
                    min = j;
                }
                int temp = wrappers.get(min).getValue();
                wrappers.get(min).setValue(wrappers.get(i).getValue());
            }
        }
    }
        public void keyPressed () {
            if (key == 's'){
                int status = binarySearchIterative();
                if(status == -1){
                    System.out.println("Doesn't exist");
                } else if(status == 0){
                    System.out.println("Found at "+ mid);
                } else{
                    System.out.println("Not found");
                }

            } else {
                target = Integer.parseInt(String.valueOf(key));
            }

        }

    }




