import org.testng.annotations.Test;
/*
Assuming the homework is contained in TODOs in the Day2 java code on GitHub.
Here's the refactored code
 */
public class Day2Homework {
    //TODO: refactor this
    @Test
    public void testCountLetters() {
        String input = "hello world";
        int result = 0;
        for(char eachChar : input.toCharArray()){
            if(eachChar== 'l') result++;
        }
        System.out.println(result);
    }

    //DONE: -- TODO-- : using https://docs.oracle.com/javase/tutorial/java/data/strings.html
    //DONE: -- TODO-- : write a refactored code
    // Both variants are almost identical, but I had a hunch the 2nd one would be a bit faster – and it is (28 ms vs 48 ms!)

    @Test
    public void testCountLetters2() {
        String input = "hello world";
        int result = 0, l = input.length();


        for(int i = 0; i<l; i++){
            if(input.charAt(i)== 'l') result++;
        }

        System.out.println(result);

    }

    //TODO: create your own class, use constructors, write tests
    // DONE.
    @Test
    public void testPersonConstructors() {
        // Instead of an Actor class I'll use Person. More... generic, I guess?
        // Also, different cultures use different name orders... and don't get me started about patronymics.
        // So... let's create two objects first
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Zhang", "San", true);
        // ... and check the results
        System.out.printf("Person 1: %s\n", p1.fullName());
        System.out.printf("Person 2: %s\n", p2.fullName());
    }

    @Test
    public void testPersonMethods() {
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Zhang", "San", true);

        // Let's perform some method calls...
        p1.greetings();
        p2.greetings();
        p1.eat();p1.drink();
        p2.eat();p2.drink();

        // Now... eat, drink and be merry! Yay, polymorphism!
        p1.eat("kebabs"); p1.drink("beer");p1.beMerry();
        p2.eat("wonton soup"); p2.drink("Baijiu"); p2.beMerry();
    }
}
