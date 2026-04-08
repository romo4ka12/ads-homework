import java.util.Scanner;

public class Main {
    private static MyLinkedList list = new MyLinkedList();
    private static MyStack history = new MyStack();
    private static MyQueue bills = new MyQueue();
    private static AccountQueue adminQueue = new AccountQueue();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Task 6
        Task6();

        while (true) {
            System.out.println("\n Menu");
            System.out.println("1 - Enter Bank");
            System.out.println("2 - Enter ATM");
            System.out.println("3 - Admin Area");
            System.out.println("4 - Exit");
            System.out.print("Select an option: ");

            int choice = getIntInput();
            switch (choice) {
                case 1: bankMenu(); break;
                case 2: atmMenu(); break;
                case 3: adminMenu(); break;
                case 4:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Task 6 (arrays)
    public static void Task6() {
        BankAccount[] accounts = new BankAccount[2];
        accounts[0] = new BankAccount("101", "Ali", 15000);
        accounts[1] = new BankAccount("102", "Aruzhan", 34000);
        for (BankAccount acc : accounts) {
            System.out.println("Physical Storage: " + acc);
        }
    }

    // Bank menu
    private static void bankMenu() {
        while (true) {
            System.out.println("\nBank");
            System.out.println("1. Request New Account\n2. Deposit\n3. Withdraw\n4. Undo Last Transaction\n5. Back");
            int choice = getIntInput();

            if (choice == 1) {
                System.out.print("Enter name for new account: ");
                String name = scanner.nextLine();
                adminQueue.enqueue(new BankAccount("GEN-" + (int)(Math.random()*100), name, 0));
            } else if (choice == 2) {
                System.out.print("Username: ");
                String name = scanner.nextLine();
                System.out.print("Amount: ");
                double amt = getDoubleInput();
                list.deposit(name, amt);
                history.push("Deposit " + amt + " to " + name);
            } else if (choice == 3) {
                System.out.print("Username: ");
                String name = scanner.nextLine();
                System.out.print("Amount: ");
                double amt = getDoubleInput();
                list.withdraw(name, amt);
                history.push("Withdraw " + amt + " from " + name);
            } else if (choice == 4) {
                System.out.print("Undoing last action: ");
                history.pop();
            } else if (choice == 5) break;
        }
    }

    // ATM Menu
    private static void atmMenu() {
        System.out.print("\nEnter username: ");
        String name = scanner.nextLine();
        BankAccount acc = list.getByUsername(name);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }
        while (true) {
            System.out.println("\nATM");
            System.out.println("1. Balance Enquiry\n2.Withdraw\n3. Back");
            int choice = getIntInput();
            if (choice == 1) System.out.println("Current balance: " + acc.getBalance());
            else if (choice == 2) {
                list.withdraw(name, 1000);
                history.push("ATM Withdraw 1000 by " + name);
            } else if (choice == 3) break;
        }
    }

    // Admin menu
    private static void adminMenu() {
        while (true) {
            System.out.println("\nAdmin");
            System.out.println("1. Process Account Request\n2. View/Pay Bills\n3. Display All Accounts\n4. Back");
            int choice = getIntInput();
            if (choice == 1) {
                if (!adminQueue.isEmpty()) {
                    BankAccount approved = adminQueue.dequeue();
                    list.add(approved);
                    System.out.println("Approved account for: " + approved.getUsername());
                } else System.out.println("No pending requests.");
            } else if (choice == 2) {
                bills.display();
                bills.dequeue();
            } else if (choice == 3) {
                list.displayAll();
            } else if (choice == 4) break;
        }
    }

    // Inputs
    private static int getIntInput() {
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    private static double getDoubleInput() {
        double val = scanner.nextDouble();
        scanner.nextLine();
        return val;
    }

    public static class MyLinkedList {
        private Node head;
        private Node tail;

        private static class Node {
            BankAccount data;
            Node next;
            Node(BankAccount data) { this.data = data; }
        }

        public void add(BankAccount newData) {
            Node newNode = new Node(newData);
            if (head == null) { head = tail = newNode; }
            else { tail.next = newNode; tail = newNode; }
        }

        public void displayAll() {
            Node current = head;
            while (current != null) {
                System.out.println(current.data.toString());
                current = current.next;
            }
        }

        public BankAccount getByUsername(String username) {
            Node current = head;
            while (current != null) {
                if (current.data.username.equals(username)) { return current.data; }
                current = current.next;
            }
            return null;
        }

        public void deposit(String username, double amount) {
            Node current = head;
            while (current != null) {
                if (current.data.getUsername().equals(username)) {
                    current.data.setBalance(current.data.getBalance() + amount);
                    System.out.println("You deposited " + amount);
                    System.out.println("Your new balance is " + current.data.getBalance());
                    return;
                }
                current = current.next;
            }
            System.out.println("Account not found");
        }

        public void withdraw(String username, double amount) {
            Node current = head;
            while (current != null) {
                if (current.data.getUsername().equals(username)) {
                    if (amount <= current.data.getBalance()) {
                        current.data.setBalance(current.data.getBalance() - amount);
                        System.out.println(amount + " has been withdrawn");
                        System.out.println("Current balance - " + current.data.getBalance());
                    } else {
                        System.out.println("Not enough funds");
                    }
                    return;
                }
                current = current.next;
            }
        }
    }

    public static class MyStack {
        private static class Node {
            String data;
            Node next;
            Node(String transaction) { this.data = transaction; }
        }
        Node head;
        public void push(String newData) {
            Node newNode = new Node(newData);
            newNode.next = head;
            head = newNode;
        }
        public void pop() {
            if (head == null) { System.out.println("Stack is empty"); return; }
            System.out.println("Removed: " + head.data);
            head = head.next;
        }
        public void peek() {
            if (head == null) System.out.println("Stack is empty");
            else System.out.println(head.data);
        }
    }

    private static class MyQueue {
        private static class Node {
            String billName;
            Node next;
            Node(String billName) { this.billName = billName; }
        }
        private Node front;
        private Node rear;
        public void enqueue(String billName) {
            Node newNode = new Node(billName);
            if (rear == null) { front = rear = newNode; }
            else { rear.next = newNode; rear = newNode; }
            System.out.println("Bill enqueued");
        }
        public void dequeue() {
            if (front == null) { System.out.println("Queue is empty"); return; }
            System.out.println("Processing bill: " + front.billName);
            front = front.next;
            if (front == null) rear = null;
        }
        public void display() {
            if (front == null) { System.out.println("No bills"); return; }
            Node current = front;
            System.out.print("Bills: ");
            while (current != null) {
                System.out.print("[" + current.billName + "] ");
                current = current.next;
            }
            System.out.println();
        }
    }

    private static class AccountQueue {
        class AccountRequestNode {
            BankAccount accountData;
            AccountRequestNode next;
            public AccountRequestNode(BankAccount accountData) { this.accountData = accountData; }
        }
        private AccountRequestNode front;
        private AccountRequestNode rear;
        public void enqueue(BankAccount account) {
            AccountRequestNode newNode = new AccountRequestNode(account);
            if (rear == null) { front = rear = newNode; }
            else { rear.next = newNode; rear = newNode; }
            System.out.println("Request submitted for: " + account.getUsername());
        }
        public BankAccount dequeue() {
            if (front == null) return null;
            BankAccount account = front.accountData;
            front = front.next;
            if (front == null) rear = null;
            return account;
        }
        public boolean isEmpty() { return front == null; }
    }
}

class BankAccount {
    String accountNumber;
    String username;
    double balance;

    public BankAccount(String accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }
    public String getAccountNumber() { return accountNumber; }
    public String getUsername() { return username; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return username + " (Acc: " + accountNumber + ") – Balance: " + balance;
    }
}