# Notes:

### Code Snippets:

Returning empty array from function:
```java
public static String[] returnEmptyStringArray() {
        
        String[] emptyArray = {};
        return emptyArray;
    }

 public static String[] returnEmptyStringArray() {
        return new String[]{};
    }
````

Convert array of characters to string
```java
public static String toString(char[] a)
    {
        // Creating object of String class
        String string = new String(a);
 
        return string;
    }
```
