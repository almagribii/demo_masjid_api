package org.demo;

public class Temp {
    public static void main(String[] args) {
        Response res = new childB("Child B");
        Response res2 = new childB("Child B");


    }
}

abstract class Response {
    private String msg;

    public Response(String msg) {
        this.msg = msg;
    }
    abstract void print();

    void printMsg(){
        System.out.println(msg);
    }
}

class child extends Response {
    @Override
    void print() {
        System.out.println("Child A");
    }

    public child(String msg) {
        super(msg);
    }
}


class childB extends Response {
    @Override
    void print() {
        System.out.println("Child B");
    }

    public childB (String msg) {
        super(msg);
    }
}