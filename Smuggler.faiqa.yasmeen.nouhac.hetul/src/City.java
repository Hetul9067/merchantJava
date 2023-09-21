import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class City {
    private String name;
    private List<Item> inventories;
    private List<String> inventoriesReferenceLi;

    private Scanner scanner = new Scanner(System.in);

    public City(String name, List<Item> items){
        this.name = name;
        inventories = items;
        inventoriesReferenceLi = new ArrayList<>();
        inventoriesReferenceLi.add("Lsd");
        inventoriesReferenceLi.add("Weed");
        inventoriesReferenceLi.add("Heroin");
        inventoriesReferenceLi.add("Cocaine");
        inventoriesReferenceLi.add("Ketamine");


    }

    public void sellsItem() throws Exception {
        int ans = 0;
        float quantity = 0;
        boolean quantityChecker = false;

        System.out.println("#############################################################");
        System.out.println("#### Item\t\tSelling Price\t\tQuantity");

        for(int i=0; i<inventories.size(); i++)
            System.out.println("####" + (i + 1) + ". " + inventories.get(i).getName()+"\t\t\t"+inventories.get(i).getSellingPrice()+"\t\t\t" + inventories.get(i).getQuantity());

        boolean checker1 = true;
        do{
            try{
                checker1 = false;
                System.out.println("#############################################################");
                System.out.println("Please enter the item number for selling : ");


                String input = scanner.nextLine();


                ans = Integer.parseInt(input);

                if(ans < 1 || ans > inventories.size()){
                    throw new CustomException("error1");
                }

            }catch(CustomException ce){
                checker1 = true;
                System.out.println("#########################");
                CustomException.processError(CustomException.msg, inventories.size());
                System.out.println("#########################");
            }
            catch(Exception e){
                checker1 = true;
                System.out.println("#########################");
                System.out.println(HardCodedValue.ERROR2);
                System.out.println("#########################");
                throw new Exception(e);
            }


        }while (checker1);

        do{

            boolean checker = true;
            do{
                try{
                    checker = false;
                    System.out.println("Please enter the quantities of the "+inventories.get(ans-1).getName() + " : ");

                    quantity = Float.parseFloat(scanner.nextLine());

                }catch (Exception e){
                    checker = true;
                    System.out.println("#########################");
                    System.out.println(HardCodedValue.ERROR3);
                    System.out.println("#########################");
                }
            }while(checker);

            quantityChecker = false;
            if(quantity > inventories.get(ans -1 ).getQuantity()){
                System.out.println("########################################");
                System.out.println("Sorry, we don't have that much quantity ");

                quantityChecker = true;
            }
        }while(quantityChecker);

        System.out.println("################################");
        System.out.println("####       Item sold        ####");
        System.out.println("################################");




        //decreasing city's item quantity after selling it successfully
        inventories.get(ans - 1).setQuantity(inventories.get(ans - 1).getQuantity() - quantity);
        System.out.println("going to city menu");

        cityMenu();


    }

    public void buyTheirWares() throws Exception {
        int ans = 0;
        float quantity = 0;
        boolean quantityChecker = false;

        System.out.println("#############################################################");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < inventoriesReferenceLi.size(); i++)
        {
            System.out.println("##### " + (i + 1)+ ". " + inventoriesReferenceLi.get(i) + ".");

        }

        System.out.println ( "------------------------------------------------");
        System.out.println ( "#############################################################" );



        boolean checker1 = true;
        do
        {
            try
            {
                checker1 = false;
                System.out.println ( "#############################################################" );
                System.out.println ( "Enter 0 to exit from buying menu : ");

                System.out.println("Please enter the item number to buy item for " + name +" : " );
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
            catch (Exception e)
            {

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

        boolean checker = true;
        do
        {
            try
            {
                checker = false;
                System.out.println("Please enter the quantities of the " + inventoriesReferenceLi.get(ans - 1) + " : ");
                quantity = Float.parseFloat(scanner.nextLine());

            }

            catch (Exception e)
            {

                checker = true;
                System.out.println("#########################");
                System.out.println(HardCodedValue.ERROR3);
                System.out.println("#########################");
            }
        } while (checker) ;



        System.out.println("#################################");
        System.out.println("Item has been bought successfully!");
        System.out.println("#################################");

        //increasing city's item quantity after buying it successfully
        int itemNumber = -1;
        for (int i = 0; i < inventories.size(); i++)
        {
            if (inventories.get(i).getName() == inventoriesReferenceLi.get(ans - 1))
            {
                itemNumber = i;
                break;
            }
        }
        if (itemNumber == -1)
        {

            Random ran = new Random();

            int x = ran.nextInt(32)+10;
            float bPrice = x;

            Item it = new Item(inventoriesReferenceLi.get(ans - 1), bPrice, 0);
            inventories.add(it);
            itemNumber = inventories.size() - 1;

        }

        inventories.get(itemNumber).setQuantity(inventories.get(itemNumber).getQuantity() + quantity);
        System.out.println ("it's finished");

        cityMenu();
    }

    public void cityAnsChecker(int a) throws Exception {
        switch(a){
            case 1:
                sellsItem();
                break;
            case 2:
                buyTheirWares();
                break;
        }
    }

    public void cityMenu() throws Exception {
        System.out.println("###############################################################");
        System.out.println("welcome to " + name + " : ");


        System.out.println("#####| Item\t\t|"+ "Buying Price|\t\t|"+ "Selling Price|\t\t|"+ "Quantity|");
        for (int i = 0; i < inventories.size(); i++)
        {
            System.out.println("#####| " + inventories.get(i).getName() + "\t\t\t|" + inventories.get(i).getBuyingPrice()
                    +"|\t\t\t|"+ inventories.get(i).getSellingPrice() +"\t\t\t|" + inventories.get(i).getQuantity() + "|");


        }
        System.out.println( "###############################################################");

        int ans = 0;
        System.out.println("1. Sell city's wares");
        System.out.println("2. Buy city's wares");
        System.out.println("3. Going back to Main Page");




        boolean checker = true;
        do
        {
            try
            {
                checker = false;
                System.out.println("Please enter the require option: ");
                ans = Integer.parseInt(scanner.nextLine());

                if (ans < 1 || ans > 3) throw new CustomException("error1");
            }
            catch (CustomException ce)
            {
                checker = true;

                System.out.println("#########################");
                CustomException.processError(CustomException.msg, 3);
                System.out.println("#########################");

            }
            catch (Exception e)
            {

                checker = true;
                System.out.println("#########################");
                System.out.println(HardCodedValue.ERROR2);
                System.out.println("#########################");
            }
        } while (checker) ;


        if (ans == 3) return;
        cityAnsChecker(ans);

    }

    public String getCityName()
    {
        return name;
    }

    public void setCityName(String name)
    {
        this.name = name;
    }


    public List<Item> getInventories()
    {
        return inventories;
    }

    public void setInventroies(List<Item> inv)
    {

        inventories = inv;
    }



}
