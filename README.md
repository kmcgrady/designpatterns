# Java Design Patterns using Spring Boot

Welcome to the project specifically made for the Java Design Patterns class. In this project, we will explore and implement the design patterns we learned in class. Spring Boot offers a `HELP.md` if you are curious about Spring Boot related concepts.

## Project Setup

This application was built in IntelliJ Ultimate for its support for Spring Boot and build tools. Setting up the application, should be as easy as opening the project in IntelliJ (if necessary, let IntelliJ know this is a Maven project with the `pom.xml` file).

This project assumes Java 11 as the minimum. Be sure to have that version or later.

### Database

This application uses the H2 database, which is a built in Java Database system that saves to a file. It works properly with Hibernate to make things relatively seamless. There may be a couple things you need to do.

1. You may need to install H2. IntelliJ makes it easy to do this.
2. This project was built on MacOS (Unix based) and assumes the database file in your home directory. If you are a Windows user or would like to store the database file in an different place, you will need to change the path. To change the path of the file, open up `src/main/resources/application.properties` and update the datasource url.

```
spring.datasource.url=jdbc:h2:file:~/design_patterns_db
```

to

```
spring.datasource.url=jdbc:h2:file:PATH/FOR/FILE
```

## Implementing the Design Patterns

This class explored 5 different design patterns:

* State Pattern
* Observer Pattern
* Strategy Pattern
* Factory Pattern
* Decorator Pattern

To save time, a lot of the heavy lifting will be done for you.

## State Pattern

Our sales funnel has multiple states in `src/main/java/com.ga.designpatterns/states` directory. View each one to see how each one works. Compare it with the State Diagram we constructed in the example.

Let's implement the state movement. Inside, `src/main/java/com.ga.designpatterns/models/SalesFunnel.java` implement the following methods:

```
public void interested()
public void deciding(List<String> competitors)
public void acted(int numYearsInContract, boolean didChooseUs)
```

**TIP** Be sure to check out the `aware` method for a hint on how to implement the rest.

## Observer Pattern

First, take a look at each of the observers (in our case, listeners) for the SalesFunnel. Look at the interface at `src/main/java/com.ga.designpatterns/listeners/StateChangeListener.java`. Next, take a look at the two listeners provided:

* `src/main/java/com.ga.designpatterns/listeners/SendInterestMessageListener.java`
* `src/main/java/com.ga.designpatterns/listeners/SendPromotionMessageListener.java`

Next, open up `src/main/java/com.ga.designpatterns/models/SalesFunnel.java`. We've already provided the instance variable for the listeners as well as initialized it. We now need to implement the following methods at the bottom:

```
private void notifyListeners(SalesFunnelState previousState, SalesFunnelState nextState)
public void addStateChangeListener(StateChangeListener listener)
public void removeStateChangeListener(StateChangeListener listener)
```

Make use of `ArrayList`'s methods `add` and `remove` to make the last two methods easy.

To see it in action, take a look at `src/main/java/com.ga.designpatterns/services/SalesFunnelService.java`. Note that we add the listeners after we create or retrieve a `SalesFunnel`. We do not save persist listeners, so we need to ensure we add them every time.

## Strategy Pattern

First, take a look at each of the strategies for assembling an Item Package for the SalesFunnel. Look at the interface at `src/main/java/com.ga.designpatterns/strategies/PackageStrategy.java`. Next, take a look at the two strategies provided:

* `src/main/java/com.ga.designpatterns/strategies/MaximizeCostStrategy.java`
* `src/main/java/com.ga.designpatterns/strategies/MaximizeValueStrategy.java`

They are very similar (and can possibly be abstracted more), but for now, they serve the purpose.

Move on to `src/main/java/com.ga.designpatterns/models/User.java` and uncomment the value on line 73. This exposes a method we need to create for each of our user types.

Update the following files to implement that method.

* `src/main/java/com.ga.designpatterns/models/users/PriceSensitiveUser.java`
* `src/main/java/com.ga.designpatterns/models/users/PriceInsensitiveUser.java`

Finally, update the `offerPackage` method below to use the strategy from `getStrategy`. You can see it in action on line 48 of the `src/main/java/com.ga.designpatterns/services/UserService.java`.

## Factory Pattern

Take a look at `src/main/java/com.ga.designpatterns/models/User.java`, and look at Line 64. Complete the following method. Some pseudo code is provided for you:

```
public static User createUser(String name, int budget, SalesFunnel salesFunnel)
```

Afterwards, let's update the `UserService` (located at `src/main/java/com.ga.designpatterns/services/UserService.java`) and update the code to use our factory method (Line 31).

## Decorator Pattern

Lastly, let's use the Decorator Pattern on our `Item` by looking at the `src/main/java/com.ga.designpatterns/models/ServiceContractedItem`.

Implement the following methods:

```
public int getValue();
public void setValue(int value);
public int getCost();
public void setCost(int cost);
```

To calculate the value and cost, simply multiply the original Item's respective value and cost it by its corresponding multiplier. You will need to cast the value to an `int` to satisfy the signature.