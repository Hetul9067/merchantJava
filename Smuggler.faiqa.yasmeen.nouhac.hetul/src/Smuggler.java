import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Smuggler {

    private Scanner scanner = new Scanner(System.in);
    private String name;
    private float SmugglerMoney = 100000;
    private City currentCity = null;
    private City nextCity;
    private City tempCity;
    private int caughtPercentageCounter = 0;
    private int fine = 100;

    private List<City> citiesLi= new ArrayList<City>();
    private boolean gameLose = false;
    private int randomValue = 0;
    private boolean caught = false;


    //aggregate relation
    private List<Item> inventories = new ArrayList<Item>();

    //constructor
    public Smuggler() { }

    //constructor
    public Smuggler(String n, List<City> cities) {
        name = n;

        citiesLi = cities;
        randomCityAssigner();
        randomNextCityAssigner();


    }

    void randomCityAssigner() {

        Random ran = new Random();
        int x = ran.nextInt(3);// Generate a random number between 0 and 2 (inclusive)

        // Loop until a city is found that is not the current city
        while (currentCity == null || citiesLi.get(x) != currentCity)
        {
            // Assign a random city to the currentCity
            currentCity = citiesLi.get(ran.nextInt(3) );
        }
        System.out.println("current city name: " + currentCity.getCityName()) ;

    }

    //it will assign random city object to next city object
    void randomNextCityAssigner() {


        Random ran = new Random();
        int x = ran.nextInt(3) ;

        do
        {

            nextCity = citiesLi.get(ran.nextInt(3) );

        } while (nextCity == currentCity || nextCity == tempCity);

    }

    //this feature is for showing the inventories on the console.
    public void inventoriesListDisplay() {
        System.out.println("################################################################################");

        System.out.println("##### Item\t\t" + "Buying Price\t\t" + "Selling Price\t\t" + "Quantity");
        for (int i = 0; i < inventories.size(); i++)
        {
            System.out.println("##### " + (i + 1) + ". " + inventories.get(i).getName() + "\t\t\t|" + inventories.get(i).getBuyingPrice()
                    + "|\t\t\t|" + inventories.get(i).getSellingPrice() + "|\t\t|" + inventories.get(i).getQuantity() + "|");


        }

        System.out.println("################################################################################");

    }

    //for selling wares
    void sellTheirWares() {
        int ans = 0;
        float quantity = 0;
        boolean quantityChecker = false;
        float earnedMoney = 0;


        System.out.println("#############################################################");
        System.out.println("##### Item\t\t" + "Selling Price\t\tQuantity");
        for (int i = 0; i < inventories.size(); i++)
        {
            System.out.println("##### " +( i + 1) + ". " + inventories.get(i).getName() + "\t\t\t|" + inventories.get(i).getSellingPrice() + "|\t\t\t|" + inventories.get(i).getQuantity() + "|");
        }
        System.out.println("#############################################################");


        boolean checker1 = true;
        do
        {
            try
            {
                checker1 = false;
                System.out.println("#############################################################");
                System.out.println("Enter 0 to exit from Selling menu : ");
                System.out.println("Please enter the item number for selling : ");
                ans = Integer.parseInt(scanner.nextLine());



                if (ans < 0 || ans > inventories.size()) throw new CustomException("error2");
            }
            catch (CustomException ce)
            {
                checker1 = true;

                System.out.println("#########################");
                CustomException.processError(CustomException.msg, inventories.size());
                System.out.println("#########################");

            }
            catch (Exception e) {

                checker1 = true;
                System.out.println("#########################");
                System.out.println(HardCodedValue.ERROR2);
                System.out.println("#########################");
            }
        } while (checker1) ;

        if (ans == 0)
        {
            System.out.println("Thank you for visiting selling menu!");
            return;
        }


        do
        {



            boolean checker = true;
            do
            {
                try
                {
                    checker = false;
                    System.out.println("Please enter the quantities of the " + inventories.get(ans - 1).getName() + " : ");
                    quantity = Float.parseFloat(scanner.nextLine());



                }

                catch (Exception e) {

                    checker = true;
                    System.out.println("#########################");
                    System.out.println(HardCodedValue.ERROR3);
                    System.out.println("#########################");
                }
            } while (checker) ;

            quantityChecker = false;
            if (quantity > inventories.get(ans - 1).getQuantity())
            {
                System.out.println("########################################");
                System.out.println("Sorry, we don't have that much quantity ");
                quantityChecker = true;
            }
        } while (quantityChecker);

        earnedMoney = quantity * inventories.get(ans - 1).getSellingPrice();
        System.out.println("################################");
        System.out.println("Earned amount : " + earnedMoney + "");
        System.out.println("################################");

        //increasing Smuggler's money after selling the item
        SmugglerMoney += earnedMoney;

        //decreasing Smuggler's item quantity after selling it successfully
        inventories.get(ans - 1).setQuantity(inventories.get(ans - 1).getQuantity() - quantity);
        System.out.println("going to Smuggler menu");
        SmugglerMenu();
    }

    public void buyTheirWares() {
        int ans = 0;
        float quantity = 0;
        boolean quantityChecker = false;
        float investedMoney = 0;
        int j = 0;

        System.out.println("#############################################################");
        for (int i = 0; i < citiesLi.size(); i++)
        {
            System.out.println("------------------------------------------------");
            System.out.println("city name : " + citiesLi.get(i).getCityName());
            System.out.println("##### Item \t\t\t" + " Price\t\t\tQuantity");
            for (j = 0; j < citiesLi.get(i).getInventories().size(); j++)
            {
                System.out.println("##### " + (j + 1) + ". " + citiesLi.get(i).getInventories().get(j).getName() + "\t\t\t|" + citiesLi.get(i).getInventories().get(j).getSellingPrice() + "|\t\t\t|" + citiesLi.get(i).getInventories().get(j).getQuantity() + "|");

            }
            System.out.println("------------------------------------------------");
        }

        boolean checker1 = true;
        do
        {
            try
            {
                checker1 = false;
                System.out.println("#############################################################");
                System.out.println("Enter 0 to exit from buying menu : ");

                System.out.println("Please enter the item number to buy item in " + currentCity.getCityName() + " : ");
                ans = Integer.parseInt(scanner.nextLine());


                if (ans < 0 || ans > currentCity.getInventories().size()) throw new CustomException("error2");
            }

            catch (CustomException ce)
            {
                checker1 = true;

                System.out.println("#########################");
                CustomException.processError(CustomException.msg, currentCity.getInventories().size());
                System.out.println("#########################");

            }
            catch (Exception e ) {

                checker1 = true;
                System.out.println("#########################");
                System.out.println(HardCodedValue.ERROR2);
                System.out.println("#########################");
            }
        } while (checker1) ;


        if (ans == 0)
        {
            System.out.println("Thank you for visiting buying menu!");
            return;
        }

        do
        {
            boolean checker = true;
            do
            {
                try
                {
                    checker = false;
                    System.out.println("Please enter the quantities of the " + currentCity.getInventories().get(ans - 1).getName() + " : ");
                    quantity = Float.parseFloat(scanner.nextLine());



                }

                catch (Exception e) {

                    checker = true;
                    System.out.println("#########################");
                    System.out.println(HardCodedValue.ERROR3);
                    System.out.println("#########################");
                }
            } while (checker) ;

            quantityChecker = false;
            if (quantity > currentCity.getInventories().get(ans - 1).getQuantity())
            {
                System.out.println("########################################");
                System.out.println("Sorry, we don't have that much quantity ");
                quantityChecker = true;
            }
        } while (quantityChecker);



        investedMoney = quantity *  currentCity.getInventories().get(ans - 1).getSellingPrice();

        if (investedMoney > SmugglerMoney)
        {

            System.out.println("###################################");
            System.out.println("Sorry! you don't have enough money.");
            System.out.println("###################################");


        }
        else
        {


            //increasing Smuggler's item quantity after buying it successfully
            int itemNumber = -1;


            for (int i = 0; i < inventories.size(); i++)
            {
                if (inventories.get(i).getName() == currentCity.getInventories().get(ans - 1).getName())
                {
                    itemNumber = i;
                    break;
                }
            }


            if (itemNumber == -1)
            {

                Item it = new Item(currentCity.getInventories().get(ans - 1).getName(), currentCity.getInventories().get(ans - 1).getSellingPrice(),0 );
                //it.setName();
                it.setBuyingPrice(currentCity.getInventories().get(ans - 1).getSellingPrice());

                //it.randomlySetSellingPrice();
                it.setQuantity(0);

                inventories.add(it);

                itemNumber = inventories.size() - 1;

            }
            Item ite = currentCity.getInventories().get(ans - 1);
            ite.setQuantity(currentCity.getInventories().get(ans - 1).getQuantity() - quantity);

            inventories.get(itemNumber).setQuantity(inventories.get(itemNumber).getQuantity() + quantity);
            inventories.get(itemNumber).setBuyingPrice(currentCity.getInventories().get(ans - 1).getSellingPrice());
            inventories.get(itemNumber).randomlySetSellingPrice();
            System.out.println("################################");
            System.out.println("Total cost : " + investedMoney);
            System.out.println("################################");

            //decreasing Smuggler's money after buying the item
            SmugglerMoney -= investedMoney;


            System.out.println("it's finished");

        }
        SmugglerMenu();
    }

    //this method is for checking the user's answer to call respective method.
    public void SmugglerAnsChecker(int a) {
        switch (a)
        {
            case 1:
                sellTheirWares();
                break;
            case 2:
                buyTheirWares();
                break;
            case 3:
            {
                tempCity = currentCity;

                currentCity = nextCity;


                randomNextCityAssigner();

                fineChecker();

                if (gameLose)
                {
                    return;
                }
                SmugglerMenu();
                break;

            }

        }
    }
    //for printing Smuggler menu
    public void SmugglerMenu() {

        if (caught)
        {
            System.out.println("#############################");
            System.out.println("You are caught by the Police!");
            System.out.println("#############################");
            caught = false;

        }
        System.out.println("###########################");
        System.out.println("###########################");
        System.out.println("Welcome to Smuggler Page : ");
        System.out.println("###########################");
        System.out.println("Smuggler name : " + name + "");
        System.out.println("Smuggler's total money : " + SmugglerMoney);
        System.out.println("Smuggler's current City name : " + currentCity.getCityName());
        System.out.println("-------------------------------------------------------------------");
        System.out.println("After reaching 100% " + name + " is going to caught by the Police");
        System.out.println(caughtPercentageCounter + "% chances to be caught by the Police");
        System.out.println("-------------------------------------------------------------------");


        inventoriesListDisplay();



        int ans = 0;
        System.out.println("1. Sell their wares");
        System.out.println("2. Buy their wares");
        System.out.println("3. travel to a new city");
        System.out.println("4. Going back to Main Page");

        boolean checker = true;
        do
        {
            try
            {
                checker = false;
                System.out.println("Please enter the require option : ");
                ans = Integer.parseInt(scanner.nextLine());

                if (ans < 1 || ans > 4) throw new CustomException("error1");
            }
            catch (CustomException ce)
            {
                checker = true;

                System.out.println("#########################");
                CustomException.processError(CustomException.msg, 4);
                System.out.println("#########################");

            }
            catch (Exception e) {

                checker = true;
                System.out.println("#########################");
                System.out.println(HardCodedValue.ERROR2);
                System.out.println("#########################");
            }
        } while (checker) ;

        if (ans == 4) return;
        SmugglerAnsChecker(ans);


    }

//for randomly assigning the city to Smuggler



    public String getName() {
        return name;
    }
    public void setName(String n) {
        name = n;
    }

    public List<Item> getInventories() {
        return inventories;
    }

    public void setInventories(List < Item > inv) {
        inventories = inv;
    }


    public float itemQuantitiesChecker() {
        float totalQuantities = 0;
        for (int i = 0; i < inventories.size(); i++)
        {
            totalQuantities += inventories.get(i).getQuantity();
        }
        return totalQuantities;
    }
    public void fineChecker() {

        float totalQuantity = itemQuantitiesChecker();
        if (totalQuantity >= 8000)
        {
            caughtPercentageCounter += 40;
            System.out.println(caughtPercentageCounter + "percentage of the caught ");
        }
        else if (totalQuantity >= 5000)
        {
            caughtPercentageCounter += 30;
            System.out.println(caughtPercentageCounter + "percentage of the caught ");

        }
        else if (totalQuantity >= 2000)
        {
            caughtPercentageCounter += 20;
            System.out.println(caughtPercentageCounter + "percentage of the caught ");

        }
        else if (totalQuantity >= 500)
        {
            caughtPercentageCounter += 10;
            System.out.println(caughtPercentageCounter + "percentage of the caught ");

        }

        if (caughtPercentageCounter >= 100)
        {
            caught = true;
            caughtPercentageCounter = 0;
            tempCity = currentCity;
            currentCity = nextCity;
            randomNextCityAssigner();

            //minimum one item require

            if (inventories.size() != 0)
            {
                inventories.remove(inventories.size() -1 );

            }

            SmugglerMoney -= fine;
            fine += 50;
            if (SmugglerMoney <= 0)
            {
                gameLose = true;
                System.out.println("ohh You lose the game!");

            }
            System.out.println("Smuggler Money is : " + SmugglerMoney);

        }
    }

    public boolean getGameLose()
    {
        return gameLose;
    }

    public void setGameLose(boolean status)
    {
        gameLose = status;
    }

}
