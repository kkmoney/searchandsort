public class Wrapper {
    private int x;
    private int y;
    private int w;
    private int h;

    private boolean beenCompared;
    private int rank;
    private String country;

    public Wrapper(int x, int y, int w, int h, int rank, String country) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        beenCompared = false;
        this.rank = rank;
        this.country = country;
    }

    public void display() {
        if(beenCompared == false){
            Main.app.fill(255, 0, 0);
        } else{
            Main.app.fill(0,255,0);
        }

        Main.app.rect(x, y, w, h);
        Main.app.fill(0,0,0);
        Main.app.text(country, x, y);
        Main.app.text(rank, x, y+150);
    }

    public void wasCompared(){
        beenCompared = true;
    }


    public int getRank() {
        return rank;
    }

    public String getCountry(){
        return country;
    }

    public void setRank(int rank){
        this.rank = rank;
    }

    public void setCountry(String country){
        this.country = country;
    }
}


