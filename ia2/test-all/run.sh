#! /bin/sh
#In all tests, arguments to java Interpreter are separated by the null character
cd ..
make clean
make
for test in test-all/test*
do
	xargs -a $test/prog --null sh -c "java \"\$@\" < $test/in" java Interpreter &> $test/out
	if diff -q -Z $test/out $test/exp
	then
		echo "$test passed"
	else
		echo "$test FAILED"
	fi
done
