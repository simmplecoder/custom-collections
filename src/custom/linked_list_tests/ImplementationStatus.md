#<font color="purple">CustomLinkedList</font> implementation status

<h2>Features</h2>

Checked checkbox will mean that feature is implemented, but not if tests are passing. 
 
<h3><font color="purple">Object</font> interface implementation status: full</h3>
 
 - [x] `toString()` method. Tests: <font color="green">passed</font>
 
   <font color="yellow">Bugfix:</font> it returned "]" instead of "[]" when it was empty.
 
 - [x] `equals()` method. Tests: <font color="green">passed</font>
 
   Note: heavily depends on `size` maintained correctly.
 
 - [x] `hashCode()` method. Tests: <font color="green">passed</font> 

   <font color="yellow">Bugfix</font>: lists that were anagrams were giving the same hashcode
   
Three function listed in the *Effective Java, Second Edition* were implemented, other weird `Object` methods will not be considered.
 
<h3><font color="purple">Collection</font> interface implementation status: partial</h3>

 - [x] `add(T object)` method. Tests: <font color="green">passed</font> 
 
 - [x] `get(int index)` method. Tests: <font color="green">passed</font> 
 
 - [x] `clear()` method. Tests: <font color="blue">Not required</font>.
 
    Note: the function is too small and easy, thus doesn't require tests.
    
 - [x] `remove(Object o)` method. Tests: <font color="green">passed</font>
 
 - [x] `size()` method. Tests: <font color="green">passed</font>
 
    Note: `size` field should be maintained by modifier functions.
    
Most of the non `*All(Collection<? extends T>)` functions were implemented. Further progression will require major leap.
 
<h2>Testing methodology</h2>

Currently tests are oneshot small functions. Every test suite have been moved into their own functions. Every test now prints it's name with blue, result with green or red depending on pass or failure.

<h3>Future plan</h3>

Tests should be moved to a subclass of abstract class named `Test`. The abstract class should be rougly like the following:
 
    abstract class Test
    {
        public String getTestName() //default behavior
        {
            return "Anonymous test";
        }
        
        public String getTestDescription() //default behavior
        {
            return "No description.";
        }
        
        private abstract boolean execute();
        
        private void printResult(boolean result)
        {
            if (result)
            {
                //print "Test Passed!" in green
                return;
            }
            
            //print "Test Failed!" in red
        }
        
        public void run()
        {
            //print test name in blue and description in default color
            printResult(execute());
        }
        
        //optionally make the result be queriable
    }