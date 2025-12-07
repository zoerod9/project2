# before you commit
clean:
	rm *.class
	rm report.txt

# to run the project
run:
	clear
	javac *.java
	java -cp . Driver