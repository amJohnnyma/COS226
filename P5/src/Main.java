

public class Main{
    public static void main(String[] args){
        int numShips = 5;
        int dockingsPerShip = 3;

        AndersonLock lock = new AndersonLock(numShips);

        SpaceShip[] ships = new SpaceShip[numShips];

        for(int i = 0; i < numShips; i ++)
        {
            ships[i] = new SpaceShip(i, lock, dockingsPerShip);
            ships[i].start();
        }


        for(int i = 0 ; i < numShips; i ++)
        {
            try{
                ships[i].join();
            }
            catch(InterruptedException e){e.printStackTrace();}
        }
    }
}
