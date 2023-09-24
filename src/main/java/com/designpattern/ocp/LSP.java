package com.designpattern.ocp;

class Rectangle {
    public int width, height;
    Rectangle(){
    }
    Rectangle(int width, int height) {
        this.width= width;
        this.height= height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public int getArea(){
        return width*height;
    }
}

class Square extends Rectangle {

    Square(int size) {
        width= height= size;
    }

    @Override
    public void setHeight(int size) {
        super.setHeight(size);
        super.setWidth(size);
    }
    @Override
    public void setWidth(int size) {
        super.setWidth(size);
        super.setHeight(size);
    }
}
class Demo{
    public void useIt(Rectangle rectangle){
        int width= rectangle.width;
        rectangle.setHeight(10);
        System.out.println("expected: "+(width*10)+" actual:"+rectangle.getArea());
    }
}
public class LSP {
    public static void main(String[] args) {
        Demo d1= new Demo();
        d1.useIt(new Rectangle(2,3));
        d1.useIt(new Square(2));//while passing Square object LSP failed since expected and actual value differs, happening b/c of setters method initializing width and height
    }
}
