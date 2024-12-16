package colunga;

public class MyMain {
    public static void main(String[] args) {
        Canvas canvas = new Canvas();
        //initialize canvas

        
        // initialize and also add a starting point on canvas as well as velocity
        // Object A
        Type_A_GameObject typeA = new Type_A_GameObject(100, 100);
        typeA.setVelocity(5);
        canvas.addGameObject(typeA);

       // Object B
        Type_B_GameObject typeB = new Type_B_GameObject(300, 300);
        typeB.setVelocity(5);
        canvas.addGameObject(typeB);

        
     // Object C
        Type_C_GameObject typeC = new Type_C_GameObject(500, 500);
        typeC.setVelocity(5);
        canvas.addGameObject(typeC);

     // Object D
        Type_D_GameObject typeD = new Type_D_GameObject(200, 200);
        typeD.setVelocity(5);
        canvas.addGameObject(typeD);
    }
}
