# DESCRIPTION

A look-and-say sequence is a sequence of numbers. 
Given the current number, the next number is obtained by reading the current number out loud, writing what we say. 
For example, if the current number is "1", then we read that as "one 1" and thus the next number in the sequence will be "11" . 
Similarly, if the current number is "112321", then we read that as "two 1s, one 2, one 3, one 2, one 1", producing the next number as "2112131211", and so on. 
A look-and-say sequence is a simple example of run-length encoding.

The look-and-say sequence can also be computed in reverse. 
For example "2112131211" can be reversed by taking digits two at a time, and writing them out (write two 1s, then one 2, and so on). 
This produces "112321". Work out a couple of examples of the sequence before proceeding.
