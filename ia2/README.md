I made my own test suite for multiple programs. It can be found in the test-all
directory. Use ./run.sh from there to compile and run all the tests.

Comments are bounded on both sides with a "#". They are not recursive, due to 
the symmetry of the tokens. They can be placed between, before and after any tokens.

Example:
java Interpreter "#comment#x#comment#=#comment##comment#5#comment#;"

Is valid.
