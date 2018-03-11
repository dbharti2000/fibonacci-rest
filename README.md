# Testing Rest Services

I've created 3 junit tests. Below are the description -

- It tests status code 200 for fibonacci(/fib) service
- It tests when service stops returning fibonacci numbers in their correct sequence.
- It tests the whole sequence of fibonacci returned from service based on provided range, if application provides the wrong sequence, test fails and shows
the expected and actual fibonacci sequence.


### Execution Steps

- Clone the repository or download project as a zip file
- Download all the required libraries
- Run test through IDE Maven Goal

OR

Through command line, use maven commands e.g.

```
mvn test

```
