public class Wrapper {
    private int x;
    private int y;
    private int w;
    private int h;

    private int id;
    private int value;

    public Wrapper(int x, int y, int w, int h, int value) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        // id = (int) (Math.random() * 100);
        id = x / 10;
        this.value = value;
    }

    public void display() {
        Main.app.rect(x, y, w, h);
    }

    public int getID() {
        return id;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }
}


