public class Person {
    String personName;
    String familyName;

    Boolean familyNameFirst = false; // In some cultures you put family name first. We're Eurocentric here.

    // Construct some default-name-ordered person...
    public Person(String personName, String familyName) {
        this.personName = personName;
        this.familyName = familyName;
        // Confirm that the constructor was called...
        System.out.println("\tNew Person object instance.\n");
    }

    // This constructor is a bad form, actually.
    // Use of different parameter order is almost guaranteed to cause errors
    public Person(String familyName, String personName, Boolean familyNameFirst) {
        this.personName = personName;
        this.familyName = familyName;
        this.familyNameFirst = familyNameFirst;

        // Let's display some message to recognize that this constructor was called
        System.out.println("\tNew Person object instance. Last name will be displayed first.\n");
    }

    // After writing this method, I set out to refactor it.
    // Conditional expression could be replaced by call to this.fullName() for readability.
    // This method is left here as is, all the changes done in the methods below.

    public void eat(){

        if(!this.familyNameFirst) // BAD form: It's easy to miss the '!' in the condition.
            System.out.printf("\t%s %s is hungry! Eating some food. Tastes ok, I guess\n", this.personName, this.familyName);
        else
            System.out.printf("\t%s %s is hungry! Eating some food. Tastes ok, I guess\n", this.familyName, this.personName);
    }

    // OOP allows for method overload, right? Right.
    // Readability improved.
    public void eat(String food) {
        System.out.printf("\t%s is hungry! Eating %s. It's delicious!\n", this.fullName(), food);
    }

    public void drink(){
        System.out.printf("\t%sis thirsty! They drink some water. Taste is fine, I guess\n", this.fullName());
    }

    public void drink(String drink) {
        System.out.printf("\t%s is thirsty! They drink %s. Refreshing!\n", this.fullName(), drink);
    }

    public void beMerry() {
        System.out.printf("\t%s is happy!\n", this.fullName());
    }

    // Let's get the full name of our person.
    public String fullName() {

        if(this.familyNameFirst) {
            // Bit of an overkill here. Useful with more complex strings
            return String.format("%s %s", this.familyName, this.personName);
        }
        else {
            return this.personName + " " + this.familyName; // Definitely better! Cleaner, more readable.
        }

        // Could've used the ternary, but they say it's a bad habit. Would be less readable, too.
        // But this actually works:
        //  return (this.familyNameFirst?this.familyName:this.personName) +
        //         " " +
        //         (this.familyNameFirst?this.personName:this.familyName);
    }

    public void greetings() {
        System.out.printf("Hello! My name is %s\n", this.fullName());
    }
}
