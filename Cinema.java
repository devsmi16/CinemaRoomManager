import java.util.Scanner;

public class Cinema {
    public static final int PRICE_FRONT = 10;
    public static final int PRICE_BACK = 8;
    public static final int MANY_SEATS = 60;

    private static int currentIncome = 0;
    private static int ticketsSold = 0;
    private static int rows;
    private static int seats;
    private static int seatsTotal;
    public static void main(String[] args) {
        System.out.println("Enter the number of rows: ");
        Scanner scanner = new Scanner(System.in);
        rows = scanner.nextInt();
        seats = scanner.nextInt();

        seatsTotal = rows * seats;
        String[][] cinema = initCinemaRoom(rows,seats);

        while (true){
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int operation = scanner.nextInt();
            switch (operation){
                case 0:
                    return;
                case 1:
                    printCinemaRoom(cinema);
                    break;
                case 2:
                    tryToBuyTicket(scanner,cinema);
                    break;
                case 3:
                    printStatistics();
                    break;
            }
        }
    }
    private static void tryToBuyTicket(Scanner scanner, String[][] cinema){
        System.out.println("Enter a row number: ");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        int seat = scanner.nextInt();
        System.out.println();

        if (row <= 0 || seat <= 0 || row > rows || seat > seats){
            System.out.println("Wrong input");
            tryToBuyTicket(scanner,cinema);
        } else if (cinema[row][seat].equals("B")) {
            System.out.println("That ticket has already been purchased");
            tryToBuyTicket(scanner, cinema);
        }else {
            int price = buyTicket(cinema,row,seat);
            System.out.println("Ticket price: " + price);
        }
    }
    private static int buyTicket(String[][] cinema, int row, int seat){
        cinema[row][seat] = "B";
        int price = getPrice(row);
        currentIncome = currentIncome + price;
        ticketsSold++;
        return price;
    }
    private static int getPrice(int row){
        if (seatsTotal <= MANY_SEATS){
            return PRICE_FRONT;
        }else {
            if (row <= rows / 2){
                return PRICE_BACK;
            }
        }
        return row;
    }
    private static void printCinemaRoom(String[][] cinema){
        System.out.println("Cinema: ");
        for (String[] seatsStr: cinema){
            for (String seat : seatsStr){
                System.out.println(seat +  " ");
            }
        }
    }
    private static String[][] initCinemaRoom(int rows, int seats){
        String[][] cinema = new String[rows + 1][seats + 1];
        for (int i = 0; i <= rows; i++){
            for (int j = 0; j <= seats; j++){
                if (i == 0 && j == 0){
                    cinema[i][j] = " ";
                    continue;
                }
                if (i == 0){
                    cinema[i][j] = Integer.toString(j);
                    continue;
                }
                if (j == 0){
                    cinema[i][j] = Integer.toString(j);
                    continue;
                }
                cinema[i][j] = "S";
            }
        }
        return cinema;
    }
    private static void printStatistics(){
        System.out.println("Number of purchased tickets: " + ticketsSold);
        double percentage = (double) 100 * ticketsSold / seatsTotal;
        System.out.println("Percentage : " + percentage);
        System.out.println("Current income: " + currentIncome);
        System.out.println("Total income: " + currentIncome);
    }
    private static int totalIncome(){
        int rowsFront;
        int rowsBack;
        int income;
        
        if (seatsTotal < 60){
            income =seatsTotal * PRICE_FRONT;
        }else {
            if (rows % 2 == 1){
                rowsFront = (rows - 1) / 2;
                rowsBack = (rows + 1) / 2;
            }else {
                rowsFront = rows / 2;
                rowsBack = rowsFront;
            }
            income = (rowsFront * PRICE_FRONT + rowsBack * PRICE_BACK) * seats;
        }
        return income;
    }
}
