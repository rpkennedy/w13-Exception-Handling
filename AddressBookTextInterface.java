/**
 * Provide a textual interface to an AddressBook.
 * Different commands provide access to the data in the address book.
 *
 *      One to search the address book.
 *
 *      One to allow a set of contact details to be entered.
 *
 *      One to show all the entries in the book.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2016.02.29
 */
public class AddressBookTextInterface
{
    // The address book to be viewed and manipulated.
    private AddressBook book;
    // A parser for handling user commands.
    private Parser parser;
    //used for exception catching
    private String temp;
    /**
     * Constructor for objects of class AddressBookTextInterface
     * @param book The address book to be manipulated.
     */
    public AddressBookTextInterface(AddressBook book)
    {
        this.book = book;
        parser = new Parser();
    }
    
    /**
     * Read a series of commands from the user to interact
     * with the address book. Stop when the user types 'quit'.
     */
    public void run()
    {
        System.out.println("Address Book.");
        System.out.println("Type 'help' for a list of commands.");
        
        String command;
        do{
            command = parser.getCommand();
            if(command.equals("add")){ 
                add();
            }
            else if(command.equals("get")){
                get();
            }
            else if(command.equals("list")){
                list();
            }
            else if(command.equals("search")){
                find();
            }
            else if(command.equals("remove")){
                remove();
            }
            else if(command.equals("help")){
                help();
            }
            else if(command.equals("change")){
                change();
            }
            else{
                // Do nothing.
            }
        } while(!(command.equals("quit")));

        System.out.println("Goodbye.");
    }
    
    /**
     * Add a new entry.
     */
    private void add()
    {
        System.out.print("Name: ");
        String name = parser.readLine();
        System.out.print("Phone: ");
        String phone = parser.readLine();
        System.out.print("Address: ");
        String address = parser.readLine();
        book.addDetails(new ContactDetails(name, phone, address));
    }
    
    /**
     * Find an entry matching a key.
     */
    private void get()
    {
        System.out.println("Type the key of the entry.");
        String key = parser.readLine();
        ContactDetails result = book.getDetails(key);
        System.out.println(result);
    }
    
    /**
     * Remove an entry matching a key.
     */
    private void remove()
    {
        System.out.println("Type the key of the entry.");
        String key = parser.readLine();
        try{
            book.removeDetails(key);
           }
        catch(NoMatchingDetailsException e){
            System.out.println("NoMatchingDetailsException has been caught");
            System.out.println(e.toString());
           }
    }
    
    /**
     * Find entries matching a key prefix.
     */
    private void find()
    {
        System.out.println("Type a prefix of the key to be found.");
        String prefix = parser.readLine();
        ContactDetails[] results = book.search(prefix);
        for(int i = 0; i < results.length; i++){
            System.out.println(results[i]);
            System.out.println("=====");
        }
    }
    
    /**
     * List the available commands.
     */
    private void help()
    {
        parser.showCommands();
    }
    
    /**
     * List the address book's contents.
     */
    private void list()
    {
        System.out.println(book.listDetails());
    }
    
    /**
     * Change the contents of an entry
     */
    private void change(){
        System.out.println("Enter the entry's Old Key: ");
        String oldKey = parser.readLine();
        System.out.println("Enter the entry's name: ");
        String name = parser.readLine();
        System.out.println("Enter the entry's phone: ");
        String phone = parser.readLine();
        System.out.println("Enter the entry's address: ");
        String address = parser.readLine();
        
        ContactDetails details = new ContactDetails(name, phone, address);
        
        try{
            book.changeDetails(oldKey, details);
        }
        catch(NoMatchingDetailsException e){
            System.out.println("NoMatchingDetailsException has been caught");
            System.out.println(e.toString());
        }
    }
}
