public class Coordinator {
    int x;
    int y;
    Coordinator parent;

    public Coordinator(int x, int y){
        this.x= x;
        this.y= y;
        this.parent= null;
    }

    public  Coordinator(int x, int y, Coordinator parent){
        this.x= x;
        this.y= y;
        this.parent= parent;
    }

    int getX(){
        return  x;
    }

    int getY(){
        return  y;
    }
}
