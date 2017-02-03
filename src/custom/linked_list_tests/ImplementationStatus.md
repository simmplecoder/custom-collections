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
 
   <font color="yellow">Bugfix:</font> it was keeping the objects when doing reverse traversal of the list
 
 - [x] `size()` method. Tests: <font color="green">passed</font>
 
    Note: `size` field should be maintained by modifier functions.
    
 - [x] `iterator()` method. Tests: <font color="blue">Not Required</font>
 
 - [x] `addAll(Collection<? extends E> c)` method. Tests: <font color="green">passed</font>
 
 - [x] `removeAll(Collection<?> c)` method. Tests: Tests: <font color="green">passed</font>
 
 - [x] `containsAll(Collection<?> c)` method. Tests: <font color ="red">To be written</font>
 
 - [x] `retainAll(Collection<?> c)` method. Tests: Tests: <font color="green">passed</font>.
 
 - [x] `toArray()` method. Tests: <font color="green">passed</font>.
 
 - [x] `toArray(T[] a)`. Tests: Tests: <font color="green">passed</font>
 
<h3><font color="purple">Iterator</font> interface implementation status: full</h3>

 - [x] `next()` method. Tests: <font color="green">passed</font>
 
 - [x] `hasNext()` method. Tests: <font color="green">passed</font>
 
 - [x] `remove()` method. Tests: <font color="green">passed</font>
 
 <hr>
 
<h2>Testing methodology</h2>

Currently tests are oneshot small functions. Every test suite have been moved into their own functions. Every test now prints it's name with blue, result with green or red depending on pass or failure.

<h3>Future plan</h3>

Tests should be moved to a subclass of abstract class named `Test`. The abstract class should be roughly like the following:
 
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