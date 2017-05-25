# Abstract
Intense testing usually requires a lot of boilerplate, such as checking for exceptions since testing method will include `throws` statement. The class aims to get rid of all of the boilerplate and provide required tools to make thorough, randomized tests. It also should encourage proper unit testing, such as randomizing input and making multiple runs if the testing target supports that. . 

# <font color="purple">UnitTest</font>

## Description

The class aims to be easy to use correctly and hard to use incorrectly. It also lifts some boilerplate, but parameters such as `runCount` must be provided.
 
 **Terminology:**
 
  - The `UnitTest` introduces term of "kernel", a test method that is designed to run multiple times. The kernel is used inside of the loop to do multiple runs of one test. As a result, users are encouraged to randomize the input to their test, so that the multiple runs will not be wasted.
 
 In order to make a better design, the constructor of the `UnitTest` has one mandatory parameter, and two optional parameters. `testName` and `testDescription` will be substituted by `"Anonymous test"` and `"No description provided."` when the one parameter constructor is called. Users are encouraged to provide at least `testName`, so that human observer will be able to distinguish outputs from multiple tests. Also, the strings are not settable after object creation because  onekernel should match specific test name, thus being able to set test name will probably lead to error prone usage. 

The `UnitTest` class expects user to throw especially created `TestFailed` exception, but will catch anything and indicate if the unexpected exception is being thrown.
