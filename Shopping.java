/*
  *Program to implement a simple checkout line and billing system in a shopping aisle.
  *DATA STRUCTURE USED: QUEUE (implemented by array)
  *Program has 3 menus, 1 Main menu and 2 submenus of employee and customer which do their respective work.
  *Main menu: 1. Go to customer menu , 2. Go to employee menu  3.Exit the program

  *Customer menu : 1.Add items to the queue and checkout 2.Return to main menu.Control should remain in this menu until the user
  chooses to go back to main menu, user should not be able to exit the program from the sub menus,only from the main menu
  1. Items listed by the customer in the first option of the Customer sub menu are inserted into the queue and waits for bill generation
  2. second option takes us back to the main menu

  *Employee menu: 1.Generate bill, 2.view the queue 3.go back to main menu.Control should remain in this menu until the user
  chooses to go back to main menu, user should not be able to exit the program from the sub menus,only from the main menu
  1.Generates the bill of the foremost customer and removes that customer from the queue after bill generation
  2.Views the entire queue
  3.Takes us back to the main menu

  *Authors: Prarthana.R.Nayak , Priya.M.G
  *Date started: 14/05/2021
  *Date ended: 2/06/2021
 */
import java.util.Scanner;
//import statements

public class Shopping
{
    private static final int MAXSIZE = 10;
    Queue shoppingLine = new Queue(MAXSIZE);
    public static void main(String[] args)
    {
        System.out.println("Welcome to T mart!");
        Shopping main = new Shopping();
        main.mainSwitchStatements();
    }

    /*****function that contains the main menu switch statements
    Doesn't have any parameters and returns void****/
    void mainSwitchStatements()
    {
        int choice;
        Scanner scanner = new Scanner(System.in);
        //printing the choices to the console to ask user to choose one
        System.out.println("MAIN MENU\n=========");
        System.out.println("1.you are a customer");
        System.out.println("2.you are a billing employee");
        System.out.println("3.EXIT");
        while (true)
        {
            //reading the users choice from the menu
            choice = scanner.nextInt();
            if (choice > 3 || choice < 1)
            {
                System.out.println("you have not entered a valid choice! enter again");
                continue; //continues the while loop, asks for input again
            }
            break;//if input is valid,breaks out of the loop
        }
        switch (choice)
        {
            case 1:customerSwitchStatements();
                //as the user is a customer,the method containing the customer sub menu as switch is called
                break;
            case 2:employeeSwitchStatements();
                //as the user is a employee,the method containing the employee sub menu as switch is called
                break;
            case 3:System.out.println("Exiting the program");
                System.exit(0);//successful exit
        }
    }//end of function

    /*******function that contains the Customer menu switch statements
    Doesn't have any parameters and returns void*********************/
    void customerSwitchStatements()
    {
        int choice2;
        Scanner scanner = new Scanner(System.in);
        //printing customer menu and the choices in it on the console
        System.out.println("CUSTOMER MENU\n=============");
        System.out.println("1.Add items to your cart and proceed to checkout\n" +
                "2.Go back to Main menu");
        while (true)
        {
            choice2 = scanner.nextInt(); //takes the input from the customer
            if (choice2 > 2 || choice2 < 1) {
                System.out.println("you have not entered a valid choice! enter again");
                continue; //loop continues because valid choice not entered
            }
            break;//otherwise break out of the loop
        }
        switch(choice2)
        {
            case 1:addItemsCheckout();
                break;
            case 2:mainSwitchStatements();
                break;
        }
    } //end of function

    /****function that contains the Employee menu switch statements
    Doesn't have any parameters and returns void****/
    void employeeSwitchStatements()
    {
        int choice2;
        Scanner scanner = new Scanner(System.in);
        //printing Employee menu and the choices in it on the console
        System.out.println("EMPLOYEE MENU\n=============");
        System.out.println("Enter your choice");
        System.out.println("1.Checkout and generate bill\n" +
                "2.View the queue\n"+
                "3.Go back to main menu\n");
        while (true) //takes the input from the Employee
        {
            choice2 = scanner.nextInt();
            if (choice2 > 3 || choice2 < 1)
            {
                System.out.println("you have not entered a valid choice! enter again");
                continue;//loop continues because valid choice not entered
            }
            break;//otherwise break out of the loop
        }
        switch(choice2)
        {
            case 1: billGeneration();
                break;
            case 2: viewQueue();
                break;
            case 3: mainSwitchStatements();
        }
    }//end of method

