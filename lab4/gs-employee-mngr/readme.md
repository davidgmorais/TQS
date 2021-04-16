# Review question
**a) Identify a couple of examples on the use of AssertJ expressive methods chaining**
Some examples of this are present in the `EmployeeRepositoryTest.java` file
```java
assertThat(fromDb.getEmail()).isEqualTo( emp.getEmail());
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
```
and in the `EmployeeRestControllerIT.java` file
```java
assertThat(found).extracting(Employee::getName).containsOnly("bob");
```

**b) Identify an example in which you mock the behavior of the repository (and avoid involving a database)**
We use this in the unit test of the service present in the `EmployeeService_UnitTest.java` file
```java
Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
```
This is used to test the service without depending on the repository and without envolving a database, being able to mock the service so that when we call the findByName() method with john's name, the mock repository returns the object john.

**c) What is the difference between standard @Mock and @MockBeam?**
@Mock is used for unit tests of the business logic while the @MockBean is used to mock objects in the SpringBoot application context, adding or replacing existing beans of the same type for this mock beans. So the main difference is on their usage and deppends on the type of test we want to perform.

**d) What is the role of the file "application-integrationtest.properties"? In which conditions will it be used?**
In conjunction with the @TestPropertySource annotation, this file is used to set the properties of different environments where the integration test are to be runned.