
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class rule {
    private int left;
    private int right;

    public rule(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getRight() {
        return right;
    }
}

class Updates {
    public ArrayList<Integer> pages = new ArrayList<>();
    public Boolean isValid;

    public Updates() {

    }
    
    public Updates(ArrayList<Integer> p, Boolean b) {
        this.isValid = b;
        this.pages = p;
    }
}
public class main {

    public static void part1() {
        System.out.println("AoC Day 5 Part 1");
   
        ArrayList<rule> rules = new ArrayList<>();
        ArrayList<Updates> updates = new ArrayList<>();

        boolean full = true;
        Scanner scanner = null;
 
        try {
            if (full) {
                scanner = new Scanner(new File("input_full.txt.txt"));
            } else {
                scanner = new Scanner(new File("input_test.txt"));
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
 
        int lineCounter = 0;
 
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);

            if (line.isBlank()) {
                break;
            } else {
                String[] nums = line.split("\\|");

                rule r = new rule(Integer.parseInt(nums[0]),Integer.parseInt(nums[1]));
                rules.add(r);
                }
        }
    
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            String[] nums = line.split(",");
            ArrayList<Integer> ups = new ArrayList<>();

            for (String n : nums) {
                ups.add(Integer.valueOf(n));
            }
            Updates u = new Updates(ups, true);

            updates.add(u);
        }

        boolean isValid = true;

        for (Updates update : updates) {
            // we're not checking the last nubmer, as it cannot be in the wrong place :-)
            for (int i=0; i<update.pages.size()-1; i++) {
                int toCheck = update.pages.get(i);

                for (rule r : rules) {
                    if (r.getLeft() == toCheck) {
                        // rule for this page found
                        if (update.pages.contains(r.getRight())) {
                            // rule applies
                            if (i < update.pages.indexOf(r.getRight())) {
                                // order already OK
                                
                            } else {
                                update.isValid = false;
                            }
                        } else {
                            // ruile does not apply
                        }
                    }
                }
            }
        }
        int sum = 0;
        for (Updates update : updates) {
            if (update.isValid) {
                int size = update.pages.size();
                if ((size % 2) == 0) {
                    // even number of elements, which one to take now ?
                    System.out.println("Even number of elements, which one to take now ?");
                }
                int m = size / 2;
                sum += update.pages.get(m);
            }
        }
        System.out.println("Sum= " + sum);
    }

    public static void part2() {
        System.out.println("AoC Day 5 Part 2");
   
        ArrayList<rule> rules = new ArrayList<>();
        ArrayList<Updates> updates = new ArrayList<>();

        boolean full = false;
        Scanner scanner = null;
 
        try {
            if (full) {
                scanner = new Scanner(new File("input_full.txt.txt"));
            } else {
                scanner = new Scanner(new File("input_test.txt"));
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
 
        int lineCounter = 0;
 
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);

            if (line.isBlank()) {
                break;
            } else {
                String[] nums = line.split("\\|");

                rule r = new rule(Integer.parseInt(nums[0]),Integer.parseInt(nums[1]));
                rules.add(r);
                }
        }
    
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            String[] nums = line.split(",");
            ArrayList<Integer> ups = new ArrayList<>();

            for (String n : nums) {
                ups.add(Integer.valueOf(n));
            }
            Updates u = new Updates(ups, true);

            updates.add(u);
        }

        boolean isValid = true;
        int sum = 0;
        ArrayList<Integer> corrected = null;
        ArrayList<ArrayList<Integer>> correctedArrayList = new ArrayList<>();

        for (Updates update : updates) {
            // Making a copy of the input which we can modify
            corrected = new ArrayList<>(update.pages);

            for (int i=0; i<update.pages.size(); i++) {
                int toCheck = update.pages.get(i);

                for (rule r : rules) {
                    if (r.getLeft() == toCheck) {
                        // rule for this page found
                        if (update.pages.contains(r.getRight())) {
                            // rule applies
                            int rightPos = update.pages.indexOf(r.getRight());
                            if (i < rightPos) {
                                // order already OK
                                
                            } else {
                                update.isValid = false;
                                // switch the 2 numbers
                                int pos = corrected.indexOf(r.getRight());
                                int p2 = corrected.indexOf(toCheck);
                                corrected.set(pos,toCheck);
                                corrected.set(p2,r.getRight());
                            }
                        } else {
                            // ruile does not apply
                        }
                    }
                }
            }
            correctedArrayList.add(corrected);
        }

        int i=0;
        for (ArrayList<Integer> c : correctedArrayList) {
            int size = c.size();
            if ((size % 2) == 0) {
                    // even number of elements, which one to take now ?
                    System.out.println("Even number of elements, which one to take now ?");
    
            }
@            int m = size / 2;
            sum += c.get(m);
            i++;
        }
        System.out.println("Sum= " + sum);
    }
    public static void main(String[] args) {
        part2();
    }
}