    /*****belongs to Customer menu
    function which takes the user input from the customer(their name,number and items they want to buy)
    stores the user input in a string, and inserts the string into a queue(like a line in the checkout isle)
    does not have any parameters,returns void
    ************/
    void addItemsCheckout()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your name");
        String name = scanner.nextLine();
        System.out.println("please enter your number");
        String number = scanner.nextLine();
        System.out.println("Please enter the items you would like to checkout(multiple items separated by commas)");
        String items = scanner.nextLine();
        String input = "Name: " + name + "\nNumber: " + number + "\nitems: " + items;
        //the above line stores all the user input in a string by concatenating them
        int value = shoppingLine.enqueue(input);
        //enqueue returns -1 if there is unsuccessful insertion(queue is full)
        if (value == -1)
        {
            System.out.println("queue full, kindly wait for some time and enter your items again"); //error message
            customerSwitchStatements();
            /*goes back the customer menu again because the requirement is that the program should not exit until
              the user goes back to the main menu and chooses exit
             */
        }
        //enqueue returns 0 if there is successful insertion(queue has space)
        System.out.println("Your items have been added to the queue for checkout and bill generation");
        customerSwitchStatements(); //does back to the customer sub menu again
    }//end of function

    /****Belongs to Employee sub menu
    Function which displays the next customer in line for bill generation
    Asks the employee for the total amount of the purchased items,prints the bill and removes said customer from the line.
    does not have any parameters,returns void
    ***/
    void billGeneration()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("The next customer is :");
        String customer = shoppingLine.peek(); //peek function of the queue class returns the foremost customer details
        //if the queue is empty, the peek function returns the string "empty"
        if (customer.equals("empty"))
        {
            System.out.println("The queue is empty,no customers to bill");
            employeeSwitchStatements();
            /* Goes back the Employee menu again because the requirement is that the program should not exit until
              the user goes back to the main menu and chooses exit, control goes to the method called*/
        }
        //if the queue is not empty,print the customer details stored in the string customer(see the peek function call above)
        System.out.println(customer);
        System.out.println("please enter the total amount for the items in rupees");
        float amount = scanner.nextFloat();
        //starts to print the bill
        System.out.println("Bill Generated :");
        System.out.println("================================================\n" +
                customer + "\nprice:"+ amount +
                "\n===============================================");
        shoppingLine.dequeue();//delete the customer from the queue once the bill is generated
        employeeSwitchStatements();
         /* Goes back the Employee menu again because the requirement is that the program should not exit until
              the user goes back to the main menu and chooses exit */
    }

    /****Belongs to Employee sub menu
   Function which displays the entire queue
   does not have any parameters,returns void
   ***/
    void viewQueue()
    {
        System.out.println("viewing the queue");
        int val = shoppingLine.display();
        //the display function returns -1 if the queue is empty,0 if queue is not empty(stored in variable value)
        if(val == -1)
        {
            System.out.println("There are no customers to view");
            /* Goes back the Employee menu again because the requirement is that the program should not exit until
              the user goes back to the main menu and chooses exit */
            employeeSwitchStatements();
        }
        /* Goes back the Employee menu again because the requirement is that the program should not exit until
              the user goes back to the main menu and chooses exit */
        employeeSwitchStatements();
    }
} //end of class main

/**Class queue
   Contains functions enqueue(),dequeue(),display(),peek() and some other utility variables and functions
   Core Data structure used in this program
   This queue stores strings and is implemented by array
   Insertion of elements in this Data structure happens from the rear end, and deletion happens from the front end
   Thus useful to emulate real life queue
 */
class Queue {
    String[] arr;
    int front;
    int rear;
    int capacity;   // maximum capacity of the queue
    int count;      // current size of the queue(stored elements)

    // Constructor to initialize a queue based on the size given
    Queue(int size)
    {
        arr = new String[size];
        capacity = size;
        front = 0;
        rear = -1;
        count = 0;//queue is empty initially so count = 0
    }

    /**Utility function to dequeue the front element
      Returns String, has no parameters
      returns  a string "empty" if the queue is empty
      else returns the deleted element/String
     */
    public String dequeue()
    {
        // check for queue underflow
        if (isEmpty())
        {
            //prints the error message
            return "empty";//returns empty as queue empty
        }
        String deletedVal = arr[front];
        front = (front + 1) % capacity; //deleting the foremost value
        count--; //decrementing the count as deletion happens
        return deletedVal;
    }

    /**Utility function to add an item to the queue
       Returns int value, has 1 parameter which takes the item to be added
       returns  a value -1 if the queue is empty
       else returns 0
     */
    public int enqueue(String item)
    {
        // check for queue overflow
        if (isFull())
        {
            return -1;
        }
        rear = (rear + 1) % capacity;
        arr[rear] = item; //inserting the item provided
        count++; //incrementing counter as there is one more element in the queue
        return 0;
    }

    /**Utility function to return the front element of the queue
       returns a String,takes no parameters
       returns a string "empty" if the queue is empty
       returns the foremost value in the queue if teh queue not empty
     */
    public String peek() {
        if (isEmpty())
        {
            //queue id empty
            return "empty";
        }
        //queue is not empty, so return foremost value in queue
        return arr[front];
    }

    /** utility function to display the whole queue for viewing
       returns int value,takes no parameters
       returns -1 is queue is empty,else returns 0
     */
    public int display()
    {
        int i;
        if (isEmpty()) //checks if queue is empty
        {
            return -1;
        }
        //else display the queue
        for (i = front; i <= rear; i++) {
            System.out.println(arr[i]);
            System.out.println("=============================");
        }
        return 0;
    }

    // Utility function to return the size of the queue
    public int size() {
        return count; //contains the total elements present at that time in the queue
    }

    // Utility function to check if the queue is empty or not
    public Boolean isEmpty() {
        return (size() == 0);
    }

    // Utility function to check if the queue is full or not
    public Boolean isFull() {
        return (size() == capacity);
    }
}//end of class queue